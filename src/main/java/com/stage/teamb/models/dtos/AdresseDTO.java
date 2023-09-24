package com.stage.teamb.models.dtos;

import lombok.Data;

@Data
public class AdresseDTO {
    private Long id;
    private String rue;
    private String ville;
    private Long employeeId;
}
