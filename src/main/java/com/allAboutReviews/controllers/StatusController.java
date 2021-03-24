package com.allAboutReviews.controllers;

import com.allAboutReviews.models.Status;
import com.allAboutReviews.requests.StatusRequest;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.responses.StatusResponse;
import com.allAboutReviews.services.StatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value = "/api/status")
@RestController
public class StatusController {

    @Autowired
    private StatusServiceImpl statusServiceImpl;

    //list of all the statuses
    @GetMapping(value = "/getall")
    public StatusResponse getAll(){
        log.info("Ready to find all the statuses");
        return new StatusResponse("Found all the statuses", statusServiceImpl.getAll());
    }

    //get status by id
    @GetMapping(value ="getbyid/{statusId}")
    public StatusResponse getById(@PathVariable(value = "statusId") Long statusId){
        log.info("Ready to find the status with id: {}",statusId);
        return new StatusResponse("Found the status", statusServiceImpl.getById(statusId));
    }

    //create a new status
    @PostMapping(value = "/new",consumes = "application/json", produces = "application/json")
        public Response CreateNewStatus(@RequestBody StatusRequest request){
        statusServiceImpl.newStatus(request);
        log.info("The status has been saved");
        return new Response("The status has been inserted in the DB");
    }

    //update an existing status
    @PutMapping(value = "/update/{statusId}",consumes = "application/json", produces = "application/json")
    public Response updateExistingStatus(@PathVariable(value = "statusId") Long statusId,@RequestBody StatusRequest request){
        log.info("Ready to update the status with id: {}",statusId);
        Status status= statusServiceImpl.updateStatus(statusId,request);
        if(isNull(status)){
            return new Response("The status does not exist. It can't be updated");
        }
        return new Response("The status has been updated successfully");
    }

    //delete an existing status
    @DeleteMapping(value = "/delete/{statusId}")
    public Response deleteStatus(@PathVariable(value = "statusId") Long statusId) {
        log.info("Ready to delete a status");
        if (statusServiceImpl.deleteStatus(statusId) == false) {
            return new Response("The status could not be deleted");
        }
        return new Response("The status has been deleted successfully");
    }
}
