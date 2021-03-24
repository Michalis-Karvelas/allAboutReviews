package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Status;
import com.allAboutReviews.requests.StatusRequest;
import java.util.List;

public interface IStatusService {

    //list of all statuses
    List<Status> getAll();

    //get status by the given id
    Status getById(Long statusId);

    //get status for the given place's id
    Status getByPlaceId(Long placeId);// ask dim nikol if it should be here or in the place class

    //create a new status
    boolean newStatus(StatusRequest status);

    //update an existing status with the given id
    Status updateStatus(Long statusId, StatusRequest status);

    //delete the status with the given id
    boolean deleteStatus(Long statusId);
}
