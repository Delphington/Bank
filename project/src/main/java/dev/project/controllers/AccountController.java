package dev.project.controllers;

import dev.project.dto.AccountDTO;
import dev.project.model.Account;
import dev.project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public List<Account> getAll() {
        System.out.println(accountService.findAll());
        return accountService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "find account by Id")
    public AccountDTO findAccountById(@PathVariable Long id){
        return  null;
    }


    @PostMapping()
    @Operation(summary = "Create Account")
    public AccountDTO createAccount() {
        return null;
    }


}
