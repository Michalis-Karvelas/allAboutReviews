package com.allAboutReviews.services;

import com.allAboutReviews.models.Account;
import com.allAboutReviews.models.AccountStatus;
import com.allAboutReviews.models.Role;
import com.allAboutReviews.repository.AccountRepository;
import com.allAboutReviews.repository.AccountStatusRepository;
import com.allAboutReviews.repository.RoleRepository;
import com.allAboutReviews.requests.AccountRequest;
import com.allAboutReviews.services.interfaces.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {
    //need some repositories
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountStatusRepository accountStatusRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Account> getAll() {
        log.info("Ready to find all the accounts");
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Long accountId) {
        log.info("Ready to find the account with id:{}",accountId);
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public boolean newAccount(AccountRequest request) {
        log.info("Ready to create a new account");
//      Role role=roleRepository.findById(request.getRoleId()).orElse(null);
//      if (role.equals(null)) {
        Role role=new Role(2L,"User");
        AccountStatus accountStatus=new AccountStatus(1L,"Active");
        Account tempAccount=new Account(request.getEmail(),request.getPassword(),request.getFirstName(),
                request.getLastName(),request.getDateOfBirth(),request.getGender(),accountStatus,role);
//        account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        Account newAccount = accountRepository.save(tempAccount);
        log.info("The new account is {}", newAccount);
        log.info("The account has been inserted in the DB");
        return true;
    }

    @Override
    public Account updateAccount(Long accountId, AccountRequest request) {
        log.info("Ready to update the account with id:{}", accountId);
        Account existingAccount=accountRepository.findById(accountId).orElse(null);
        if (isNull(existingAccount)){
            return null;
        }
        Role role= existingAccount.getRole();
        AccountStatus accountStatus= existingAccount.getAccountStatus();// they can go in the or else
        existingAccount.setEmail(request.getEmail());
        existingAccount.setPassword((request.getPassword()));
        existingAccount.setFirstName(request.getFirstName());
        existingAccount.setLastName(request.getLastName());
        existingAccount.setDateOfBirth(request.getDateOfBirth());
        existingAccount.setGender(request.getGender());
        if(!(isNull(request.getAccountStatusId()))) {
            existingAccount.setAccountStatus(accountStatusRepository.findById(request.getAccountStatusId()).orElse(accountStatus));
        }
        if (!(isNull(request.getRoleId()))){
        existingAccount.setRole(roleRepository.findById(request.getRoleId()).orElse(role));
        }
        Account updatedAccount = accountRepository.save(existingAccount);//check if it needs role etc.
        log.info("The updated account is {}", updatedAccount);
        log.info("The updated account has been inserted to the DB");
        return updatedAccount;
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        log.info("Ready to delete the account with id:{} ",accountId );
        if (accountRepository.existsById(accountId)){
            accountRepository.deleteById(accountId);
            log.info("Account deleted successfully");
            return true;
        }
        log.info("The account has not been deleted successfully");
        return false;
    }
}
