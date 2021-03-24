package com.allAboutReviews.repository;

import com.allAboutReviews.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findReviewsByAccount_AccountId(Long accountId);

    List<Review> findReviewsByPlace_PlaceId(Long placeId);
}
