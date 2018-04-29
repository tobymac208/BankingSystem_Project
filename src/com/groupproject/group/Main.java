package com.groupproject.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Changelog:
 * 04/22/2018: Updated menu
 * 04/22/2018: Began adding file operations
 * 04/23/2018: Updates to menu, UserAccount, and to file operations
 */

public class Main {
    private static UserAccount account = new UserAccount("Bobs Account",false); // account name and balance
    private static UserAccountList accountList = new UserAccountList();
    private static Scanner input = new Scanner(System.in);
    //delete this later, this is for testing


    public static void main(String[] args) {
        // writeToFile();

        // make sure to add acctNum to some sort of list or something that we can test.
        // same with passwords
        accountList.addAccount(account);
        String stringChoice;
        int choice = 0;
        System.out.println("======================");
        System.out.println("---- BANK PORTAL -----");
        System.out.println("======================");

        System.out.println("Type 'Login' or type 'register' to register: ");
        stringChoice = input.nextLine();

        if(stringChoice.equals("Login") || stringChoice.equals("login")) {
            System.out.print("Please enter your id number: ");
            int idNum = input.nextInt();


            //Takes in the extra line
            String junkLine = input.nextLine();

            // adding and checking for if the account exists
            boolean search = false;
            while(!search) {                        //Loop to make sure an existing ID is input with matching password
                UserAccount account = accountList.findById(idNum);
                if (account != null) {
                    System.out.print("Please enter your password: ");

                    String password = input.nextLine();
                    if (account.getPassword().equals(password)) {
                        search = true;
                    } else{ System.out.println("Password and Id did not match");
                    }

                } else {
                    System.out.println("Could not find account, please check to make sure ID entered is correct.");
                    System.out.print("Please enter your id number: ");
                    idNum = input.nextInt();
                }
            }
        } else if (stringChoice.equals("Register") || stringChoice.equals("register")) {
                addAccount(); // adds an account
        }

        printMenu();
        System.out.println("SELECT A MENU OPTION");
        choice = input.nextInt();


        // MENU
        do {
            switch (choice) {
                case 1:
                    double amount;
                    int type;

                    System.out.print("Please enter account type to deposit to(1:Savings 2:Checking 5:Cancel): ");
                    type = input.nextInt();
                    //Display amount currently in chosen account
                    account.displayAccountBalance(type);
                    System.out.print("Please enter the amount to deposit: ");
                    amount = input.nextDouble();
                    // input validation code
                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = input.nextInt();
                    }
                    if(type == 1 || type == 2)
                        account.deposit(type, amount);

                    // print the menu and request input
                    printMenu();
                    choice = input.nextInt();
                    break;
                case 2:
                    System.out.println("WITHDRAW");
                    System.out.println("Please enter account type to withdraw from(1:Savings 2:Checking 5:Cancel): ");
                    type = input.nextInt();
                    //Display amount currently in chosen account
                    account.displayAccountBalance(type);
                    System.out.println("Please enter the amount to withdraw: ");
                    amount = input.nextDouble();

                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = input.nextInt();
                    }
                    if(type == 1 || type == 2)
                        account.withdraw(type, amount);

                    // print the menu and request input
                    printMenu();
                    choice = input.nextInt();
                    break;
                case 3:
                    System.out.println("TRANSFER");

                    System.out.println("Please enter account to transfer from(1:Savings 2:Checking 5:Cancel): ");
                    int fromType = input.nextInt();
                    //Display amount currently in chosen account
                    account.displayAccountBalance(fromType);
                    System.out.println("Please enter the amount to transfer: ");
                    amount = input.nextDouble();
                    System.out.println("Please enter account to transfer to(1:Savings 2:Checking 3: Credit 5:Cancel): ");
                    int toType = input.nextInt();

                    //input validation
                    while(fromType != 1 && fromType != 2 && fromType != 5 && toType != 1 && toType != 2 && toType != 3 && toType != 5 ){
                        System.out.println("Invalid option selected");
                        System.out.println("Please enter the account to transfer From (1:Savings 2:Checking 5:Cancel): ");
                        fromType = input.nextInt();
                        System.out.println("Please enter the account to transfer to(1:Savings 2:Checking 3: Credit 5:Cancel): ");
                        toType= input.nextInt();
                    }
                    if((fromType == 1 || fromType == 2) && (toType == 1 || toType == 2 || toType == 3))
                    account.transferBetweenAccounts(fromType, toType, amount);

                    // print the menu and request input
                    printMenu();
                    choice = input.nextInt();
                case 5:
                    System.out.println("(5)Exit");
                    break;
                default:
                    System.out.println("(" + choice + ")" + "Something else");
                    System.exit(0); // kill the program
            }
        }while(choice != 5);

        // write the information to the files
        //writeToFile(); // print out the current user information
        //writeTransactionsToFile(); // print out the transactions to the transactions file

    }

    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println("MENU");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("5. Exit");
    }

    /** method that allows a user to create a new account */
    public static void addAccount(){
        String password;
        int ans;

        System.out.println("CREATE NEW ACCOUNT");
        System.out.print("Please Enter Account Password: ");
        password = input.nextLine();

        System.out.println("Did you want to add a credit account? (1) for yes (2) for no ");
        ans = input.nextInt();
        if(ans == 1) {
            UserAccount account = new UserAccount(password, true);
            // add more access to the account set-up like an initial deposit amount or things of that nature.
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New Account Created (with Credit). Your id is " + account.getId());
        }if(ans == 2) {
            UserAccount account = new UserAccount(password, false);
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New Account Created ");
        }
    }

    /** Writes the current account information to the account_info.txt file */
    /* public static void writeToFile() {
        File file = new File("src/com/groupproject/group/account_info.txt");
        if(!file.exists()){ // check if the doesn't exist
            // if it doesn't, create it
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Exception thrown");
            }
        }
        try (PrintWriter printer = new PrintWriter(file)) {
            // print out the account information. Order: account name, savings account balance, checking account value, amount left in credit, and the outstanding balance due
            printer.println(account.getUsername() + " " + account.getsAccount().getBalance() + " " + account.getchAccount().getBalance() + " " + account.getCcAccount().getAmountLeft() + " " + account.getCcAccount().getOustandingBalance());
        } catch (FileNotFoundException e) { // catches the possible exception throw by the printer object being created
            System.out.println("The file does not exist!");
        }
    } */

    /** Reads information from "account_info.txt" and populates the fields for the accounts */
    /* public static UserAccount readFromFile(){
        // Create the file. This will help to read content from a file
        File file = new File("src/com/groupproject/group/account_info.txt");
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
            // Task: Check to see if there is anything in the file yet
            String testString = fileReader.nextLine();
            if(testString.isEmpty()){ // check to see if the file is empty
                return new UserAccount(null, false); // give back an empty UserAccount object
            }else{ // there IS something in the file, so read from it.
                // TODO: Process information
                return new UserAccount(null, false);
            }
        }catch (FileNotFoundException e) {
            System.out.println("The file was not found!"); // debug code.
            return new UserAccount(null, false); // give back an empty UserAccount object
        }
    } */

    /** Writes the current transaction list to a file to be stored */
    /* public static void writeTransactionsToFile(){
        File file = new File("src/com/groupproject/group/transactions.txt");
        if(!file.exists()){ // check if the doesn't exist
            // if it doesn't, create it
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Exception thrown");
            }
        }
        // use a printWriter to write the information to the file
        try(PrintWriter writer = new PrintWriter(file)){
            for(Transaction transaction : account.getTransactionArrayList()){ // iterate through the transactions list
                writer.println(transaction.getDescription()); // write the description to a line
            }
        }catch (FileNotFoundException e){
            System.out.println("Can't print transactions! File was not found.");
        }
    } */
}
