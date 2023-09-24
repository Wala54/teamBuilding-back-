package com.stage.teamb.controllers;

import com.stage.teamb.models.Departement;
import com.stage.teamb.models.Employee;
import com.stage.teamb.models.dtos.DepartementDTO;
import com.stage.teamb.models.dtos.DepartementWithEmployeesDTO;
import com.stage.teamb.services.DepartementMapper;
import com.stage.teamb.services.DepartementService;
import com.stage.teamb.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/departement")
@RestController
public class DepartementController {
    private final DepartementService departementService;
private final EmployeeService employeeService;
private final DepartementMapper departementMapper;
    @Autowired
    public DepartementController(DepartementService departementService, EmployeeService employeeService, DepartementMapper departementMapper) {
        this.departementService = departementService;
        this.employeeService = employeeService;

        this.departementMapper = departementMapper;
    }
    @PostMapping("/addOne")
    public DepartementDTO addOne(@io.swagger.v3.oas.annotations.parameters.RequestBody DepartementDTO departementDTO) {
        return (departementService.saveOne(departementDTO));
    }

    @GetMapping("/{id}")
    public Optional<DepartementDTO> findOne(@PathVariable Long id) {
        return (departementService.findOne(id));
    }
    @GetMapping()
    public Optional<List<DepartementDTO>> findAll() {
        return Optional.ofNullable(departementService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> addEmployeeToDepartement(@io.swagger.v3.oas.annotations.parameters.RequestBody DepartementWithEmployeesDTO departementWithEmployeesDTO) {
            List<Employee> employeeOptional = employeeService.findEmployees(departementWithEmployeesDTO.getEmployeesIds());
            if (!employeeOptional.isEmpty()) {
                Departement departement =departementMapper.toDepartementWithEmployeesEntity(departementWithEmployeesDTO);
                departement.setEmployees(employeeOptional);
                departementService.save(departement);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    @PutMapping
    public DepartementDTO update(@org.springframework.web.bind.annotation.RequestBody DepartementDTO departement) {
        return departementService.saveOne(departement);
    }
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        departementService.deleteOne(id);
    }
    }
