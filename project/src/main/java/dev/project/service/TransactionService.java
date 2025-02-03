package dev.project.service;

import dev.project.dto.AccountDto;
import dev.project.dto.TransactionDto;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.mapper.TransactionMapper;
import dev.project.entity.Account;
import dev.project.entity.Transaction;
import dev.project.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;


    @Transactional(readOnly = true)
    public TransactionDto getTransactionById(Long id) {
        return transactionMapper.convertToDto(transactionRepository.findById(id).orElseThrow(
                () -> new NotFoundDataException("Transaction was not found, where id = " + id)));
    }

    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactionListAccountId(Long id){
        AccountDto accountDTO = accountService.getAccountById(id);
        Account account = accountMapper.convertToEntity(accountDTO);
        List<Transaction> list = transactionRepository.findByAccount(account);
        if(list == null){
            throw new NotFoundDataException("Account has not list of transaction, account_id = " + id);
        }
        return transactionMapper.convertToListDto(list);
    }

    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDTO, Long accountId){
        AccountDto accountDTO = accountService.getAccountById(accountId);
        Account account = accountMapper.convertToEntity(accountDTO);
        Transaction transaction = transactionMapper.convertToEntity(transactionDTO);
        transaction.setAccount(account);
        transaction.setId(null);
        transactionRepository.save(transaction);
        return transactionMapper.convertToDto(transaction);

    }

    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()->new NotFoundDataException("transaction was not found id = " + id));
        transactionRepository.delete(transaction);
    }
}
