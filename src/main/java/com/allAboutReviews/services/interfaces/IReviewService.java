package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Review;
import com.allAboutReviews.requests.ReviewRequest;

import java.util.List;

public interface IReviewService {

    //a list with all the reviews
    List<Review> getAll(); //not needed

    //a list of all the reviews the account with the given id has made
    List<Review> getAllByAccountId(Long accountId);

    //a list of all the reviews for a place with the given id
    List<Review> getAllByPlaceId(Long placeId);

    //get a review by a given id
    Review getById(Long reviewId);

    //create a review
    boolean newReview(ReviewRequest review);

    //update the review with the given id
    Review updateReview(Long reviewId,ReviewRequest review);

    //delete the review with the given id
    boolean deleteReview(Long reviewId);
}
