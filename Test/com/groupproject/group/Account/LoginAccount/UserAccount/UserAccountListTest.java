//package com.groupproject.group.Account.LoginAccount.UserAccount;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Description: Test class for UserAccountList.
// * UPDATED 12/1/18 @ 2:04 A.M. By Mike Haapoja
// * I went in to all the test cases and implemented them to the best of my ability, I went off of your notes and tried to
// * stick with just using the "users" accountList, but I think it worked out OK(?).
// *
// * I still have yet to implement the UserAccountTest.java methods which I am going to try to do before work tomorrow but If I
// * don't get to it before then I will do it after I close.
// *
// * There's still much more for me to do...
// */
//
//class UserAccountListTest {
//    // Create a users object to work with
//    UserAccountList users = new UserAccountList();
//
//    /* Edited by: Nik
//     * Note: Deal directly with the 'users' object.
//     * Tip: Add something to the object, or remove something (depending on what the operation requires.)
//     */
//    @Test
//    void getUsers() {
//
//        UserAccount newUser = new UserAccount("yo", "yo", 42, "username", "password", true);
//        // Creates a new user Account.
//        users.add(newUser);
//        // Adds the user to the list.
//        UserAccount[] list = users.getUsers();
//        // Creation of the list of UserAccounts.
//        assertNotNull(list[0].getFirstName());
//        users.removeAccount(newUser);
//    }
//
//    @Test
//    void add() {
//
//        UserAccount newUser = new UserAccount("example", "example", 18, true);
//        // Add a new user
//        users.add(newUser);
//        // Adds the user to the UserAccountList.
//        UserAccount[] list = users.getUsers();
//        // Checking to see if there is a user inside the account.
//        assertNotNull(list[0].getFirstName());
//        // Removes the new account for future testing.
//        users.removeAccount(newUser);
//    }
//
//    @Test
//    void findByUsername() {
//
//        UserAccount newUser = new UserAccount("John", "Wayne", 87, "wayne87", "giddyup", true);
//        // Create new UserAccount
//        users.add(newUser);
//        // Adds the user to the user list;
//        UserAccountl[] list = users.getUsers();
//        // Creation of instance variable UserAccount list.
//
//        assertEquals(newUser.username, users.findByUsername());
//        // Checking to see if the new registered user can be found by username from the list of users.
//        assertNotNull(list[0].getFirstName());
//        // Statement to check and see if there is a user inside the list.
//        users.removeAccount(newUser);
//        // Removes the account for future testing.
//    }
//
//    @Test
//    void addAll() {
//
//        UserAccount newUser = new UserAccount("Jimmy", "John", 25, "jj2018", "freakyfast", true);
//        UserAccount newUser2 = new UserAccount("Papa", "John", 60, "pj1980", "betteringriedients", true);
//        // Creation of two user accounts.
//        users.add(newUser, newUser2);
//        // Adds both the users to the user list.
//        UserAccount[] list = users.getUsers();
//        // Create a userAccount instance variable.
//        assertEquals(users.newUser, newUser2.addAll());
//        // Adds the contents of newUser to newUser2.... still a bit confused on this one.
//        assertNotNull(list[0].getFirstName());
//        // Checking for a user.
//        users.removeAccount(newUser);
//        // Remove user for more testing.
//    }
//
//    @Test
//    void getUser() {
//
//        UserAccount newUser = new UserAccount("Darren", "Appleton", 45, "dynamite", "poolshark123", true);
//        users.add(newUser);
//        // Adds user to the list.
//        UserAccount[] list = users.getUsers();
//        // Instance variable of full list of users.
//        assertEquals(users.newUser, users.getUser(newUser));
//        // Method checking to get the specific user.
//        assertNotNull(list[0].getFirstName());
//        // Checks to find a user in the list.
//        users.removeAccount(newUser);
//        // Removes the account for more testing.
//    }
//
//    @Test
//    void verifyPassword() {
//
//        UserAccount newUser = new UserAccount("Shane", "VanBoening", 30, "southdakotakid", "9ballmaster", true);
//        users.add(newUser);
//        // Adds the user to the list.
//        UserAccount[] list = users.getUsers();
//        // Creation of instance variable.
//        assertEquals(users.newUser, users.verifyPassword(newUser));
//        // Method checking to verify the password to the user.
//        assertNotNull(list[0].getFirstName());
//        // Checks to find a user in the list.
//        users.removeAccount(newUser);
//        // Removes the account for future testing.
//    }
//
//    @Test
//    void countOccurrences() {
//
//        UserAccount newUser = new UserAccount("Michael", "Scott", 35, "scarn", "goldenfacesucks", true);
//        users.add(newUser);
//        // Adds the user to the user
//        UserAccount[] list = users.getUsers();
//        // Creation of user account list instance variable.
//        if (assertNotNull(list[0].getFirstName())) {
//            assertEquals(users.newUser, users.countOccurences(newUser));
//            // Method calling to count occurences.
//            users.removeAccount(newUser);
//            // Removes Account for future testing.
//        }
//    }
//
//    @Test
//    void ensureCapacity() {
//
//        UserAccount newUser = new UserAccount("Happy", "Gilmore", 25, "chubbs", "allinthehips", true);
//        // Creates a new user.
//        users.add(newUser);
//        // Adds the user to the list.
//        UserAccount[] list = users.getUsers();
//        // Creates user account list instance variable.
//        if (users.length > users.minimumCapacity)
//            assertEquals(users.minimumCapacity, users.ensureCapacity(newUser));
//        // Checks to see if the users length is longer than the minimum capacity.
//        // If true then it calls the ensure capacity method.
//        assertNotNull(list[0].getFirstName());
//        // Checks the list for a user.
//        users.removeAccount(newUser);
//        // Removes the account.
//    }
//
//    @Test
//    void getCapacity() {
//
//        UserAccount newUser = new UserAccount("Jack", "Nickels", 56, "dullboy", "redrum", true);
//        // Create a new user account.
//        users.add(newUser);
//        // Add the user to the list.
//        UserAccount [] list = users.getUsers();
//        // Creation of the UserAccount List instance variable.
//        assertEquals(users.newUser, users.getCapacity(newUser));
//        // Method calling to get the capacity of the newUser.
//        assertNotNull(list[0].getFirstName());
//        // Checking the list for a user.
//        users.removeAccount();
//        // Removes the account for more testing.
//    }
//
//    @Test
//    void removeAccount() {
//        // TODO: Implement
//        UserAcount newUser = new UserAccount("Moses", "Shchrute", "farmboy007", "beetman", true);
//        // Creates a new user Account.
//        users.add(newUser);
//        // Adds the user to the list.
//        UserAccount[] list = users.getUsers();
//        // Create a list of UserAcccounts.
//        assertNotNull[list[0].getFirstName()];
//        // Check to see if the list has a user inside it.
//        assertEquals(users.newUser, users.removeAccount(newUser));
//        // Method calling to remove the account from the list.
//    }
//
//    @Test
//    void getSize() {
//        // TODO: Implement
//        UserAccount newUser = new UserAccount("Patrick", "Bateman", 23, "pb1990", "watermark", true);
//        // Create a new user Account.
//        users.add(newUser);
//        // Add the user to the list.
//        UserAccount[] list = users.getUsers();
//        // Create a list of UserAccounts.
//        assertEquals(users.newUser, users.getSize(newUser));
//        // Method calling to get the size of the new user.
//        assertNotNull(list[0].getFirstName());
//        // Checks the list for an existing user account.
//        users.removeAccount();
//        // Removes the account for testing.
//
//    }
//}