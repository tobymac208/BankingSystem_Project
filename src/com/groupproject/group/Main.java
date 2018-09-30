package com.groupproject.group;

import com.groupproject.group.Account.Banking.CreditBankingAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccountList;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Authors: Nik F, Joe K, Mike H
 * Description: A simple banking system.
 *              Allows a manager to manage accounts, and users to use their accounts.
 * Due: 05/01/2018
 */

public class Main {
    // private static UserAccount account = new UserAccount("Bobs BankingAccount",false); // account name and balance
    private static UserAccount currentAccountOpen; // used for holding the current account's info
    private static UserAccountList accountList = new UserAccountList();
    // scanner to read in data from the user
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<UserAccount> readInList = readFromFile(); // read in the items
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

        printMenu(); // print the menu for the user to view options
        System.out.println("SELECT A MENU OPTION");
        // get the first input from the user
        choice = input.nextInt();

        // MENU
        do {
            switch (choice) {
                case 1:
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

                    // print the menu and request input
                    printMenu();
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 2:
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

                    // print the menu and request input
                    printMenu();
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 3:
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

                    // print the menu and request input
                    printMenu();
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 4:
                    String junkLine = input.nextLine();
                    addAccount(); // adds an account
                    printMenu();
                    System.out.println("SELECT A MENU OPTION");
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 5:
                    login();
                    printMenu();
                    System.out.println("SELECT A MENU OPTION");
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 6:
                    if (currentAccountOpen.isCreditAccount()){
                        System.out.println("You already have a credit account open");
                    }else {
                        currentAccountOpen.setCreditFlag(true);
                        currentAccountOpen.setCcAccount(new CreditBankingAccount());
                        System.out.println("Your new credit account is now open");
                    }
                    printMenu();
                    System.out.println("SELECT A MENU OPTION");
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 7:
                    System.out.println("--------- BankingAccount Balance -----------");
                    currentAccountOpen.displayAccountBalance();
                    System.out.println("-------------------------------------");
                    printMenu();
                    System.out.println("SELECT A MENU OPTION");
                    try{
                        choice = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Incorrect data-type entered.");
                    }
                    break;
                case 8:
                    System.out.println("(8)Exit");
                    break;
                default:
                    System.out.println("(" + choice + ")" + "Something else");
                    System.exit(0); // kill the program
            }
        }while(choice != 8);

        // write the information to the files
        writeToFile(); // print out the current user information
        writeTransactionsToFile(); // print out the transactions to the transactions file
        System.out.println("Closing Program");
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
        if(ans == 1) {
            account = new UserAccount(password, true);
            // add more access to the account set-up like an initial deposit amount or things of that nature.
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New BankingAccount Created (with Credit). Your ID is " + account.getId());
        }else if(ans == 2) {
            account = new UserAccount(password, false);
            // add to arrayList
            accountList.addAccount(account);
            System.out.println("New account created (without Credit). Your ID is: " + account.getId());
        }
        return account;
    }

    /** Writes the current account information to the account_info.txt file */
    public static void writeToFile() {
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
            for(UserAccount elem : accountList.getAccountsList()){
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
                        account = new UserAccount(pass, true);
                        account.getCcAccount().setAmountLeft(ccAcctBal); // sets the amount left
                    } else {
                        account = new UserAccount(pass, false);
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
    public static void writeTransactionsToFile(){
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
            for(UserAccount account : accountList.getAccountsList()){ // gets every account
                for(Transaction transaction : account.getTransactionArrayList()){ // gets every transaction in each account
                    writer.println("BankingAccount #" + account.getId() + ": " + transaction.getDescription()); // write each transaction's description to the file
                }
            }
            bw.close();
            writer.close();
        }catch (IOException e){
            System.out.println("Can't print transactions! File was not found.");
        }
    }
}
