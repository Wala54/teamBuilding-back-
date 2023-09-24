package com.stage.teamb.services;


import com.stage.teamb.models.Departement;
import com.stage.teamb.models.dtos.DepartementDTO;
import com.stage.teamb.models.dtos.DepartementWithEmployeesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  DepartementMapper {

    DepartementDTO toDepartementDTO(Departement departement);

    List<DepartementDTO> toDepartementListDto(List<Departement> departements);

    Departement toDepartementEntity(DepartementDTO departementDTO);
    Departement toDepartementWithEmployeesEntity(DepartementWithEmployeesDTO departementWithEmployeesDTO);
}
