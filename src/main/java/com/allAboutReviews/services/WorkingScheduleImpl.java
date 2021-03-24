package com.allAboutReviews.services;

import com.allAboutReviews.models.WorkingSchedule;
import com.allAboutReviews.repository.DayOfTheWeekRepository;
import com.allAboutReviews.repository.PlaceRepository;
import com.allAboutReviews.repository.WorkingScheduleRepository;
import com.allAboutReviews.requests.WorkingScheduleRequest;
import com.allAboutReviews.services.interfaces.IWorkingSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class WorkingScheduleImpl implements IWorkingSchedule {

    @Autowired
    private WorkingScheduleRepository workingScheduleRepository;

    @Autowired
    private DayOfTheWeekRepository dayOfTheWeekRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public List<WorkingSchedule> getAll() {
        log.info("Ready to get all the schedules");
        return workingScheduleRepository.findAll();
    }

    @Override
    public WorkingSchedule getById(Long workingScheduleId) {
        log.info("Ready to find the working schedule with id: {}",workingScheduleId);
        return workingScheduleRepository.findById(workingScheduleId).orElse(null);
    }

    @Override
    public List<WorkingSchedule> getByPlaceId(Long placeId) {
        log.info("Ready to find the working schedule of the place with id: {}",placeId);
        return workingScheduleRepository.findWorkingSchedulesByPlace_PlaceId(placeId);
    }

    @Override
    public boolean newWorkingSchedule(WorkingScheduleRequest request) {
        log.info("Ready to create a new working schedule");
        WorkingSchedule tempWorkingSchedule=new WorkingSchedule(request.getOpeningTime(),request.getClosingTime(),
                placeRepository.findById(request.getPlaceId()).orElse(null),
                dayOfTheWeekRepository.findById(request.getDayId()).orElse(null));
        WorkingSchedule newWorkingSchedule=workingScheduleRepository.save(tempWorkingSchedule);
        log.info("The new working schedule is :{}", newWorkingSchedule);
        log.info("The working schedule has been inserted into the DB");
        return true;
    }

    @Override
    public WorkingSchedule updateWorkingSchedule(Long workingScheduleId, WorkingScheduleRequest request) {
        log.info("Ready to update the working schedule with id: {}", workingScheduleId);
        WorkingSchedule existingWorkingSchedule= workingScheduleRepository.findById(workingScheduleId).orElse(null);
        if(isNull(existingWorkingSchedule)){
            log.info("There is not any working schedule with the given id");
            return null;
        }
        existingWorkingSchedule.setOpeningTime(request.getOpeningTime());
        existingWorkingSchedule.setClosingTime(request.getClosingTime());
        existingWorkingSchedule.setPlace(placeRepository.findById(request.getPlaceId()).
                orElse(existingWorkingSchedule.getPlace()));
        existingWorkingSchedule.setDay(dayOfTheWeekRepository.findById(request.getDayId()).
                orElse(existingWorkingSchedule.getDay()));
        WorkingSchedule updatedWorkingSchedule=workingScheduleRepository.save(existingWorkingSchedule);
        log.info("The working schedule has been updated");
        log.info("The updated working schedule has been inserted into the DB");
        return updatedWorkingSchedule;
    }

    @Override
    public boolean deleteWorkingSchedule(Long workingScheduleId) {
        log.info("Ready to delete the working schedule with id: {}",workingScheduleId);
        if (workingScheduleRepository.existsById(workingScheduleId)){
            workingScheduleRepository.deleteById(workingScheduleId);
            log.info("The working schedule has been successfully deleted");
            return true;
        }
        log.info("The working schedule wasn't deleted successfully. This id doesn't exist in the DB");
        return false;
    }

    //probably not needed
//    @Override
//    public boolean deleteWorkingScheduleForPlaceAndDay(Long placeId, Long dayId) {
//        return false;
//    }

    @Transactional
    @Override
    public boolean deleteWholeWorkingScheduleForPlace(Long placeId) {
        log.info("Ready to delete all the schedules for the place with id: {}",placeId);
        if ((workingScheduleRepository.findWorkingSchedulesByPlace_PlaceId(placeId)).isEmpty()){
            log.info("The whole working schedule wasn't deleted successfully");
            return false;
        }
        workingScheduleRepository.deleteByPlace_PlaceId(placeId);
        log.info("The whole working schedule for the place was deleted successfully");
        return true;
    }
}
