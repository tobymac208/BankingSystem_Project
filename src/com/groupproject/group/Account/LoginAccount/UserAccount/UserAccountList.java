package com.groupproject.group.Account.LoginAccount.UserAccount;

import java.util.Arrays;

public class UserAccountList implements Cloneable {
        // manyUsers will be one greater than size always. we must account for this if we call size.
        // for right now all we are saving in studentCollections is there Report ID and not by object type student.
        // we will change and further implement these techniques below when the time comes.
        private UserAccount[] users;
        private int manyUsers;

        /** No-arg Constructor: studentCollections creates a collection of students with an initial capacity of 10 obj elements. */
        public UserAccountList() {
            final int INITIAL_CAPACITY = 10;
            manyUsers = 0;
            users = new UserAccount[INITIAL_CAPACITY];
        }
        // METHOD: studentCollections makes sure that the initial capacity which if we were to ask the user for it
        // ensure that the value cannot be 0 or negative.
        public UserAccountList(int initialCapacity) {
            if(initialCapacity < 0)
                throw new IllegalArgumentException("initialCapacity is negative : " + initialCapacity);
            manyUsers = 0;
            users = new UserAccount[initialCapacity];
        }

        /** Get the list of accounts */
        public UserAccount[] getUsers(){
            return users;
        }

        // METHOD: add simply adds a student to the array and ensures that it can fit.
        public void add(UserAccount user2){
            if(manyUsers == users.length) {
                ensureCapacity(manyUsers*2 + 1);
            }
            users[manyUsers] = user2;
            manyUsers++;
        }
        // METHOD: addAll adds all data from one collection to another and adds it to the end of one array.
        public void addAll(UserAccountList addend) {
            // we might need to use a if statement.
            System.arraycopy(addend.users, 0, users, manyUsers, addend.manyUsers);
            manyUsers += addend.manyUsers;
        }
        // METHOD: adds many different elements to the list but also calls ensureCapacity to make sure that they can fit.
        public void addMany(String... elements) {
            if(manyUsers + elements.length > users.length) {
                ensureCapacity((manyUsers + elements.length)*2);
            }
            System.arraycopy(elements, 0, users, manyUsers, elements.length);
            manyUsers += elements.length;
        }
        // METHOD:
        public UserAccountList clone() {
            UserAccountList answer;

            try {
                answer = (UserAccountList) super.clone();
            }
            catch(CloneNotSupportedException e) {
                throw new RuntimeException("This class does not implement Cloneable.");
            }
            answer.users = users.clone();
            return answer;
        }
        public boolean findByUserName(String target) {
            for (int index = 0; index < manyUsers; index++) {
                if (users[index].getUsername().equals(target)) {
                    System.out.println("USER REMOVED");
                    return true;

                } else {
                    System.out.println("USER NOT FOUND");
                }


                return false;
            }
            // literally nothing in thr array this is not good
            return false;
        }
        public UserAccount getUser(UserAccount account){
            for(int index = 0; index < manyUsers; index++) {
                if(users[index].equals(account)) {
                    users[index] = null;
                    System.out.println("USER REMOVED");
                    return account;

                }
                else {
                    System.out.println("USER NOT FOUND");
                    break;
                }

            }
            return null;
        }
        public boolean verifyPassword(String password){
            for (int index = 0; index < manyUsers; index++) {
                if (users[index].getPassword().equals(password)) {
                    System.out.println("VERIFIED");
                    return true;

                } else {
                    System.out.println("PASSWORD IS INCORRECT");
                }


                return false;
            }
            // literally nothing in thr array this is not good
            return false;
        }

        public int countOccurrences(UserAccountList user) {
            int answer;
            int index;


            answer = 0;
            for(index = 0; index < manyUsers; index++)
                if(user.equals(users[index])) {
                    answer++;
                }
            return answer;
        }
        // METHOD: ensureCapacity takes a minimum capacity and makes sure the value can be stored
        // in the array and if it cannot we are going to copy the smaller array to a larger array.
        public void ensureCapacity(int minimumCapacity) {
            UserAccount[] biggerArray;
            if(users.length < minimumCapacity) {
                biggerArray = new UserAccount[minimumCapacity];
                System.arraycopy(users, 0, biggerArray, 0, manyUsers);
                users = biggerArray;
            }
        }
        // METHOD: getCapacity just returns the length of the array.
        public int getCapacity() {
            return users.length;
        }
        // METHOD: remove take the parameter Student target and searches through the array starting at index value 0.
        // If and when the Student is found we set that index to null technically removing the student from our array.
        // resizing the array will be handled in additional methods.
        // if we cannot find the student we send a messege to the user that we could not find the student.
        public boolean remove(UserAccountList target) {

            for(int index = 0; index < manyUsers; index++) {
                if(users[index].equals(target)) {
                    users[index] = null;
                    System.out.println("USER REMOVED");
                    return true;

                }
                else {
                    System.out.println("USER NOT FOUND");
                    break;
                }

            }
            return false;


        }

        // METHOD: size just returns the length of the array.
        public int size() {
            return users.length;
        }
        // METHOD: trimToSize looks at how many Students are currently held in the array and copies the values at non-null indexes
        // to a new array named trimmedArray. This will handle our array getting out of control and being larger than we really want.
        public void trimToSize() {
            UserAccount[] trimmedArray;
            if(users.length != manyUsers) {
                trimmedArray = new UserAccount[manyUsers];
                System.arraycopy(users, 0, trimmedArray, 0, manyUsers);
                users = trimmedArray;
            }
        }
        // METHOD: a union. A union method is so that we can connect two seperate student collection linked list
        // and combines them together in one single list.
        public static UserAccountList union(UserAccountList b1, UserAccountList b2) {
            UserAccountList answer = new UserAccountList(b1.getCapacity() + b2.getCapacity());
            System.arraycopy(b1.users, 0, answer.users, 0, b1.manyUsers);
            System.arraycopy(b2.users, 0, answer.users, 0, b2.manyUsers);
            answer.manyUsers = b1.manyUsers + b2.manyUsers;
            return answer;
        }
        // METHOD: toString so we can print the information from array out.
        @Override
        public String toString() {
            return "studentCollections data=" + Arrays.toString(users) ;
        }

    }
