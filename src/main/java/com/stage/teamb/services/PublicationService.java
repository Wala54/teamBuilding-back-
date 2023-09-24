package com.stage.teamb.services;

import com.stage.teamb.models.Published;
import com.stage.teamb.models.dtos.PublishedDTO;
import com.stage.teamb.repository.PublicationRepository;
import com.stage.teamb.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService implements GeneriqueService<PublishedDTO> {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, PublicationMapper publicationMapper, RatingRepository ratingRepository, RatingRepository ratingRepository1)
    {
        this.publicationRepository = publicationRepository;
        this.publicationMapper = publicationMapper;
    }

    @Override
    public List<PublishedDTO> findAll() {
        List<Published> published = publicationRepository.findAll();
        return publicationMapper.toListPublishedDTO(published);
    }

    @Override
    public Optional<PublishedDTO> findOne(Long id) {
        Optional<Published> published = publicationRepository.findById(id);
        return published.map(publicationMapper::toPublishedDTO);
    }

    @Override
    public PublishedDTO saveOne(PublishedDTO publishedDTO) {
        Published published = publicationMapper.toPublishedEntity(publishedDTO);
        Published savedPublished = publicationRepository.save(published);
        return publicationMapper.toPublishedDTO(savedPublished);
    }

    @Override
    public void deleteOne(Long id) {
        if (publicationRepository.existsById(id)) {
            publicationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Publication Not Exist");
        }
    }

    @Override
    public PublishedDTO update(PublishedDTO publishedDTO) {
        Published published = publicationMapper.toPublishedEntity(publishedDTO);
        Published updatedPublished = publicationRepository.save(published);
        return publicationMapper.toPublishedDTO(updatedPublished);
    }

    public Optional<Published> findPublished(Long publishedId) {
        return this.publicationRepository.findById(publishedId);
    }

    public List<Published> findPublisheds(List<Long> publishedIds) {
        return this.publicationRepository.findAllById(publishedIds);
    }

    public Published save(Published published) {
        return publicationRepository.save(published);

    }
}
