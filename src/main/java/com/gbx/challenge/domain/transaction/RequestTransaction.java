package com.gbx.challenge.domain.transaction;

import java.sql.Date;
public record RequestTransaction(String origin, String destiny, Double value, Date date) {
    // Um record não necessita de getters:
    // requestTransaction.origin()
}
