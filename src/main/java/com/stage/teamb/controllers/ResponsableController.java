package com.stage.teamb.controllers;

import com.stage.teamb.models.*;
import com.stage.teamb.models.dtos.ResponsableDTO;
import com.stage.teamb.services.EventService;
import com.stage.teamb.services.ResponsableMapper;
import com.stage.teamb.services.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsable")
public class ResponsableController {
    private final ResponsableService responsableService;
    private final EventService eventService;
    private final ResponsableMapper responsableMapper;

    @Autowired
    public ResponsableController(ResponsableService responsableService,
                                 ResponsableMapper responsableMapper,
                                 EventService eventService, ResponsableMapper responsableMapper1) {
        this.responsableService = responsableService;
        this.eventService = eventService;
        this.responsableMapper = responsableMapper1;
    }

    @GetMapping("/{id}")
        public Optional<ResponsableDTO> findOne(@PathVariable Long id) {
            return (responsableService.findOne(id));
        }
        @GetMapping()
        public Optional<List<ResponsableDTO>> findAll() {
            return Optional.ofNullable(responsableService.findAll());
        }


    @PostMapping("/addEvent")
    public ResponseEntity<String> addEventToResponsable(@RequestBody ResponsableDTO responsableDTO) {
        List<Event> events = eventService.findEvents(responsableDTO.getEventsId());
        if (!events.isEmpty()) {
            Responsable responsable = responsableMapper.toResponsableEntity(responsableDTO);
            responsable.setEvents(events);
            responsableService.save(responsable);
            return ResponseEntity.ok("Event added to publication.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping( "/update")
        public ResponsableDTO update(@RequestBody ResponsableDTO responsable) {
            return responsableService.saveOne(responsable);
        }

        @DeleteMapping("/{id}")
        public void deleteOne(@PathVariable Long id) {
            responsableService.deleteOne(id);
        }
    }

