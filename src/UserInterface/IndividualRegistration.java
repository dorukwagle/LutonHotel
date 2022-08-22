package UserInterface;

import BusinessLayer.BLCustomer;
import BusinessLayer.BLLoginDetails;
import DataModel.Customer;
import DataModel.LoginDetails;
import Utility.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualRegistration extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    private JLabel errorMsg;
    private JTextField fullName, age, address, contact, creditCard, email, userName, password;
    private JRadioButton male, female;
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

        fullName = new JTextField();
        fullName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(fullName);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(ageLabel);

        age = new JTextField();
        age.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(age);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(addressLabel);

        address = new JTextField();
        address.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(address);

        JLabel contactLabel = new JLabel("Contact: ");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contactLabel);

        contact = new JTextField();
        contact.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contact);

        JLabel creditLabel = new JLabel("Credit Card No.:(optional) ");
        creditLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(creditLabel);

        creditCard = new JTextField();
        creditCard.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(creditCard);

        JLabel emailLabel = new JLabel("Email Address: ");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(emailLabel);

        email = new JTextField();
        email.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(email);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(usernameLabel);

        userName = new JTextField();
        userName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(userName);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(passwordLabel);

        password = new JTextField();
        password.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(password);

        male = new JRadioButton();
        male.setText("Male");
        male.setFont(new Font("Serif", Font.BOLD, 20));
        male.setFocusable(false);
        inputHolder.add(male);

        female = new JRadioButton();
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

        errorMsg = new JLabel("");
        errorMsg.setFont(new Font("Serif", Font.BOLD, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        subHolder.add(errorMsg, BorderLayout.NORTH);

        JButton register = new JButton("Register");
        register.setFont(new Font("Serif", Font.BOLD, 40));
        register.setFocusable(false);
        register.addActionListener(this);
        subHolder.add(register, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        if(command.equals("< Back")){
            this.window.removeAllChild();
            this.window.add(new HomePage());
        }
        else if (command.equals("Register")) {
            this.register();
        }
    }
    private void register() {
            try {
                //before regestering firs check if the user already exists
                LoginDetails loginDetails = new LoginDetails();
                loginDetails.setUserPassword(password.getText().trim());
                loginDetails.setEmailAddress(email.getText().trim());
                loginDetails.setUserRole("individual");
                loginDetails.setUserName(userName.getText().trim());
                loginDetails.setModelType("register");

                //now validate the login information
                BLLoginDetails blLoginDetails = new BLLoginDetails(loginDetails);

                Customer customer = new Customer();
                customer.setCustomerType("individual");
                customer.setCustFullName(fullName.getText().trim());
                customer.setAddress(address.getText().trim());
                customer.setContact(contact.getText().trim());
                customer.setCustAge(age.getText().trim());
                customer.setUserName(userName.getText().trim());
                customer.setCreditCardNo(creditCard.getText().trim());
                String gender = (male.isSelected()? "male" : "");
                gender = (female.isSelected()? "female" : gender);
                customer.setCustGender(gender);

                //now validate customer information
                BLCustomer blCustomer = new BLCustomer(customer);

                //now save the login details first, in case if the user already exists we can terminate the process
                loginDetails = blLoginDetails.save();
                //now finally save the user information
                customer = blCustomer.save();

                if(customer != null && loginDetails != null){
                        window.removeAllChild();
                        window.add(new IndividualDashboard());
                }
            } catch (Exception e) {
                String msg = e.getMessage();
                if(msg.contains("Null")){
                    errorMsg.setText("Please fill all the details");
                }
                else if(msg.contains("InvalidContact")){
                    errorMsg.setText("Invalid phone number");
                }
                else if(msg.contains("FullName")){
                    errorMsg.setText("Please enter your full name");
                }
                else if(msg.contains("CreditCard")){
                    errorMsg.setText("Please provide a valid Credit Card Number");
                }
                else if(msg.contains("EmailAddress")){
                    errorMsg.setText("Invalid Email Address");
                }
                else if(msg.contains("Password")){
                    errorMsg.setText("Password should contain at least 4 characters");
                }
                else if(msg.contains("Email")){
                    errorMsg.setText("An user already exists with the given email");
                }
                else if(msg.contains("UserName")){
                    errorMsg.setText("Username is already used");
                } else if (msg.contains("InvalidAge")) {
                    errorMsg.setText("Please enter your age");
                } else {
                    System.out.println(msg);
                }
            }
        }
}
