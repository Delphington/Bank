package dev.project.service;

import dev.project.dto.AccountDTO;
import dev.project.dto.TransactionDTO;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.AccountMapper;
import dev.project.mapper.TransactionMapper;
import dev.project.model.Account;
import dev.project.model.Transaction;
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
    public TransactionDTO getTransactionById(Long id) {
        return transactionMapper.convertToDto(transactionRepository.findById(id).orElseThrow(
                () -> new NotFoundDataException("Transaction was not found, where id = " + id)));
    }


    @Transactional(readOnly = true)
    public List<TransactionDTO> getTransactionListAccountId(Long id){
        AccountDTO accountDTO = accountService.getAccountById(id);
        Account account = accountMapper.convertToModel(accountDTO);
        List<Transaction> list = transactionRepository.findByAccount(account);
        return transactionMapper.convertToListDto(list);
    }

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO, Long accountId){
        AccountDTO accountDTO = accountService.getAccountById(accountId);
        Account account = accountMapper.convertToModel(accountDTO);

        Transaction transaction = transactionMapper.convertToModel(transactionDTO);

        transaction.setAccount(account);
        transaction.setId(null);
        transactionRepository.save(transaction);
        return transactionMapper.convertToDto(transaction);

    }

    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()->new NotFoundDataException("transaction not found"));
        transactionRepository.delete(transaction);
    }
}
