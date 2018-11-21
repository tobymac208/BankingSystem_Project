package com.groupproject.group.Account.LoginAccount;

import java.io.Serializable;

/** Provides a base for any accounts that need to be created -- includes Manager and UserAccount */
public abstract class LoginAccount implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String password;
    // Level defines what level of access a user has. 0 = ManagerAccount, 1 = UserAccount
    private int level;

    // all-arg constructor
    public LoginAccount(String firstName, String lastName, int age, String username, String password, int level){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.level = level; // immutable -- can only be set in the constructor
    }

    // getters and setters
    // first name
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    // last name
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    // age
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    // username -- CANNOT change this value, as of right now
    public String getUsername(){return username;}
    // password
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    // level
    public int getLevel(){return level;}
    // ------------------------------------
}
