package com.groupproject.group.Account.Banking;

/** Abstract class for making other BankingAccount classes */
public abstract class BankingAccount {
    private int id; // an id to identify which account type it is
    private String account_name; // name of the account

    // All-arg constructor
    public BankingAccount(String account_name, int id){
        this.account_name = account_name;
        this.id = id;
    }

    // getters and setters
    public String getAccount_name(){return account_name;}
    public void setAccount_name(String account_name){this.account_name = account_name;}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
}
