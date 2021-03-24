package com.allAboutReviews.controllers;

import com.allAboutReviews.models.Account;
import com.allAboutReviews.models.AccountStatus;
import com.allAboutReviews.requests.AccountStatusRequest;
import com.allAboutReviews.responses.AccountStatusResponse;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.services.AccountStatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value = "/api/accountStatus")
@RestController
public class AccountStatusController {

    @Autowired
    private AccountStatusServiceImpl accountStatusServiceImp;

    //list of alla the account statuses
    @GetMapping(value = "/getall")
    public AccountStatusResponse getAll(){
        log.info("Ready to find all the account statuses");
        return new AccountStatusResponse("Found all the account statuses",accountStatusServiceImp.getAll());
    }

    //get account status by id
    @GetMapping(value = "/getbyid/{accountStatusId}")
    public AccountStatusResponse getById(@PathVariable(value = "accountStatusId") Long accountStatusId){
        AccountStatus accountStatus= accountStatusServiceImp.getById(accountStatusId);
        log.info("Ready to find the status with id: {}",accountStatusId);
        String msg="Found the account status";
        if (accountStatus==null){
            msg="There wasn't any account status with the given id";
        }
        return new AccountStatusResponse(msg,accountStatus);
    }

    //create a new account status
    @PostMapping(value = "/new",consumes = "application/json", produces = "application/json")
    public Response createNewAccountStatus(@RequestBody AccountStatusRequest request){
        log.info("Ready to create a new account status");
        accountStatusServiceImp.newAccountStatus(request);
        log.info("The account status has been saved");
        return new Response("The account status has been inserted in the DB");
    }

    //update an existing account status
    @PutMapping(value = "/update/{accountStatusId}")
    public Response updateAccountStatus(@PathVariable(value = "accountStatusId") Long accountStatusId,
                                        @RequestBody AccountStatusRequest request){
        log.info("Ready to update the account status with id: {}", accountStatusId);
        AccountStatus accountStatus = accountStatusServiceImp.updateAccountStatus(accountStatusId,request);
        if(isNull(accountStatus)){
            return new Response("The account status does not exist. It can't be updated");
        }
        return new Response("The account status has been updated successfully");
    }

    //delete an existing account status
    @DeleteMapping(value = "/delete/{accountStatusId}")
    public Response deleteAccountStatus(@PathVariable(value = "accountStatusId") Long accountStatusId){
        log.info("Ready to delete the account status with id:{} ",accountStatusId);
        if(accountStatusServiceImp.deleteAccountStatus(accountStatusId)== false){
            return new Response("The account status couldn't be deleted");
        }
        return new Response("Account status deleted successfully");
    }
}
