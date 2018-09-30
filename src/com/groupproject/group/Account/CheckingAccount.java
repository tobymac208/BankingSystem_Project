package com.groupproject.group.Account;

public class CheckingAccount extends Account {
    private double balance;

    public CheckingAccount(){
        super("Checking Account", 2); // calls the super-class's default constructor
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
