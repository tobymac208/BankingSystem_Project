package com.groupproject.group.Windows;

/**
 * Description: This class contains all of the GUI's implementation. It is run using a Swing's CardLayout.
 */

import com.groupproject.group.Account.LoginAccount.ManagerAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Utility.FileOps;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JPanel {
    //private static ManagerAccount managerAccount = new ManagerAccount("Jeff", "Linkman", 43, "linklink", "password"); -- here for testing purposes
    private static ManagerAccount managerAccount;
    private static UserAccount currentAccountOpen; // used for holding the current account's info
    // scanner to read in data from the user

    /**
     * Create the panel.
     * 1st panel will store the remaining panels.
     */
    private JPanel panelCont = new JPanel();
    private JPanel login_Panel;
    private JPanel createAccount_Panel;
    private JPanel managerAccount_Panel;
    private JPanel userAccount_Panel;
    private JPanel removeUserAccount_Panel;
    private JPanel createManager_Panel;
    private JPanel userHistoryPanel;
    private JPanel transactionsPanel;
    private CardLayout c1 = new CardLayout();

    // Strings for all the panels
    private final String loginPanel_title = "login";
    private final String createAccountPanel_title = "createAccount";
    private final String managerAccountPanel_title = "managerAccount";
    private final String userAccountPanel_title = "userAccount";
    private final String removeAccountPanel_title = "removeUserAccount";
    private final String createManager_title = "createManager";
    private final String userHistory_title = "userHistory";
    private final String transaction_title = "transactions";

    // then the other variables that we needed.
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField amountTextField;
    private JTextField withdrawTxtField;
    private JTextField transferTxtField;
    private JTextField transToTxtField;
    private JTextPane managerTextPane = new JTextPane();


    /**
     * WE LAUNCH THE APPLICATION HERE AND EVERYTHING NEEDS TO BE CALLED AND ADDED HERE
     **/
    private Application() {
        // call panels here
        LoginPanel();
        CreateAccountPanel();
        ManagerPanel();
        UserActPanel();
        removeUserAccountPanel();
        createManagerPanel();
        userHistoryPanel();
        transactionPanel();
        // everything gets added to here now panel wise. we just have to give it the attributes we did
        panelCont.setLayout(c1);
        // we have to finally add this panel to the panelCont.
        panelCont.add(login_Panel, loginPanel_title);
        panelCont.add(createAccount_Panel, createAccountPanel_title);
        panelCont.add(managerAccount_Panel, managerAccountPanel_title);
        panelCont.add(userAccount_Panel, userAccountPanel_title);
        panelCont.add(removeUserAccount_Panel, removeAccountPanel_title);
        panelCont.add(createManager_Panel, createManager_title);
        panelCont.add(userHistoryPanel, userHistory_title);
        panelCont.add(transactionsPanel, transaction_title);
        if(managerAccount != null){
            // this is what panel we want to show right away.
            c1.show(panelCont, loginPanel_title);
        }else{
            // show this if the managerAccount didn't exist
            c1.show(panelCont, createManager_title);
        }
        // finally add it to the frame.
        JFrame frame = new JFrame();
        frame.getContentPane().add(panelCont);

        //leave everything below this
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/com/groupproject/group/Resources/Images/folder-red-java-icon.png")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void LoginPanel() {
        // all of this is now from the login menu itself and can be created using the building tool and we can hook it up from there
        JPanel panel = new JPanel();
        login_Panel = new JPanel();
        login_Panel = new JPanel();
        login_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        login_Panel.setLayout(new BorderLayout(0, 0));
        login_Panel.add(panel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("BANKING PORTAL");
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(titleLabel);

        JPanel panel_1 = new JPanel();
        login_Panel.add(panel_1, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("DATE AND TIME/VERSION");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(lblNewLabel_2);

        // This label is to alert the user if something worked or not
        JLabel successMessage = new JLabel("");
        successMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(successMessage); // add the label

        JPanel panel_2 = new JPanel();
        login_Panel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Application.class.getResource("/com/groupproject/group/Resources/Images/00205-3D-art-logo-design-free-logos-online-011.png")));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblNewLabel_3, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JCheckBox isManagerCheckbox = new JCheckBox("manager");
        isManagerCheckbox.setBackground(Color.LIGHT_GRAY);
        isManagerCheckbox.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(isManagerCheckbox);

        JLabel usernameLabel = new JLabel("USERNAME:");
        usernameLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
        panel_3.add(usernameLabel);

        JTextField usernameField = new JTextField(10);
        panel_3.add(usernameField);

        JLabel passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
        panel_3.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(10);
        panel_3.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.addActionListener(e -> {
            if (e.getSource() == loginButton) { // the btnNewButton was clicked
                // there is no manager account yet
                if(managerAccount == null){
                    successMessage.setText("Login failed. No manager account exists.");
                }
                // The manager account is not null -- try to log them in
                else{
                    String username = usernameField.getText(), password = passwordField.getText();
                    if(isManagerCheckbox.isSelected()){ // try to login the administrator
                        if(managerAccount.getUsername().equals(username) && managerAccount.getPassword().equals(password)){
                            c1.show(panelCont, managerAccountPanel_title);
                            successMessage.setText("Login successful! Manager logged in.");
                            usernameField.setText("");
                            passwordField.setText("");
                        }
                        // login a user account
                    }else{
                        if (managerAccount.getUserAccounts().findByUsername(username) !=null && managerAccount.getUsername().equals(username)){
                            // username found
                            UserAccount accountLoggingIn = managerAccount.findByUsername(username);
                            if (accountLoggingIn.getPassword().equals(password)) { // password is the same
                                currentAccountOpen = managerAccount.findByUsername(username); // login the user
                                successMessage.setText("Account logged in.");
                                c1.show(panelCont, userAccountPanel_title);
                                usernameField.setText("");
                                passwordField.setText("");
                            }
                        }
                    }
                }
            }
        });
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(loginButton);
    }

    private void CreateAccountPanel() {
        createAccount_Panel = new JPanel();
        createAccount_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        createAccount_Panel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        createAccount_Panel.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("WELCOME NEW-USER");
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        createAccount_Panel.add(panel_1, BorderLayout.CENTER);
        // rows ,cols
        panel_1.setLayout(new GridLayout(5, 2));

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_3);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // Labels and new panels
        JLabel firstName_Label = new JLabel("FIRST-NAME ");
        firstName_Label.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JLabel lastName_Label = new JLabel("LAST-NAME");
        lastName_Label.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_4);

        JLabel password_Label = new JLabel("PASSWORD ");
        password_Label.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JLabel age_Label = new JLabel("AGE ");
        age_Label.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JLabel username_Label = new JLabel("USERNAME ");
        username_Label.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        // TextFields
        JTextField firstName_TextField = new JTextField();
        firstName_TextField.setColumns(10);

        JTextField lastName_TextField = new JTextField();
        lastName_TextField.setColumns(10);

        JTextField password_TextField = new JTextField();
        password_TextField.setColumns(10);

        JTextField age_TextField = new JTextField();
        age_TextField.setColumns(10);

        JTextField username_TextField = new JTextField();
        username_TextField.setColumns(10);

        // Add the objects
        panel_3.add(firstName_Label);
        panel_3.add(firstName_TextField);
        panel_3.add(lastName_Label);
        panel_3.add(lastName_TextField);
        panel_4.add(username_Label);
        panel_4.add(username_TextField);
        panel_4.add(password_Label);
        panel_4.add(password_TextField);
        panel_4.add(age_Label);
        panel_4.add(age_TextField);

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_7);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("CREDIT ACCOUNT");
        chckbxNewCheckBox_1.setBackground(Color.LIGHT_GRAY);
        chckbxNewCheckBox_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_7.add(chckbxNewCheckBox_1);

        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8);

        JButton btnNewButton = new JButton("CREATE");
        // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
        btnNewButton.addActionListener((ActionEvent e) -> {
            // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
            if(username_TextField.getText()!=null && password_TextField.getText()!=null && age_TextField.getText()!=null && firstName_TextField.getText()!=null && lastName_TextField.getText()!=null) {
                // if the fields are not null we are going to expect that they have entered the information correctly
                // create temp variables to create user.
                String firstName = firstName_TextField.getText();
                String lastName = lastName_TextField.getText();
                String password = password_TextField.getText();
                String userName = username_TextField.getText();
                int age = Integer.parseInt(age_TextField.getText());
                // create user account and then add each account type that they specify on wanting.
                // create a new account
                boolean hasCreditAccount = chckbxNewCheckBox_1.isSelected();
                UserAccount newAccount = new UserAccount(firstName, lastName, age, userName, password, hasCreditAccount);
                // add it to the ManagerAccount object
                boolean accountAdded = managerAccount.addUser(newAccount);
                if(accountAdded){
                    // save settings
                    saveSettings();
                    c1.show(panelCont, managerAccountPanel_title); // go back to the manager account panel
                    managerTextPane.setText("Account added, username: " + userName);
                }else{
                    System.err.println("Account failed to add.");
                }
            }
        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_8.add(btnNewButton);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(e -> {
            c1.show(panelCont, "managerAccount");
            // empty all of the text fields
            username_TextField.setText("");
            password_TextField.setText("");
            firstName_TextField.setText("");
            lastName_TextField.setText("");
            age_TextField.setText("");
        });
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_8.add(cancelButton);

        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        // please edit me or make me larger so i can fill the screen.
        lblNewLabel_1.setIcon(new ImageIcon(("/com/groupproject/group/Resources/Images/texture2.jpg")));
        panel_6.add(lblNewLabel_1);
    }

    private void ManagerPanel(){
        managerAccount_Panel = new JPanel();
        managerAccount_Panel.setBackground(Color.LIGHT_GRAY);
        managerAccount_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        managerAccount_Panel.setLayout(new BorderLayout(0, 0));

        JLabel headerLabel = new JLabel("MANAGER ACCOUNT\r\n");
        headerLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        managerAccount_Panel.add(headerLabel, BorderLayout.NORTH);

        JLabel footerLabel = new JLabel("VERSION 1.08\r\n");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        managerAccount_Panel.add(footerLabel, BorderLayout.SOUTH);

        JPanel managerControlPanel = new JPanel();
        managerControlPanel.setBackground(Color.LIGHT_GRAY);
        managerAccount_Panel.add(managerControlPanel, BorderLayout.WEST);
        // 3 rows 1 col
        managerControlPanel.setLayout(new GridLayout(6,1));

        JButton addBtn = new JButton("ADD-USER");
        addBtn.addActionListener(e -> {
            // we are going to open the other GUI after we verify CREATE has been clicked.
            // well we need to use this.dispose() to close previous GUI's
            if (e.getSource() == addBtn) {
                // Similar catch block statement used in main to initially launch the application.
                c1.show(panelCont, createAccountPanel_title);
            }
        });
        addBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(addBtn);

        JButton removeBtn = new JButton("REMOVE-USER");
        removeBtn.addActionListener(e -> c1.show(panelCont, removeAccountPanel_title));
        removeBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(removeBtn);

        JButton historyBtn = new JButton("USER-HISTORY");
        historyBtn.addActionListener(e -> c1.show(panelCont, userHistory_title));
        historyBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(historyBtn);

        JButton transBtn = new JButton("TRANSACTIONS");
        transBtn.addActionListener(e -> c1.show(panelCont, transaction_title));
        transBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(transBtn);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.addActionListener(e -> c1.show(panelCont, loginPanel_title));
        logoutBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(logoutBtn);

        JPanel panel = new JPanel();
        managerAccount_Panel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel footpanel = new JPanel();
        footpanel.setBackground(Color.LIGHT_GRAY);
        panel.add(footpanel, BorderLayout.SOUTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Joe Kelly\\Pictures\\LOGOS\\texture3.jpg"));
        footpanel.add(lblNewLabel);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_2.add(panel_3);

        JLabel usernameLbl = new JLabel("USERNAME");
        usernameLbl.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(usernameLbl);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        panel_3.add(usernameTextField);

        JLabel lblNewLabel_2 = new JLabel("RE-ENTER");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_2);

        JTextField usernameReEnterField = new JTextField();
        usernameReEnterField.setColumns(10);
        panel_3.add(usernameReEnterField);

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // search for a user
                String usernameToSearchFor = usernameTextField.getText();
                // make sure something is input here.
                if(usernameTextField.getText() !=null && usernameReEnterField.getText() !=null){
                    // make sure username is the same on both sides
                    if(usernameTextField.getText().equals(usernameReEnterField.getText())){
                        // perform the search.
                        String username = usernameTextField.getText();
                        if(username.equals(managerAccount.findByUsername(username))){
                            // we have found the user.
                            //display to textPane.
                            managerTextPane.setText("USER FOUND:" + username);


                        }else{
                            managerTextPane.setText("USER WAS NOT FOUND");
                            // clear the fields for the user
                            usernameTextField.setText("");
                            usernameReEnterField.setText("");
                        }
                    }else{
                        managerTextPane.setText("USERNAMES DO NOT MATCH TRY AGAIN!");
                        // clear the fields for the user.
                        usernameTextField.setText("");
                        usernameReEnterField.setText("");
                    }

                }else{
                    managerTextPane.setText("PLEASE ENTER SOMETHING IN THE FIELDS!");
                }
            }
        });
        searchButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(searchButton);

        JButton clrBtn = new JButton("CLEAR");
        clrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // clear all the fields.
                usernameTextField.setText("");
                usernameReEnterField.setText("");
            }
        });
        clrBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(clrBtn);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.LIGHT_GRAY);
        panel_2.add(panel_5);

        managerTextPane.setContentType("MESSAGE CENTER\r\n");
        panel_5.add(managerTextPane);
        managerTextPane.setToolTipText("MESSAGE CENTER");
        managerTextPane.setText("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n                                                                                                                                      ");
    }

    private void removeUserAccountPanel(){
        removeUserAccount_Panel = new JPanel();
        removeUserAccount_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        removeUserAccount_Panel.setLayout(new BorderLayout(10, 10));
        if(managerAccount != null){
            removeUserAccount_Panel = new JPanel(new BorderLayout());
            JPanel topLayout = new JPanel();
            topLayout.setLayout(new BoxLayout(topLayout, BoxLayout.PAGE_AXIS));
            JPanel fieldAndButton = new JPanel(new FlowLayout());

            JPanel bottomLayout = new JPanel(new FlowLayout());

            // Components
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JButton viewUsers_Button = new JButton("View User List");
            viewUsers_Button.addActionListener(e -> {
                String stringToSetTextArea = "";
                for(UserAccount currentAccount : managerAccount.getUserAccounts().getUsers()){
                    if(currentAccount != null){
                        stringToSetTextArea += currentAccount.getUsername() + "\n";
                    }
                }
                textArea.setText(stringToSetTextArea);
            });
            JLabel requestEntry_Label = new JLabel("Username to remove: ");
            JTextField requestEntry_TextField = new JTextField();
            requestEntry_TextField.setColumns(10);

            JButton removeUserButton = new JButton("Remove");
            removeUserButton.addActionListener(e -> {
                String username = requestEntry_TextField.getText();
                if(username.trim().length() > 0){
                    // remove the user, specified by the username
                    boolean removed = managerAccount.removeUser(managerAccount.findByUsername(username));
                    if(removed){
                        c1.show(panelCont, managerAccountPanel_title);
                        managerTextPane.setText("Account removed: " + username);
                        saveSettings();
                    }else{
                        c1.show(panelCont, managerAccountPanel_title);
                        managerTextPane.setText("Account failed to remove: " + username);
                    }
                }else{
                    c1.show(panelCont, managerAccountPanel_title);
                    managerTextPane.setText("No username entered.");
                }
            });
            removeUserButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

            JButton cancelBtn = new JButton("Cancel");
            cancelBtn.addActionListener(e -> c1.show(panelCont, managerAccountPanel_title));
            cancelBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

            topLayout.add(textArea);
            topLayout.add(viewUsers_Button);
            fieldAndButton.add(requestEntry_Label);
            fieldAndButton.add(requestEntry_TextField);
            fieldAndButton.add(removeUserButton);
            topLayout.add(fieldAndButton);
            bottomLayout.add(cancelBtn);
            removeUserAccount_Panel.add(topLayout, BorderLayout.CENTER);
            removeUserAccount_Panel.add(bottomLayout, BorderLayout.SOUTH);
        }
    }

    private void UserActPanel(){
        //deposit
        JList list1 = new JList();
        JList fromAccountList = new JList();
        list1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        JTextArea textArea = new JTextArea();
        // withdraw (WE SHOULD CHANGE NAME TO LIST2)
        JList list3 = new JList();
        list3.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        userAccount_Panel = new JPanel();
        // row, col
        userAccount_Panel.setLayout(new GridLayout(4,3));

        JPanel depPanel = new JPanel();
        userAccount_Panel.add(depPanel);
        // TODO: FIGURE OUT WHY THIS ISNT WORKING PROPERLY

//        // attempting to print current status for the user after initial login. throwing null pointer exception so ive put it in a try catch block to keep it from crashing
//        try{
//
//            double savingsAcctBal = currentAccountOpen.getSavingsAccount().getBalance();
//            double checkingAcctBal =currentAccountOpen.getchAccount().getBalance();
//            double creditAcctBal = currentAccountOpen.getCcAccount().getAmountLeft();
//            textArea.setText("---CURRENT ACCOUNT STATUS---\n " +
//                    " SAVINGS: $" + savingsAcctBal + "\n" +
//                    " CHECKINGS: $" + checkingAcctBal + "\n" +
//                    " CREDIT: $" + creditAcctBal);
//
//
//        }catch(Exception f){
//            // deals w null pointer from user loggin in. We want to show them there account standings but will show them reguardless after every action they do in the menus.
//            textArea.setText("Error: Trouble processing account standings.");
//        }


        JButton depositBtn = new JButton("DEPOSIT");
        depositBtn.setBackground(Color.LIGHT_GRAY);
        depositBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        depositBtn.addActionListener(e -> {
            int index = list1.getSelectedIndex();
            if(amountTextField!=null && index == 0){
                try {
                    double amount = Double.parseDouble(amountTextField.getText());

                    // account types: 1 = savings, 2= checking.
                    // have to surround this call with a try or catch if the cast fails due to an error.

                    // type, amount.
                    currentAccountOpen.deposit(1, amount);

                    textArea.setText("deposit to savings acct $"  + amount + "\n" + "---CURRENT ACCOUNT STATUS---\n " +
                            " SAVINGS: $" + currentAccountOpen.getSavingsAccount().getBalance() + "\n" +
                            " CHECKINGS: $" + currentAccountOpen.getchAccount().getBalance() + "\n" +
                            " CREDIT: $" + currentAccountOpen.getCcAccount().getAmountLeft());


                    // clear the textField and save this information.
                    amountTextField.setText("");
                    saveSettings();


                }catch (Exception b){
                    // input is not casting correctly.
                    // maybe instead of throwing the exception we try to help the user out.
                    textArea.setText("PLEASE RE-ENTER AMOUNT!");
                    amountTextField.setText("");
                    //throw new NumberFormatException();
                    // b.printStackTrace();
                }}
            if(amountTextField!=null && index == 1){
                try {
                    double amount = Double.parseDouble(amountTextField.getText());
                    currentAccountOpen.deposit(2, amount);
                    textArea.setText("deposit to checking acct $"  + amount + "\n" + "---CURRENT ACCOUNT STATUS---\n " +
                            " SAVINGS: $" + currentAccountOpen.getSavingsAccount().getBalance() + "\n" +
                            " CHECKINGS: $" + currentAccountOpen.getchAccount().getBalance() + "\n" +
                            " CREDIT: $" + currentAccountOpen.getCcAccount().getAmountLeft());
                    // clear the textArea and save the information.
                    textArea.setText("");
                    saveSettings();
                }catch (Exception c){
                    // textfield amount is not casting correctly.
                    throw new NumberFormatException();
                    // c.printStackTrace();
                }
            }


        });
        depPanel.add(depositBtn);

        JLabel amountLbl = new JLabel("AMOUNT:");
        amountLbl.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        depPanel.add(amountLbl);

        amountTextField = new JTextField();
        depPanel.add(amountTextField);
        amountTextField.setColumns(10);

        JLabel toLbl = new JLabel("TO:");
        toLbl.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        depPanel.add(toLbl);


        list1.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        depPanel.add(list1);


        JPanel withPanel = new JPanel();
        userAccount_Panel.add(withPanel);

        JButton withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.setBackground(Color.LIGHT_GRAY);
        withdrawBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        withdrawBtn.addActionListener(arg0 -> {
            int index = list3.getSelectedIndex();
            if(withdrawTxtField !=null && index == 0) {
                try {
                    double amount = Double.parseDouble(withdrawTxtField.getText());
                    // savings account

                    currentAccountOpen.withdraw(1, amount);
                    // let the user know what happened and save settings.
                    textArea.setText(amount + " " + " withdrawn from savings account");
                    withdrawTxtField.setText("");
                    saveSettings();
                }catch (Exception f){
                    //f.printStackTrace()
                    //throw new NumberFormatException();
                    textArea.setText("PLEASE RE-ENTER AMOUNT!");
                    withdrawTxtField.setText("");
                }

            }
            else if(withdrawTxtField !=null && index == 1){
                // checking account
                try{
                    double amount = Double.parseDouble(withdrawTxtField.getText());
                    currentAccountOpen.withdraw(2,amount);
                    // let the user know what happened
                    textArea.setText(amount + " " + " withdrawn from Checking Account");
                    withdrawTxtField.setText("");
                    saveSettings();

                }catch (Exception g){
                    //g.printStackTrace();
                    //throw new NumberFormatException();
                    textArea.setText("PLEASE RE-ENTER AMOUNT");
                    withdrawTxtField.setText("");
                }

            }
            else if(withdrawTxtField !=null && index == 2){
                // credit account
                try{
                    double amount = Double.parseDouble(withdrawTxtField.getText());
                    currentAccountOpen.withdraw(3,amount);
                    textArea.setText(amount + " " + " withdrawn from Credit Account");
                    withdrawTxtField.setText("");
                    saveSettings();
                }catch (Exception h){
                    //h.printStackTrace();
                    //throw new NumberFormatException
                    textArea.setText("PLEASE RE-ENTER AMOUNT");
                    withdrawTxtField.setText("");
                }

            }


        });
        withPanel.add(withdrawBtn);

        JLabel amntLbl = new JLabel("AMOUNT:");
        amntLbl.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        withPanel.add(amntLbl);

        // this is third amount textfield
        withdrawTxtField = new JTextField();
        withPanel.add(withdrawTxtField);
        withdrawTxtField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("TO:");
        lblNewLabel_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        withPanel.add(lblNewLabel_1);

        //      CONFUSING BECAUSE WE HAD REMOVED PREVIOS LIST FROM ABOVE SO NOW THE SECOND LIST IS REALLY LABELED AS THE THIRD.

        list3.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        withPanel.add(list3);


        JPanel TranPanel = new JPanel();
        userAccount_Panel.add(TranPanel);

        JButton tranBtn = new JButton("TRANSFER");
        tranBtn.addActionListener(e -> {
            try{

                int index = fromAccountList.getSelectedIndex();
                if(transToTxtField.getText() !=null && transferTxtField!=null && index == 0 ){  // savings account
                    double amount = Double.parseDouble(transferTxtField.getText());
                    String username = transToTxtField.getText();
                    if(managerAccount.getUserAccounts().findByUsername(username) !=null && managerAccount.getUsername().equals(username)){
                        // the user they have selected has been found and now we can transfer the object money and save the setting
                        UserAccount accountTransferTo = managerAccount.findByUsername(username);
                        // take money away from the first user
                        currentAccountOpen.withdraw(1,amount);
                        // transfer method doesnt really let us do this as easily as just generically depositing all transfer from account to account
                        accountTransferTo.deposit(1,amount);
                        textArea.setText("Transfered "  + amount + " From Savings Accoount" +
                                "\n To " + accountTransferTo.getUsername()
                                + "\n" + "---CURRENT ACCOUNT STATUS---\n " +
                                " SAVINGS: $" + currentAccountOpen.getSavingsAccount().getBalance() + "\n" +
                                " CHECKINGS: $" + currentAccountOpen.getchAccount().getBalance() + "\n" +
                                " CREDIT: $" + currentAccountOpen.getCcAccount().getAmountLeft());
                    }else{
                        // not good couldnt find the user.
                        textArea.setText("USER NOT FOUND PLEASE TRY AGAIN");
                        // reset the fields
                        transferTxtField.setText("");
                        transToTxtField.setText("");
                    }

                }
            }catch (Exception h){
                textArea.setText("PLEASE RE-ENTER TRANSFER AMOUNT");
                transferTxtField.setText("");
            }
            try{
                int index = fromAccountList.getSelectedIndex();
                if(transToTxtField.getText() !=null && transferTxtField!=null && index == 1 ){  // savings account
                    double amount = Double.parseDouble(transferTxtField.getText());
                    String username = transToTxtField.getText();
                    if(managerAccount.getUserAccounts().findByUsername(username) !=null && managerAccount.getUsername().equals(username)){
                        // the user they have selected has been found and now we can transfer the object money and save the setting
                        UserAccount accountTransferTo = managerAccount.findByUsername(username);
                        // take money away from the first user
                        currentAccountOpen.withdraw(2,amount);
                        // transfer method doesnt really let us do this as easily as just generically depositing all transfer from account to account
                        accountTransferTo.deposit(1,amount);
                        textArea.setText("Transfered "  + amount + " From Checking Accoount" +
                                "\n To " + accountTransferTo.getUsername()
                                + "\n" + "---CURRENT ACCOUNT STATUS---\n " +
                                " SAVINGS: $" + currentAccountOpen.getSavingsAccount().getBalance() + "\n" +
                                " CHECKINGS: $" + currentAccountOpen.getchAccount().getBalance() + "\n" +
                                " CREDIT: $" + currentAccountOpen.getCcAccount().getAmountLeft());
                    }else{
                        // not good couldnt find the user.
                        textArea.setText("USER NOT FOUND PLEASE TRY AGAIN");
                        // reset the fields
                        transferTxtField.setText("");
                        transToTxtField.setText("");
                    }

                }
            }catch (Exception i){
                textArea.setText("PLEASE RE-ENTER TRANSFER AMOUNT");
                transferTxtField.setText("");
            }
        });
        tranBtn.setBackground(Color.LIGHT_GRAY);
        tranBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        tranBtn.addActionListener(e -> {

        });
        TranPanel.add(tranBtn);

        JLabel lblNewLabel = new JLabel("AMOUNT:");
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel);

        transferTxtField = new JTextField();
        TranPanel.add(transferTxtField);
        transferTxtField.setColumns(10);


        JLabel lblNewLabel_3 = new JLabel("TO:");
        lblNewLabel_3.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel_3);



        // we really dont even want a list. talk to nick about using the j combox box that he had made for the previous example.
        JList toAccountList = new JList();
        toAccountList.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        toAccountList.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        TranPanel.add(toAccountList);

        JLabel lblNewLabel_4 = new JLabel("FROM");
        lblNewLabel_4.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel_4);

        fromAccountList.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        fromAccountList.setModel(new AbstractListModel() {

            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        TranPanel.add(fromAccountList);

        transToTxtField = new JTextField();
        transToTxtField.setColumns(10);
        TranPanel.add(transToTxtField);

        JPanel panel = new JPanel();
        userAccount_Panel.add(panel);

        //add button here.
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(Color.LIGHT_GRAY);
        logoutBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        textArea.setColumns(52);
        textArea.setRows(3);
        panel.add(textArea);
        panel.add(logoutBtn);

        logoutBtn.addActionListener(e -> {
            c1.show(panelCont, loginPanel_title);
            // Logs user out of program returning to the main screen.
        });
    }

    private void createManagerPanel(){
        createManager_Panel = new JPanel();
        createManager_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        createManager_Panel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        createManager_Panel.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("WELCOME, NEW MANAGER!");
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        createManager_Panel.add(panel_1, BorderLayout.CENTER);
        // rows ,cols
        panel_1.setLayout(new GridLayout(5, 2));

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_3);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel_2 = new JLabel("FIRST-NAME ");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_2);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        panel_3.add(usernameTextField);

        JLabel lblNewLabel_3 = new JLabel("LAST-NAME");
        lblNewLabel_3.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_3);

        textField_1 = new JTextField();
        panel_3.add(textField_1);
        textField_1.setColumns(10);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_4);

        JLabel lblNewLabel_4 = new JLabel("PASSWORD ");
        lblNewLabel_4.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_4.add(lblNewLabel_4);

        textField_2 = new JTextField();
        panel_4.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("AGE ");
        lblNewLabel_5.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_4.add(lblNewLabel_5);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        panel_4.add(textField_3);

        JLabel lblNewLabel_6 = new JLabel("USERNAME ");
        lblNewLabel_6.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_4.add(lblNewLabel_6);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        panel_4.add(textField_4);


        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_7);

        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8);

        JButton btnNewButton = new JButton("CREATE");
        // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
        btnNewButton.addActionListener(e -> {
            // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
            if(usernameTextField.getText()!=null && textField_1.getText()!=null && textField_2.getText()!=null && textField_3.getText()!=null && textField_4.getText()!=null) {
                // if the fields are not null we are going to expect that they have entered the information correctly
                // create temp variables to create user.
                String firstName = usernameTextField.getText();
                String lastName = textField_1.getText();
                String password = textField_2.getText();
                String userName = textField_4.getText();
                int age = Integer.parseInt(textField_3.getText());
                managerAccount = new ManagerAccount(firstName, lastName, age, userName, password);
                saveSettings();
                System.exit(0); // close the program
            }
        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_8.add(btnNewButton);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(e -> {
            // Manager account not created -- kill the program
            System.exit(0);
        });
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_8.add(cancelButton);

        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6);
    }

    private void userHistoryPanel(){
        userHistoryPanel = new JPanel();
        if(managerAccount != null){
            userHistoryPanel.setLayout(new BorderLayout(0, 0));

            JPanel textPanel = new JPanel();
            userHistoryPanel.add(textPanel, BorderLayout.CENTER);

            JTextArea textArea = new JTextArea();
            textArea.setRows(40);
            textArea.setColumns(65);
            textPanel.add(textArea);
            int count = 0;


            for(UserAccount account : managerAccount.getUserAccounts().getUsers()){
                if(account != null){
                    count++;

                    textArea.append("USER " + count +  " " + "USERNAME: "+account.getUsername() + "  " + "FIRSTNAME: "+account.getFirstName() + "  " +
                            "LASTNAME: "+account.getLastName() + "  " + "AGE: "+ account.getAge() + "\n");
                }
            }


            JPanel buttonPanel = new JPanel();
            userHistoryPanel.add(buttonPanel, BorderLayout.SOUTH);

            JButton backBtn = new JButton("BACK");
            backBtn.addActionListener(e -> c1.show(panelCont, managerAccountPanel_title));
            backBtn.setBackground(Color.LIGHT_GRAY);
            backBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            buttonPanel.add(backBtn);

            JButton logoutBtn = new JButton("LOGOUT");
            logoutBtn.addActionListener(arg0 -> c1.show(panelCont,loginPanel_title));
            logoutBtn.setBackground(Color.LIGHT_GRAY);
            logoutBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            buttonPanel.add(logoutBtn);
        }
    }

    private void transactionPanel(){
        transactionsPanel = new JPanel();

        transactionsPanel.setLayout(new BorderLayout(0, 0));

        JPanel textPanel = new JPanel();
        transactionsPanel.add(textPanel, BorderLayout.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setRows(40);
        textArea.setColumns(65);
        textPanel.add(textArea);

        JPanel buttonPanel = new JPanel();
        transactionsPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton viewBtn = new JButton("VIEW");
        viewBtn.addActionListener(e -> {
            textArea.setText("");
            StringBuilder stringBuilder = new StringBuilder();
            for(UserAccount userAccount : managerAccount.getUserAccounts().getUsers()){
                if(userAccount != null){
                    stringBuilder.append("Username: " + userAccount.getUsername() + ":");
                    stringBuilder.append("\n");
                    stringBuilder.append(userAccount.getTransactionList().toString());
                    stringBuilder.append("\n\n\n");
                }
            }
            textArea.append(stringBuilder.toString());
        });
        viewBtn.setBackground(Color.LIGHT_GRAY);
        viewBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        buttonPanel.add(viewBtn);

        JButton backBtn = new JButton("BACK");
        backBtn.addActionListener(e -> {
            c1.show(panelCont, managerAccountPanel_title);
            textArea.setText(""); // we have to empty the text area
        });
        backBtn.setBackground(Color.LIGHT_GRAY);
        backBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        buttonPanel.add(backBtn);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        logoutBtn.addActionListener(arg0 -> c1.show(panelCont,loginPanel_title));
        logoutBtn.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(logoutBtn);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // DESERIALIZE managerAccount
            managerAccount = FileOps.deserialize(); // might be null

//               ManagerAccount differentManagerAccount = new ManagerAccount("Jeff", "Linkman", 43, "username", "password");
//               FileOps.serialize(differentManagerAccount);
//               System.exit(0);
            new Application();
        });
    }

    /** Save settings to the file */
    private static void saveSettings(){
        // serialize the file
        boolean didSerialize = FileOps.serialize(managerAccount);
        if(didSerialize){
            System.out.println("*saved settings*");
        }
    }
}