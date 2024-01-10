package com.gbx.challenge.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name="users")
@Entity(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String cpf;
    private Date date_of_birth;

    public User(RequestUser requestUser){
        this.name = requestUser.name();
        this.cpf = requestUser.cpf();
        this.date_of_birth = requestUser.date_of_birth();
    }
}
