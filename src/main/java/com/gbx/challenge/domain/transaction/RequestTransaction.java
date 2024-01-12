package com.gbx.challenge.domain.transaction;

import java.sql.Date;
public record RequestTransaction(String origin, String destiny, Double value, Date date) {

    public String getOrigin() { return this.origin; }

    public Double getValue(){
        return this.value;
    }

    public String getDestiny(){
        return this.destiny;
    }
}
