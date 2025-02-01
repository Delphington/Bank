package dev.project.service;

import dev.project.dto.AccountDTO;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.model.Account;
import dev.project.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Transactional(readOnly = true)
    public AccountDTO getAccountById(Long id){
        return accountMapper.convertAccountDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Model not found")));    }


}
