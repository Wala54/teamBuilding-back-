package com.stage.teamb.services;

import com.stage.teamb.models.Employee;
import com.stage.teamb.models.dtos.EmployeeDTO;
import com.stage.teamb.models.dtos.EmployeeWithAdresseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee employee);

    List<EmployeeDTO> toEmployeeListDto(List<Employee> employees);

    Employee toEmployeeEntity(EmployeeDTO employeeDTO);
    Employee toEmployeeEntity (EmployeeWithAdresseDTO employeeWithAdresseDTO);

}
