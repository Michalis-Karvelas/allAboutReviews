package com.allAboutReviews.responses;

import com.allAboutReviews.models.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse extends Response{

    private Status status;
    private List<Status> statusList;
    private Long statusId;

    public StatusResponse(String msg,Status status){
        super(msg);
        this.status=status;
    }

    public StatusResponse(String msg, List<Status> statusList){
        super(msg);
        this.statusList=statusList;
    }

    public StatusResponse(String msg,Long statusId){
        super(msg);
        this.statusId=statusId;
    }

}
