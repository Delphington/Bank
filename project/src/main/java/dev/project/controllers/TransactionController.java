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

    @LogDataSourceError
    @Operation(summary = "find transaction by id")
    @GetMapping("/{id}")
    public TransactionDTO findTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @LogDataSourceError
    @Operation(summary = "find transaction by Account id")
    @GetMapping()
    public List<TransactionDTO> findTransactionByAccountId(@Valid @RequestParam(name = "accountId") Long id){
        return transactionService.getTransactionListAccountId(id);
    }


    @LogDataSourceError
    @Operation(summary = "find transaction by Account id")
    @PostMapping()
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO,
                                            @RequestParam("accountId") Long id){
        return transactionService.createTransaction(transactionDTO, id);
    }


    @LogDataSourceError
    @Operation(summary = "delete transaction by Account id")
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
    }


}
