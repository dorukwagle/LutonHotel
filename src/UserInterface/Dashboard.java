package UserInterface;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import BusinessLayer.BLUser;
import DataModel.User;
import Utility.DatabaseConnector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class Dashboard extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     */
    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 893, 515);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaption);
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblName = new JLabel("Name");
        panel.add(lblName);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Address here");
        panel.add(lblNewLabel_1);

        textField_1 = new JTextField();
//        textField_1.add(lblNewLabel_1);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String address = textField_1.getText();
                User user = new User(1, name, address );
                try {
                    BLUser buser = new BLUser(user);
                    user = buser.save();
                } catch (Exception ex){
                    System.out.println(ex.toString());
                }
            }
        });
        panel.add(btnNewButton);

        JButton btnNewButton_3 = new JButton("List All User");
        panel.add(btnNewButton_3);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.activeCaption);
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(175, 238, 238));
        panel_1.add(panel_2, BorderLayout.NORTH);

        JLabel lblNewLabel_2 = new JLabel("Search:");
        panel_2.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Name");
        panel_2.add(lblNewLabel_3);

        textField_2 = new JTextField();
        panel_2.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Address");
        panel_2.add(lblNewLabel_4);

        textField_3 = new JTextField();
        panel_2.add(textField_3);
        textField_3.setColumns(10);

        JButton btnNewButton_1 = new JButton("Search");
        panel_2.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Search All User");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel_2.add(btnNewButton_2);
    }

}