package com.stage.teamb.services;

import com.stage.teamb.models.Rating;
import com.stage.teamb.models.dtos.RatingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDTO toRatingDTO(Rating rating);

    List<RatingDTO> toRatingListDTO(List<Rating> ratings);

    Rating toRatingEntity(RatingDTO ratingDTO);


}
