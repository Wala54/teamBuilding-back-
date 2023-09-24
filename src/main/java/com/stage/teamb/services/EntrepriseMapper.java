package com.stage.teamb.services;

import com.stage.teamb.models.Entreprise;
import com.stage.teamb.models.dtos.EntrepriseDTO;
import com.stage.teamb.models.dtos.EntrepriseWithDepDTO;
import com.stage.teamb.models.dtos.EntrepriseWithDepsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {

    EntrepriseDTO toEntrepriseDTO(Entreprise entreprise);

    List<EntrepriseDTO> toEntrepriseDTOList(List<Entreprise> entreprises);
    
    Entreprise toEntrepriseWithDepsEntity(EntrepriseWithDepsDTO entrepriseWithDepsDTO);

    Entreprise toEntrepriseWithDepsEntity(EntrepriseWithDepDTO entrepriseWithDepsDTO);

    EntrepriseWithDepsDTO toEntrepriseWithDepsDTO(Entreprise entreprise);

    List<EntrepriseDTO> toEntrepriseListDto(List<Entreprise> entreprises);

    Entreprise toEntrepriseEntity(EntrepriseDTO entrepriseDTO);
}