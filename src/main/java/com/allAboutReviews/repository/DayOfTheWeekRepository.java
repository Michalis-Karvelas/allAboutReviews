package com.allAboutReviews.repository;

import com.allAboutReviews.models.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfTheWeekRepository extends JpaRepository<DayOfTheWeek,Long> {

}
