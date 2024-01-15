package com.gbx.challenge.domain.account;

import com.gbx.challenge.OperationType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "accounts")
@Entity(name = "accounts")
@Data
@Builder // O Builder do Lombok é muito útil para instanciar classes
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String owner;
    private Integer account_number;
    private Double balance;


    // Esta é considerada uma regra de negócio, com sua lógica própria. Deve ser implementada na camada de service,
    // não na própria classe da entidade
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
