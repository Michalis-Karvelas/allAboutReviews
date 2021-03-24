package com.allAboutReviews.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    private String name;
    private String address;
    private String description;
    private Double overallRating;
    private Long statusId;
    private List<Long> categoriesIds;
    private List<Long> reviewsIds;//maybe it's not needed
//    private Set<Category> categories;
//    private Set<Review> placeReviews;
//    private Set<WorkingSchedule> workingScheduleSet;

    public PlaceRequest(String name, String address, String description, Long statusId, List<Long> categoriesIds) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.statusId = statusId;
        this.categoriesIds = categoriesIds;
    }

}
