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
    public UserAccountList getUserAccounts() {return userAccounts;}

    /** Add a user */
    public void addUser(UserAccount account){
        userAccounts.add(account);
    }
    public String findUser(String target){
        userAccounts.findByUsername(target);
        return "User Found";
    }
    /** Remove a user */
    public boolean removeUser(UserAccount account){
        // return the result of the call to removeAccount
        return userAccounts.removeAccount(account);
    }
}
