package com.stage.teamb.controllers;

import com.stage.teamb.models.Departement;
import com.stage.teamb.models.Entreprise;
import com.stage.teamb.models.dtos.*;
import com.stage.teamb.services.DepartementService;
import com.stage.teamb.services.EntrepriseMapper;
import com.stage.teamb.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;
    private final DepartementService departementService;
    private final EntrepriseMapper entrepriseMapper;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService, DepartementService departementService, EntrepriseMapper entrepriseMapper) {
        this.entrepriseService = entrepriseService;
        this.departementService = departementService;
        this.entrepriseMapper = entrepriseMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrepriseDTO> findOne(@PathVariable Long id) {
        EntrepriseDTO entreprise = entrepriseService.findOne(id);
        if (entreprise != null) {
            return ResponseEntity.ok(entreprise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public List<EntrepriseDTO> findAll() {
        return entrepriseService.findAll();
    }

    @PostMapping
    public ResponseEntity<EntrepriseDTO> saveOne(@RequestBody EntrepriseDTO entreprise) {
        EntrepriseDTO savedEntreprise = entrepriseService.saveOne(entreprise);
        return ResponseEntity.ok(savedEntreprise);
    }

    @PostMapping("/addDepartementToEntreprise")
    public ResponseEntity<String> addDepartementToEntreprise(@RequestBody EntrepriseWithDepDTO entrepriseWithDepDto) {
        List<Departement> departement = departementService.findDepartements(entrepriseWithDepDto.getDepartementsIds());

        if (!departement.isEmpty()) {
            Entreprise entreprise = entrepriseMapper.toEntrepriseWithDepsEntity(entrepriseWithDepDto);
            entreprise.setDepartement(departement);
            entrepriseService.save(entreprise);
            return ResponseEntity.ok("Départements ajoutés à l'entreprise avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<EntrepriseDTO> update(@RequestBody EntrepriseDTO entreprise) {
        EntrepriseDTO updatedEntreprise = entrepriseService.update(entreprise);
        return ResponseEntity.ok(updatedEntreprise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOne(@PathVariable Long id) {
        try{
            entrepriseService.deleteOne(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getEntreprise/{id}")
    public ResponseEntity<EntrepriseWithDepsDTO> getEntreprise(@PathVariable Long id) {
        Entreprise entreprise = entrepriseService.findOrigineOne(id);
        EntrepriseWithDepsDTO entrepriseWithDepsDTO = new EntrepriseWithDepsDTO();
        entrepriseWithDepsDTO.setId(entreprise.getId());
        entrepriseWithDepsDTO.setNomEnreprise(entreprise.getNomEnreprise());
        entrepriseWithDepsDTO.setLocalEnreprise(entreprise.getLocalEnreprise());

        if (entreprise != null) {
            List<DepartementDTO> departementList = departementService.findDepByEnrepId(id);
            entrepriseWithDepsDTO.setDepartementDTOList(departementList);
            return ResponseEntity.ok(entrepriseWithDepsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
