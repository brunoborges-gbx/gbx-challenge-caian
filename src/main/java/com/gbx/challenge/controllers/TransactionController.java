package com.gbx.challenge.controllers;

import com.gbx.challenge.OperationType;
import com.gbx.challenge.domain.account.Account;
import com.gbx.challenge.domain.account.AccountRepository;
import com.gbx.challenge.domain.transaction.RequestTransaction;
import com.gbx.challenge.domain.transaction.Transaction;
import com.gbx.challenge.domain.transaction.TransactionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        // Sempre usar o type correto do retorno esperado quando sabemos qual é:
        List<Transaction> allTransactions = transactionRepository.findAll();
        return ResponseEntity.ok(allTransactions);
    }

    @PostMapping
    public ResponseEntity addTransaction(@RequestBody @Valid RequestTransaction data) {
        Transaction newTransaction = Transaction.builder()
                .origin(data.origin())
                .destiny(data.destiny())
                .value(data.value())
                .build();

        // Regras de negócio não devem estar na camada de controller, e sim na camada domain/service - separação de responsabilidades
        if (attBalance(data.origin(), data.value(), OperationType.DEBIT) &&
                attBalance(data.destiny(), data.value(), OperationType.CREDIT)) {
            transactionRepository.save(newTransaction);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
    }

    // Regras de negócio não devem estar na camada de controller - separação de responsabilidades
    private boolean attBalance(String account, Double value, OperationType operationType) {
        var findAccount = accountRepository.findById(account);

        if (findAccount.isPresent()) {
            Account acc = findAccount.get();
            acc.updateBalance(value, operationType);
            accountRepository.save(acc);
            return true;
        } else {
            return false;
        }
    }
}
