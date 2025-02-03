package dev.project.controllers;

import dev.project.annotation.LogDataSourceError;
import dev.project.dto.AccountDTO;
import dev.project.exception.DataConversionException;
import dev.project.exception.ValidationException;
import dev.project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить счет идентификатору id")
    @LogDataSourceError
    public AccountDTO findAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping()
    @Operation(summary = "Получить все счета по идентификатору клиента")
    @LogDataSourceError
    public List<AccountDTO> findAccountByClientId(@RequestParam(name = "clientId", required = false) Long id) {
        return accountService.getListAccountByClientId(id);
    }

    @PostMapping()
    @Operation(summary = "Создание счета")
    @LogDataSourceError
    public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDTO, BindingResult bindingResult,
                                    @RequestParam(name = "clientId", required = false) String clientId) {
        Long id;
        try {
            id = Long.parseLong(clientId);
        } catch (RuntimeException e) {
            throw new DataConversionException("Incorrect type clientId");
        }
        handleValidationErrors(bindingResult);

        return accountService.createAccount(accountDTO, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалениче счета по идентификатору")
    @LogDataSourceError
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }


    private void handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new ValidationException(errors);
        }
    }
}
