package com.allAboutReviews.responses;

import com.allAboutReviews.models.WorkingSchedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkingScheduleResponse extends Response{

    private WorkingSchedule workingSchedule;
    private List<WorkingSchedule> workingScheduleList;

    public WorkingScheduleResponse(String msg, WorkingSchedule workingSchedule){
        super(msg);
        this.workingSchedule=workingSchedule;
    }

    public WorkingScheduleResponse(String msg, List<WorkingSchedule> workingScheduleList){
        super(msg);
        this.workingScheduleList=workingScheduleList;
    }
}
