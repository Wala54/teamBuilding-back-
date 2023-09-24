package com.stage.teamb.models.dtos;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Long departementId;

}


