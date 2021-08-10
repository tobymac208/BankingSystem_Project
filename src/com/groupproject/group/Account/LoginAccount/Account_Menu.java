package com.groupproject.group.Account.LoginAccount;

// import javax.swing.*;

    /**so basically the only reason why i have this class is because when you use eclipse window builder it doesnt recognize more
     * than one GUI at a time so if any work or updating needs to be done on these gui's it can be done here and tested before being
     * implemented into the main GUI for this project which is Login_Menu.java
     */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

    public class Account_Menu extends JFrame {

        private JPanel contentPane;
        private JTextField textField;
        private JTextField textField_1;
        private JTextField textField_2;
        private JTextField textField_3;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        // change L&F here
                        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");
                        Account_Menu frame = new Account_Menu();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * Create the frame.
         * @throws IOException
         */
        public Account_Menu() throws IOException {
            setResizable(false);
            setTitle("CREATE ACCOUNT");
            setIconImage(Toolkit.getDefaultToolkit().getImage(Account_Menu.class.getResource("/com/groupproject/group/folder-red-java-icon.png")));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 606, 455); // mimicks the size of the first window
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

            JButton btnNewButton = new JButton("CREATE");
            panel_8.add(btnNewButton);
            btnNewButton.setBackground(Color.LIGHT_GRAY);
            btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));

            JPanel panel_6 = new JPanel();
            panel_1.add(panel_6);

            JLabel lblNewLabel_1 = new JLabel("");
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            // please edit me or make me larger so i can fill the screen.
            lblNewLabel_1.setIcon(new ImageIcon(Account_Menu.class.getResource("/com/groupproject/group/texture2.jpg")));
            panel_6.add(lblNewLabel_1);




            // JPanel panel_2 = new JPanel();



        }

    }


