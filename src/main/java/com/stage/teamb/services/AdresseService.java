package com.stage.teamb.services;

import com.stage.teamb.models.Adresse;
import com.stage.teamb.models.dtos.AdresseDTO;
import com.stage.teamb.repository.AdresseRepository;
import com.stage.teamb.services.mappers.AdresseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdresseService implements GeneriqueService<AdresseDTO> {
    private final AdresseRepository adresseRepository;
    private final AdresseMapper adresseMapper;

    @Autowired
    public AdresseService(AdresseRepository adresseRepository, AdresseMapper adresseMapper) {
        this.adresseRepository = adresseRepository;
        this.adresseMapper = adresseMapper;
    }


    @Override
    public List<AdresseDTO> findAll() {
        List<Adresse> adresses = adresseRepository.findAll();
        return adresseMapper.toAdresseListDto(adresses);
    }

    @Override
    public Optional<AdresseDTO> findOne(Long id) {
        Optional<Adresse> adresse = adresseRepository.findById(id);
        return Optional.of(adresseMapper.toAdresseDTO(adresse.get()));
    }

    @Override
    public AdresseDTO saveOne(AdresseDTO adresseDTO) {
        Adresse adresse = adresseMapper.toAdresseEntity(adresseDTO);
        Adresse savedAdresse = adresseRepository.save(adresse);
        return adresseMapper.toAdresseDTO(savedAdresse);
    }

    @Override
    public void deleteOne(Long id) {
        if (adresseRepository.existsById(id)) {
            adresseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Adresse Not Exist");
        }
    }

    @Override
    public AdresseDTO update(AdresseDTO adresseDTO) {
        Adresse adresse = adresseMapper.toAdresseEntity(adresseDTO);
        Adresse updatedAdresse = adresseRepository.save(adresse);
        return adresseMapper.toAdresseDTO(updatedAdresse);
    }

    public List<Adresse> findAdresse(List<Long> adresseIds) {
    return  this.adresseRepository.findAllById(adresseIds);
}

    public Adresse save(Adresse adresse) {
        return adresseRepository.save(adresse);
    }
}
