package dev.project.service;

import dev.project.model.Account;
import dev.project.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }
}
