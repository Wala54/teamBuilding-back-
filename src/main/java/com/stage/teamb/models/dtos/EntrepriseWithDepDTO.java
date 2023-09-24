package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EntrepriseWithDepDTO {
    private Long id;
    private String nomEnreprise;
    private String localEnreprise;
    private List<Long> departementsIds;
}
