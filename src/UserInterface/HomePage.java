package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    private Window window;
    private Container container;
    private int btnHeightPct = 10;
    private int btnWidthPct = 100;
    public HomePage(){
        window = Window.getWindow();
        container = window.getContainer();
        setPreferredSize(new Dimension(Values.fillParent(container)));
        setLayout(new BorderLayout());

        //create a welcome label
        JLabel welcome = new JLabel("Welcome To Luton Hotel");
        welcome.setFont(new Font("Serif", Font.BOLD, 50 ));
        welcome.setPreferredSize(new Dimension(Values.widthPct(container,100), Values.heightPct(container,15)));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcome, BorderLayout.NORTH);

        //create panel to hold everything in center
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        center.setPreferredSize(new Dimension(Values.widthPct(container, 100), Values.heightPct(container, 85)));
        add(center);

        //panel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout(5,50));
        center.add(btnPanel);

        JButton loginBtn = new JButton("Login Now");
        loginBtn.setFont( new Font("Serif", Font.BOLD, 40));
        loginBtn.setFocusable(false);
        btnPanel.add(loginBtn, BorderLayout.NORTH);

        JPanel headings = new JPanel();
        headings.setLayout(new BoxLayout(headings, BoxLayout.Y_AXIS));
        btnPanel.add(headings, BorderLayout.CENTER);

        JLabel heading = new JLabel("Don't Have An Account ?");
        heading.setFont(new Font("Serif", Font.BOLD, 35 ));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        headings.add(heading);

        JLabel heading2 = new JLabel("Register Below");
        heading2.setFont(new Font("Serif", Font.BOLD, 20 ));
        headings.add(heading2);

        JPanel registerHolder = new JPanel();
        registerHolder.setLayout(new BorderLayout(5, 50));
        btnPanel.add(registerHolder, BorderLayout.SOUTH);

        JButton indRegister = new JButton("Individual Register");
        indRegister.setFont( new Font("Serif", Font.BOLD, 40));
        indRegister.setFocusable(false);
        registerHolder.add(indRegister, BorderLayout.NORTH);

        JButton corpRegister = new JButton("Organization Register");
        corpRegister.setFont( new Font("Serif", Font.BOLD, 40));
        corpRegister.setFocusable(false);
        registerHolder.add(corpRegister, BorderLayout.CENTER);
    }
}
