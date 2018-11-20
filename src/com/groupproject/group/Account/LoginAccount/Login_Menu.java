package com.groupproject.group.Account.LoginAccount;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login_Menu extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login_Menu frame = new Login_Menu();


                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login_Menu() {
        setForeground(Color.LIGHT_GRAY);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Joe Kelly\\Pictures\\LOGOS\\folder-red-java-icon.png"));
        //photo is large and will screw over the aesthetic dont enlarge.
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);
        setTitle("BANKING PORTAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this felt like a good size.... on a 27inch monitor at least.
        setBounds(100, 100, 606, 455);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("BANKING PORTAL");
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("DATE AND TIME/VERSION");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(lblNewLabel_2);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Joe Kelly\\Pictures\\LOGOS\\00205-3D-art-logo-design-free-logos-online-011.png"));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblNewLabel_3, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JLabel lblNewLabel_4 = new JLabel("USERNAME:");
        lblNewLabel_4.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_4);

        textField = new JTextField();
        panel_3.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("PASSWORD:");
        lblNewLabel_5.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
        panel_3.add(lblNewLabel_5);

        passwordField = new JPasswordField(10);
        panel_3.add(passwordField);

        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CREATE");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // we are going to open the other GUI after we verify CREATE has been clicked.
                // well we need to use this.dispose() to close previous GUI's
                // Similar Catch/Block statement used in main to intially launch the applicatio
                if(e.getSource() == btnNewButton_1) {
                    try {

                        Account_Menu frame = new Account_Menu();
                        frame.setVisible(true);

                    } catch (IOException e1) {

                        e1.printStackTrace();
                    }
                }
            }
        });
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton_1);
    }
    public class Account_Menu extends JFrame {

        private JPanel contentPane;
        private JTextField textField;
        private JTextField textField_1;
        private JTextField textField_2;
        private JTextField textField_3;


        public Account_Menu() throws IOException {
            setResizable(false);
            setTitle("CREATE ACCOUNT");
            setIconImage(Toolkit.getDefaultToolkit().getImage("/com/groupproject/group/folder-red-java-icon.png"));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 606, 455); // mimicks the first windows size.
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(new BorderLayout(0, 0));
            setContentPane(contentPane);

            JPanel panel = new JPanel();
            contentPane.add(panel, BorderLayout.NORTH);

            JLabel lblNewLabel = new JLabel("WELCOME NEW-USER");
            lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            panel.add(lblNewLabel);

            JPanel panel_1 = new JPanel();
            contentPane.add(panel_1, BorderLayout.CENTER);
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

            textField = new JTextField();
            panel_3.add(textField);
            textField.setColumns(10);

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

            JLabel lblNewLabel_5 = new JLabel("NEW LABEL");
            lblNewLabel_5.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            panel_4.add(lblNewLabel_5);

            textField_3 = new JTextField();
            textField_3.setColumns(10);
            panel_4.add(textField_3);
            JCheckBox chckbxNewCheckBox = new JCheckBox("CHECKING ACCT");
            JPanel panel_5 = new JPanel();
            panel_5.add(chckbxNewCheckBox);

            JCheckBox chckbxNewCheckBox_1 = new JCheckBox("SAVINGS ACCT");
            panel_5.add(chckbxNewCheckBox_1);

            JPanel panel_7 = new JPanel();
            panel_7.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel_1.add(panel_7);

            JCheckBox chckbxNewCheckBox_2 = new JCheckBox("CHECKING ACCT");
            chckbxNewCheckBox_2.setBackground(Color.LIGHT_GRAY);
            chckbxNewCheckBox_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            panel_7.add(chckbxNewCheckBox_2);

            JCheckBox chckbxNewCheckBox_3 = new JCheckBox("SAVINGS ACCT");
            chckbxNewCheckBox_3.setBackground(Color.LIGHT_GRAY);
            chckbxNewCheckBox_3.setForeground(Color.BLACK);
            chckbxNewCheckBox_3.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            panel_7.add(chckbxNewCheckBox_3);

            JPanel panel_8 = new JPanel();
            panel_1.add(panel_8);
            // this create button is for the second GUI Account_Menu.
            // should only need to add users to our list.
            JButton btnNewButton = new JButton("CREATE");
            panel_8.add(btnNewButton);
            btnNewButton.setBackground(Color.LIGHT_GRAY);
            btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

            JPanel panel_6 = new JPanel();
            panel_1.add(panel_6);

            JLabel lblNewLabel_1 = new JLabel(" ");
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_1.setIcon(new ImageIcon(Account_Menu.class.getResource("/com/groupproject/group/texture2.jpg")));
            panel_6.add(lblNewLabel_1);




            JPanel panel_2 = new JPanel();


            dispose();
        }


    }

    // add the other GUI here so we can use main in login menu to run the other GUI/s
    // we want to be able to run the next gui at the actionPerformed method for clicking the button on login menu.

}


