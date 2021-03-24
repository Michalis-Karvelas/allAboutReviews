package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Place;
import com.allAboutReviews.requests.PlaceRequest;

import java.util.List;

public interface IPlaceService {

    //list with all the places
    List<Place> getAll();

    //get the place with the given id
    Place getById(Long placeId);

    //create a new place
    boolean newPlace(PlaceRequest place);

    //update an existing place
    Place updatePlace(Long placeId,PlaceRequest place);

    //delete place by the given id
    boolean deletePlace(Long placeId);
}
