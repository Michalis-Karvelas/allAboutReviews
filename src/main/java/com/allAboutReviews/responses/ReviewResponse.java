package com.allAboutReviews.responses;

import com.allAboutReviews.models.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse extends Response{

    private Review review;
    private List<Review> reviews;
    private Long reviewId;

    public ReviewResponse(String msg,Review review){
        super(msg);
        this.review=review;
    }

    public ReviewResponse(String msg,List<Review> reviews){
        super(msg);
        this.reviews=reviews;
    }

    public ReviewResponse(String msg,Long reviewId){
        super(msg);
        this.reviewId=reviewId;
    }
}
