package dev.project.service;

import dev.project.dto.AccountDTO;
import dev.project.dto.ClientDTO;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.model.Account;
import dev.project.model.Client;
import dev.project.repository.AccountRepository;
import dev.project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;

    private final AccountMapper accountMapper;


    @Transactional(readOnly = true)
    public AccountDTO getAccountById(Long id) {
        return accountMapper.convertAccountDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Model not found")));
    }

//
//    public List<AccountDTO> getListAccountByClientId(Long clientId) {
//        ClientDTO clientDTO = clientService.findById(clientId);
//    }

}
