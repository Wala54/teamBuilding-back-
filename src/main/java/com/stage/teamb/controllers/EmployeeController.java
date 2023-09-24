package com.stage.teamb.controllers;

import com.stage.teamb.models.Adresse;
import com.stage.teamb.models.Employee;
import com.stage.teamb.models.Published;
import com.stage.teamb.models.Rating;
import com.stage.teamb.models.dtos.EmployeeDTO;
import com.stage.teamb.models.dtos.EmployeeWithAdresseDTO;
import com.stage.teamb.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PublicationService publicationService;
    private final RatingService ratingService;
    private final AdresseService adresseService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, PublicationService publicationService,
                              RatingService ratingService, AdresseService adresseService,
                              EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.publicationService = publicationService;
        this.ratingService = ratingService;
        this.adresseService = adresseService;
        this.employeeMapper = employeeMapper;
    }

    public ResponseEntity<EmployeeDTO> findOne(@PathVariable Long id) {
        Optional<EmployeeDTO> employee = employeeService.findOne(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }
    @PostMapping
    public EmployeeDTO saveOne(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveOne(employeeDTO);
    }


    @PostMapping("/addPublished")
    public ResponseEntity<String> addPublishedToEmployee(@io.swagger.v3.oas.annotations.parameters.RequestBody EmployeeWithAdresseDTO employeeWithAdresseDTO) {
        List<Published> publisheds = publicationService.findPublisheds(employeeWithAdresseDTO.getPublicationIds());

        if (!publisheds.isEmpty()) {
            Employee employee = employeeMapper.toEmployeeEntity(employeeWithAdresseDTO);
            employee.setPublications(publisheds);
            employeeService.save(employee);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addRating")
    public ResponseEntity<String> addRatingToEmployee(@io.swagger.v3.oas.annotations.parameters.RequestBody EmployeeWithAdresseDTO employeeWithAdresseDTO) {
        List<Rating> ratings = ratingService.findRatings(employeeWithAdresseDTO.getRatingIds());

        if (!ratings.isEmpty()) {
            Employee employee = employeeMapper.toEmployeeEntity(employeeWithAdresseDTO);

            employee.setRatings(ratings);
            employeeService.save(employee);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addAdresse")
    public ResponseEntity<String> addAdresseToEmployee(@io.swagger.v3.oas.annotations.parameters.RequestBody EmployeeWithAdresseDTO employeeWithAdresseDTO) {
        List<Adresse> adresses = adresseService.findAdresse(employeeWithAdresseDTO.getAdresseIds());

        if (!adresses.isEmpty()) {
            Employee employee = employeeMapper.toEmployeeEntity(employeeWithAdresseDTO);
            employee.setAdresses(adresses);
            employeeService.save(employee);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> update(@io.swagger.v3.oas.annotations.parameters.RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.saveOne(employeeDTO);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        employeeService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
