package com.groupproject.group;

import java.util.ArrayList;

public class UserAccount {
    private SavingsAccount sAccount;
    private CheckingAccount chAccount;
    private CreditAccount ccAccount;
    private String name;
    private ArrayList<Transaction> transactionArrayList;
    private double balance;

    public UserAccount(String name, double balance){
        this.name = name;
        this.balance = balance;
        sAccount = new SavingsAccount();
        transactionArrayList = new ArrayList<>(); // initializes the list
    }

    // Transaction methods
    private void addTransaction(int type, String description){
        switch (type){
            case 1:
                transactionArrayList.add(new Transaction(description + ", to Saving Account"));
                break;
        }
    }

    /** Allows a user to deposit money into an account */
    public boolean deposit(int type, double amount){
        switch(type){
            case 1:
                sAccount.setBalance(sAccount.getBalance() + amount);
                sAccount.setBalance(sAccount.getBalance() + amount);
                addTransaction(type, "Deposit, Amount deposited: " + amount + ", Total in account: " + sAccount.getBalance());
                break;
            case 2:
                chAccount.setBalance(chAccount.getBalance() + amount);
                break;
            case 3:
                // TODO: Work on deposit choice
                break;
        }
        return true;
    }
    /** Deposits money into a default account */
    public boolean deposit(double amount){
        sAccount.setBalance(sAccount.getBalance() + amount);
        return true;
    }

    /** Allows a user to withdraw money from the account chosen */
    public boolean withdraw(String accountName, double amount){
        return true;
    }
    /** Allows a user to withdraw money from  */

    // GETTERS & SETTERS
    // transactionsList
    public ArrayList<Transaction> getTransactionArrayList(){return this.transactionArrayList;}
}
