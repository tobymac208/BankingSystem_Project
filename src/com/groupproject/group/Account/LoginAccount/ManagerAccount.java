package com.groupproject.group.Account.LoginAccount;

import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;

import java.io.Serializable;

public class ManagerAccount extends LoginAccount implements Serializable {
    private UserAccountList userAccounts;

    public ManagerAccount(String firstName, String lastName, int age, String username, String password){
        // call the base class constructor, passing in all arguments
        super(firstName, lastName, age, username, password);
    }

    /** Returns a UserAccountList */
    public UserAccountList getUserAccounts() {return userAccounts;}

    /** Add a user */
    public void addUser(UserAccount account){
        this.userAccounts.add(account);
    }
    /** Remove a user */
    public boolean removeUser(UserAccount account){
        // return the result of the call to removeUserAccount
        return userAccounts.removeUserAccount(account);
    }
}
