package dev.project.controller;

import dev.project.annotation.LogDataSourceError;
import dev.project.dto.AccountDto;
import dev.project.exception.util.ConversionValidationExceptionHandler;
import dev.project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить счет идентификатору id")
    @LogDataSourceError
    public AccountDto findAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping()
    @Operation(summary = "Получить все счета по идентификатору клиента")
    @LogDataSourceError
    public List<AccountDto> findAccountByClientId(@RequestParam(name = "clientId", required = false) Long id) {
        return accountService.getListAccountByClientId(id);
    }

    @PostMapping()
    @Operation(summary = "Создание счета")
    @LogDataSourceError
    public AccountDto createAccount(@Valid @RequestBody AccountDto accountDTO, BindingResult bindingResult,
                                    @RequestParam(name = "clientId", required = false) String clientId) {
        Long id = ConversionValidationExceptionHandler.handleTypeConversionErrors(clientId);
        ConversionValidationExceptionHandler.handleValidationErrors(bindingResult);
        return accountService.createAccount(accountDTO, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалениче счета по идентификатору")
    @LogDataSourceError
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
