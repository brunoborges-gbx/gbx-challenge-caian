package com.gbx.challenge.domain.account;

import com.gbx.challenge.OperationType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "accounts")
@Entity(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String owner;
    private Integer account_number;
    private Double balance;

    public Account(RequestAccount requestAccount){
        this.owner = requestAccount.owner();
        this.account_number = requestAccount.account_number();
        this.balance = requestAccount.balance();
    }

    public void updateBalance(Double value, OperationType operationType) {
        switch (operationType) {
            case DEBIT:
                debit(value);
                break;
            case CREDIT:
                credit(value);
                break;
            default:
                throw new IllegalArgumentException("Tipo de operação não suportado: " + operationType);
        }
    }

    private void debit(Double value) {
        if (value > balance) {
            throw new IllegalArgumentException("Saldo insuficiente para débito.");
        }
        balance -= value;
    }

    private void credit(Double value) {
        balance += value;
    }

}
