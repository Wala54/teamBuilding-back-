package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;

@Data
    public class DepartementWithEmployeesDTO {
        private Long id;
        private String nomDep;
        private List<Long> employeesIds;

}
