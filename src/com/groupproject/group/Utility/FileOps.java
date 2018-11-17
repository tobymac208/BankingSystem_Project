package com.groupproject.group.Utility;

import com.groupproject.group.Account.LoginAccount.ManagerAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.Transaction;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccountList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOps {
    private static final String filePath = "src/com/groupproject/group/Resources/Data/file.ser";

    /** Deserialize the file */
    public static ManagerAccount deserialize(){
        ManagerAccount managerAccount = null;
        // Try-with-resources block
        try(FileInputStream file = new FileInputStream(filePath); ObjectInputStream inputStream = new ObjectInputStream(file)){
            managerAccount = (ManagerAccount)inputStream.readObject();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }catch(IOException e){
            System.out.println("IOException.");
        }catch(ClassNotFoundException e){
            System.out.println("\"ManagerAccount\" class was not found.");
        }
        return managerAccount;
    }

    /** Serialize the specified ManagerAccount object */
    public static boolean serialize(ManagerAccount managerAccount){
        try(FileOutputStream file = new FileOutputStream(filePath); ObjectOutputStream outputStream = new ObjectOutputStream(file)){
            outputStream.writeObject(managerAccount);
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
            return false;
        }catch(IOException e){
            System.out.println("IO Exception thrown.");
            return false;
        }
        return true;
    }
}
