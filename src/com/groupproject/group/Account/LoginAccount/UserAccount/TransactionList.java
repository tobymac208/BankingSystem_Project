package com.groupproject.group.Account.LoginAccount.UserAccount;

import java.io.Serializable;

public class TransactionList implements Serializable {
    private TransactionNode head;
    private TransactionNode tail;
    private int numberOfTransactions;

    /** No-arg constructor */
    public TransactionList(){
        this.head = null;
        this.tail = null;
        this.numberOfTransactions = 0;
    }

    /** Returns the amount of transactions in the list. */
    public int getNumberOfTransactions(){return numberOfTransactions;}

    /** Add a new Transaction to the list. */
    public void addTransaction(Transaction transaction){
        TransactionNode node = new TransactionNode(transaction, null);
        // set this node's next account as the first item in the list
        node.setLink(head);
        // set the node as the head of the list
        head = node;
        if(numberOfTransactions == 0){
            tail = head; // the head and tail are the same thing
        }
        numberOfTransactions++; // add 1 to the number of transactions
    }

    /** Removes the account specified, if it was found. */
    public boolean removeTransaction(Transaction target) {
        boolean answer = false;
        for (TransactionNode cursor = head, precursor = null; cursor != null; precursor = cursor, cursor = cursor.getLink()) {
            // check for if the first element is the head
            if (target.equals(cursor.getData()) && cursor == head) {
                if (numberOfTransactions == 1) {
                    head = tail = null; // empty the head and the tail
                } else {
                    head = head.getLink(); // erase the first element, and move the last element to the head
                }
                numberOfTransactions--; // subtracts one from the number of accounts
                answer = true;
            } else if (target.equals(cursor.getData()) && cursor == tail && precursor != null) { // we are trying to removeAccount the tail of the list
                if (numberOfTransactions == 1) {
                    head = tail = null; // empty head and tail
                } else {
                    precursor.setLink(null); // removeAccount this course from the link of its previous node
                    tail = precursor;
                }
                numberOfTransactions--; // subtracts one from the number of accounts
                answer = true;
            } else if (target.equals(target) && precursor != null) { // found the item, but it's not at the beginning or end of the list
                precursor.setLink(cursor.getLink());
                numberOfTransactions--; // subtracts one from the number of accounts
                answer = true;
            }
        }
        return answer;
    }

    /** Prints out a styled list of al transactions. */
    public void printTransactions(){
        String stringToPrint = "";
        for(TransactionNode curretNode = head; curretNode != null; curretNode = curretNode.getLink()){
            stringToPrint += "\"" + curretNode.getData().getDescription() + "\"";
            if(curretNode.getLink() != null){
                stringToPrint += "->";
            }
        }

        System.out.println(stringToPrint);
    }
}
