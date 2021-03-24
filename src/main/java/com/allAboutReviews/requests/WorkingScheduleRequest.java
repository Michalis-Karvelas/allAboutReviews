package com.allAboutReviews.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingScheduleRequest {

    private Time openingTime;//maybe it's not needed
    private Time closingTime;//maybe it's not needed
    private Long placeId;
    private Long dayId;
}
