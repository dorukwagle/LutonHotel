package UserInterface;

import DataModel.Booking;
import DataModel.Customer;
import DataModel.LoginDetails;
import Utility.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Supplier;

public class IndividualDashboard extends CustomerDashboard {
    private Customer customer;
    private LoginDetails loginDetails;

    private JTable upcomingBookings, activeBookings, pendingBookings, bookingsHistory;
    private JLabel creditCard;
    private JTextField checkinDate, checkoutDate, credit;
    private JComboBox roomType;
    public IndividualDashboard(Customer customer, LoginDetails loginDetails){
        this.customer = customer;
        this.loginDetails = loginDetails;
        this.contentHolder.add(aboutPage());

    }

    protected JPanel aboutPage(){
        JPanel aboutPage = new JPanel();
        aboutPage.setLayout(new BoxLayout(aboutPage, BoxLayout.Y_AXIS));

        //set heading text
        String txt = "Welcome Back, " + (customer.getCustGender().equals("male") ? "Mr." : "Ms.") + customer.getCustFullName();
        this.headingText.setText(txt);

        JPanel profileHolder = new JPanel();
        profileHolder.setLayout(new GridLayout(0, 1));
        aboutPage.add(profileHolder);

        JLabel head = new JLabel("Profile Details");
        head.setFont(new Font("Serif", Font.BOLD, 25));
        profileHolder.add(head);

        JLabel name = new JLabel("Name: " + customer.getCustFullName());
        name.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(name);

        JLabel username = new JLabel("Username: @" + customer.getUserName());
        username.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(username);

        JLabel gender = new JLabel("gender: " + customer.getCustGender());
        gender.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(gender);

        JLabel email = new JLabel("Email Address: " + loginDetails.getEmailAddress());
        email.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(email);

        creditCard = new JLabel("Credit Card No.: " + customer.getCreditCardNo());
        creditCard.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(creditCard);

        JLabel head1 = new JLabel("Upcoming Bookings");
        head1.setFont(new Font("Serif", Font.BOLD, 30));
        profileHolder.add(head1);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No upcoming bookings"};
        upcomingBookings = new JTable(data, column);
        upcomingBookings.setRowHeight(30);
        upcomingBookings.setFont(new Font("Serif", Font.PLAIN, 20));

        JScrollPane scroll = new JScrollPane(upcomingBookings);
        aboutPage.add(scroll);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        aboutPage.add(btnHolder, BorderLayout.SOUTH);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Serif", Font.BOLD, 40));
        btnEdit.setFocusable(false);
        btnEdit.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnEdit);

        return aboutPage;
    }

    protected JPanel bookNow(){
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

        JLabel checkinLabel = new JLabel("Check In Date(dd-MM-yyyy): ");
        checkinLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinLabel);

        checkinDate = new JTextField();
        checkinDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinDate);

        JLabel checkoutLabel = new JLabel("Check Out Date(dd-MM-yyyy): ");
        checkoutLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkoutLabel);

        checkoutDate = new JTextField();
        checkoutDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkoutDate);

        JLabel creditLabel = new JLabel("Credit Card No.: ");
        creditLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(creditLabel);

        credit = new JTextField(this.customer.getCreditCardNo());
        credit.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(credit);

        JLabel roomLabel = new JLabel("Room Type: ");
        roomLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(roomLabel);

        String[] rooms = {"Single", "Double", "Twin"};
        roomType = new JComboBox(rooms);
        roomType.setFont(new Font("Serif", Font.BOLD, 25));
        inputHolder.add(roomType);

        JPanel btnHold = new JPanel();
        btnHold.setLayout(new FlowLayout());
        btnHold.setPreferredSize(new Dimension(Values.widthPct(this.container, 40), Values.heightPct(this.container, 10)));
        bookNow.add(btnHold);
        JButton requestBooking = new JButton("Request Booking");
        requestBooking.setFont(new Font("Serif", Font.BOLD, 40));
        requestBooking.setFocusable(false);
        requestBooking.addActionListener(this);
        btnHold.add(requestBooking);

        return bookNow;
    }

    protected JPanel activeBooking(){
        JPanel activeBooking = new JPanel();
        activeBooking.setLayout(new BoxLayout(activeBooking, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        activeBooking.add(head);

        JLabel heading = new JLabel("Currently Active Bookings:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No active bookings"};
        activeBookings = new JTable(data, column);
        activeBookings.setRowHeight(30);
        activeBookings.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(activeBookings);
        head.add(scroll, BorderLayout.CENTER);

        return activeBooking;
    }

    protected JPanel pendingBooking(){
        JPanel pendingBooking = new JPanel();
        pendingBooking.setLayout(new BoxLayout(pendingBooking, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        pendingBooking.add(head);

        JLabel heading = new JLabel("Your Pending Bookings:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No pending bookings"};
        pendingBookings = new JTable(data, column);
        pendingBookings.setRowHeight(30);
        pendingBookings.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(pendingBookings);
        head.add(scroll, BorderLayout.CENTER);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        head.add(btnHolder, BorderLayout.SOUTH);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(this);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Serif", Font.BOLD, 40));
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(this);
        btnEdit.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnEdit);

        return pendingBooking;
    }

    protected JPanel bookingHistory(){
        JPanel bookingHistory = new JPanel();
        bookingHistory.setLayout(new BoxLayout(bookingHistory, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        bookingHistory.add(head);

        JLabel heading = new JLabel("Booking History:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No bookings History"};
        bookingsHistory = new JTable(data, column);
        bookingsHistory.setRowHeight(30);
        bookingsHistory.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(bookingsHistory);
        head.add(scroll, BorderLayout.CENTER);

        return bookingHistory;
    }

    //override methods for handling button clicks
    @Override
    protected Booking requestBooking() {

        return null;
    }

    @Override
    protected void cancelBooking() {

    }

    @Override
    protected Booking editBooking() {
        return null;
    }
}
