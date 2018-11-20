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
    public void addUser(UserAccount account){
        userAccounts.add(account);
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
}
