package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.WorkingSchedule;
import com.allAboutReviews.requests.WorkingScheduleRequest;

import java.util.List;

public interface IWorkingSchedule {

    //a list for all the working schedules
    List<WorkingSchedule> getAll();//not needed

    //get the working schedule with the given id
    WorkingSchedule getById(Long workingScheduleId);//not needed

    //get  the working schedule of a specific place
    List<WorkingSchedule> getByPlaceId(Long placeId);

    //create a new working schedule
    boolean newWorkingSchedule(WorkingScheduleRequest workingSchedule);

    //update an existing working schedule with the given id
    WorkingSchedule updateWorkingSchedule(Long workingScheduleId, WorkingScheduleRequest workingSchedule);

    //delete the working schedule with the given id
    boolean deleteWorkingSchedule(Long workingScheduleId);

    // delete working schedule for a place and for a specific day
    // boolean deleteWorkingScheduleForPlaceAndDay(Long placeId,Long dayId);

    //delete a place's whole schedule in case the place closes indefinitely
    boolean deleteWholeWorkingScheduleForPlace(Long placeId);

}
