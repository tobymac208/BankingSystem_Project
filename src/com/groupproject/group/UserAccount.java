package com.groupproject.group;

import java.util.ArrayList;

public class UserAccount {
    private SavingsAccount sAccount;
    private CheckingAccount chAccount;
    private CreditAccount ccAccount;
    private String password;
    private static int id = 100; // auto-incremented ids
    private int current_user_id;
    private ArrayList<Transaction> transactionArrayList;
    private double balance;
    private boolean hasCreditAccount; // keeps track of if the user decided to have a credit account included

    public UserAccount(String password, boolean creditAccountFlag){
        this.password = password;
        this.balance = 0.0; // we don't set this by default, because this is the overall total of all of the accounts
        this.sAccount = new SavingsAccount();
        this.chAccount = new CheckingAccount();
        if(creditAccountFlag)                    //flag for if user wants to open up credit account when they open an account
            this.ccAccount = new CreditAccount();
        transactionArrayList = new ArrayList<>(); // initializes the list
        this.hasCreditAccount = creditAccountFlag;
        id++; // increment the id
        current_user_id = id; // sets the current id to the most recent id
    }

    // GETTERS & SETTERS
    // transactionsList
    public ArrayList<Transaction> getTransactionArrayList(){return this.transactionArrayList;}
    // username
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
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
    // id
    public int getId(){return current_user_id;}

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
                if(amount > 0) {
                    sAccount.setBalance(sAccount.getBalance() + amount);
                    System.out.println("Deposit Successful\nNew savings account balance is: " + sAccount.getBalance());
                    //addTransaction(type, "Deposit, Amount deposited: " + amount + ", Total in account: " + sAccount.getBalance());
                    transactionArrayList.add(new Transaction("Deposited " + amount + " into your savings account. Current savings account balance is: " + sAccount.getBalance()));
                } else System.out.println("Please select an amount greater than 0.");
                break;
            case 2:
                if(amount > 0) {
                    chAccount.setBalance(chAccount.getBalance() + amount);
                    System.out.println("Deposit Successful\nNew checking account balance is: " + chAccount.getBalance());
                    transactionArrayList.add(new Transaction("Deposited " + amount + " into your checking account. Current checking account balance is: " + chAccount.getBalance()));
                } else System.out.println("Please select an amount greater than 0.");
                break;
            default:
                System.out.println("Please select a valid account to deposit to.");
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
        boolean balanceFlag;
        switch (type) {
            case 1://savings account withdrawal
                balanceFlag = checkFunds(type, amount);
                if(balanceFlag){
                    sAccount.setBalance(sAccount.getBalance() - amount);
                    System.out.println("Withdrawal Successful\nRemaining savings account balance is: " + sAccount.getBalance());
                    transactionArrayList.add(new Transaction("Withdrew " + amount + " from savings account. Current balance is: " + sAccount.getBalance()));
                }else
                    System.out.println("Insufficient Funds");
                break;
            case 2://checking account withdrawal
                balanceFlag = checkFunds(type, amount);
                if(balanceFlag){
                    chAccount.setBalance(chAccount.getBalance() - amount);
                    System.out.println("Withdrawal Successful\nRemaining checking account balance is: " + chAccount.getBalance());
                    transactionArrayList.add(new Transaction("Withdrew " + amount + " from checking account. Current balance is: " + chAccount.getBalance()));
                }else
                    System.out.println("Insufficient Funds");
                break;
            default://incorrect input
                System.out.println("Please enter either a 1 for Savings account or 2 for Checking Account, to withdraw from.");
                return false;
        }
        return true;
    }


    /** Allows a user to transfer money between accounts */
    public boolean transferBetweenAccounts(int fromAccount, int toAccount, double amount){
        boolean balanceFlag = checkFunds(fromAccount, amount);
        switch(fromAccount){
            case 1:                                                     //transferring money from savings
                if (balanceFlag){
                    sAccount.setBalance(sAccount.getBalance() - amount);
                    if (toAccount == 2) {
                        chAccount.setBalance(chAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining savings account balance is: " + sAccount.getBalance() +
                                "\nNew checking account balance is: " + chAccount.getBalance());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from savings account to checking account." +
                                "Current savings account balance is: " + sAccount.getBalance() + "Current checking account balance is: " + chAccount.getBalance()));
                    } else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                        chAccount.setBalance(chAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining savings account balance is: " + sAccount.getBalance() +
                                "\nAvailable credit: " + ccAccount.getAmountLeft());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from savings account to credit account." +
                                "Current savings account balance is: " + sAccount.getBalance() + "Current available credit is: " + ccAccount.getAmountLeft()));
                    } else {
                        System.out.println("Please choose a valid account to transfer to.");
                    }
                }
                else {
                    System.out.println("Please check your information and input a valid amount to transfer.");
                }
                break;
            case 2:                                                 //transferring from checking account
                if (balanceFlag){
                    chAccount.setBalance(chAccount.getBalance() - amount);
                    if (toAccount == 1) {
                        sAccount.setBalance(sAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining checking account balance is: " + chAccount.getBalance() +
                                "\nNew savings account balance is: " + sAccount.getBalance());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from checking account to savings account." +
                                "Current checking account balance is: " + chAccount.getBalance() + "Current savings account balance is: " + sAccount.getBalance()));
                    }
                    else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                        System.out.println("Transfer Successful\nRemaining checking account balance is: " + chAccount.getBalance() +
                                "\nAvailable Credit: " + ccAccount.getAmountLeft());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from checking account to credit account." +
                                "Current checking account balance is: " + chAccount.getBalance() + "Current credit account balance is: " + ccAccount.getAmountLeft()));
                    }
                    else{
                        System.out.println("Please choose a valid account to transfer to.");
                    }
                }
                else {
                    System.out.println("Please check your information and input a valid amount to transfer.");
                }
                break;
            case 3:
                System.out.println("Cannot transfer from Credit Account.");
        }
        return true;
    }

    /*checks to make sure the amount being taken from an account is not greater than the amount in the account.
     **returns true if the amount to be withdrawn is ok  */
    public boolean checkFunds(int accountID, double amount){
        switch(accountID){
            case 1://checks amount to savings account amount
                if(amount <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    return false;
                }else if(amount > sAccount.getBalance()) {
                    return false;
                }
                else return true;
            case 2://checks amount to checking account amount
                if(amount <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    return false;
                }else if(amount > chAccount.getBalance()) {
                    return false;
                }
                else return true;
            default://incorrect input
                System.out.println("Please enter either a 1 for Savings account or 2 for Checking Account.");
                return false;
        }
    }

    //Displays the current amount of the chosen account
    public void displayAccountBalance(int type){
        if(type == 1)
            System.out.println("Savings balance: $" + sAccount.getBalance());
        else if(type == 2)
            System.out.println("Checking balance: $" + chAccount.getBalance());
        else if(type == 3)
            System.out.println("Checking balance: $" + ccAccount.getAmountLeft());
    }
}
