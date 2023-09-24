package com.stage.teamb.controllers;

import com.stage.teamb.models.Adresse;
import com.stage.teamb.models.dtos.AdresseDTO;
import com.stage.teamb.models.Employee;
import com.stage.teamb.services.mappers.AdresseMapper;
import com.stage.teamb.services.AdresseService;
import com.stage.teamb.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adresse")
public class AdresseController {
    private final AdresseService adresseService;
    private final EmployeeService employeeService;
    private final AdresseMapper adresseMapper;
    @Autowired
    public AdresseController(AdresseService adresseService, EmployeeService employeeService, AdresseMapper adresseMapper) {
        this.adresseService = adresseService;
        this.employeeService = employeeService;
        this.adresseMapper = adresseMapper;
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<Optional<Employee>> getEmployeesForAdresse(@PathVariable Long id) {
        Optional<AdresseDTO> adresseOptional = adresseService.findOne(id);
        if (adresseOptional.isPresent()) {
            AdresseDTO adresseDTO = adresseOptional.get();
            Optional<Employee> employees = employeeService.findEmployee(adresseDTO.getEmployeeId());
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public Optional<List<AdresseDTO>> findAll() {
        return Optional.ofNullable(adresseService.findAll());
    }

    @PostMapping("addEmployeeToAdresse")
    public ResponseEntity<String> addEmployeeToAdresse(@RequestBody AdresseDTO adresseDTO) {
        Optional<Employee> employeeOptional = employeeService.findEmployee(adresseDTO.getEmployeeId());
        if (employeeOptional.isPresent()) {
            Adresse adresse = adresseMapper.toAdresseEntity(adresseDTO);
            adresse.setEmployee(employeeOptional.get());
            adresseService.save(adresse);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public AdresseDTO update(@RequestBody AdresseDTO adresse) {
        return adresseService.update(adresse);
    }
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        adresseService.deleteOne(id);
    }
}
