package com.stage.teamb.services.mappers;

import com.stage.teamb.models.Adresse;
import com.stage.teamb.models.dtos.AdresseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface
AdresseMapper {

    AdresseDTO toAdresseDTO(Adresse adresse);

    List<AdresseDTO> toAdresseListDto(List<Adresse> adresse);

    Adresse toAdresseEntity(AdresseDTO adresseDTO);
}