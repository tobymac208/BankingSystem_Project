package com.groupproject.group.Account.Banking;

public class CheckingBankingAccount extends BankingAccount {
    private double balance;

    public CheckingBankingAccount(){
        super("Checking BankingAccount", 2); // calls the super-class's default constructor
        balance = 0.0;
    }

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return balance;}
}
