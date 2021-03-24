package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Account;
import com.allAboutReviews.requests.AccountRequest;

import java.util.List;

public interface IAccountService {

    //a list with all the accounts
    List<Account> getAll();

    //account found by the given id
    Account getById(Long accountId);

    //create a new account
    boolean newAccount(AccountRequest account);

    //update account with the given id
    Account updateAccount(Long accountId, AccountRequest account);

    //delete an account with the given id
    boolean deleteAccount(Long accountId);
}
