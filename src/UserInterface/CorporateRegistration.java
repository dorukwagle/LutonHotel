package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class CorporateRegistration extends JPanel{
    private Window window;
    private Container container;

    public CorporateRegistration(){
        window = Window.getWindow();
        container = window.getContainer();
        setPreferredSize(new Dimension(Values.fillParent(container)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setMaximumSize(new Dimension(Values.widthPct(this, 100), Values.heightPct(this, 5)));
        add(topBar);
        JButton backBtn = new JButton("< Back");
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Serif", Font.BOLD, 30));
        topBar.add(backBtn, BorderLayout.WEST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);
        JLabel headingText = new JLabel("Corporate Organization Registration");
        headingText.setFont(new Font("Serif", Font.BOLD, 35));
        heading.add(headingText);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        add(center);

        JPanel inputHolder = new JPanel();
        GridLayout glay = new GridLayout(0, 2);
        glay.setVgap(10);
        inputHolder.setLayout(glay);
        inputHolder.setPreferredSize(new Dimension(Values.widthPct(container, 40), Values.heightPct(container, 50)));
        center.add(inputHolder);

        JLabel orgNameLabel = new JLabel("Organization Name: ");
        orgNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(orgNameLabel);

        JTextField orgName = new JTextField();
        orgName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(orgName);

        JLabel websiteLabel = new JLabel("Organization Website: ");
        websiteLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(websiteLabel);

        JTextField website = new JTextField();
        website.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(website);

        JLabel emailLabel = new JLabel("Organization Email: ");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(emailLabel);

        JTextField email = new JTextField();
        email.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(email);

        JLabel contactLabel = new JLabel("Contact: ");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contactLabel);

        JTextField contact = new JTextField();
        contact.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contact);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(usernameLabel);

        JTextField userName = new JTextField();
        userName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(userName);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(passwordLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(password);

        JPanel holder = new JPanel();
        holder.setLayout(new FlowLayout());
        add(holder);
        JPanel subHolder = new JPanel();
        subHolder.setLayout(new BorderLayout(2, 10));
        subHolder.setPreferredSize(new Dimension(Values.widthPct(container, 40), Values.heightPct(container, 15)));
        holder.add(subHolder);

        JLabel errorMsg = new JLabel("error msg");
        errorMsg.setFont(new Font("Serif", Font.BOLD, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        subHolder.add(errorMsg, BorderLayout.NORTH);

        JButton register = new JButton("Register");
        register.setFont(new Font("Serif", Font.BOLD, 40));
        subHolder.add(register, BorderLayout.SOUTH);
    }
}
