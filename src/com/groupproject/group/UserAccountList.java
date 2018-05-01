package com.groupproject.group;

import java.util.ArrayList;

public class UserAccountList {
    private ArrayList<UserAccount> accountsList;

    public UserAccountList(){
        accountsList = new ArrayList<>();
    }

    // getter
    public ArrayList<UserAccount> getAccountsList(){return accountsList;}
    // this method should ONLY be called when reading in the file
    public void setAccountsList(ArrayList<UserAccount> accountsList){this.accountsList = accountsList;}

    /** Adds an account to the list */
    public void addAccount(UserAccount theAccount){
        accountsList.add(theAccount);
    }

    /** Finds an account by its id */
    public UserAccount findById(int id){
        for(UserAccount currAccount : accountsList){
            if(currAccount.getId() == id){
                return currAccount;
            }
        }
        return null; // didn't find an account with that id
    }
}
