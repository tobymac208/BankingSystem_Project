package com.groupproject.group;

import java.util.ArrayList;

public class UserAccount {
    private SavingsAccount sAccount;
    private CheckingAccount chAccount;
    private CreditAccount ccAccount;
    private String name;
    private ArrayList<Transaction> transactionArrayList;
    private double balance;
    private boolean hasCreditAccount; // keeps track of if the user decided to have a credit account included

    public UserAccount(String name, boolean creditAccountFlag){
        this.name = name;
        this.balance = 0.0; // we don't set this by default, because this is the overall total of all of the accounts
        this.sAccount = new SavingsAccount();
        this.chAccount = new CheckingAccount();
        if(creditAccountFlag)                    //flag for if user wants to open up credit account when they open an account
            this.ccAccount = new CreditAccount();
        transactionArrayList = new ArrayList<>(); // initializes the list
        this.hasCreditAccount = creditAccountFlag;
    }

    // GETTERS & SETTERS
    // transactionsList
    public ArrayList<Transaction> getTransactionArrayList(){return this.transactionArrayList;}
    // name
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    // savings account
    public SavingsAccount getsAccount(){return sAccount;}
    public void setsAccount(SavingsAccount sAccount){this.sAccount = sAccount;}
    // checking account
    public CheckingAccount getchAccount(){return chAccount;}
    public void setchAccount(CheckingAccount chAccount){this.chAccount = chAccount;}
    // credit account
    public CreditAccount getCcAccount(){return ccAccount;}
    public void setChAccount(CreditAccount ccAccount){this.ccAccount = ccAccount;}
    // has credit account
    public boolean isCreditAccount(){return hasCreditAccount;}
    // balance
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}

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
    // same style as above. match the account types to withdraw from.
    public boolean withdraw(int type, double amount) {
        switch (type) {
            case 1:
                System.out.println("Savings amount: $" + sAccount.getBalance());
                //TODO create helper method
                //boolean balanceFlag = helperMethod
                //if(balanceFlag){
                sAccount.setBalance(sAccount.getBalance() - amount);
                System.out.println("Withdrawal Succesful");
                transactionArrayList.add(new Transaction("Withdrew " + amount + " from savings account. Current balance is: " + sAccount.getBalance()));
                //}else
                //System.out.println("Insufficient Funds");
                break;
            case 2:
                //TODO create helper method
                //boolean balanceFlag = helperMethod
                //if(balanceFlag){
                chAccount.setBalance(chAccount.getBalance() - amount);
                System.out.println("Checking Account Withdrawl");
                //}else
                //System.out.println("Insufficient Funds");
                break;
        }
        return true;
    }


    /** Allows a user to transfer money between accounts */
    public boolean transferBetweenAccounts(int fromAccount, int toAccount, double amount){
        switch(fromAccount){
            case 1:                                                     //transferring money from savings
                if (amount > sAccount.getBalance()){
                    System.out.println("Insufficient funds");
                }
                else {
                    sAccount.setBalance(sAccount.getBalance() - amount);
                    if (toAccount == 2) {                                  //input validation for the from account
                        chAccount.setBalance(chAccount.getBalance() + amount);
                    } else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                    } else {
                        System.out.println("Please choose a valid account to transfer to.");
                    }
                }
                break;
            case 2:
                if (amount > chAccount.getBalance()){
                    System.out.println("Insufficient funds");
                }
                else {
                    chAccount.setBalance(chAccount.getBalance() - amount);
                    if (toAccount == 1) {                                  //input validation for the from account
                        sAccount.setBalance(sAccount.getBalance() + amount);
                    }
                    else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                    }
                    else{
                        System.out.println("Please choose a valid account to transfer to.");
                    }
                }
                break;
            case 3:
                System.out.println("Cannot transfer from Credit Account.");
        }
        return true;
    }

    //TODO finish helperMethod
}
