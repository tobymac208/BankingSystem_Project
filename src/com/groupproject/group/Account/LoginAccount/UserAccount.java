package com.groupproject.group.Account.LoginAccount;

import com.groupproject.group.Account.Banking.CheckingBankingAccount;
import com.groupproject.group.Account.Banking.CreditBankingAccount;
import com.groupproject.group.Account.Banking.SavingsBankingAccount;

import java.util.ArrayList;

public class UserAccount extends LoginAccount {
    private SavingsBankingAccount savingsAccount;
    private CheckingBankingAccount checkingAccount;
    private CreditBankingAccount ccAccount;
    private static int id = 100; // auto-incremented id
    private int current_user_id; // holds the id for the current user
    private ArrayList<Transaction> transactionArrayList;
    private double balance;
    private boolean hasCreditAccount; // keeps track of if the user decided to have a credit account included

    private static final int SAVINGS_ID = 1;
    private static final int CHECKING_ID = 2;
    private static final int CREDIT_ID = 3;

    public UserAccount(String firstName, String lastName, int age, String username, String password, boolean creditAccountFlag){
        // Call super class constructor
        super(firstName, lastName, age, username, password);
        this.balance = 0.0; // we don't set this by default, because this is the overall total of all of the accounts
        this.savingsAccount = new SavingsBankingAccount();
        this.checkingAccount = new CheckingBankingAccount();
        if(creditAccountFlag) //flag for if user wants to open up credit account when they open an account
            this.ccAccount = new CreditBankingAccount(); // otherwise, this is left null
        transactionArrayList = new ArrayList<>(); // initializes the list
        this.hasCreditAccount = creditAccountFlag;
        id++; // increment the id
        current_user_id = id; // sets the current id to the most recent id
    }

    // GETTERS & SETTERS
    // transactionsList
    public ArrayList<Transaction> getTransactionArrayList(){return this.transactionArrayList;}
    // savings account
    public SavingsBankingAccount getSavingsAccount(){return savingsAccount;}
    public void setSavingsAccount(SavingsBankingAccount savingsAccount){this.savingsAccount = savingsAccount;}
    // checking account
    public CheckingBankingAccount getchAccount(){return checkingAccount;}
    public void setchAccount(CheckingBankingAccount chAccount){this.checkingAccount = chAccount;}
    // credit account
    public CreditBankingAccount getCcAccount(){return ccAccount;}
    public void setCcAccount(CreditBankingAccount ccAccount){this.ccAccount = ccAccount;}
    // has credit account
    public boolean isCreditAccount(){return hasCreditAccount;}
    public void setCreditFlag(boolean creditFlag){this.hasCreditAccount = creditFlag; }
    // balance
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
    // id
    public int getId(){return current_user_id;}

    /** Helper method - adds a transaction to the ArrayList of transaction items */
    private void addTransaction(int type, String description){
        switch (type){
            case SAVINGS_ID:
                transactionArrayList.add(new Transaction(description + ", to Savings Account"));
                break;
            case CHECKING_ID:
                transactionArrayList.add(new Transaction(description + ", to Checking Account"));
                break;
            case CREDIT_ID:
                transactionArrayList.add(new Transaction(description + ", to Credit Account"));
                break;
        }
    }

    /** Allows a user to deposit money into an account */
    public boolean deposit(double type, double amount){
        switch((int)type){
            case 1:
                if(amount > 0) {
                    savingsAccount.setBalance(savingsAccount.getBalance() + amount);
                    System.out.println("Deposit Successful\nNew savings account balance is: " + savingsAccount.getBalance());
                    //addTransaction(type, "Deposit, Amount deposited: " + amount + ", Total in account: " + savingsAccount.getBalance());
                    transactionArrayList.add(new Transaction("Deposited " + amount + " into your savings account. Current savings account balance is: " + savingsAccount.getBalance()));
                } else System.out.println("Please select an amount greater than 0.");
                break;
            case 2:
                if(amount > 0) {
                    checkingAccount.setBalance(checkingAccount.getBalance() + amount);
                    System.out.println("Deposit Successful\nNew checking account balance is: " + checkingAccount.getBalance());
                    transactionArrayList.add(new Transaction("Deposited " + amount + " into your checking account. Current checking account balance is: " + checkingAccount.getBalance()));
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
        savingsAccount.setBalance(savingsAccount.getBalance() + amount);
        return true;
    }

    /** Allows a user to withdraw money from the account chosen */
    // same style as above. match the account types to withdraw from.
    public boolean withdraw(double type, double amount) {
        boolean balanceFlag;
        switch ((int)type) {
            case 1://savings account withdrawal
                balanceFlag = checkFunds(type, amount);
                if(balanceFlag){
                    savingsAccount.setBalance(savingsAccount.getBalance() - amount);
                    System.out.println("Withdrawal Successful\nRemaining savings account balance is: " + savingsAccount.getBalance());
                    transactionArrayList.add(new Transaction("Withdrew " + amount + " from savings account. Current balance is: " + savingsAccount.getBalance()));
                }else
                    System.out.println("Insufficient Funds");
                break;
            case 2://checking account withdrawal
                balanceFlag = checkFunds(type, amount);
                if(balanceFlag){
                    checkingAccount.setBalance(checkingAccount.getBalance() - amount);
                    System.out.println("Withdrawal Successful\nRemaining checking account balance is: " + checkingAccount.getBalance());
                    transactionArrayList.add(new Transaction("Withdrew " + amount + " from checking account. Current balance is: " + checkingAccount.getBalance()));
                }else
                    System.out.println("Insufficient Funds");
                break;
            default://incorrect input
                System.out.println("Please enter either a 1 for Savings account or 2 for Checking BankingAccount, to withdraw from.");
                return false;
        }
        return true;
    }


    /** Allows a user to transfer money between accounts */
    public boolean transferBetweenAccounts(double fromAccount, double toAccount, double amount){
        boolean balanceFlag = checkFunds(fromAccount, amount);
        switch((int)fromAccount){
            case 1:                                                     //transferring money from savings
                if (balanceFlag){
                    savingsAccount.setBalance(savingsAccount.getBalance() - amount);
                    if (toAccount == 2) {
                        checkingAccount.setBalance(checkingAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining savings account balance is: " + savingsAccount.getBalance() +
                                "\nNew checking account balance is: " + checkingAccount.getBalance());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from savings account to checking account." +
                                "Current savings account balance is: " + savingsAccount.getBalance() + "Current checking account balance is: " + checkingAccount.getBalance()));
                    } else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                        checkingAccount.setBalance(checkingAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining savings account balance is: " + savingsAccount.getBalance() +
                                "\nAvailable credit: " + ccAccount.getAmountLeft());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from savings account to credit account. " +
                                "Current savings account balance is: " + savingsAccount.getBalance() + ". Current available credit is: " + ccAccount.getAmountLeft()));
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
                    checkingAccount.setBalance(checkingAccount.getBalance() - amount);
                    if (toAccount == 1) {
                        savingsAccount.setBalance(savingsAccount.getBalance() + amount);
                        System.out.println("Transfer Successful\nRemaining checking account balance is: " + checkingAccount.getBalance() +
                                "\nNew savings account balance is: " + savingsAccount.getBalance());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from checking account to savings account. " +
                                "Current checking account balance is: " + checkingAccount.getBalance() + ". Current savings account balance is: " + savingsAccount.getBalance()));
                    }
                    else if (toAccount == 3) {
                        ccAccount.setAmountLeft(-amount);
                        System.out.println("Transfer Successful\nRemaining checking account balance is: " + checkingAccount.getBalance() +
                                "\nAvailable Credit: " + ccAccount.getAmountLeft());
                        transactionArrayList.add(new Transaction("Transferred " + amount + " from checking account to credit account. " +
                                "Current checking account balance is: " + checkingAccount.getBalance() + ". Current credit account balance is: " + ccAccount.getAmountLeft()));
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
                System.out.println("Cannot transfer from Credit BankingAccount.");
        }
        return true;
    }

    /*checks to make sure the amount being taken from an account is not greater than the amount in the account.
     **returns true if the amount to be withdrawn is ok  */
    public boolean checkFunds(double accountID, double amount){
        switch((int)accountID){
            case 1://checks amount to savings account amount
                if(amount <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    return false;
                }else if(amount > savingsAccount.getBalance()) {
                    return false;
                }
                else return true;
            case 2://checks amount to checking account amount
                if(amount <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    return false;
                }else if(amount > checkingAccount.getBalance()) {
                    return false;
                }
                else return true;
            default://incorrect input
                System.out.println("Please enter either a 1 for Savings account or 2 for Checking BankingAccount.");
                return false;
        }
    }

    /** Allows the current amount, from the chosen banking account, to be displayed
     * Values must range from 1 to 3 */
    public void displayAnAccountBalance(double type){
        if(type == SAVINGS_ID)
            System.out.printf("Savings balance: $%.2f\n", savingsAccount.getBalance());
        else if(type == CHECKING_ID)
            System.out.printf("Checking balance: $%.2f\n", checkingAccount.getBalance());
        else if(type == CREDIT_ID)
            System.out.printf("Credit amount left: $%.2f\n", ccAccount.getAmountLeft());
    }

    /** Display all of the amounts from the banking accounts */
    public void displayAccountBalance(){
        System.out.printf("Savings balance: $%.2f\n", savingsAccount.getBalance());
        System.out.printf("Checking balance: $%.2f\n", checkingAccount.getBalance());
        if(hasCreditAccount)
            System.out.printf("Credit amount left: $%.2f\n", ccAccount.getAmountLeft());
    }
}
