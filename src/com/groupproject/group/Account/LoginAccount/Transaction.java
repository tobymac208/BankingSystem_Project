package com.groupproject.group.Account.LoginAccount;

public class Transaction {
    private static int id = 0;
    private String description;

    public Transaction(String description){
        this.description = description;
        id++; // increment id
    }

    public static int getId(){return id;}

    // desc
    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}
}
