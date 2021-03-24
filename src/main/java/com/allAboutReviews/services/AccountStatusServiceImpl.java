package com.allAboutReviews.services;

import com.allAboutReviews.models.AccountStatus;
import com.allAboutReviews.repository.AccountStatusRepository;
import com.allAboutReviews.requests.AccountStatusRequest;
import com.allAboutReviews.services.interfaces.IAccountStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class AccountStatusServiceImpl implements IAccountStatusService {

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Override
    public List<AccountStatus> getAll() {
        log.info("Ready to find all the account statuses");
        return accountStatusRepository.findAll();
    }

    @Override
    public AccountStatus getById(Long accountStatusId) {
        log.info("Ready to find the account status with id: {}", accountStatusId);
        return accountStatusRepository.findById(accountStatusId).orElse(null);
    }

    @Override
    public boolean newAccountStatus(AccountStatusRequest request) {
        log.info("Ready to create an acount status");
        AccountStatus tempAccountStatus=new AccountStatus(request.getAccountStatusType());
        AccountStatus newAccountStatus=accountStatusRepository.save(tempAccountStatus);
        log.info("The new account status is: {} ",newAccountStatus);
        log.info("The account status has been inserted in the DB");
        return true;
    }

    @Override
    public AccountStatus updateAccountStatus(Long accountStatusId, AccountStatusRequest request) {
        log.info("Ready to update the account status with id: {}",accountStatusId);
        AccountStatus existingAccountStatus=accountStatusRepository.findById(accountStatusId).orElse(null);
        if(isNull(existingAccountStatus)){
        return null;
        }
        existingAccountStatus.setAccountStatusType(request.getAccountStatusType());
        AccountStatus updatedAccountStatus=accountStatusRepository.save(existingAccountStatus);
        log.info("The updated account status is {}", updatedAccountStatus);
        log.info("The updated account status has been inserted to the DB");
        return updatedAccountStatus;
    }

    @Override
    public boolean deleteAccountStatus(Long accountStatusId) {
        log.info("Ready to delete the account status with id: {}", accountStatusId);
        if (accountStatusRepository.existsById(accountStatusId)){
            accountStatusRepository.deleteById(accountStatusId);
            log.info("The account status has been successfully deleted");
            return true;
        }
        log.info("The account status could not be deleted");
        return false;
    }
}
