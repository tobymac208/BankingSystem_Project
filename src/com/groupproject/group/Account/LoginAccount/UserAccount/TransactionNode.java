package com.groupproject.group.Account.LoginAccount.UserAccount;

import java.io.Serializable;

public class TransactionNode implements Serializable {
    private Transaction data;
    private TransactionNode link;

    public TransactionNode(Transaction data, TransactionNode link){
        this.data = data;
        this.link = link;
    }

    // Getters and setters
    public void setData(Transaction data){this.data = data;}
    public Transaction getData(){return data;}
    public void setLink(TransactionNode link){this.link = link;}
    public TransactionNode getLink(){return link;}

    /** Checks if two objects are the same, according to their data.
     * Post-condition: Returns false if the objects aren't UserAccountNodes. Returns true or false if they are the same type.  */
    public boolean equals(Object otherObj){
        if(!(otherObj instanceof TransactionNode)) {
            return false;
        }
        TransactionNode otherNode = (TransactionNode) otherObj;
        // Return a compound equality statement. Ensuring both pieces of data are have the same contents.
        return (data.equals(otherNode.getData()) && link.getData().equals(otherNode.getLink().getData()));
    }
}
