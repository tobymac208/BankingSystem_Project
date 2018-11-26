package com.groupproject.group.Windows;

import com.groupproject.group.Account.LoginAccount.ManagerAccount;
import com.groupproject.group.Account.LoginAccount.UserAccount.UserAccount;
import com.groupproject.group.Utility.FileOps;

import java.awt.*;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;


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
    JPanel panelCont = new JPanel();
    private JPanel firstpanel;
    private JPanel secondpanel;
    private JPanel thirdpanel;
    private JPanel fourthpanel;
    CardLayout c1 = new CardLayout();

    // then the other variables that we needed.
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField amountTextField;

    public void LoginPanel() {
        // all of this is now from the login menu itself and can be created using the building tool and we can hook it up from there
        JPanel panel = new JPanel();
        firstpanel = new JPanel();
        firstpanel = new JPanel();
        firstpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        firstpanel.setLayout(new BorderLayout(0, 0));
        firstpanel.add(panel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("BANKING PORTAL");
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(titleLabel);

        JPanel panel_1 = new JPanel();
        firstpanel.add(panel_1, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("DATE AND TIME/VERSION");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(lblNewLabel_2);

        // This label is to alert the user if something worked or not
        JLabel successMessage = new JLabel("");
        successMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(successMessage); // add the label

        JPanel panel_2 = new JPanel();
        firstpanel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Application.class.getResource("/com/groupproject/group/Resources/Images/00205-3D-art-logo-design-free-logos-online-011.png")));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblNewLabel_3, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JCheckBox isAdminCheckbox = new JCheckBox("admin");
        isAdminCheckbox.setBackground(Color.LIGHT_GRAY);
        isAdminCheckbox.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(isAdminCheckbox);

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
                    // The manager account is not null
                    else{
                        String username = usernameField.getText(), password = passwordField.getText();
                        if(isAdminCheckbox.isSelected()){ // try to login the administrator
                            System.err.println("DEBUG CODE: Checkbox was selected.");
                            System.err.println("DEBUG CODE: Username: " + managerAccount.getUsername() + " password: " + managerAccount.getPassword());
                            System.err.println("DEBUG CODE: Username: " + username + " password: " + password);
                            if(managerAccount.getUsername().equals(username) && managerAccount.getPassword().equals(password)){
                                System.err.println("DEBUG CODE: This was true.");
                                c1.show(panelCont, "3");
                                successMessage.setText("Login successful! Manager logged in.");
                            }
                        // login a user account
                        }else{
                            // TODO: WE NEED TO FIGURE THIS BUG OUT HERE AND PROPERLY CALL cl.show(panelCont, "3");
                            // also since we dont have the gui for the user account yet can we place a comment
                            // with the call cl.show(panelCont, "#"); #indicates what frame will show next.
                            if (managerAccount.getUserAccounts().findByUsername(username) !=null && managerAccount.getUsername().equals(username)){
                                // username found
                                UserAccount accountLoggingIn = managerAccount.findByUsername(username);
                                if (accountLoggingIn.getPassword().equals(password)) { // password is the same
                                    currentAccountOpen = managerAccount.findByUsername(username); // login the user
                                    successMessage.setText("Account logged in.");
                                    c1.show(panelCont, "4");
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

        JButton createAccountButton = new JButton("CREATE");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // we are going to open the other GUI after we verify CREATE has been clicked.
                // well we need to use this.dispose() to close previous GUI's
                if (e.getSource() == createAccountButton) {
                    // Similar Catch/Block statement used in main to intially launch the application.
                    c1.show(panelCont, "2");
                }
            }
        });

        createAccountButton.setBackground(Color.LIGHT_GRAY);
        createAccountButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(createAccountButton);
    }

    public void CreateAccountPanel() {
        secondpanel = new JPanel();
        secondpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        secondpanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        secondpanel.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("WELCOME NEW-USER");
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        secondpanel.add(panel_1, BorderLayout.CENTER);
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
        panel_3.add(usernameTextField);
        usernameTextField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel(" LAST-NAME");
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
                if(usernameTextField.getText()!=null && textField_1.getText()!=null && textField_2.getText()!=null && textField_3.getText()!=null && textField_4.getText()!=null)
                {
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
                    managerAccount.addUser(newAccount);
                    System.out.println("Added: " + newAccount.getUsername());
                    // save settings
                    saveSettings();
                }
            }

        });
        panel_8.add(btnNewButton);
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        // please edit me or make me larger so i can fill the screen.
        lblNewLabel_1.setIcon(new ImageIcon(("/com/groupproject/group/Resources/Images/texture2.jpg")));
        panel_6.add(lblNewLabel_1);
    }

    public void ManagerPanel(){
        thirdpanel = new JPanel();
        thirdpanel.setBackground(Color.LIGHT_GRAY);
        thirdpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        thirdpanel.setLayout(new BorderLayout(0, 0));


        JLabel headerLabel = new JLabel("MANAGER ACCOUNT\r\n");
        headerLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thirdpanel.add(headerLabel, BorderLayout.NORTH);

        JLabel footerLabel = new JLabel("VERSION 1.08\r\n");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thirdpanel.add(footerLabel, BorderLayout.SOUTH);

        JPanel mangerControlPanel = new JPanel();
        mangerControlPanel.setBackground(Color.LIGHT_GRAY);
        thirdpanel.add(mangerControlPanel, BorderLayout.WEST);
        // 3 rows 1 col
        mangerControlPanel.setLayout(new GridLayout(6,1));

        JButton addBtn = new JButton("ADD-USER");
        addBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        mangerControlPanel.add(addBtn);

        JButton removeBtn = new JButton("REMOVE-USER");
        removeBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        mangerControlPanel.add(removeBtn);

        JButton historyBtn = new JButton("USER-HISTORY");
        historyBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        mangerControlPanel.add(historyBtn);

        JButton transBtn = new JButton("TRANSACTIONS");
        transBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        mangerControlPanel.add(transBtn);

        JPanel panel = new JPanel();
        thirdpanel.add(panel, BorderLayout.CENTER);
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
        panel_3.add(usernameTextField);
        usernameTextField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("RE-ENTER");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_2);

        textField_1 = new JTextField();
        panel_3.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CLEAR");
        btnNewButton_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton_1);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.LIGHT_GRAY);
        panel_2.add(panel_5);

        JTextPane textPane = new JTextPane();
        textPane.setContentType("MESSEGE CENTER\r\n");
        panel_5.add(textPane);
        textPane.setToolTipText("MESSEGE CENTER");
        textPane.setText("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n                                                                                                                                      ");
    }

    public void UserActPanel(){
        fourthpanel = new JPanel();
        // row, col
        fourthpanel.setLayout(new GridLayout(4,3));

        JPanel depPanel = new JPanel();
        fourthpanel.add(depPanel);

        JButton depositBtn = new JButton("DEPOSIT");
        depPanel.add(depositBtn);

        JLabel amountLbl = new JLabel("AMOUNT:");
        depPanel.add(amountLbl);

        amountTextField = new JTextField();
        amountTextField.setText("$");
        depPanel.add(amountTextField);
        amountTextField.setColumns(10);

        JLabel toLbl = new JLabel("TO:");
        depPanel.add(toLbl);

        JList list1 = new JList();
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

        JLabel fromLbl = new JLabel("FROM:");
        depPanel.add(fromLbl);

        JList list2 = new JList();
        list2.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        depPanel.add(list2);

        JPanel withPanel = new JPanel();
        fourthpanel.add(withPanel);

        JButton withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        withPanel.add(withdrawBtn);

        JLabel amntLbl = new JLabel("AMOUNT:");
        withPanel.add(amntLbl);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setText("$");
        withPanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("TO:");
        withPanel.add(lblNewLabel_1);

        JList list3 = new JList();
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

        JLabel lblNewLabel_2 = new JLabel("FROM:");
        withPanel.add(lblNewLabel_2);

        JList list4 = new JList();
        list4.setModel(new AbstractListModel() {
            String[] values = new String[] {"Savings", "Checking", "Credit"};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        withPanel.add(list4);

        JPanel TranPanel = new JPanel();
        fourthpanel.add(TranPanel);

        JButton tranBtn = new JButton("TRANSFER");
        tranBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        TranPanel.add(tranBtn);

        JLabel lblNewLabel = new JLabel("AMOUNT:");
        TranPanel.add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setText("$");
        TranPanel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("TO:");
        TranPanel.add(lblNewLabel_3);

        JList list5 = new JList();
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

        JLabel lblNewLabel_4 = new JLabel("FROM:");
        TranPanel.add(lblNewLabel_4);

        JList list6 = new JList();
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
        fourthpanel.add(panel);

        JTextArea textArea = new JTextArea();
        textArea.setColumns(52);
        textArea.setRows(3);
        panel.add(textArea);
    }

    /**
     * WE LAUNCH THE APPLICATION HERE AND EVERYTHING NEEDS TO BE CALLED AND ADDED HERE
     **/
    public Application() {
        // call panels here
        LoginPanel();
        CreateAccountPanel();
        ManagerPanel();
        UserActPanel();
        // everything gets added to here now panel wise. we just have to give it the attributes we did
        panelCont.setLayout(c1);
        // we have to finally add this panel to the panelCont.
        panelCont.add(firstpanel, "1");
        panelCont.add(secondpanel, "2");
        panelCont.add(thirdpanel, "3");
        panelCont.add(fourthpanel, "4");
        // this is what panel we want to show right away.
        c1.show(panelCont, "1");
        // finally add it to the frame.
        frame.getContentPane().add(panelCont);

        //leave everything below this
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/com/groupproject/group/Resources/Images/folder-red-java-icon.png")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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