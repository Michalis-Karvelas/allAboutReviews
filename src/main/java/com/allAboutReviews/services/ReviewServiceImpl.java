package com.allAboutReviews.services;

import com.allAboutReviews.models.Review;
import com.allAboutReviews.repository.AccountRepository;
import com.allAboutReviews.repository.PlaceRepository;
import com.allAboutReviews.repository.ReviewRepository;
import com.allAboutReviews.requests.ReviewRequest;
import com.allAboutReviews.services.interfaces.IReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AccountRepository accountRepository;

    //get all the reviews not needed
    @Override
    public List<Review> getAll() {
        log.info("Ready to find all the reviews");
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getAllByAccountId(Long accountId) {
        log.info("Ready to find all the reviews for the account with id: {}", accountId);
        return reviewRepository.findReviewsByAccount_AccountId(accountId);
    }

    @Override
    public List<Review> getAllByPlaceId(Long placeId) {
        log.info("Ready to find the reviews for the place with id: {}",placeId);
        return reviewRepository.findReviewsByPlace_PlaceId(placeId);
    }

    //get all by place id and good rating

    //get alla by place id and bad rating

    @Override
    public Review getById(Long reviewId) {
        log.info("Ready to find the review with id: {}", reviewId);
        return reviewRepository.findById(reviewId).orElse(null);
    }

    //needs to update the rating in place
    @Override
    public boolean newReview(ReviewRequest request) {
        log.info("Ready to create a review");
        Review tempReview=new Review(request.getReviewDescription(), request.getRating(),
                placeRepository.findById(request.getPlaceId()).orElse(null),
                accountRepository.findById(request.getAccountId()).orElse(null));
        Review newReview=reviewRepository.save(tempReview);
        log.info("The new review is: {}",newReview);
        log.info("The new review has been inserted into the DB");
        return true;
    }

    //needs to update the rating in place
    @Override
    public Review updateReview(Long reviewId, ReviewRequest request) {
        log.info("Ready to update the review with id: {}",reviewId);
        Review existingReview=reviewRepository.findById(reviewId).orElse(null);
        if (isNull(existingReview)){
            log.info("Couldn't find the review");
            return null;
        }
        existingReview.setRating(request.getRating());
        existingReview.setReviewDescription(request.getReviewDescription());
        Review updatedReview=reviewRepository.save(existingReview);
        log.info("The updated review is: {}", updatedReview);
        log.info("The updated review has been inserted into the DB");
        return updatedReview;
    }

    //needs to update the rating in place
    @Override
    public boolean deleteReview(Long reviewId) {
        log.info("Ready to delete the review with id: {}",reviewId);
        if (reviewRepository.existsById(reviewId)){
            reviewRepository.deleteById(reviewId);
            log.info("The review has been successfully deleted");
            return true;
        }
        log.info("The review couldn't be deleted");
        return false;
    }
}
