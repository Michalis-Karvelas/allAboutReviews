package com.allAboutReviews.services.interfaces;


import com.allAboutReviews.models.AccountStatus;
import com.allAboutReviews.requests.AccountStatusRequest;

import java.util.List;

public interface IAccountStatusService {

    //list with all the account statuses
    List<AccountStatus> getAll();

    //get the status with the given id
    AccountStatus getById(Long accountStatusId);

    //create a new status
    boolean newAccountStatus(AccountStatusRequest accountStatus);

    //update an existing status
    AccountStatus updateAccountStatus(Long accountStatusId,AccountStatusRequest accountStatus);

    //delete accountStatus by the given id
    boolean deleteAccountStatus(Long accountStatusId);

}
