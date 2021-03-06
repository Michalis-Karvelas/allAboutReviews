package com.allAboutReviews.responses;

import com.allAboutReviews.models.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse extends Response{

    private Account account;
    private List<Account> accounts;
    private Long accountId;

    public AccountResponse(String msg, Account account) {
        super(msg);
        this.account = account;
    }

    public AccountResponse(String msg, List<Account> accounts){
        super(msg);
        this.accounts=accounts;
    }

    public AccountResponse(String msg, Long accountId){
        super(msg);
        this.accountId=accountId;
    }

}
