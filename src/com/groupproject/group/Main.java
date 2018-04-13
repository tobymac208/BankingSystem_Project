package com.groupproject.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Changelog:
 * 04/13/2018: Added file operations (writeToFile() and readFromFile())
 */

public class Main {
    public static UserAccount account = new UserAccount("Bob's Account", 5000);
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // call the readFromFile() method to populate the accounts for 'account' object
        UserAccount utilityAccount =  readFromFile(); // this account will be what the above 'account' object is always set to at the start of the program

        int choice = 0;

        printMenu(); // prints the menu
        System.out.print("Please enter an integer (1-5): ");
        choice = input.nextInt(); // reads in the integer
        // MENU
        do {
            switch(choice){
                case 1:
                    double amount;
                    int type = 0;
                    System.out.print("Please enter the amount: ");
                    amount = input.nextDouble();
                    System.out.print("Please enter account type (1-3): "); // TODO: Write type-checking here
                    type = input.nextInt();
                    account.deposit(type, amount);

                    System.out.print("Please enter an integer (1-5): ");
                    choice = input.nextInt(); // reads in the integer
                    break;
                case 2:
                    System.out.println("(2)Nothing wired yet.");
                    break;
                case 5:
                    System.out.println("(5)Exit");
                    break;
                default:
                    System.out.println("(" + choice + ")" + "Something else");
                    System.exit(0);
                    break;
            }
        }while(choice != 5);

        for(Transaction transaction : account.getTransactionArrayList()){
            System.out.println(transaction.getDescription());
        }

        System.out.println("Program is done.");

    }
    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println("MENU");
        System.out.println("1. Deposit");
        System.out.println("2. Something else");
        System.out.println("5. Exit");
    }

    /** Writes the current account information to the account_info.txt file */
    public static void writeToFile(){
        try(PrintWriter printer = new PrintWriter("src/com/groupproject/group/account_info.txt")){
            printer.println("This is a test!");
        }catch(FileNotFoundException e){ // catches the possible exception throw by the printer object being created
            System.out.println("The file does not exist!");
        }
    }

    /** Reads information from "account_info.txt" and populates the fields for the accounts */
    public static UserAccount readFromFile(){
        // Create the file. This will help to read content from a file
        File file = new File("src/com/groupproject/group/account_info.txt");
        // Create a Scanner object -- this will be used to do the reading
        try(Scanner fileReader = new Scanner(file)) {
            // Task: Check to see if there is anything in the file yet
            String testString = fileReader.nextLine();
            if(testString.isEmpty()){ // check to see if the file is empty
                return new UserAccount(null, 0.0); // give back an empty UserAccount object
            }else{ // there IS something in the file, so read from it.
                // TODO: Process information
                return new UserAccount(null, 0.0);
            }
        }catch (FileNotFoundException e) {
            System.out.println("The file was not found!"); // debug code.
            return new UserAccount(null, 0.0); // give back an empty UserAccount object
        }
    }
}
