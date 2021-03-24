package com.allAboutReviews.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

    private String reviewDescription;//maybe it's not needed
    private double rating;//maybe it's not needed
    private Long placeId;
    private Long accountId;
}
