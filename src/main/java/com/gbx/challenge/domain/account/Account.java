package com.gbx.challenge.domain.account;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Table(name = "account")
@Entity(name = "account")
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
}
