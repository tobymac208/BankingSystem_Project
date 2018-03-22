package com.groupproject.group;

public class CheckingAccount extends Account {
    private double balance;

    public CheckingAccount(){
        super(); // calls the super-class's default constructor
        super.setId(2);
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
