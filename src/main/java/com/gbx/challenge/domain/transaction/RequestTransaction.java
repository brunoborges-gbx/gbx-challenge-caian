package com.gbx.challenge.domain.transaction;

import lombok.Getter;

import java.sql.Date;
public record RequestTransaction(String origin, String destiny, Double value, Date date) {

}
