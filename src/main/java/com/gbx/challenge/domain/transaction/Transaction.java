package com.gbx.challenge.domain.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name = "transactions")
@Entity(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String origin;
    private String destiny;
    private Double value;
    private Date date;

    public Transaction(RequestTransaction requestTransaction){
        this.origin = requestTransaction.origin();
        this.destiny = requestTransaction.destiny();
        this.value = requestTransaction.value();
        this.date = currentDate();
    }

    private Date currentDate(){
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public Double getValue() {
        return value;
    }
}
