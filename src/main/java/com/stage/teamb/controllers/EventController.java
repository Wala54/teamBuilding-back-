package com.stage.teamb.controllers;

import com.stage.teamb.models.Event;
import com.stage.teamb.models.dtos.EventDTO;
import com.stage.teamb.models.Published;
import com.stage.teamb.models.Responsable;
import com.stage.teamb.services.EventMapper;
import com.stage.teamb.services.EventService;
import com.stage.teamb.services.PublicationService;
import com.stage.teamb.services.ResponsableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private final PublicationService publicationService;
    private final EventMapper eventMapper;
    private final ResponsableService responsableService;

    public EventController(EventService eventService, PublicationService publicationService, EventMapper eventMapper, ResponsableService responsableService) {
        this.eventService = eventService;
        this.publicationService = publicationService;
        this.eventMapper = eventMapper;
        this.responsableService = responsableService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findOne(@PathVariable Long id) {
        Optional<EventDTO> event = eventService.findOne(id);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EventDTO>> findAll() {
        List<EventDTO> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/addPublishedToEvent")
    public ResponseEntity<String> addPublishedToEvent(@RequestBody EventDTO eventDTO) {
        Optional<Published> published = publicationService.findPublished(eventDTO.getPublishedId());
        if (published.isPresent()) {
            Event event = eventMapper.toEventEntity(eventDTO);
            event.setPublished(published.get());
            eventService.save(event);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addResponsableToEvent")
    public ResponseEntity<String> addResponsableToEvent(@RequestBody EventDTO eventDTO) {
        Optional<Responsable> responsable = responsableService.findResponsable(eventDTO.getResponsableID());
        if (responsable.isPresent()) {
            Event event = eventMapper.toEventEntity(eventDTO);
            event.setResponsable(responsable.get());
            eventService.saveOne(eventDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<EventDTO> update(@RequestBody EventDTO event) {
        EventDTO updatedEvent = eventService.saveOne(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        eventService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
