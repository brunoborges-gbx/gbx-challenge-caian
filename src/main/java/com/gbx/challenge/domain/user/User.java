package com.gbx.challenge.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Table(name="users")
@Entity(name="users")
@Data // A annotation Data do lombok substitui o Constructor, Getters, Setters, Equals e Hashcode
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String cpf;
    private Date date_of_birth;

}
