package com.evozon.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Payment implements Serializable {

    @Column(insertable=false,updatable=false)
    private String paymentMethod;

    @Column(insertable=false,updatable=false)
    private String cardNumber;

    @Column(insertable=false,updatable=false)
    private String cardCode;

    public Payment() {}

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}
