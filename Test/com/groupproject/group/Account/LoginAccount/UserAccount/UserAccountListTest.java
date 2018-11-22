package com.groupproject.group.Account.LoginAccount.UserAccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description: Test class for UserAccountList. Template.
 * Updated 10:55 P.M. 11/20/2018
 * Currently having trouble building test cases for the UserAccountList,
 * going to look into this one more Wednesday morning as well as the UserAccountTest
 */

class UserAccountListTest {
    // Create a users object to work with
    UserAccountList users = new UserAccountList();

    /* Edited by: Nik
     * Note: Deal directly with the 'users' object.
     * Tip: Add something to the object, or remove something (depending on what the operation requires.)
     */
    @Test
    void getUsers() {
        UserAccount newUser = new UserAccount("yo", "yo", 42, "username", "password", true);
        // add a user to the list
        users.add(newUser);
        // initialize  a list of UserAccounts
        UserAccount[] list = users.getUsers();
        assertNotNull(list[0].getFirstName());
    }

    @Test
    void add() {
        // TODO: Implement
    }

    @Test
    void add1() {
        // TODO: Implement
    }

    @Test
    void findByUsername() {
        // TODO: Implement
    }

    @Test
    void addAll() {
        // TODO: Implement
    }

    @Test
    void getUser() {
        // TODO: Implement
    }

    @Test
    void verifyPassword() {
        // TODO: Implement
    }

    @Test
    void countOccurrences() {
        // TODO: Implement
    }

    @Test
    void ensureCapacity() {
        // TODO: Implement
    }

    @Test
    void getCapacity() {
        // TODO: Implement
    }

    @Test
    void removeAccount() {
        // TODO: Implement
    }

    @Test
    void getSize() {
        // TODO: Implement
    }
}