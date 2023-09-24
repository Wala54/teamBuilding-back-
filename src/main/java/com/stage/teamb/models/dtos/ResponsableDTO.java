package com.stage.teamb.models.dtos;
import lombok.Data;
import java.util.List;
@Data
public class ResponsableDTO {
    private Long id;
    private int matricule;
    private String nom;
    private String prenom;
    private List<Long> eventsId;
}

