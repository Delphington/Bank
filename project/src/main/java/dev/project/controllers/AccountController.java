package dev.project.controllers;

import dev.project.dto.AccountDTO;
import dev.project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "find account by id")
    public AccountDTO findAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping()
    @Operation(summary = "find accounts by clientId")
    public List<AccountDTO> findAccountByClientId(@RequestParam(name = "clientId", required = false) Long id) {
        return accountService.getListAccountByClientId(id);
    }

    @PostMapping()
    @Operation(summary = "Create Account")
    public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDTO,
                                    @RequestParam(name = "clientId", required = false) Long id) {

        return accountService.createAccount(accountDTO, id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account by id")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }

}
