package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EntrepriseWithDepsDTO {
    private Long id;
    private String nomEnreprise;
    private String localEnreprise;
   private List<DepartementDTO> departementDTOList;


}
