package dev.project.service;

import dev.project.dto.AccountDTO;
import dev.project.dto.ClientDTO;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.mapper.ClientMapper;
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
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public AccountDTO getAccountById(Long id) {
        return accountMapper.convertAccountDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Model not found")));
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> getListAccountByClientId(Long clientId) {
        ClientDTO clientDTO = clientService.findById(clientId);
        Client client = clientMapper.convertModel(clientDTO);
        List<Account> list = accountRepository.findByClient(client);
        return accountMapper.convertAccountDtoList(list);
    }

    @Transactional
    public AccountDTO createAccount(AccountDTO accountDTO, Long clientId) {
        System.out.println(accountDTO);
        Client client = clientMapper.convertModel(clientService.findById(clientId));
        Account account = accountMapper.convertToModel(accountDTO);

        account.setClient(client);
        account.setId(null);
        accountRepository.save(account);
        return accountMapper.convertAccountDto(account);
    }


    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundDataException("Not found"));
        accountRepository.delete(account);
    }

}
