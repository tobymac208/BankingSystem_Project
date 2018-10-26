package com.groupproject.group.Account.LoginAccount.UserAccount;

import java.util.ArrayList;

public class UserAccountList {
    private UserAccountNode head;
    private UserAccountNode tail;
    private int numberOfAccounts;
    // private ArrayList<UserAccount> accountsList;

    public UserAccountList(){
        // accountsList = new ArrayList<>();
        this.head = null;
        this.tail = null;
        this.numberOfAccounts = 0;
    }

    // Getters and setters
    /** Note: This method should ONLY be called when reading in the file */
    public void setAccountsList(ArrayList<UserAccount> accountsList){
        for(UserAccount account : accountsList){
            // Add each item to the list
            addAccount(account);
        }
    }

    public int getNumberOfAccounts(){return numberOfAccounts;}

    /** Adds an account to the list */
    public void addAccount(UserAccount account){
        UserAccountNode node = new UserAccountNode(account, null);
        // set this node's next account as the first item in the list
        node.setLink(head);
        // set the node as the head of the list
        head = node;

        if(numberOfAccounts == 0){
            tail = head; // the head and tail are the same thing
        }

        numberOfAccounts++; // add 1 to the number of students
    }

    /** Removes the account specified, if it was found. */
    public boolean removeAccount(UserAccount target) {
        boolean answer = false;
        for (UserAccountNode cursor = head, precursor = null; cursor != null; precursor = cursor, cursor = cursor.getLink()) {
            // check for if the first element is the head
            if (target.equals(cursor.getData()) && cursor == head) {
                if (numberOfAccounts == 1) {
                    head = tail = null; // empty the head and the tail
                } else {
                    head = head.getLink(); // erase the first element, and move the last element to the head
                }
                numberOfAccounts--; // subtracts one from the number of accounts
                answer = true;
            } else if (target.equals(cursor.getData()) && cursor == tail && precursor != null) { // we are trying to remove the tail of the list
                if (numberOfAccounts == 1) {
                    head = tail = null; // empty head and tail
                } else {
                    precursor.setLink(null); // remove this course from the link of its previous node
                    tail = precursor;
                }
                numberOfAccounts--; // subtracts one from the number of accounts
                answer = true;
            } else if (target.equals(target) && precursor != null) { // found the item, but it's not at the beginning or end of the list
                precursor.setLink(cursor.getLink());
                numberOfAccounts--; // subtracts one from the number of accounts
                answer = true;
            }
        }
        return answer;
    }

    // TODO: Implement findAccountById()
    public int findAccountById(){
        return 0;
    }

//    /** Finds an account by its id */
//    public UserAccount findById(int id){
//        for(UserAccount currAccount : accountsList){ // run through each account in the list
//            if(currAccount.getId() == id){
//                return currAccount;
//            }
//        }
//        return null; // didn't find an account with that id
//    }
}
