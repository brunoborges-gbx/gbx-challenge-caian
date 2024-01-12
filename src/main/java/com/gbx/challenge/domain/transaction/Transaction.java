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
        this.origin = requestTransaction.getOrigin();
        this.destiny = requestTransaction.getDestiny();
        this.value = requestTransaction.getValue();
        this.date = currentDate();
    }

    private Date currentDate(){
        return new Date(System.currentTimeMillis());
    }

}
