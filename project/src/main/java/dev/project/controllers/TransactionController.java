package dev.project.controllers;

import dev.project.annotation.LogDataSourceError;
import dev.project.dto.TransactionDTO;
import dev.project.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{id}")
    @Operation(summary = "find transaction by id")
    @LogDataSourceError
    public TransactionDTO findTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @GetMapping()
    @Operation(summary = "find transaction by Account id")
    @LogDataSourceError
    public List<TransactionDTO> findTransactionByAccountId(@Valid @RequestParam(name = "accountId") Long id){
        return transactionService.getTransactionListAccountId(id);
    }


    @PostMapping()
    @Operation(summary = "find transaction by Account id")
    @LogDataSourceError
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO,
                                            @RequestParam("accountId") Long id){
        return transactionService.createTransaction(transactionDTO, id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete transaction by Account id")
    @LogDataSourceError
    public void deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
    }


}
