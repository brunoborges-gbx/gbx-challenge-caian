package com.gbx.challenge.domain.user;

import jakarta.validation.constraints.Size;

import java.sql.Date;

// Neste record de request, devemos validar os campos de input
public record RequestUser(
        String name,
        @Size(min = 11, max = 11, message = "CPF must have exactly 11 characters")
        String cpf,
        Date date_of_birth) {
}
