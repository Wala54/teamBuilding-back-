package com.stage.teamb.controllers;
import com.stage.teamb.models.*;
import com.stage.teamb.models.dtos.RatingDTO;
import com.stage.teamb.services.EmployeeService;
import com.stage.teamb.services.PublicationService;
import com.stage.teamb.services.RatingMapper;
import com.stage.teamb.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
    private final RatingMapper ratingMapper ;
    private final EmployeeService employeeService;
    private final PublicationService publicationService;

    @Autowired
    public RatingController(RatingService ratingService, RatingMapper ratingMapper, EmployeeService employeeService, PublicationService publicationService) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
        this.employeeService = employeeService;
        this.publicationService = publicationService;
    }

    @GetMapping("/{id}")
    public Optional<RatingDTO> findOne(@PathVariable Long id) {
        return ratingService.findOne(id);
    }

    @GetMapping
    public List<RatingDTO> findAll() {
        return ratingService.findAll();
    }
    @PostMapping
    public RatingDTO saveOne(@RequestBody RatingDTO rating) {
        return ratingService.saveOne(rating);
    }
    @PostMapping("/addEmployeeToRating")
    public ResponseEntity<String> addEmployeeToRating(@RequestBody RatingDTO ratingDto) {
        Optional<Employee> employeeOptional = employeeService.findEmployee(ratingDto.getEmployeeId());
        if (employeeOptional.isPresent()) {
            Rating rating = ratingMapper.toRatingEntity(ratingDto);
            rating.setEmployee(employeeOptional.get());
            ratingService.save(rating);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addPublishedToRating")
    public ResponseEntity<String> addPublishedToRating(@RequestBody RatingDTO ratingDto) {
        List<Published> publisheds = publicationService.findPublisheds(ratingDto.getPublishedsId());

        if (!publisheds.isEmpty()) {
            Rating rating = ratingMapper.toRatingEntity(ratingDto);
            rating.setPublisheds((Published) publisheds);
            ratingService.save(rating);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update")
    public RatingDTO update(@RequestBody RatingDTO rating) {
        return ratingService.update(rating);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        ratingService.deleteOne(id);
    }
}

