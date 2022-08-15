package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    private Container window;
    public Login(){
        window = Window.getWindow();
        setPreferredSize(new Dimension(Values.fillParent(window)));
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
        JPanel inputPanel = new JPanel();
        GridLayout lay = new GridLayout(0, 2);
        lay.setVgap(20);
        inputPanel.setLayout(lay);
        center.add(inputPanel);

        JLabel emailLable = new JLabel("Email/Username: ");
        emailLable.setFont(new Font("Serif", Font.BOLD, 20));
        inputPanel.add(emailLable);

        JTextField userName = new JTextField();
        userName.setFont(new Font("Serif", Font.BOLD, 20));
        inputPanel.add(userName);

        JLabel pwLabel = new JLabel("Password: ");
        pwLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputPanel.add(pwLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Serif", Font.BOLD, 20));
        inputPanel.add(password);



    }
}
