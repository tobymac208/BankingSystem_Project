package com.groupproject.group;

import java.util.Scanner;

public class Main {
    public static UserAccount account = new UserAccount("Bob's Account", 5000);
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

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
}