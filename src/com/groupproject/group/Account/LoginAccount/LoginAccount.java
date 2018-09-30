package com.groupproject.group.Account.LoginAccount;

/** Provides a base for any accounts that need to be created -- includes Manager and UserAccount */
public abstract class LoginAccount {
    private String firstName;
    private String lastName;
    private int age;

    // all-arg constructor
    public LoginAccount(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // getters and setters
    // first name, getter and setter
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    // last name, getter and setter
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    // age, getter setter
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
}
