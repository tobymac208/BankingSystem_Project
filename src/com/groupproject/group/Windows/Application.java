package com.groupproject.group.Windows;

/**
 * Description: This class contains all of the GUI's implementation. It is run using a Swing's CardLayout.
 */

import com.groupproject.group.Account.LoginAccount.ManagerAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Utility.FileOps;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import static com.groupproject.group.Main.createManager;

public class Application extends JPanel {
     //private static ManagerAccount managerAccount = new ManagerAccount("Jeff", "Linkman", 43, "linklink", "password"); -- here for testing purposes
    private static ManagerAccount managerAccount;
    private static UserAccount currentAccountOpen; // used for holding the current account's info
    // scanner to read in data from the user
    private static Scanner stringInput = new Scanner(System.in);
    private static Scanner intInput = new Scanner(System.in);
    JFrame frame = new JFrame();

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
    CardLayout c1 = new CardLayout();

    // Strings for all the panels
    private final String loginPanel_title = "login";
    private final String createAccountPanel_title = "createAccount";
    private final String managerAccountPanel_title = "managerAccount";
    private final String userAccountPanel_title = "userAccount";
    private final String removeAccountPanel_title = "removeUserAccount";

    // then the other variables that we needed.
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField amountTextField;

    /**
     * WE LAUNCH THE APPLICATION HERE AND EVERYTHING NEEDS TO BE CALLED AND ADDED HERE
     **/
    public Application() {
        // call panels here
        LoginPanel();
        CreateAccountPanel();
        ManagerPanel();
        UserActPanel();
        removeUserAccountPanel();
        // everything gets added to here now panel wise. we just have to give it the attributes we did
        panelCont.setLayout(c1);
        // we have to finally add this panel to the panelCont.
        panelCont.add(login_Panel, loginPanel_title);
        panelCont.add(createAccount_Panel, createAccountPanel_title);
        panelCont.add(managerAccount_Panel, managerAccountPanel_title);
        panelCont.add(userAccount_Panel, userAccountPanel_title);
        panelCont.add(removeUserAccount_Panel, removeAccountPanel_title);
        // this is what panel we want to show right away.
        c1.show(panelCont, loginPanel_title);
        // finally add it to the frame.
        frame.getContentPane().add(panelCont);

        //leave everything below this
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/com/groupproject/group/Resources/Images/folder-red-java-icon.png")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void LoginPanel() {
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
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(loginButton);
    }

    public void CreateAccountPanel() {
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

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("CREDIT ACCOUNT");
        chckbxNewCheckBox_1.setBackground(Color.LIGHT_GRAY);
        chckbxNewCheckBox_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_7.add(chckbxNewCheckBox_1);

        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8);

        JButton btnNewButton = new JButton("CREATE");
        // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // we have to add the action listener here so that it takes all the fields from the panel and creates the user if they are entered correctly.
                if(usernameTextField.getText()!=null && textField_1.getText()!=null && textField_2.getText()!=null && textField_3.getText()!=null && textField_4.getText()!=null) {
                    // if the fields are not null we are going to expect that they have entered the information correctly
                    // create temp variables to create user.
                    String firstName = usernameTextField.getText();
                    String lastName = textField_1.getText();
                    String password = textField_2.getText();
                    String age =  textField_3.getText();
                    String userName = textField_4.getText();
                    int age2 = Integer.parseInt(age);
                    // create user account and then add each account type that they specify on wanting.
                    // create a new account
                    boolean hasCreditAccount = chckbxNewCheckBox_1.isSelected();
                    UserAccount newAccount = new UserAccount(firstName, lastName, age2, userName, password, hasCreditAccount);
                    // add it to the ManagerAccount object
                    boolean accountAdded = managerAccount.addUser(newAccount);
                    if(accountAdded){
                        System.out.println("Added: " + newAccount.getUsername());
                        // save settings
                        saveSettings();
                        c1.show(panelCont, managerAccountPanel_title); // go back to the manager account panel
                        // TODO: SEE ME - if we take the textPane that we are using in the managerPanel and make it a global variable we may be able to let the manager know that the user has indeed been added.

                    }else{
                        System.err.println("Account failed to add.");
                    }
                }
            }
        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_8.add(btnNewButton);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, "managerAccount");                                                                                                                                                                                                                        
                // empty all of the text fields
                usernameTextField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
            }
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

    public void ManagerPanel(){
        managerAccount_Panel = new JPanel();
        managerAccount_Panel.setBackground(Color.LIGHT_GRAY);
        managerAccount_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        managerAccount_Panel.setLayout(new BorderLayout(0, 0));
        JTextPane textPane = new JTextPane();

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
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // we are going to open the other GUI after we verify CREATE has been clicked.
                // well we need to use this.dispose() to close previous GUI's
                if (e.getSource() == addBtn) {
                    // Similar catch block statement used in main to initially launch the application.
                    c1.show(panelCont, createAccountPanel_title);
                }
            }
        });
        addBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(addBtn);

        JButton removeBtn = new JButton("REMOVE-USER");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, removeAccountPanel_title);
            }
        });
        removeBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(removeBtn);

        JButton historyBtn = new JButton("USER-HISTORY");
        historyBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(historyBtn);

        JButton transBtn = new JButton("TRANSACTIONS");
        transBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        managerControlPanel.add(transBtn);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, loginPanel_title);
            }
        });
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
                // TODO: Make sure that this is working and understood.
                // make sure something is input here.
                if(usernameTextField.getText() !=null && usernameReEnterField.getText() !=null){
                    // make sure username is the same on both sides
                    if(usernameTextField.getText().equals(usernameReEnterField.getText())){
                        // perform the search.
                        String username = usernameTextField.getText();
                        if(username.equals(managerAccount.findByUsername(username))){
                            // we have found the user.
                            //display to textPane.
                            textPane.setText("USER FOUND:" + username);


                        }else{
                            textPane.setText("USER WAS NOT FOUND");
                            // clear the fields for the user
                            usernameTextField.setText("");
                            usernameReEnterField.setText("");
                        }
                    }else{
                        textPane.setText("USERNAMES DO NOT MATCH TRY AGAIN!");
                        // clear the fields for the user.
                        usernameTextField.setText("");
                        usernameReEnterField.setText("");
                    }

                }else{
                    textPane.setText("PLEASE ENTER SOMETHING IN THE FIELDS!");
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

        textPane.setContentType("MESSAGE CENTER\r\n");
        panel_5.add(textPane);
        textPane.setToolTipText("MESSAGE CENTER");


        textPane.setText("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n                                                                                                                                      ");
    }

    public void removeUserAccountPanel(){
        removeUserAccount_Panel = new JPanel(new BorderLayout());
        JPanel centerLayout = new JPanel(new GridLayout(2, 2));

        // Components
        JComboBox<String> userNames = new JComboBox<>();
        // Add all the user names to the list
        for(UserAccount account : managerAccount.getUserAccounts().getUsers()){
            if(account != null){
                userNames.addItem(account.getUsername());
            }
        }

        JButton removeUserButton = new JButton("Remove");
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement remove functionality
            }
        });
        removeUserButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, managerAccountPanel_title);
            }
        });
        cancelBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        centerLayout.add(userNames);
        centerLayout.add(removeUserButton);
        centerLayout.add(cancelBtn);
        removeUserAccount_Panel.add(centerLayout, BorderLayout.CENTER);
    }

    public void UserActPanel(){
        //deposit
        JList list1 = new JList();
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

        JButton depositBtn = new JButton("DEPOSIT");
        depositBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        depositBtn.addActionListener(new ActionListener() {
            // we just have to make sure that the "$" that we are creating in the box is added to the value of amount.

            @Override
            // amountextfields cannot be negative or a none number. must not take either.
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if(amountTextField!=null && index == 0){
                    try {
                        double amount = Double.parseDouble(amountTextField.getText());

                        // account types: 1 = savings, 2= checking.
                        // have to surround this call with a try or catch if the cast fails due to an error.

                        // type, amount.
                        currentAccountOpen.deposit(1, amount);
                        textArea.setText("deposit to savings acct" + amount);
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
                        textArea.setText("deposit to checking acct");
                        // clear the textArea and save the information.
                        textArea.setText("");
                        saveSettings();
                    }catch (Exception c){
                        // textfield amount is not casting correctly.
                        throw new NumberFormatException();
                        // c.printStackTrace();
                    }
                }
                // TODO: MAKE SURE DEPOSIT METHOD LETS TYPE 3 FOR CREDIT ACCOUNT. deposit(types,amount)
                if(amountTextField!=null && index == 2){
                    try {
                        double amount = Double.parseDouble(amountTextField.getText());
                        textArea.setText("deposit to credit acct");

                        currentAccountOpen.deposit(3, amount);
                        textArea.setText("");
                        saveSettings();
                    }catch (Exception d){
                        throw new NumberFormatException();
                        //d.printStackTrace();
                    }
                }
                else if(amountTextField == null ){
                    // do something if they dont enter something and want to click on the button
                    textArea.setText("PLEASE ENTER AN AMOUNT!");
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


        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // make a variable so that the list variable can be copied and compared to what the user selected.
                int index = list1.getSelectedIndex();
                if(index == 0){
                    // we are in savings account.


                    //works
                   // textArea.setText("Savings selected");
                }
                else if(index == 1){
                    // we are in checkings account

                    //works
                   // textArea.setText("Checking selected");

                }
                else if(index == 2) {
                    // we are in credit account.

                    //works
                   // textArea.setText("Credit selected");
                }
            }
        });
        list1.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
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
        withdrawBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int index = list3.getSelectedIndex();
                if(amountTextField !=null && index == 0) {
                    try {
                        double amount = Double.parseDouble(amountTextField.getText());
                        // savings account

                        currentAccountOpen.withdraw(1, amount);
                        // let the user know what happened and save settings.
                        textArea.setText(amount + " " + " withdrawn from savings account");
                        amountTextField.setText("");
                        saveSettings();
                    }catch (Exception f){
                        //f.printStackTrace()
                        //throw new NumberFormatException();
                        textArea.setText("PLEASE RE-ENTER AMOUNT!");
                        amountTextField.setText("");
                    }

                }
                else if(amountTextField !=null && index == 1){
                    // checking account
                    try{
                        double amount = Double.parseDouble(amountTextField.getText());
                        currentAccountOpen.withdraw(2,amount);
                        // let the user know what happened
                        textArea.setText(amount + " " + " withdrawn from Checking Account");
                        amountTextField.setText("");
                        saveSettings();

                    }catch (Exception g){
                        //g.printStackTrace();
                        //throw new NumberFormatException();
                        textArea.setText("PLEASE RE-ENTER AMOUNT");
                        amountTextField.setText("");
                    }

                }
                else if(amountTextField !=null && index == 2){
                    // credit account
                    try{
                        double amount = Double.parseDouble(amountTextField.getText());
                        currentAccountOpen.withdraw(3,amount);
                        textArea.setText(amount + " " + " withdrawn from Credit Account");
                        amountTextField.setText("");
                        saveSettings();
                    }catch (Exception h){
                        //h.printStackTrace();
                        //throw new NumberFormatException
                        textArea.setText("PLEASE RE-ENTER AMOUNT");
                        amountTextField.setText("");
                    }

                }


            }
        });
        withPanel.add(withdrawBtn);

        JLabel amntLbl = new JLabel("AMOUNT:");
        amntLbl.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        withPanel.add(amntLbl);

        JTextField usernameTextField = new JTextField();
        withPanel.add(usernameTextField);
        usernameTextField.setColumns(10);

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
        tranBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        tranBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: FINISH HOOKING UP FUNCTIONALITY
            }
        });
        TranPanel.add(tranBtn);

        JLabel lblNewLabel = new JLabel("AMOUNT:");
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel);

        textField_1 = new JTextField();
        TranPanel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("TO:");
        lblNewLabel_3.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel_3);

        JList list5 = new JList();
        list5.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        list5.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        TranPanel.add(list5);

        JLabel lblNewLabel_4 = new JLabel("FROM");
        lblNewLabel_4.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        TranPanel.add(lblNewLabel_4);


        JList list6 = new JList();
        list6.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        list6.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        TranPanel.add(list6);

        JPanel panel = new JPanel();
        userAccount_Panel.add(panel);

        //add buttoon here.
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        textArea.setColumns(52);
        textArea.setRows(3);
        panel.add(textArea);
        panel.add(logoutBtn);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(panelCont, loginPanel_title);
                // Logs user out of program returning to the main screen.
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // DESERIALIZE managerAccount
                managerAccount = FileOps.deserialize();
                if (managerAccount == null) {
                    System.out.println("There is no manager account. You must create one.");
                    managerAccount = createManager();
                    if (managerAccount == null) { // did the account creation fail?
                        System.out.println("Account creation failed.\nProject exiting.");
                        // close the program
                        System.exit(0);
                    }
                }

//               ManagerAccount differentManagerAccount = new ManagerAccount("Jeff", "Linkman", 43, "username", "password");
//               FileOps.serialize(differentManagerAccount);
//               System.exit(0);
                new Application();
            }
        });
    }

    /** Save settings to the file */
    public static void saveSettings(){
        // serialize the file
        boolean didSerialize = FileOps.serialize(managerAccount);
        if(didSerialize){
            System.out.println("*saved settings*");
        }
    }
}