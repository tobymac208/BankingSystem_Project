package com.groupproject.group;

public class SavingsAccount extends Account{
    private double balance;

    public SavingsAccount(){
        super("Savings Account", 1); // calls the super-class's default constructor
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
