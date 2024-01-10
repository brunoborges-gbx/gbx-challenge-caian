package com.gbx.challenge.domain.user;

import java.sql.Date;

public record RequestUser(String name, String cpf, Date date_of_birth) {
}
