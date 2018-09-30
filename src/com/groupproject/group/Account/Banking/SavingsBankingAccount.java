package com.groupproject.group.Account.Banking;

public class SavingsBankingAccount extends BankingAccount {
    private double balance;

    public SavingsBankingAccount(){
        super("Savings BankingAccount", 1); // calls the super-class's default constructor
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
