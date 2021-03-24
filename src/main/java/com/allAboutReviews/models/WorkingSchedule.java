package com.allAboutReviews.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "working_hours")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id",nullable = false)
    private Long scheduleId;

    @Column(name="opening_time",nullable = false)
    private Time openingTime;

    @Column(name="closing_time",nullable = false)
    private Time closingTime;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="day_id", nullable = false)
    private DayOfTheWeek day;

    public WorkingSchedule(Time openingTime, Time closingTime, Place place, DayOfTheWeek day) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.place = place;
        this.day = day;
    }

//    @Override
//    public int hashCode() {
//
//        return Objects.hashCode(scheduleId);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        WorkingSchedule other = (WorkingSchedule) obj;
//        return Objects.equals(scheduleId, other.scheduleId);
//    }
}
