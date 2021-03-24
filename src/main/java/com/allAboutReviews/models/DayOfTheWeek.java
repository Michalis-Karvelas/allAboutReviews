package com.allAboutReviews.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "days_of_the_week")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOfTheWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id",nullable = false)
    private Long dayId;

    @Column(name = "day_of_the_week",nullable = false)
    private String day;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "day")
    private Set<WorkingSchedule> workingScheduleSet;

    public DayOfTheWeek(String day) {
        this.day = day;
    }

    public DayOfTheWeek(Long dayId, String day) {
        this.dayId = dayId;
        this.day = day;
    }

}
