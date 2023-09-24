package com.stage.teamb.services;

import com.stage.teamb.models.Entreprise;
import com.stage.teamb.models.dtos.EntrepriseDTO;
import com.stage.teamb.repository.EntrepriseRepsitory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EntrepriseService {

    private final EntrepriseRepsitory entrepriseRepository;
    private final EntrepriseMapper entrepriseMapper;

    @Autowired
    public EntrepriseService(
            EntrepriseRepsitory entrepriseRepository,
            EntrepriseMapper entrepriseMapper,
            DepartementService departementService
    ) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
    }

    public List<EntrepriseDTO> findAll() {
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        return entrepriseMapper.toEntrepriseDTOList(entreprises);
    }

    public EntrepriseDTO findOne(Long id) {
        return entrepriseRepository.findById(id)
                .map(entrepriseMapper::toEntrepriseDTO)
                .orElse(null);
    }

       public Entreprise findOrigineOne(Long id) {
        return entrepriseRepository.findById(id).get();
    }



    public EntrepriseDTO saveOne(EntrepriseDTO entrepriseDTO) {
        Entreprise entreprise = entrepriseMapper.toEntrepriseEntity(entrepriseDTO);
        Entreprise savedEntreprise = entrepriseRepository.save(entreprise);
        return entrepriseMapper.toEntrepriseDTO(savedEntreprise);
    }

    public void deleteOne(Long id) {
        Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entreprise with ID " + id + " not found"));
        entrepriseRepository.deleteById(entreprise.getId());
    }



    public EntrepriseDTO update(EntrepriseDTO entrepriseDTO) {
        Entreprise entreprise = entrepriseMapper.toEntrepriseEntity(entrepriseDTO);
        Entreprise updatedEntreprise = entrepriseRepository.save(entreprise);
        return entrepriseMapper.toEntrepriseDTO(updatedEntreprise);
    }
    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
}
