package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;
@Data
public class EmployeeWithAdresseDTO{
        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private List<Long> adresseIds;
        private List<Long> ratingIds;
        private List<Long> publicationIds;
}
