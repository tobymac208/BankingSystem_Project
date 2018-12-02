package com.groupproject.group.Account.LoginAccount;

import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;

public class ManagerAccount extends LoginAccount {
    private UserAccountList userAccounts;

    public ManagerAccount(String firstName, String lastName, int age, String username, String password){
        // call the base class constructor, passing in all arguments
        super(firstName, lastName, age, username, password);
        userAccounts = new UserAccountList();
    }

    /** Returns a UserAccountList */
    public UserAccountList getUserAccounts() {
        return userAccounts;
    }

    /** Add a user, given a user account object. */
    public boolean addUser(UserAccount account){
        return userAccounts.add(account);
    }
    /** Returns an account if it exists, by username, in the manager account's list of UserAccount objects.
     * Pre-condition: Pass in a valid string.
     * Post-condition: Get a valid account returned.
     * Throws: May return a null object that will result in a NullPointerException. Be careful about that.*/
    public UserAccount findByUsername(String target){
        return userAccounts.findByUsername(target);
    }
    /** Remove a user */
    public boolean removeUser(UserAccount account){
        // return the result of the call to removeAccount
        return userAccounts.removeAccount(account);
    }

    /** Prints a styled string of every transaction for every UserAccount */
    public void printAllTransactions(){
        // check if userAccounts has anything in it
        if(!(userAccounts.getSize() > 0)){
            System.out.println("No accounts to display."); // it doesn't, so tell the user that.
            return;
        }
        // Go through the list of users
        for(UserAccount account : userAccounts.getUsers()){
            if(account != null){ // some may be empty UserAccount objects
                System.out.println(account.getUsername());
                account.getTransactionList().printTransactions();
            }
        }
    }

    /** Print a certain user's transactions. */
    public void printTransactionsByUsername(String target){
        UserAccount userAccount = userAccounts.findByUsername(target);
        if(userAccount == null){ // user account doesn't exist.
            System.out.println("That username doesn't exist.");
            return; // simple leave the method
        }

        // Print the user's transactions
        System.out.println("Transactions, for user " + "\"" + userAccount.getUsername() + "\":");
         // print out their transactions
        userAccount.getTransactionList().printTransactions();
    }
}
