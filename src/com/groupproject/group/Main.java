package com.groupproject.group;

import com.groupproject.group.Account.Banking.CreditBankingAccount;
import com.groupproject.group.Account.LoginAccount.Transaction;
import com.groupproject.group.Account.LoginAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccountList;
import com.groupproject.group.Utility.FileOps;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Authors: Nik F, Joe K, Mike H
 * Description: A simple banking system.
 *              Allows a manager to manage accounts, and users to use their accounts.
 * Due date:
 */

public class Main {
    // private static UserAccount account = new UserAccount("Bobs BankingAccount",false); // account name and balance
    private static UserAccount currentAccountOpen; // used for holding the current account's info
    private static UserAccountList accountList = new UserAccountList();
    // scanner to read in data from the user
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<UserAccount> readInList = FileOps.readFromFile(); // read in the items
        accountList.setAccountsList(readInList); // populates all of the fields in accountsList object

        // make sure to add acctNum to some sort of list or something that we can test.
        // same with passwords
        // accountList.addAccount(account);
        String stringChoice;
        int choice;
        System.out.println("==============================================");
        System.out.println("---------------- BANK PORTAL -----------------");
        System.out.println("==============================================");

        System.out.println("Type 'Login' or type 'register' to register: ");
        stringChoice = input.nextLine();

        if(stringChoice.toUpperCase().equals("LOGIN")) {
            login();
        } else if (stringChoice.toUpperCase().equals("REGISTER")) {
            currentAccountOpen = addAccount(); // adds an account
        }else{
            System.out.println("You didn't choose 'Register' or 'Login'. Program closing.");
            System.exit(0); // kill the program
        }

        // MENU
        do {
            // print the menu, and request entry
            printMenu();
            System.out.println("SELECT A MENU OPTION");
            try{
                choice = input.nextInt();
            }catch(InputMismatchException e){
                choice = 0; // 0 is not a valid option, so it will force them to go through the default option of the
                            // switch statement
            }

            switch (choice) {
                case 1: // DEPOSIT
                    double amount;
                    double type;

                    System.out.print("Please enter account type to deposit to(1:Savings 2:Checking 5:Cancel): ");
                    type = input.nextDouble();
                    // input validation code
                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = input.nextDouble();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(type);
                    System.out.print("Please enter the amount to deposit: ");
                    amount = input.nextDouble();
                    if(type == 1 || type == 2)
                        currentAccountOpen.deposit(type, amount);
                    break;
                case 2: // WITHDRAWAL
                    System.out.println("WITHDRAW");
                    System.out.println("Please enter account type to withdraw from(1:Savings 2:Checking 5:Cancel): ");
                    type = input.nextInt();


                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = input.nextInt();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(type);
                    System.out.println("Please enter the amount to withdraw: ");
                    amount = input.nextDouble();
                    if(type == 1 || type == 2)
                        currentAccountOpen.withdraw(type, amount);
                    break;
                case 3: // TRANSFER
                    System.out.println("TRANSFER");

                    System.out.println("Please enter account to transfer from(1:Savings 2:Checking 5:Cancel): ");
                    double fromType = input.nextDouble();
                    //input validation
                    while(fromType != 1 && fromType != 2 && fromType != 5) {
                        System.out.println("Invalid option selected");
                        System.out.println("Please enter the account to transfer From (1:Savings 2:Checking 5:Cancel): ");
                        fromType = input.nextDouble();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(fromType);
                    System.out.println("Please enter the amount to transfer: ");
                    amount = input.nextDouble();
                    System.out.println("Please enter account to transfer to(1:Savings 2:Checking 3:Credit 5:Cancel): ");
                    double toType = input.nextDouble();
                    while(toType != 1 && toType != 2 && toType != 3 && toType != 5 || (toType == 3 && currentAccountOpen.getCcAccount() == null)) {
                        if(currentAccountOpen.isCreditAccount()) {
                            System.out.println("Please enter an account to transfer to(1:Savings 2:Checking 3:Credit 5:Cancel): ");
                            toType = input.nextInt();
                        } else{
                            System.out.println("You do not have a Credit account, please choose from the following options(1:Savings 2:Checking 5:Cancel): ");
                            toType = input.nextInt();
                        }
                    }
                    if((fromType == 1 || fromType == 2) && (toType == 1 || toType == 2 || toType == 3))
                        currentAccountOpen.transferBetweenAccounts(fromType, toType, amount);
                    break;
                case 4:
                    String junkLine = input.nextLine();
                    addAccount(); // adds an account
                    break;
                case 5:
                    login();
                    break;
                case 6:
                    if (currentAccountOpen.isCreditAccount()){
                        System.out.println("You already have a credit account open");
                    }else {
                        currentAccountOpen.setCreditFlag(true);
                        currentAccountOpen.setCcAccount(new CreditBankingAccount());
                        System.out.println("Your new credit account is now open");
                    }
                    break;
                case 7:
                    System.out.println("--------- BankingAccount Balance -----------");
                    currentAccountOpen.displayAccountBalance();
                    System.out.println("-------------------------------------");
                case 8:
                    System.out.println("(8)Exit");
                    break;
                default: // chose an incorrect option
                    System.out.println("\"" + choice + "\" was not an option. Returning to menu.");
                    break;
            }
        }while(choice != 8);

        // write the information to the files
        FileOps.writeToFile(accountList); // print out the current user information
        FileOps.writeTransactionsToFile(accountList); // print out the transactions to the transactions file
        System.out.println("Program exiting.");
    }

    private static void login() {
        System.out.print("Please enter your id number: ");
        int idNum = input.nextInt();

        // adding and checking for if the account exists
        boolean search = false;
        while(!search) { //Loop to make sure an existing ID is input with matching password
            //Takes in the extra line
            String junkLine = input.nextLine();
            UserAccount account = accountList.findById(idNum);
            if (account != null) {
                System.out.print("Please enter your password: ");

                String password = input.nextLine();
                if (account.getPassword().equals(password)) {
                    search = true;
                    currentAccountOpen = account;
                } else{
                    System.out.println("Password and Id did not match");
                    System.out.println("Press 0 to exit or 1 to try again");
                    int tryAgain = input.nextInt();
                    if(tryAgain == 0)
                        System.exit(0);
                }

            } else {
                System.out.println("Could not find account, please check to make sure ID entered is correct.");
                System.out.print("Please enter your id number: ");
                idNum = input.nextInt();
            }
        }
    }

    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println(); // prints a space

        System.out.println("---------------MENU---------------");
        System.out.println("----------------------------------");
        System.out.println("Logged into account #" + currentAccountOpen.getId());
        System.out.println("----------------------------------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer Money Between Accounts");
        System.out.println("4. Register New BankingAccount");
        System.out.println("5. Switch Accounts");
        System.out.println("6. Open a Credit BankingAccount");
        System.out.println("7. Display BankingAccount Balances");
        System.out.println("8. Exit/Logout");
        System.out.println("----------------------------------");
    }

    /** method that allows a user to create a new account */
    public static UserAccount addAccount(){
        UserAccount account = null;
        String password;
        int ans;
        System.out.println("*****************************");
        System.out.println("CREATE NEW ACCOUNT");
        System.out.print("Please Enter BankingAccount Password: ");
        password = input.nextLine();

        System.out.println("Did you want to add a credit account? (1) for yes (2) for no ");
        ans = input.nextInt();
        if(ans == 1) { // this did choose a credit account
            // TODO: Implement add account to take a username, password, and age
            account = new UserAccount(null, null, 0, password, password, true);
            // add more access to the account set-up like an initial deposit amount or things of that nature.
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New BankingAccount Created (with Credit). Your ID is " + account.getId());
        }else if(ans == 2) { // they did not choose a credit account
            // TODO: Implement add account to take a username, password, and age
            account = new UserAccount(null, null, 0, null, password, false);
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New account created (without Credit). Your ID is: " + account.getId());
        }
        return account;
    }
}
