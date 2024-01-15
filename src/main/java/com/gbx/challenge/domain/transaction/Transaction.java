package com.gbx.challenge.domain.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name = "transactions")
@Entity(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String origin;
    private String destiny;
    private Double value;
    // Podemos ter um valor default no momento da criação da classe
    @Builder.Default()
    private Date date = new Date(System.currentTimeMillis());

}
