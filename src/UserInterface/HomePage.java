package UserInterface;

import Utility.Values;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel implements ActionListener {
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
        center.setOpaque(false);
        add(center);

        //panel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout(5,50));
        btnPanel.setOpaque(false);
        center.add(btnPanel);

        //login button
        JButton loginBtn = new JButton("Login Now");
        loginBtn.addActionListener(this);
        loginBtn.setFont( new Font("Serif", Font.BOLD, 40));
        loginBtn.setFocusable(false);
        btnPanel.add(loginBtn, BorderLayout.NORTH);

        //panel to hold the JLabels
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
        registerHolder.setOpaque(false);
        btnPanel.add(registerHolder, BorderLayout.SOUTH);

        JButton indRegister = new JButton("Individual Register");
        indRegister.addActionListener(this);
        indRegister.setFont( new Font("Serif", Font.BOLD, 40));
        indRegister.setFocusable(false);
        registerHolder.add(indRegister, BorderLayout.NORTH);

        JButton corpRegister = new JButton("Organization Register");
        corpRegister.addActionListener(this);
        corpRegister.setFont( new Font("Serif", Font.BOLD, 40));
        corpRegister.setFocusable(false);
        registerHolder.add(corpRegister, BorderLayout.CENTER);
    }
    //adding background image to the jpanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            ImageIcon img = new ImageIcon(Values.getResPath("lutonhotel.jpg"));
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Login Now")){
            window.removeAllChild();
            window.add(new Login());
        }
        else if(command.equals("Individual Register")){
            window.removeAllChild();
            window.add(new IndividualRegistration());
        }
        else if(command.equals("Organization Register")){
            window.removeAllChild();
            window.add(new CorporateRegistration());
        }
    }
}
