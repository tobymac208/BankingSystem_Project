package com.groupproject.group;

public class SavingsAccount extends Account{
    private double balance;

    public SavingsAccount(){
        super(); // calls the super-class's default constructor
        super.setId(1);
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
