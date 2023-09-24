package com.stage.teamb.services;

import com.stage.teamb.models.Published;
import com.stage.teamb.models.dtos.PublishedDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    PublishedDTO toPublishedDTO(Published published);
    List<PublishedDTO> toListPublishedDTO (List<Published> published);
    Published toPublishedEntity (PublishedDTO publishedDTO);
}
