package dev.project.controllers;

import dev.project.model.Account;
import dev.project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public List<Account> getAll (){
        System.out.println(accountService.findAll());
        return accountService.findAll();
    }

}
