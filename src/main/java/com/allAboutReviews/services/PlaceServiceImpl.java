package com.allAboutReviews.services;

import com.allAboutReviews.models.Category;
import com.allAboutReviews.models.Place;
import com.allAboutReviews.models.Status;
import com.allAboutReviews.repository.CategoryRepository;
import com.allAboutReviews.repository.PlaceRepository;
import com.allAboutReviews.repository.StatusRepository;
import com.allAboutReviews.requests.PlaceRequest;
import com.allAboutReviews.services.interfaces.IPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PlaceServiceImpl implements IPlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Place> getAll() {
        log.info("Ready to find all the places");
        return placeRepository.findAll();
    }

    @Override
    public Place getById(Long placeId) {
        log.info("Ready to find the place with id : {}", placeId);
        return placeRepository.findById(placeId).orElse(null);
    }

    //get place by category id

    //get place by rating

    //get place by category and rating
    @Override
    public boolean newPlace(PlaceRequest request) {
        log.info("Ready to create a new place");
        Status openStatus=statusRepository.findById(1L).orElse(null);
        //find the Categories and them to a list
        List<Long> categoriesIds = request.getCategoriesIds();
        Set<Category> categorySet = new HashSet<>();
        for (Long current : categoriesIds) {
            Category category = categoryRepository.findById(current).orElse(null);
            log.info("The category is {}", category);
            categorySet.add(category);
        }
        Place tempPlace=new Place(request.getName(),request.getAddress(), request.getDescription(),openStatus,categorySet);
        Place newPlace=placeRepository.save(tempPlace);
        log.info("The new Place is : {} ",newPlace);
        log.info("The place has been inserted into the DB");
        return  true;
    }

    @Override
    public Place updatePlace(Long placeId, PlaceRequest request) {
        log.info("Ready to update the place with id: {}", placeId);
        Place existingPlace=placeRepository.findById(placeId).orElse(null);
        if(isNull(existingPlace)){
        return null;
        }
        existingPlace.setAddress(request.getAddress());
        existingPlace.setDescription(request.getDescription());
        existingPlace.setName(request.getName());
        existingPlace.setOverallRating(request.getOverallRating());
        existingPlace.setStatus(statusRepository.findById(request.getStatusId()).orElse(new Status(1L,"Open")));
        //find the Categories from their ids and add them to a list
        List<Long> categoriesIds = request.getCategoriesIds();
        Set<Category> categorySet = new HashSet<>();
        for (Long current : categoriesIds) {
            Category category = categoryRepository.findById(current).orElse(null);
            log.info("The category is {}", category);
            categorySet.add(category);
            }
        existingPlace.setCategories(categorySet);
        Place updatedPlace=placeRepository.save(existingPlace);
        return updatedPlace;
    }

    @Override
    public boolean deletePlace(Long placeId) {
        if(placeRepository.existsById(placeId)){
            placeRepository.deleteById(placeId);
            log.info("Place deleted Successfully");
            return true;
        }
        log.info("The place could not be deleted");
        return false;
    }
}
