package com.allAboutReviews.services;

import com.allAboutReviews.models.Status;
import com.allAboutReviews.repository.StatusRepository;
import com.allAboutReviews.requests.StatusRequest;
import com.allAboutReviews.services.interfaces.IStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class StatusServiceImpl implements IStatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAll() {
        log.info("Ready to find all the statuses");
        return statusRepository.findAll();
    }

    @Override
    public Status getById(Long statusId) {
        log.info("Ready to find the status with the id: {}", statusId);
        return statusRepository.findById(statusId).orElse(null);
    }

    //not needed it is in the get place by id
    @Override
    public Status getByPlaceId(Long placeId) {
        return null;
    }

    @Override
    public boolean newStatus(StatusRequest request) {
        log.info("Ready to create a new status");
        Status tempStatus=new Status(request.getStatusType());
        Status newStatus=statusRepository.save(tempStatus);
        log.info("The new status is {},", newStatus);
        log.info("The new status has been inserted in the DB");
        return true;
    }

    @Override
    public Status updateStatus(Long statusId, StatusRequest request) {
        log.info("Ready to update the status with id: {}",statusId);
        Status existingStatus=statusRepository.findById(statusId).orElse(null);
        if (isNull(existingStatus)){
            return null;
        }
        existingStatus.setStatusType(request.getStatusType());
        Status updatedStatus=statusRepository.save(existingStatus);
        log.info("The updated status is {}", updatedStatus);
        log.info("The updated status has been inserted in the DB");
        return updatedStatus;
    }

    @Override
    public boolean deleteStatus(Long statusId) {
        log.info("Ready to delete the status with id: {}", statusId);
        if(statusRepository.existsById(statusId)){
            statusRepository.deleteById(statusId);
            log.info("The status has been deleted successfully");
            return true;
        }
        log.info("The status could not be deleted");
        return false;
    }
}
