package com.SkyRise.SkyRise_express.model;

import jakarta.persistence.Entity;

@Entity
public class CashTransaction extends Payment {

    private double cashTendered;
    private double changeGiven;

    // Getters and Setters

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    public double getChangeGiven() {
        return changeGiven;
    }

    public void setChangeGiven(double changeGiven) {
        this.changeGiven = changeGiven;
    }
} 