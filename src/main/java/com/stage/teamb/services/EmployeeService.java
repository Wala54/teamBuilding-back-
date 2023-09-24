package com.stage.teamb.services;

import com.stage.teamb.models.Departement;
import com.stage.teamb.models.Employee;

import com.stage.teamb.models.dtos.EmployeeDTO;
import com.stage.teamb.repository.EmployeReprository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements GeneriqueService<EmployeeDTO> {

    private final EmployeReprository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartementService departementService;


    public EmployeeService(EmployeReprository employeeRepository, EmployeeMapper employeeMapper, DepartementService departementService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;

        this.departementService = departementService;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeListDto(employees);
    }
    @Override
    public Optional<EmployeeDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public EmployeeDTO saveOne(EmployeeDTO employeeDTO) {
        Optional<Departement> departement =departementService.findDepartement(employeeDTO.getDepartementId());
        if (departement.isPresent()) {
            Employee employee = employeeMapper.toEmployeeEntity(employeeDTO);
            employee.setDepartement(departement.get());
            Employee savedEmployee = employeeRepository.save(employee);
            return employeeMapper.toEmployeeDTO(savedEmployee);
        } else {
            throw new IllegalArgumentException("Department not found");
        }
    }


    @Override
    public void deleteOne(Long id)  {
      employeeRepository.findById(id).ifPresentOrElse( element -> employeeRepository.deleteById(id),() -> new Exception("Employee Not EXIST")  );
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployeeEntity(employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDTO(updatedEmployee);
    }

    public Optional<Employee> findEmployee(Long employeeId) {
        return  this.employeeRepository.findById(employeeId);
    }

    public List<Employee> findEmployees(List<Long> employeeIds) {
        return this.employeeRepository.findAllById(employeeIds);
    }

    public Employee save(Employee employee) {
       return  employeeRepository.save(employee);}
}
