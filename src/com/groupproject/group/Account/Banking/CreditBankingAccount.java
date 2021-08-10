package com.groupproject.group.Account.Banking;

public class CreditBankingAccount extends BankingAccount {
    // private double limit;
    private double amountLeft;
    private double oustandingBalance; // variable for tracking how much the user has used
    // private double interestRate;

    public CreditBankingAccount(){
        super("Credit BankingAccount", 3); // calls the super-class's default constructor -- passing in default values
        // limit = 6500;
        amountLeft = 6500;
    }

    // getters & setters
    // amount left
    public double getAmountLeft(){return amountLeft;}
    public void setAmountLeft(double amount){amountLeft -= amount;}
    // outstanding balance
    public double getOustandingBalance(){return oustandingBalance;}

    /** Allows the user to pay off a portion/all of their balance */
    public boolean payAmount(double amount){
        if(amount <= oustandingBalance){
            oustandingBalance -= amount; // remove that amount from the outstanding balance
            return true;
        }
        return false;
    }
}
