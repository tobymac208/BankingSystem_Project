package com.groupproject.group.Account.LoginAccount.UserAccount;

import java.io.Serializable;

public class Transaction implements Serializable {
    private static int id = 0;
    private String description;

    public Transaction(String description){
        this.description = description;
        id++; // increment id
    }

    public static int getId(){return id;}

    // get description
    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}

    /** Checks if two transactions are equal, based on their elements.
     * Must be of type Transaction */
    public boolean equals(Object otherObj){
        // NOT a transaction
        if(!(otherObj instanceof Transaction)){
           return false; // not equal
        }

        Transaction otherTransaction = (Transaction)otherObj;

        // Are the descriptions equal?
        return (otherTransaction.getDescription().equals(description));
    }
}
