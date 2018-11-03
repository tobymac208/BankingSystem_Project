package com.groupproject.group.Account.LoginAccount;

import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;

public class ManagerAccount extends LoginAccount {
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
    public void removeUser(UserAccount account){
        // TODO: Implement removeUser() in ManagerAccount
    }
}
