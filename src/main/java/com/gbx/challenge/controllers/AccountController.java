package com.gbx.challenge.controllers;

import com.gbx.challenge.domain.account.Account;
import com.gbx.challenge.domain.account.AccountRepository;
import com.gbx.challenge.domain.account.RequestAccount;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository repository;

    @GetMapping
    public ResponseEntity getAllAccount(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity addAccount(@RequestBody @Valid RequestAccount data){
        repository.save(new Account(data));
        return ResponseEntity.ok().build();
    }
}
