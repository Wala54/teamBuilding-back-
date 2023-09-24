package com.stage.teamb.services;

import com.stage.teamb.models.Event;
import com.stage.teamb.models.dtos.EventDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toEventDTO(Event event);
    List<EventDTO> toEventListDTO(List<Event> events);
    Event toEventEntity (EventDTO eventDTO);

}
