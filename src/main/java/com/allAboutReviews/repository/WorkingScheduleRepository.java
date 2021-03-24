package com.allAboutReviews.repository;

import com.allAboutReviews.models.WorkingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule,Long> {

    List<WorkingSchedule> findWorkingSchedulesByPlace_PlaceId(Long placeId);

    Integer deleteByPlace_PlaceId(Long placeId);
}
