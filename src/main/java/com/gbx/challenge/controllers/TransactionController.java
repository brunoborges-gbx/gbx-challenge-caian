package com.gbx.challenge.controllers;

import com.gbx.challenge.OperationType;
import com.gbx.challenge.domain.account.Account;
import com.gbx.challenge.domain.account.AccountRepository;
import com.gbx.challenge.domain.transaction.RequestTransaction;
import com.gbx.challenge.domain.transaction.Transaction;
import com.gbx.challenge.domain.transaction.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity getAllTransactions() {
        var allTransactions = transactionRepository.findAll();
        return ResponseEntity.ok(allTransactions);
    }

    @PostMapping
    public ResponseEntity addTransaction(@RequestBody @Valid RequestTransaction data) {
        Transaction newTransaction = new Transaction(data);

        if (attBalance(data.getOrigin(), data.getValue(), OperationType.DEBIT) &&
                attBalance(data.getDestiny(), data.getValue(), OperationType.CREDIT)) {
            transactionRepository.save(newTransaction);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
    }

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
