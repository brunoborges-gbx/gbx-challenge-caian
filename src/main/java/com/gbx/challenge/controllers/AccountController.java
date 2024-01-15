package com.gbx.challenge.controllers;

import com.gbx.challenge.domain.account.Account;
import com.gbx.challenge.domain.account.AccountRepository;
import com.gbx.challenge.domain.account.RequestAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    // Ao invés de field injection (Autowired) podemos injetar via construtor com o Lombok (RequiredArgsConstructor)
    // isso permite maior testabilidade e imutabilidade via o "final".
    private final AccountRepository repository;

    @GetMapping
    // Sempre usar o type correto do retorno esperado.
    public ResponseEntity<List<Account>> getAllAccount() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    // Sempre usar o type correto do retorno esperado.
    public ResponseEntity<Account> addAccount(@RequestBody @Valid RequestAccount data){
        // Em uma operação de Post ou Put, o usual é retornar o objeto adicionado/modificado
        return ResponseEntity.ok(repository.save(
                // Ao utilizar o Builder dispensamos a necessidade de contrutores específicos.
                Account.builder()
                        .account_number(data.account_number())
                        .balance(data.balance())
                        .owner(data.owner())
                        .build()));
    }
}
