package com.allAboutReviews.controllers;

import com.allAboutReviews.models.AccountStatus;
import com.allAboutReviews.models.Place;
import com.allAboutReviews.requests.PlaceRequest;
import com.allAboutReviews.responses.PlaceResponse;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.services.PlaceServiceImpl;
import com.allAboutReviews.services.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value = "/api/place")
@RestController
public class PlaceController {

    @Autowired
    private PlaceServiceImpl placeServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    //list of all the places
    @GetMapping(value = "/getall")
    public PlaceResponse getAll(){
        log.info("Ready to find all the places");
        return new PlaceResponse("Found all the places", placeServiceImpl.getAll());
    }

    //find a place by it's id
    @GetMapping(value = "/getbyid/{placeId}")
    public PlaceResponse getById(@PathVariable(value = "placeId") Long placeId){
        log.info("Ready to find the place with id: {}",placeId);
        return new PlaceResponse("Found the place",placeServiceImpl.getById(placeId));
    }

    //create a new place
    @PostMapping(value = "/new")
    public Response createNewPlace(@RequestBody PlaceRequest request){
        log.info("Ready to create a place");
        placeServiceImpl.newPlace(request);
        log.info("The place has been saved");
        return new Response("The place has been inserted in the DB");
    }

    //update an existing place
    @PutMapping(value = "/update/{placeId}")
    public Response updateExistingPlace(@PathVariable(value = "placeId") Long placeId,@RequestBody PlaceRequest request){
        log.info("Ready to update the place with id: {}", placeId);
        Place place = placeServiceImpl.updatePlace(placeId,request);
        if(isNull(place)){
            return new Response("The place does not exist. It can't be updated");
        }
        return new Response("Place updated successfully");
    }

    //delete a place
    @DeleteMapping(value = "delete/{placeId}")
    public Response deletePlace(@PathVariable(value = "placeId") Long placeId){
        log.info("Ready to delete the place with id:{} ",placeId);
        if(placeServiceImpl.deletePlace(placeId)== false){
            return new Response("The place couldn't be deleted");
        }
        return new Response("Place deleted successfully");
    }
}
