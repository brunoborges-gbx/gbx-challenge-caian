package com.gbx.challenge.controllers;

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

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountOriginRepository;
    @Autowired
    private AccountRepository accountDestinyRepository;

    @GetMapping
    public ResponseEntity getAllTransactions(){
        var allTransactions = transactionRepository.findAll();
        return ResponseEntity.ok(allTransactions);
    }

    @PostMapping
    public ResponseEntity addTransaction(@RequestBody @Valid RequestTransaction data){
        Transaction newTransaction = new Transaction(data);

        var findOriginAccount = accountOriginRepository.findById(data.origin());
        var findDestinyAccount = accountDestinyRepository.findById(data.destiny());

        if (findOriginAccount.isPresent()) {
            //Descontando o valor da conta de origem
            Account accountOrigin = findOriginAccount.get();
            double currentOriginBalance = accountOrigin.getBalance();
            double newOriginBalance = currentOriginBalance - data.value();
            accountOrigin.setBalance(newOriginBalance);
            accountOriginRepository.save(accountOrigin);

            //Adicionando o valor na conta de destino
            Account accountDestiny = findDestinyAccount.get();
            double currentDestinyBalance = accountDestiny.getBalance();
            double newDestinyBalance = currentDestinyBalance + data.value();
            accountDestiny.setBalance(newDestinyBalance);
            accountDestinyRepository.save(accountDestiny);

            transactionRepository.save(newTransaction);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
    }
}
