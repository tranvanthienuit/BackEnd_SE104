package com.example.backend_se104.Service;


import com.example.backend_se104.Entity.Model.Rating;
import com.example.backend_se104.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void delete() {
        ratingRepository.deleteRatingByRating();
    }
}
