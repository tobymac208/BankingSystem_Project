package com.groupproject.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Changelog:
 * 04/22/2018: Updated menu; done by Joe
 */

public class Main {
    private static UserAccount account = new UserAccount("Bobs Account", 123546, false); // accountName and ID

    public static void main(String[] args) {
        writeToFile();
        Scanner input = new Scanner(System.in); // creates a scanner to get user input

        // make sure to add acctNum to some sort of list or something that we can test.
        // same with passwords

        double num = 0;
        int choice = 0;
        System.out.println("======================");
        System.out.println("---- BANK PORTAL -----");
        System.out.println("======================");

        System.out.println("ENTER account Number: ");
        num = input.nextDouble();

        printMenu();
        System.out.println("SELECT A MENU OPTION");
        choice = input.nextInt();


        // MENU
        do {
            switch (choice) {
                case 1:
                    double amount;
                    int type;
                    System.out.print("Please enter the amount: ");
                    amount = input.nextDouble();
                    System.out.print("Please enter account type (1-3; Savings, Checking, Credit): ");
                    type = input.nextInt();
                    while(type != 1 || type != 2 || type != 3){
                        System.out.println("Retry (1-3): ");
                        type = input.nextInt();
                    }
                    account.deposit(type, amount);

                    // print the menu and request input
                    printMenu();
                    choice = input.nextInt();
                    break;
                case 2:
                    System.out.println("WITHDRAW");
                    System.out.println("Please enter the amount: ");
                    amount = input.nextDouble();
                    System.out.println("Please enter account type (1-3): ");
                    type = input.nextInt();
                    account.withdraw(type, amount);

                    // print the menu and request input
                    printMenu();
                    choice = input.nextInt();
                    break;
                case 3:
                    System.out.println("TRANSFER");
                    System.out.println("Please enter the amount: ");
                    amount = input.nextDouble();
                    System.out.println("Please enter account number (1-3): ");
                    type = input.nextInt();
                    // TODO: Add transfer functionality

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

    }
    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println("MENU");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("5. Exit");
    }

    /** Writes the current account information to the account_info.txt file */
    public static void writeToFile() {
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
            printer.println("This is a test!");
        } catch (FileNotFoundException e) { // catches the possible exception throw by the printer object being created
            System.out.println("The file does not exist!");
        }
    }

    /** Reads information from "account_info.txt" and populates the fields for the accounts */
    public static UserAccount readFromFile(){
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
                return new UserAccount(null, 0.0, false); // give back an empty UserAccount object
            }else{ // there IS something in the file, so read from it.
                // TODO: Process information
                return new UserAccount(null, 0.0, false);
            }
        }catch (FileNotFoundException e) {
            System.out.println("The file was not found!"); // debug code.
            return new UserAccount(null, 0.0, false); // give back an empty UserAccount object
        }
    }

    /** Writes the current transaction list to a file to be stored */
    public static void WriteTransactions(){
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
    }
}
