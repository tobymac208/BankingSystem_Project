package com.groupproject.group.Account;

public class CreditAccount extends Account {
    private double limit;
    private double amountLeft;
    private double oustandingBalance; // variable for tracking how much the user has used
    private double interestRate;
    // TODO: Add in Date variable and functionality

    public CreditAccount(){
        super("Credit Account", 3); // calls the super-class's default constructor -- passing in default values
        limit = 6500;
        amountLeft = 6500;
    }

    // getters & setters
    // amount left
    public double getAmountLeft(){return amountLeft;}
    public void setAmountLeft(double amount){amountLeft -= amount;}
    // outstanding balance
    public double getOustandingBalance(){return oustandingBalance;}

    /** Allows the user to pay off a portion/all of their balance */
    public boolean paymentAmount(double amount){
        // TODO: Add in functionality
        return false;
    }
}
