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

    @Test
    void getUsers() {
        double users;
        UserAccountList.getUsers(usres);
        assertEquals(users);
    }

    @Test
    void add() {
        double manyUsers;
        UserAccountList.add(user);
        assertEquals(user, manyUsers);
    }

    @Test
    void getUsers1() { //  <<<< last name?
        UserAccountList.getUsersl();
        assertEquals();
    }

    @Test
    void add1() {
            assertEquals();
        }
    }

    @Test
    void findByUsername() {
        String UserAccount;
        UserAccount account = new UserAccount();
        UserAccountList.findByUsername(account);
                assertEquals(account, UserAccount);
    }

    @Test
    void addAll() {
             UserAccountList.addAll(users;
            assertEquals(users, manyUsers);
    }

    @Test
    void getUser() {
            UserAccountList.getUser(user);
            assertEquals(user, manyUsers)
    }

    @Test
    void verifyPassword() {
            String password;
            UserAccountList.verifyPassword(password);
            assertEquals(password, user);
    }

    @Test
    void countOccurrences() {
    }

    @Test
    void ensureCapacity() {
               UserAccountList.ensureCapacity(manyUsers)
               assertEquals(biggerArray, users);
    }

    @Test
    void getCapacity() {
                UserAccountList.getCapacity(users.length);
                assertEquals(users.length);
            }
    }

    @Test
    void removeAccount() {
    }

    @Test
    void getSize() {
            UserAccountList.getSize(manyUsers);
            assertEquals(manyUsers);
    }
}