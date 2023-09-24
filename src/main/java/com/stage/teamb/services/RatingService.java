package com.stage.teamb.services;

import com.stage.teamb.models.Rating;
import com.stage.teamb.models.dtos.RatingDTO;
import com.stage.teamb.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements GeneriqueService<RatingDTO> {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper ;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public List<RatingDTO> findAll() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratingMapper.toRatingListDTO(ratings);
    }

    @Override
    public Optional<RatingDTO> findOne(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.map(ratingMapper::toRatingDTO);
    }

    @Override
    public RatingDTO saveOne(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toRatingEntity(ratingDTO);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toRatingDTO(savedRating);
    }

    @Override
    public void deleteOne(Long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rating Not Exist");
        }
    }

    @Override
    public RatingDTO update(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toRatingEntity(ratingDTO);
        Rating updatedRating = ratingRepository.save(rating);
        return ratingMapper.toRatingDTO(updatedRating);
    }

    public void save(Rating rating) {
        this.ratingRepository.save(rating);
    }
    public Optional<Rating> findRating(Long ratingId) {
        return this.ratingRepository.findById(ratingId);
    }

    public List<Rating> findRatings(List<Long> ratingIds) {
        return this.ratingRepository.findAllById(ratingIds);
    }


}
