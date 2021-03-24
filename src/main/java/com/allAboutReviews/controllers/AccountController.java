package com.allAboutReviews.controllers;


import com.allAboutReviews.models.Account;
import com.allAboutReviews.requests.AccountRequest;
import com.allAboutReviews.responses.AccountResponse;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.services.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
//@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/account")
@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    // list of all the accounts
    @GetMapping(value="/getall")
    public AccountResponse getAll(){
        log.info("Ready to find all the Accounts");
        return new AccountResponse("Found all the Accounts",accountServiceImpl.getAll());
    }

    // get account by id
    @GetMapping(value="/getbyid/{accountId}")
    public AccountResponse getById(@PathVariable Long accountId){
        log.info("Ready to find account by id:{} ", accountId);
        return new AccountResponse("Found the account",accountServiceImpl.getById(accountId));
    }

    //create new account
    @PostMapping(value="/new",consumes = "application/json",
            produces = "application/json")
    public Response createNewAccount(@RequestBody AccountRequest request){
        log.info("Ready to create a new Account");
        accountServiceImpl.newAccount(request);
        log.info("The account has been saved");
        return new Response("The account has been saved");

    }

    //update an existing account
    @PutMapping(value="/update/{accountId}",consumes = "application/json",produces = "application/json")
    public Response updateExistingAccount(@PathVariable(value = "accountId") Long accountId,@RequestBody AccountRequest request){
        log.info("Ready to update an account");
        Account account = accountServiceImpl.updateAccount(accountId, request);
        if(isNull(account)){
            return new Response("The account does not exist. It can't be updated");
        }
        return new Response("The account has been updated successfully");
    }

    //delete an existing account
    @DeleteMapping(value = "/delete/{accountId}")
    public Response deleteAccount(@PathVariable(value = "accountId") Long accountId){
        log.info("Ready to delete the account with id:{} ",accountId);
        if(accountServiceImpl.deleteAccount(accountId) == false){
            return new Response("The account couldn't be deleted");
        }
        return new Response("Account deleted successfully");
    }
}
