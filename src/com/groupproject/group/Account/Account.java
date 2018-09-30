package com.groupproject.group.Account;

/** Abstract class for making other Account classes */
public abstract class Account {
    private int id;
    private String account_name;

    public Account(String account_name, int id){
        this.account_name = account_name;
        this.id = id;
    }

    // getters and setters
    public String getAccount_name(){return account_name;}
    public void setAccount_name(String account_name){this.account_name = account_name;}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
}
