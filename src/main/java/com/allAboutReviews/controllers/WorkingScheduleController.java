package com.allAboutReviews.controllers;

import com.allAboutReviews.models.WorkingSchedule;
import com.allAboutReviews.requests.WorkingScheduleRequest;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.responses.WorkingScheduleResponse;
import com.allAboutReviews.services.WorkingScheduleImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value = "/api/schedule")
@RestController
public class WorkingScheduleController {

    @Autowired
    private WorkingScheduleImpl workingScheduleImpl;

    //list of all the working schedules- not needed
    @GetMapping(value = "/getall")
    public WorkingScheduleResponse getAll(){
        log.info("Ready to find all the schedules");
        return new WorkingScheduleResponse("Found all the schedules",workingScheduleImpl.getAll());
    }

    //get working schedule by id
    @GetMapping(value = "/getbyid/{workingScheduleId}")
    public WorkingScheduleResponse getById(@PathVariable(value = "workingScheduleId") Long workingScheduleId){
        log.info("Ready to find the schedule with id: {}",workingScheduleId);
        return new WorkingScheduleResponse("Found the working Schedule",
                workingScheduleImpl.getById(workingScheduleId));
    }

    //create a working schedule
    @PostMapping(value = "/new")
    public Response createNewWorkingSchedule(@RequestBody WorkingScheduleRequest request){
        log.info("Ready to create a new working schedule");
        workingScheduleImpl.newWorkingSchedule(request);
        log.info("The schedule has been saved");
        return new Response("The new working Schedule has been inserted into the DB");
    }

    //update an existing working schedule
    @PutMapping(value = "/update/{workingScheduleId}")
    public Response updateExistingWorkingSchedule(@PathVariable(value = "workingScheduleId") Long workingScheduleId,
                                                  @RequestBody WorkingScheduleRequest request){
        log.info("Ready to update the working schedule with id: {}", workingScheduleId);
        WorkingSchedule workingSchedule=workingScheduleImpl.updateWorkingSchedule(workingScheduleId,request);
        if(isNull(workingSchedule)){
            log.info("The schedule does not exist. It can't be updated");
            return new Response("The working schedule does not exist. It can't be updated");
        }
        return new Response("The working schedule has been updated successfully");
    }
    //2 more controllers find by place id and day

    //find by place id
    @GetMapping(value = "/getbyplaceid/{placeId}")
    public WorkingScheduleResponse findByPlaceId(@PathVariable(value = "placeId") Long placeId){
        log.info("Ready to find the whole schedule for the place with id: {}",placeId);
        return new WorkingScheduleResponse("Found the schedule list", workingScheduleImpl.getByPlaceId(placeId));
    }


    //delete an existing working schedule
    @DeleteMapping(value = "/delete/{workingScheduleId}")
    public Response deleteWorkingSchedule(@PathVariable(value = "workingScheduleId") Long workingScheduleId){
        log.info("Ready to delete the schedule with id: {}",workingScheduleId);
        if(workingScheduleImpl.deleteWorkingSchedule(workingScheduleId)==false){
            return new Response("The working schedule could not be deleted");
        }
        return new Response("Working schedule deleted successfully");
    }

    //delete all the schedules of a place
    @Transactional
    @DeleteMapping(value = "deletebyplaceid/{placeId}")
    public Response deleteWholeWorkingScheduleByPlace(@PathVariable(value = "placeId") Long placeId){
        log.info("Ready to delete the schedule for the place with id: {}",placeId);
        if(workingScheduleImpl.deleteWholeWorkingScheduleForPlace(placeId)==false){
            return new Response("The working schedule could not be deleted");
        }
        return new Response("Working schedule deleted successfully");
    }
}
