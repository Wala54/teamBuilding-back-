package com.stage.teamb.services;

import com.stage.teamb.models.Departement;
import com.stage.teamb.models.Entreprise;
import com.stage.teamb.models.dtos.DepartementDTO;
import com.stage.teamb.repository.DepartementRepository;
import com.stage.teamb.repository.EntrepriseRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService implements GeneriqueService<DepartementDTO> {

    private final DepartementRepository departementRepository;
    private final EntrepriseRepsitory entrepriseRepsitory;
    private final DepartementMapper departementMapper;

    @Autowired
    public DepartementService(DepartementRepository departementRepository, EntrepriseRepsitory entrepriseRepsitory, DepartementMapper departementMapper) {
        this.departementRepository = departementRepository;
        this.entrepriseRepsitory = entrepriseRepsitory;
        this.departementMapper = departementMapper;
    }

    @Override
    public List<DepartementDTO> findAll() {
        List<Departement> departements = departementRepository.findAll();
        return departementMapper.toDepartementListDto(departements);
    }
    @Override
    public Optional<DepartementDTO> findOne(Long id) {
        Optional<Departement> departement = departementRepository.findById(id);
        return Optional.of(departementMapper.toDepartementDTO(departement.get()));
    }

    @Override
    public DepartementDTO saveOne(DepartementDTO departementDTO) {
       Departement departement=departementMapper.toDepartementEntity(departementDTO);
        Optional<Entreprise> e = entrepriseRepsitory.findById(departementDTO.getEntrepriseId());
        if(e.isEmpty())
        {
            throw new RuntimeException("pas d'entreprise avec cette id");
        }
        departement.setEntreprise(e.get());
       Departement savedDepartement =departementRepository.save(departement);
       return departementMapper.toDepartementDTO(savedDepartement);
    }
    @Override
    public void deleteOne(Long id) {
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Adresse Not Exist");
        }
    }
    @Override
    public DepartementDTO update(DepartementDTO departementDTO) {
        Departement departement= departementMapper.toDepartementEntity(departementDTO);
        Departement updatedDepartement = departementRepository.save(departement);
        return departementMapper.toDepartementDTO(updatedDepartement);
    }


    public List<Departement> findDepartements(List<Long> departementsIds) {
        return  this.departementRepository.findAllById(departementsIds);
    }
    public Optional<Departement> findDepartement(Long departementId) {
        return  this.departementRepository.findById(departementId);
    }

    public Departement save(Departement departement) {
    return  departementRepository.save(departement);}


    public List<DepartementDTO> findDepByEnrepId(Long id) {
     List<Departement>  departementList=departementRepository.findAllByEntreprise_Id(id);
         return departementMapper.toDepartementListDto(departementList);

    }
}

