package dev.project.controller;

import dev.project.annotation.LogDataSourceError;
import dev.project.dto.TransactionDto;
import dev.project.util.ConversionValidationExceptionHandler;
import dev.project.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить тразакцию по идентификатору")
    @LogDataSourceError
    public TransactionDto findTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping()
    @Operation(summary = "Получить транзакцию по индектификатору счета")
    @LogDataSourceError
    public List<TransactionDto> findTransactionByAccountId(@Valid @RequestParam(name = "accountId") Long id) {
        return transactionService.getTransactionListAccountId(id);
    }


    @PostMapping()
    @Operation(summary = "Создать транзакцию по идентификатору счета")
    @LogDataSourceError
    public TransactionDto createTransaction(@Valid @RequestBody TransactionDto transactionDTO,
                                            BindingResult bindingResult,
                                            @RequestParam("accountId") String accountId) {
        Long id = ConversionValidationExceptionHandler.handleTypeConversionErrors(accountId);
        ConversionValidationExceptionHandler.handleValidationErrors(bindingResult);
        return transactionService.createTransaction(transactionDTO, id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить транзакцию по идентификатору счета")
    @LogDataSourceError
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

}
