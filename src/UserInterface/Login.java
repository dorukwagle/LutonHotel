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

public class Login extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    private JTextField userName, password;
    private JLabel errorMsg;
    public Login(){
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
        JLabel headingText = new JLabel("Login to your Dashboard");
        headingText.setFont(new Font("Serif", Font.BOLD, 50));
        heading.add(headingText);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        add(center);

        JPanel inputHolder = new JPanel();
        inputHolder.setLayout(new BorderLayout(3, 20));
        inputHolder.setPreferredSize(new Dimension(Values.widthPct(container, 50), Values.heightPct(container, 25)));
        center.add(inputHolder);

        JPanel inputPanel1 = new JPanel();
        inputPanel1.setLayout(new FlowLayout());
        inputHolder.add(inputPanel1, BorderLayout.NORTH);

        JLabel emailLable = new JLabel("Username: ");
        emailLable.setFont(new Font("Serif", Font.BOLD, 20));
        emailLable.setPreferredSize(new Dimension(Values.widthPct(container, 15), Values.heightPct(container, 7)));
        inputPanel1.add(emailLable);

        userName = new JTextField();
        userName.setFont(new Font("Serif", Font.BOLD, 20));
        userName.setPreferredSize(new Dimension(Values.widthPct(container, 30), Values.heightPct(container, 7)));
        inputPanel1.add(userName);

        JPanel inputPanel2 = new JPanel();
        inputPanel1.setLayout(new FlowLayout());
        inputHolder.add(inputPanel2, BorderLayout.CENTER);

        JLabel pwLabel = new JLabel("Password: ");
        pwLabel.setFont(new Font("Serif", Font.BOLD, 20));
        pwLabel.setPreferredSize(new Dimension(Values.widthPct(container, 15), Values.heightPct(container, 7)));
        inputPanel2.add(pwLabel);

        password = new JPasswordField();
        password.setFont(new Font("Serif", Font.BOLD, 20));
        password.setPreferredSize(new Dimension(Values.widthPct(container, 30), Values.heightPct(container, 7)));
        inputPanel2.add(password);

        errorMsg = new JLabel("");
        errorMsg.setFont(new Font("Serif", Font.BOLD, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        inputHolder.add(errorMsg, BorderLayout.SOUTH);

        JPanel layHolder = new JPanel();
        layHolder.setLayout(new FlowLayout());
        add(layHolder);
        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new BorderLayout(5, 20));
        btnHolder.setPreferredSize(new Dimension(Values.widthPct(container, 30), Values.heightPct(container, 25)));
        layHolder.add(btnHolder);

        JButton loginBtn = new JButton("Log In");
        loginBtn.setFont(new Font("Serif", Font.BOLD, 40));
        loginBtn.addActionListener(this);
        loginBtn.setFocusable(false);
        btnHolder.add(loginBtn, BorderLayout.NORTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("< Back")) {
            this.window.removeAllChild();
            this.window.add(new HomePage());
        }
        else if(command.equals("Log In")){
            this.login();
        }
    }
    private void login(){
        try {
            //firs create a login data model
            LoginDetails loginDetails = new LoginDetails();
            loginDetails.setUserPassword(password.getText().trim());
            loginDetails.setUserName(userName.getText().trim());
            loginDetails.setModelType("login");

            BLLoginDetails blLoginDetails = new BLLoginDetails(loginDetails);
            loginDetails = blLoginDetails.checkLogin();

            if(loginDetails != null){
                //display individual dashboard for individual customer
                if(loginDetails.getUserRole().equals("individual")){
                    //create a customer object
                    Customer customer = new Customer();
                    customer.setUserName(loginDetails.getUserName());
                    customer.setCustomerType(loginDetails.getUserRole());
                    customer.setModelType("login");

                    //now send this customer object to the business layer and get all the information about the customer
                    BLCustomer blCustomer = new BLCustomer(customer);
                    customer = blCustomer.getCustomerInfo();

                    window.removeAllChild();
                    window.add(new IndividualDashboard(customer, loginDetails));
                }
                //display corporate dashboard for corporate customer
                else if(loginDetails.getUserRole().equals("corporate")){
                    Customer customer = new Customer();
                    customer.setUserName(loginDetails.getUserName());
                    customer.setCustomerType(loginDetails.getUserRole());
                    customer.setModelType("login");

                    BLCustomer blCustomer = new BLCustomer(customer);
                    customer = blCustomer.getCustomerInfo();

                    window.removeAllChild();
                    window.add(new CorporateDashboard(customer, loginDetails));
                }
                //display receptionist dashboard for receptionist
                else if(loginDetails.getUserRole().equals("receptionist")){

                }
            }
        }catch (Exception e){
            String msg = e.getMessage();
            if(msg.contains("InvalidCredentials")){
                errorMsg.setText("Incorrect username or password");
            }
            else if(msg.contains("Null")){
                errorMsg.setText("Please fill all the fields");
            }
        }

    }
}
