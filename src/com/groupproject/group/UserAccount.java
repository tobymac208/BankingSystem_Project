package com.groupproject.group;

public class UserAccount {
    private SavingsAccount sAccount;
    private CheckingAccount chAccount;
    private CreditAccount ccAccount;

    private double balance;

    public UserAccount(){
    }

    /** Allows a user to deposit money into an account */
    public boolean deposit(int type, double amount){
        switch(type){
            case 1:
                sAccount.setBalance(sAccount.getBalance() + amount);
                break;
            case 2:
                chAccount.setBalance(chAccount.getBalance() + amount);
                break;
            case 3:

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
}
