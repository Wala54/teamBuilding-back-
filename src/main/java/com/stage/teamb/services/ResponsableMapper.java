package com.stage.teamb.services;

import com.stage.teamb.models.Responsable;
import com.stage.teamb.models.dtos.ResponsableDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponsableMapper {
    ResponsableDTO toResponsableDTO(Responsable responsable);
    List<ResponsableDTO> toResponsableListDTO (List<Responsable> responsable);
    Responsable toResponsableEntity(ResponsableDTO responsableDTO);


}