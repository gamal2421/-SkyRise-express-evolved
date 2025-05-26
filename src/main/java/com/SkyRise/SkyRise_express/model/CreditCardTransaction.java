package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;

@Entity
public class CreditCardTransaction extends Payment {

    private String nameOnCard;
    private String bankName;

    // Getters and Setters

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
} 