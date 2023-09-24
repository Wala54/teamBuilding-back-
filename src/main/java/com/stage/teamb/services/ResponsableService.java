package com.stage.teamb.services;

import com.stage.teamb.models.Responsable;
import com.stage.teamb.models.dtos.ResponsableDTO;
import com.stage.teamb.repository.ResponsableRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsableService implements GeneriqueService<ResponsableDTO> {
    private final ResponsableRepository responsableRepository;
    private final ResponsableMapper responsableMapper;

    public ResponsableService(ResponsableRepository responsableRepository , ResponsableMapper responsableMapper){
        this.responsableRepository = responsableRepository;
        this.responsableMapper = responsableMapper;
    }

    @Override
    public List<ResponsableDTO> findAll() {
        List<Responsable> responsable = responsableRepository.findAll();
        return responsableMapper.toResponsableListDTO(responsable);
    }


    @Override
    public Optional<ResponsableDTO> findOne(Long id) {
        Optional<Responsable> responsable = responsableRepository.findById(id);
        return responsable.map(responsableMapper::toResponsableDTO);
    }


    @Override
    public ResponsableDTO saveOne(ResponsableDTO responsableDTO) {
            Responsable responsable = responsableMapper.toResponsableEntity(responsableDTO);
            Responsable savedresponsable  = responsableRepository.save(responsable);
            return responsableMapper.toResponsableDTO(savedresponsable);
        }


    @Override
    public void deleteOne(Long id) {
        if (responsableRepository.existsById(id)) {
            responsableRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rating Not Exist");
        }
    }

    @Override
    public ResponsableDTO update(ResponsableDTO responsableDTO) {
        Responsable responsable = responsableMapper.toResponsableEntity(responsableDTO);
        Responsable updatedresponsable  = responsableRepository.save(responsable);
        return responsableMapper.toResponsableDTO(updatedresponsable);
    }


    public Optional<Responsable> findResponsable(Long responsableID) {
        return this.responsableRepository.findById(responsableID);
    }

    public Responsable save(Responsable responsable) {
        return responsableRepository.save(responsable);

    }
}


