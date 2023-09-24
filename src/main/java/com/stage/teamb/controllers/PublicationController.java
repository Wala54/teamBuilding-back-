package com.stage.teamb.controllers;

import com.stage.teamb.models.*;
import com.stage.teamb.models.dtos.PublishedDTO;
import com.stage.teamb.services.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationMapper publicationMapper;
    private final PublicationService publicationService;
    private final RatingService ratingService;
    private final EmployeeService employeeService;
    private final EventService eventService;

    @Autowired
    public PublicationController(PublicationMapper publicationMapper, PublicationService publicationService, RatingService ratingService, EmployeeService employeeService, EventService eventService) {
        this.publicationMapper = publicationMapper;
        this.publicationService = publicationService;
        this.ratingService = ratingService;
        this.employeeService = employeeService;
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublishedDTO> findOne(@PathVariable Long id) {
        Optional<PublishedDTO> publication = publicationService.findOne(id);
        return publication.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PublishedDTO>> findAll() {
        List<PublishedDTO> publications = publicationService.findAll();
        return ResponseEntity.ok(publications);
    }

    @PostMapping
    public ResponseEntity<PublishedDTO> saveOne(@io.swagger.v3.oas.annotations.parameters.RequestBody PublishedDTO publishedDTO) {
        PublishedDTO savedPublication = publicationService.saveOne(publishedDTO);
        return ResponseEntity.ok(savedPublication);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployeeToPublication(@io.swagger.v3.oas.annotations.parameters.RequestBody PublishedDTO publishedDTO) {
        Optional<Employee> employeeOptional = employeeService.findEmployee(publishedDTO.getEmployeeId());
        if (employeeOptional.isPresent()) {
            Published published = publicationMapper.toPublishedEntity(publishedDTO);
            published.setEmployee(employeeOptional.get());
            publicationService.save(published);
            return ResponseEntity.ok("Employee added to publication.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addRating")
    public ResponseEntity<String> addRatingToPublication(@io.swagger.v3.oas.annotations.parameters.RequestBody PublishedDTO publishedDTO) {
        List<Rating> ratingOptional = ratingService.findRatings(publishedDTO.getRatingIds());
        if (ratingOptional.isEmpty()) {
            Published published = publicationMapper.toPublishedEntity(publishedDTO);
            published.setRating(ratingOptional);
            publicationService.save(published);
            return ResponseEntity.ok("Rating added to publication.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEventToPublication(@io.swagger.v3.oas.annotations.parameters.RequestBody PublishedDTO publishedDTO) {
        List<Event> events = eventService.findEvents(publishedDTO.getEventIds());
        if (!events.isEmpty()) {
            Published published = publicationMapper.toPublishedEntity(publishedDTO);
            published.setEvents(events);
            publicationService.save(published);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping
    public ResponseEntity<PublishedDTO> update(@RequestBody PublishedDTO publishedDTO) {
        PublishedDTO updatedPublication = publicationService.saveOne(publishedDTO);
        return ResponseEntity.ok(updatedPublication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        publicationService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
