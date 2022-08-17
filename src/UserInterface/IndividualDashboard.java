package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public class IndividualDashboard extends JPanel {
    private Window window;
    private Container container;

    private JPanel contentHolder;
    public IndividualDashboard(){
        window = Window.getWindow();
        container = window.getContainer();
        setPreferredSize(new Dimension(Values.fillParent(container)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setMaximumSize(new Dimension(Values.widthPct(this, 100), Values.heightPct(this, 5)));
        add(topBar);

        JPanel btnBar = new JPanel();
        btnBar.setLayout(new GridLayout(1, 0));
        topBar.add(btnBar, BorderLayout.WEST);

        JButton about = new JButton("About");
        about.setFont(new Font("Serif", Font.BOLD, 20));
        about.setFocusable(false);
        btnBar.add(about);

        JButton bookNow = new JButton("Book Now");
        bookNow.setFont(new Font("Serif", Font.BOLD, 20));
        bookNow.setFocusable(false);
        btnBar.add(bookNow);

        JButton activeBookings = new JButton("Active Bookings");
        activeBookings.setFont(new Font("Serif", Font.BOLD, 20));
        activeBookings.setFocusable(false);
        btnBar.add(activeBookings);

        JButton pendingBookings = new JButton("Pending Bookings");
        pendingBookings.setFont(new Font("Serif", Font.BOLD, 20));
        pendingBookings.setFocusable(false);
        btnBar.add(pendingBookings);

        JButton history = new JButton("History");
        history.setFont(new Font("Serif", Font.BOLD, 20));
        history.setFocusable(false);
        btnBar.add(history);

        JButton backBtn = new JButton("< Log Out");
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Serif", Font.BOLD, 20));
        topBar.add(backBtn, BorderLayout.EAST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);
        JLabel headingText = new JLabel("Welcome Back, <mr/ms>. <Name>!");
        headingText.setFont(new Font("Serif", Font.BOLD, 25));
        heading.add(headingText);

        contentHolder = new JPanel();
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));
        add(contentHolder);

        contentHolder.add(bookNow());
    }

    private JPanel aboutPage(){
        JPanel aboutPage = new JPanel();
        aboutPage.setLayout(new BoxLayout(aboutPage, BoxLayout.Y_AXIS));

        JPanel profileHolder = new JPanel();
        profileHolder.setLayout(new GridLayout(0, 1));
        aboutPage.add(profileHolder);

        JLabel head = new JLabel("Profile Details");
        head.setFont(new Font("Serif", Font.BOLD, 25));
        profileHolder.add(head);

        JLabel name = new JLabel("Name: <name>");
        name.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(name);

        JLabel username = new JLabel("Username: @<username>");
        username.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(username);

        JLabel gender = new JLabel("gender: <gender>");
        gender.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(gender);

        JLabel email = new JLabel("Email Address: <email address>");
        email.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(email);

        JLabel creditCard = new JLabel("Credit Card No.: <card number>");
        creditCard.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(creditCard);

        JLabel head1 = new JLabel("Upcoming Bookings");
        head1.setFont(new Font("Serif", Font.BOLD, 30));
        profileHolder.add(head1);

        JTable upcomingBookings = new JTable();
        JScrollPane scroll = new JScrollPane(upcomingBookings);
        aboutPage.add(scroll);

        return aboutPage;
    }

    private JPanel bookNow(){
        JPanel bookNow = new JPanel();
        bookNow.setLayout(new BoxLayout(bookNow, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        bookNow.add(center);

        JPanel inputHolder = new JPanel();
        GridLayout glay = new GridLayout(0, 2);
        glay.setVgap(10);
        inputHolder.setLayout(glay);
        inputHolder.setPreferredSize(new Dimension(Values.widthPct(container, 40), Values.heightPct(container, 40)));
        center.add(inputHolder);

        JLabel checkinLabel = new JLabel("Check In Date: ");
        checkinLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinLabel);

        JTextField checkinDate = new JTextField();
        checkinDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinDate);

        JLabel websiteLabel = new JLabel("Check Out Date: ");
        websiteLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(websiteLabel);

        JTextField website = new JTextField();
        website.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(website);

        JLabel emailLabel = new JLabel("Credit Card No.: ");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(emailLabel);

        JTextField email = new JTextField();
        email.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(email);

        JLabel contactLabel = new JLabel("Room Type: ");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(contactLabel);

        String[] rooms = {"Single", "Double", "Twin"};
        JComboBox roomType = new JComboBox(rooms);
        roomType.setFont(new Font("Serif", Font.BOLD, 25));
        inputHolder.add(roomType);

        JPanel btnHold = new JPanel();
        btnHold.setLayout(new FlowLayout());
        btnHold.setPreferredSize(new Dimension(Values.widthPct(this.container, 40), Values.heightPct(this.container, 10)));
        bookNow.add(btnHold);
        JButton requestBooking = new JButton("Request Booking");
        requestBooking.setFont(new Font("Serif", Font.BOLD, 40));
        btnHold.add(requestBooking);

        return bookNow;
    }

}
