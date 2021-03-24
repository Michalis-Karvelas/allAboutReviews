package com.allAboutReviews.requests;

import com.allAboutReviews.models.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private String typeOfCategory;
//    private Set<Place> placeSet;
}
