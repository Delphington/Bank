package dev.project.service;

import dev.project.dto.AccountDto;
import dev.project.dto.ClientDto;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.mapper.ClientMapper;
import dev.project.entity.Account;
import dev.project.entity.Client;
import dev.project.repository.AccountRepository;
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
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long id) {
        return accountMapper.convertAccountDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Account was not found, where id = " + id)));
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getListAccountByClientId(Long clientId) {
        ClientDto clientDTO = clientService.findById(clientId);
        Client client = clientMapper.convertToEntity(clientDTO);
        List<Account> list = accountRepository.findByClient(client);
        if (list == null) {
            throw new NotFoundDataException("Account has not list of Client, client_id = " + clientId);
        }
        return accountMapper.convertAccountDtoList(list);
    }

    @Transactional
    public AccountDto createAccount(AccountDto accountDTO, Long clientId) {
        Client client = clientMapper.convertToEntity(clientService.findById(clientId));
        Account account = accountMapper.convertToEntity(accountDTO);
        account.setClient(client);
        account.setId(null);
        accountRepository.save(account);
        return accountMapper.convertAccountDto(account);
    }


    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new NotFoundDataException("Account was not found, where id = " + id));
        accountRepository.delete(account);
    }

}
