package com.groupproject.group;

public class CreditAccount extends Account {
    private double limit;
    private double amountLeft;
    private double oustandingBalance; // variable for tracking how much the user has used
    private double interestRate;
    // TODO: Add in Date variable and functionality

    public CreditAccount(){
        super(); // calls the super-class's default constructor
        super.setId(3);
        limit = 6500;
        amountLeft = 6500;
    }

    public void setAmountLeft(double amount){
        amountLeft -= amount;
    }

    /** Allows the user to pay off a portion/all of their balance */
    public boolean paymentAmount(double amount){
        return false;
    }
}
