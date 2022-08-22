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

public class CorporateRegistration extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    private JTextField orgName, userName, address, website, email, contact, password;
    private JLabel errorMsg;
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
        backBtn.addActionListener(this);
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

        orgName = new JTextField();
        orgName.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(orgName);

        JLabel websiteLabel = new JLabel("Organization Website: ");
        websiteLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(websiteLabel);

        website = new JTextField();
        website.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(website);

        JLabel addressLabel = new JLabel("Company/Org. Address: ");
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

        JLabel emailLabel = new JLabel("Organization Email: ");
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
        register.addActionListener(this);
        register.setFocusable(false);
        subHolder.add(register, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("< Back")){
            this.window.removeAllChild();
            this.window.add(new HomePage());
        }
        else if(command.equals("Register")){
            this.register();
        }
    }
    private void register(){
        try {
            //before regestering firs check if the user already exists
            LoginDetails loginDetails = new LoginDetails();
            loginDetails.setUserPassword(password.getText().trim());
            loginDetails.setEmailAddress(email.getText().trim());
            loginDetails.setUserRole("corporate");
            loginDetails.setUserName(userName.getText().trim());
            loginDetails.setModelType("register");

            //now validate the login information
            BLLoginDetails blLoginDetails = new BLLoginDetails(loginDetails);

            Customer customer = new Customer();
            customer.setCustomerType("corporate");
            customer.setOrganizationName(orgName.getText().trim());
            customer.setAddress(address.getText().trim());
            customer.setContact(contact.getText().trim());
            customer.setWebsite(website.getText().trim());
            customer.setUserName(userName.getText().trim());

            //now validate customer information
            BLCustomer blCustomer = new BLCustomer(customer);
            //set up discount for corporate user
            blCustomer.setUpDiscount();
            //now save the login details first, in case if the user already exists we can terminate the process
            loginDetails = blLoginDetails.save();
            //now finally save the user information
            customer = blCustomer.save();

            if(customer != null && loginDetails != null){
                window.removeAllChild();
                window.add(new CorporateDashboard());
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            if(msg.contains("Null")){
                errorMsg.setText("Please fill all the details");
            }
            else if(msg.contains("InvalidContact")){
                errorMsg.setText("Invalid phone number");
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
            }
            else if(msg.contains("InvalidUrl")){
                errorMsg.setText("Please enter your website url");
            }
        }
    }
}
