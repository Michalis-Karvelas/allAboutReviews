package com.allAboutReviews.responses;

import com.allAboutReviews.models.AccountStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountStatusResponse extends Response{

    private AccountStatus accountStatus;
    private List<AccountStatus> accountStatusList;
    private Long accountStatusId;

    public AccountStatusResponse(String msg, Long accountStatusId){
        super(msg);
        this.accountStatusId=accountStatusId;
    }

    public AccountStatusResponse(String msg, List<AccountStatus> accountStatusList){
        super(msg);
        this.accountStatusList=accountStatusList;
    }

    public AccountStatusResponse(String msg,AccountStatus accountStatus){
        super(msg);
        this.accountStatus=accountStatus;
    }

}
