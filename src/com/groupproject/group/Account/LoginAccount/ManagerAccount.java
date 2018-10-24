package com.groupproject.group.Account.LoginAccount;

public class ManagerAccount extends LoginAccount {
    private UserAccountList userAccounts;

    public ManagerAccount(String firstName, String lastName, int age, String username, String password){
        // call the base class constructor, passing in all arguments
        super(firstName, lastName, age, username, password);
    }
    /** Add a user */
    public void addUser(UserAccount account){
        userAccounts.addAccount(account);
    }
    /** Remove a user */
    public void removeUser(UserAccount account){
        // TODO: Implement removeUser() in ManagerAccount
    }
}
