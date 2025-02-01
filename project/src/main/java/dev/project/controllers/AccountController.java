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


//    @GetMapping
//    @Operation(summary = "get all information")
//    public List<Account> getAllInformation() {
//        System.out.println(accountService.findAll());
//        return accountService.findAll();
//    }


    @GetMapping("/{id}")
    @Operation(summary = "find account by id")
    public AccountDTO findAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping()
    @Operation(summary = "find accounts by Client_id")
    public AccountDTO findAccountById() {
        return null;
    }



    @PostMapping()
    @Operation(summary = "Create Account")
    public AccountDTO createAccount() {
        return null;
    }




}
