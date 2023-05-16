package com.portfolio.backend.service;

import com.portfolio.backend.model.Account;
import com.portfolio.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    
    @Autowired
    AccountRepository accRepo;

    @Override
    public boolean tryToLog(Account acc) {
        Account accRef = accRepo.findByUsername(acc.getUsername());
        if (accRef != null) {
            return accRef.getPassword().equals(acc.getPassword());
        } else return false;
    }
    
}
