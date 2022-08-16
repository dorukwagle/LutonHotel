package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    private Window window;
    private Container container;
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

        JLabel emailLable = new JLabel("Email/Username: ");
        emailLable.setFont(new Font("Serif", Font.BOLD, 20));
        emailLable.setPreferredSize(new Dimension(Values.widthPct(container, 15), Values.heightPct(container, 7)));
        inputPanel1.add(emailLable);

        JTextField userName = new JTextField();
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

        JTextField password = new JTextField();
        password.setFont(new Font("Serif", Font.BOLD, 20));
        password.setPreferredSize(new Dimension(Values.widthPct(container, 30), Values.heightPct(container, 7)));
        inputPanel2.add(password);

        JLabel errorMsg = new JLabel("error msg");
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
        btnHolder.add(loginBtn, BorderLayout.NORTH);

    }
}
