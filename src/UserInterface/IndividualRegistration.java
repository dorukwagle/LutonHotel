package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualRegistration extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    public IndividualRegistration(){
        window = Window.getWindow();
        container = window.getContainer();
        setPreferredSize(new Dimension(Values.fillParent(container)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setMaximumSize(new Dimension(Values.widthPct(this, 100), Values.heightPct(this, 5)));
        add(topBar);
        JButton backBtn = new JButton("< Back");
        backBtn.addActionListener(this);
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Serif", Font.BOLD, 30));
        topBar.add(backBtn, BorderLayout.WEST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);
        JLabel headingText = new JLabel("Individual Registration");
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

        JLabel labelName = new JLabel("Full Name: ");
        labelName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(labelName);

        JTextField fullName = new JTextField();
        fullName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(fullName);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(ageLabel);

        JTextField age = new JTextField();
        age.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(age);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(addressLabel);

        JTextField address = new JTextField();
        address.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(address);

        JLabel contactLabel = new JLabel("Contact: ");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contactLabel);

        JTextField contact = new JTextField();
        contact.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contact);

        JLabel creditLabel = new JLabel("Credit Card No.:(optional) ");
        creditLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(creditLabel);

        JTextField creditCard = new JTextField();
        creditCard.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(creditCard);

        JLabel emailLabel = new JLabel("Email Address: ");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(emailLabel);

        JTextField email = new JTextField();
        email.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(email);

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

        JRadioButton male = new JRadioButton();
        male.setText("Male");
        male.setFont(new Font("Serif", Font.BOLD, 20));
        male.setFocusable(false);
        inputHolder.add(male);

        JRadioButton female = new JRadioButton();
        female.setText("Female");
        female.setFocusable(false);
        female.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(female);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

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

    @Override
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        if(command.equals("< Back")){
            this.window.removeAllChild();
            this.window.add(new HomePage());
        } else if (command.equals("")) {

        }

    }
}
