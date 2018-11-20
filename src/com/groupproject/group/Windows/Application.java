package com.groupproject.group.Windows;

import com.groupproject.group.Account.LoginAccount.ManagerAccount;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class Application extends JPanel{
    private ManagerAccount managerAccount;
    JFrame frame = new JFrame();

    /**
     * Create the panel.
     * 1st panel will store the remaining panels.
     */
    JPanel panelCont = new JPanel();
    private JPanel firstpanel;
    private JPanel secondpanel;
    CardLayout c1 = new CardLayout();

    // then the other variables that we needed.
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public void LoginPanel() {
        // all of this is now from the login menu itself and can be created using the building tool and we can hook it up from there
        JPanel panel = new JPanel();
        firstpanel = new JPanel();
        firstpanel = new JPanel();
        firstpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        firstpanel.setLayout(new BorderLayout(0, 0));
        firstpanel.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("BANKING PORTAL");
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        firstpanel.add(panel_1, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("DATE AND TIME/VERSION");
        lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_1.add(lblNewLabel_2);

        JPanel panel_2 = new JPanel();
        firstpanel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Application.class.getResource("/com/groupproject/group/Resources/Images/00205-3D-art-logo-design-free-logos-online-011.png")));
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
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnNewButton) {
                    // have to show the third panel from here.
                    //c1.show(panelCont,"3"

                }
            }
        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CREATE");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // we are going to open the other GUI after we verify CREATE has been clicked.
                // well we need to use this.dispose() to close previous GUI's
                if(e.getSource() == btnNewButton_1) {
                    // Similar Catch/Block statement used in main to intially launch the application.
                    c1.show(panelCont,"2");
                }
            }
        });

        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
        panel_3.add(btnNewButton_1);
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

        JLabel lblNewLabel_5 = new JLabel("RE-ENTER PASSWORD");
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
        lblNewLabel_1.setIcon(new ImageIcon(("/com/groupproject/group/Resources/Images/texture2.jpg")));
        panel_6.add(lblNewLabel_1);
    }

    /**
     * WE LAUNCH THE APPLICATION HERE AND EVERYTHING NEEDS TO BE CALLED AND ADDED HERE**/
    public Application() {
        // read in the file
        

        // call panels here
        LoginPanel();
        CreateAccountPanel();
        // everything gets added to here now panel wise. we just have to give it the attributes we did
        panelCont.setLayout(c1);
        // we have to finally add this panel to the panelCont.
        panelCont.add(firstpanel,"1");
        panelCont.add(secondpanel,"2");
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Application();
            }
        });
    }
}





