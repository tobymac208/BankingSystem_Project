package com.groupproject.group;

import java.util.Scanner;

/*
 * Changelog:
 * 04/22/2018: Updated menu; done by Joe
 */

public class Main {
    static UserAccount account = new UserAccount("Bobs Account", 123546, false); // accountName and ID
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // create new account here to test in main.

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
        switch(choice){
            case 1:
                double amount;
                int type = 0;
                System.out.print("Please enter the amount: ");
                amount = input.nextDouble();
                System.out.print("Please enter account type (1-3): "); // TODO: Write type-checking here
                type = input.nextInt();
                account.deposit(type, amount);

                // ask again or ask to enter 5 to close program.


                break;
            case 2:
                System.out.println("WITHDRAW");
                System.out.println("Please enter the amount: ");
                amount = input.nextDouble();
                System.out.println("Please enter account type (1-3): ");
                type = input.nextInt();
                account.withdraw(type, amount);
                System.exit(0);
                break;


            case 3:
                System.out.println("TRANSFER");
                System.out.println("Please enter the amount: ");
                amount = input.nextDouble();
                System.out.println("Please eneter accountNum (1-3): ");
                type = input.nextInt();
                // TODO: Add transfer functionality

                System.exit(0); // Why quit here?
            case 5:
                System.out.println("(5)Exit");
                break;
            default:
                System.out.println("(" + choice + ")" + "Something else");
                System.exit(0);
        }

    }
    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println("MENU");
        System.out.println("1. Deposit");
        System.out.println("2. WithDrawl");
        System.out.println("3. Transfer");
        System.out.println("5. Exit");
    }
}
