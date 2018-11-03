package com.groupproject.group.Utility;

import com.groupproject.group.Account.LoginAccount.UserAccount.Transaction;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOps {
    /** Writes the current account information to the account_info.txt file */
    public static void writeToFile(UserAccountList accountList) {
        // TODO: Implement write-to-file method to write a username, password, and age to the file
        File file = new File("src/com/groupproject/group/Resources/account_info.txt");
        if(!file.exists()){ // check if the doesn't exist
            // if it doesn't, create it
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Exception thrown");
            }
        }
        try (PrintWriter printer = new PrintWriter(new FileOutputStream(file))) {
            for(UserAccount elem : accountList.getUsers()){
                // print out the account information. Order: account name, savings account balance, checking account value, amount left in credit, and the outstanding balance due
                if(elem.isCreditAccount()){
                    printer.println(elem.getPassword() + ", " + elem.getSavingsAccount().getBalance() +", "+ elem.getchAccount().getBalance() +", " +elem.getCcAccount().getAmountLeft());
                }else{
                    printer.println(elem.getPassword() + ", " + elem.getSavingsAccount().getBalance() +", "+ elem.getchAccount().getBalance());
                }
            }
        } catch (FileNotFoundException e) { // catches the possible exception throw by the printer object being created
            System.out.println("The file does not exist!");
        }
    }

    /** Reads information from "account_info.txt" and populates the fields for the accounts */
    // TODO: Change this to return a linked list instead, after we change UserAccountList to a linked list implementation
    public static ArrayList<UserAccount> readFromFile(){
        ArrayList<UserAccount> userAccounts = new ArrayList<>(); // populated list that is returned at the end
        // Create the file. This will help to read content from a file
        File file = new File("src/com/groupproject/group/Resources/account_info.txt");
        if(!file.exists()){ // check if the doesn't exist
            // if it doesn't, create it
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Exception thrown");
            }
        }
        // Create a Scanner object -- this will be used to do the reading
        try(Scanner fileReader = new Scanner(file)) {
            // there IS something in the file, so read from it.
            int SIZE = 4;
            while((fileReader.hasNextLine())) {
                String line = fileReader.nextLine();
                if(line == null)
                    continue; // avoids reading in an empty line
                else {
                    String temp[] = line.split(", "); // split up the string
                    String members[] = new String[SIZE];
                    //for loop to assign temp values to members without changing length
                    for (int index = 0; index < temp.length; index++) {
                        members[index] = temp[index];
                    }

                    String pass = members[0];
                    double sAcctBal = Double.parseDouble(members[1]);
                    double chAcctBal = Double.parseDouble(members[2]);
                    UserAccount account;
                    if (members[3] != null) { // if something was read in
                        double ccAcctBal = Double.parseDouble(members[3]);
                        // TODO: Implement read-from-file method to take a username, password, and age
                        account = new UserAccount(null, null, 0, null, pass, true);
                        account.getCcAccount().setAmountLeft(ccAcctBal); // sets the amount left
                    } else {
                        // TODO: Implement read-from-file method to take a username, password, and age
                        account = new UserAccount(null, null, 0, null, pass, false);
                    }

                    // populate other members
                    account.getSavingsAccount().setBalance(sAcctBal);
                    account.getchAccount().setBalance(chAcctBal);

                    // add the account to the local list
                    userAccounts.add(account); // adds the current user account
                }
            } // end of while loop
            return userAccounts; // returns the user accounts to the caller
        }catch(FileNotFoundException e){
            System.out.println("Exception Thrown!"); // debug code.
            return new ArrayList<>(); // give back an empty UserAccountList object
        }
    }

    /** Writes the current transaction list to a file to be stored */
    // TODO: Change implementation when we change UserAccountList to a linked list implementation
    public static void writeTransactionsToFile(UserAccountList accountList){
        File file = new File("src/com/groupproject/group/Resources/transactions.txt");
        if(!file.exists()){ // check if the file doesn't exist
            // if it doesn't, create it
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Exception thrown");
            }
        }
        // use a printWriter to write the information to the file
        try(FileWriter fWriter = new FileWriter(file, true)){
            BufferedWriter bw = new BufferedWriter(fWriter);
            PrintWriter writer = new PrintWriter(bw);
            for(UserAccount account : accountList.getUsers()){ // gets every account
                for(Transaction transaction : account.getTransactionArrayList()){ // gets every transaction in each account
                    writer.println("BankingAccount #" + account.getId() + ": " + transaction.getDescription()); // write each transaction's description to the file
                }
            }
            bw.close(); // close stream
            writer.close(); // close stream
        }catch (IOException e){
            System.out.println("Can't print transactions. A problem occurred.");
        }
    }

}
