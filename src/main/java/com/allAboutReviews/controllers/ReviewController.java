package com.allAboutReviews.controllers;

import com.allAboutReviews.models.Review;
import com.allAboutReviews.repository.PlaceRepository;
import com.allAboutReviews.repository.ReviewRepository;
import com.allAboutReviews.requests.ReviewRequest;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.responses.ReviewResponse;
import com.allAboutReviews.services.ReviewServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value = "/api/review")
@RestController
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    private PlaceRepository placeRepository;

    //list with all the reviews-not needed
    @GetMapping(value = "/getall")
    public ReviewResponse getAll(){
        log.info("Ready to find all of the reviews");
        return new ReviewResponse("Found all of the reviews", reviewServiceImpl.getAll());
    }

    //find a review by its id
    @GetMapping(value = "/getbyid/{reviewId}")
    public ReviewResponse findById(@PathVariable(value = "reviewId") Long reviewId){
        log.info("Ready to find the review with id: {}",reviewId);
        String msg="Found the review";
        if (isNull(reviewServiceImpl.getById(reviewId))){
        msg="The review with the given id doesn't exist";
        }
    return new ReviewResponse(msg,reviewServiceImpl.getById(reviewId));
    }

    //get all the reviews by an account id
    @GetMapping(value = "/getbyaccountid/{accountId}")
    public ReviewResponse findByAccountId(@PathVariable(value = "accountId") Long accountId){
        log.info("Ready to find all the reviews of the account with id: {}",accountId);
        return new ReviewResponse("Found all the reviews of the account", reviewServiceImpl.getAllByAccountId(accountId));
    }

    //get all the reviews by place id
    @GetMapping(value = "/getbyplaceid/{placeId}")
    public ReviewResponse findByPlaceId(@PathVariable(value = "placeId") Long placeId){
        log.info("Ready to find all the reviews for the place with id: {}",placeId);
        return new ReviewResponse("Found all the reviews for the place", reviewServiceImpl.getAllByPlaceId(placeId));
    }

    //create a new review
    @PostMapping(value = "/new")
    public Response createNewReview(@RequestBody ReviewRequest request){
        log.info("Ready to create a new review");
        reviewServiceImpl.newReview(request);
        log.info("The review has been saved");
        return new Response("The review has been inserted into the DB");
    }

    //update an existing review
    @PutMapping(value = "/update/{reviewId}")
    public Response updateReview(@PathVariable(value = "reviewId") Long reviewId,
                                         @RequestBody ReviewRequest request){
        log.info("Ready to update the review with id: {}",request);
        Review review=reviewServiceImpl.updateReview(reviewId,request);
        if(isNull(review)){
            return new Response("The review does not exist. It can't be updated");
        }
        return new Response("The review status has been updated successfully");
    }

    //delete an existing review
    @DeleteMapping(value = "/delete/{reviewId}")
    public Response deleteReview(@PathVariable(value = "reviewId") Long reviewId){
        log.info("Ready to delete the review with id: {}", reviewId);
        if(reviewServiceImpl.deleteReview(reviewId)== false){
            return new Response("The review couldn't be deleted");
        }
        return new Response("Review deleted successfully");
    }

}
