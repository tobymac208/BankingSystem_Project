package com.groupproject.group;

import com.groupproject.group.Account.Banking.CreditBankingAccount;
import com.groupproject.group.Account.LoginAccount.ManagerAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;
import com.groupproject.group.Utility.FileOps;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Authors: Nik F, Joe K, Mike H
 * Description: A simple banking system.
 *              Allows a manager to manage accounts, and add users to use their accounts.
 * Due date: 12/13/2018
 */

public class Main {
    // TODO: Require a user to create this account if there isn't one already created -- currently implemented, below, like this for debugging
    // private static ManagerAccount managerAccount = new ManagerAccount("Jeff", "Linkman", 43, "linklink", "password");
    private static ManagerAccount managerAccount;
    private static UserAccount currentAccountOpen; // used for holding the current account's info
    private static UserAccountList accountList = new UserAccountList();
    // scanner to read in data from the user
    private static Scanner stringInput = new Scanner(System.in);
    private static Scanner intInput = new Scanner(System.in);

    public static void main(String[] args) {
        // DESERIALIZE managerAccount
        managerAccount = FileOps.deserialize();
        if(managerAccount == null) {
            System.out.println("There is no manager account. You must create one.");
            managerAccount = createManager();
            if(managerAccount == null){ // did the account creation fail?
                System.out.println("Account creation failed.\nProject exiting.");
                // close the program
                System.exit(0);
            }
        }

        // make sure to add acctNum to some sort of list or something that we can test. Same with passwords.
        String stringChoice;
        int choice;
        System.out.println("==============================================");
        System.out.println("---------------- BANK PORTAL -----------------");
        System.out.println("==============================================");

        System.out.println("Type 'Login' or type 'register' to register: ");
        stringChoice = stringInput.nextLine();

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
                choice = intInput.nextInt();
            }catch(InputMismatchException e){
                choice = 0; // 0 is not a valid option, so it will force them to go through the default option of the
                            // switch statement
            }

            switch (choice) {
                case 1: // DEPOSIT
                    double amount;
                    double type;

                    System.out.print("Please enter account type to deposit to(1:Savings 2:Checking 5:Cancel): ");
                    type = intInput.nextDouble();
                    // input validation code
                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = intInput.nextDouble();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(type);
                    System.out.print("Please enter the amount to deposit: ");
                    amount = intInput.nextDouble();
                    if(type == 1 || type == 2)
                        currentAccountOpen.deposit(type, amount);
                    break;
                case 2: // WITHDRAWAL
                    System.out.println("WITHDRAW");
                    System.out.println("Please enter account type to withdraw from(1:Savings 2:Checking 5:Cancel): ");
                    type = intInput.nextInt();


                    while(type != 1 && type != 2 && type != 5){
                        System.out.println("Retry (1:Savings 2:Checking 5:Cancel): ");
                        type = intInput.nextInt();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(type);
                    System.out.println("Please enter the amount to withdraw: ");
                    amount = intInput.nextDouble();
                    if(type == 1 || type == 2)
                        currentAccountOpen.withdraw(type, amount);
                    break;
                case 3: // TRANSFER
                    System.out.println("TRANSFER");

                    System.out.println("Please enter account to transfer from(1:Savings 2:Checking 5:Cancel): ");
                    double fromType = intInput.nextDouble();
                    //input validation
                    while(fromType != 1 && fromType != 2 && fromType != 5) {
                        System.out.println("Invalid option selected");
                        System.out.println("Please enter the account to transfer From (1:Savings 2:Checking 5:Cancel): ");
                        fromType = intInput.nextDouble();
                    }
                    //Display amount currently in chosen account
                    currentAccountOpen.displayAnAccountBalance(fromType);
                    System.out.println("Please enter the amount to transfer: ");
                    amount = intInput.nextDouble();
                    System.out.println("Please enter account to transfer to(1:Savings 2:Checking 3:Credit 5:Cancel): ");
                    double toType = intInput.nextDouble();
                    while(toType != 1 && toType != 2 && toType != 3 && toType != 5 || (toType == 3 && currentAccountOpen.getCcAccount() == null)) {
                        if(currentAccountOpen.isCreditAccount()) {
                            System.out.println("Please enter an account to transfer to(1:Savings 2:Checking 3:Credit 5:Cancel): ");
                            toType = intInput.nextInt();
                        } else{
                            System.out.println("You do not have a Credit account, please choose from the following options(1:Savings 2:Checking 5:Cancel): ");
                            toType = intInput.nextInt();
                        }
                    }
                    if((fromType == 1 || fromType == 2) && (toType == 1 || toType == 2 || toType == 3))
                        currentAccountOpen.transferBetweenAccounts(fromType, toType, amount);
                    break;
                case 4:
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

        boolean serialized = FileOps.serialize(managerAccount);
        if(serialized){
            System.out.println("Serialized the ManagerAccount");
        }else{
            System.out.println("Serialization failed.");
        }
        System.out.println("Program exiting.");
    }

    /** Logs the user in
     * Throws:  */
    private static void login() {
        // Control variable for when the loop will end.
        boolean query = true;
        do{
            try {
                System.out.print("Please enter your User-Name: ");
                String userName = intInput.next();

                // Search for the id in the list
                //accountList.removeByUserName(userName);

                if (accountList.findByUsername(userName) != null) { // is the account NOT null?
                    System.out.print("Please enter your password: ");

                    String password = stringInput.nextLine();
                    if (accountList.verifyPassword(password)) {
                        query = false;
                        /*TODO: NOT QUITE SURE WHAT THIS WAS MEANT FOR AGAIN**/
                        // we still have to find the index of the User from the methods above.
                        // we can compare there index returns to verify that the object is truly the same.
                        // we then will take that index and assign it to a new object so that we can then set
                        // it to the currentAccountOpen so that we will not have any errors (SEE BELOW COMMENT)
                       // currentAccountOpen = account;
                    } else {
                        System.out.println("Enter 0 to exit or 1 to try again");
                        int tryAgain = intInput.nextInt();
                        if (tryAgain == 0)
                            System.exit(0);
                    }

                } else {
                    System.out.println("Could not find account, please check to make sure the UserName entered is correct.");

                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Exiting to main menu.");
                query = false; // exit the loop
            }
        }while(query); // run until query is false
    }

    /** Prints menu for users to see */
    public static void printMenu(){
        System.out.println(); // prints a space

        System.out.println("---------------MENU---------------");
        System.out.println("----------------------------------");
        if(currentAccountOpen != null){
            System.out.println("Logged into account #" + currentAccountOpen.getId());
        }
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
        String firstName, lastName, username, password, reenterPassword;
        int answer, age;
        boolean isCreditAccount;
        System.out.println("*****************************");
        System.out.println("CREATE NEW ACCOUNT");
        // first name
        System.out.print("First name: ");
        firstName = stringInput.nextLine();
        // last name
        System.out.print("Last name: ");
        lastName = stringInput.nextLine();
        // age
        System.out.print("Age (integer only): ");
        age = intInput.nextInt();
        // username
        System.out.print("Username: ");
        username = stringInput.nextLine();
        // password
        System.out.print("Password: ");
        password = stringInput.nextLine();
        // re-enter password
        System.out.print("Re-enter password: ");
        reenterPassword = stringInput.nextLine();

        System.out.println("Did you want to add a credit account? (1) for yes (2) for no ");
        answer = intInput.nextInt();
        if(answer == 1){
            isCreditAccount = true;
        }else{
            isCreditAccount = false;
        }

        if(password.equals(reenterPassword)){ // do the password match?
            // create the new account
            account = new UserAccount(firstName, lastName, age, username, password, isCreditAccount);
            // add the account to the Manager Account
            managerAccount.addUser(account);
            System.out.println("New account created. Username: " + account.getUsername());
        }else{
            System.out.println("Passwords don't match.\n Creation failed.");
        }

        return account;
    }

    /** Method that creates a manager account */
    public static ManagerAccount createManager(){
        ManagerAccount theManager = null;
        String firstName, lastName, username, password, reenterPassword;
        int age;

        System.out.println("Welcome to the Manager Account creation!");
        System.out.println("Please enter the following required fields, and then press ENTER.");
        // take the required information from the user
        // first name
        System.out.print("First name: ");
        firstName = stringInput.nextLine();
        // last name
        System.out.print("Last name: ");
        lastName = stringInput.nextLine();
        // age
        System.out.print("Age: ");
        age = intInput.nextInt();
        // username
        System.out.print("Username: ");
        username = stringInput.nextLine();
        // password
        System.out.print("Password: ");
        password = stringInput.nextLine();
        // re-enter password
        System.out.print("Re-enter password: ");
        reenterPassword = stringInput.nextLine();

        if(password.equals(reenterPassword)){ // do the passwords match?
            theManager = new ManagerAccount(firstName, lastName, age, username, password);
        }

        return theManager; // may return null or a filled ManagerAccount object.
    }

    /** method that allows the manager to remove an account */
    public static void removeAccount(){
        String username;
        UserAccount accountToRemove = null;

        System.out.print("Please enter username of the account you'd like to remove:");
        username = stringInput.nextLine();

        accountToRemove = managerAccount.getUserAccounts().findByUsername(username);
        if(accountToRemove != null){
            managerAccount.removeUser(accountToRemove);
            System.out.println("Account removed.");
        }else{
            System.out.println("Account does not exist. Nothing removed.");
        }
    }
}
