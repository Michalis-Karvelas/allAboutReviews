package com.allAboutReviews.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusRequest {

//    private Long accountStatusId;
    private String accountStatusType;

//    public AccountStatusRequest(Long accountStatusId) {
//        this.accountStatusId = accountStatusId;
//    }

//    public AccountStatusRequest(String accountStatusType) {
//        this.accountStatusType = accountStatusType;
//    }
}
