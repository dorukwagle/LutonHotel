package UserInterface;

import DataModel.Customer;
import DataModel.LoginDetails;
import Utility.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CorporateDashboard extends CustomerDashboard {
    private Customer customer;
    private LoginDetails loginDetails;
    public CorporateDashboard(Customer customer, LoginDetails loginDetails){
        this.customer = customer;
        this.loginDetails = loginDetails;
        this.contentHolder.add(aboutPage());
    }

    protected JPanel aboutPage(){
        JPanel aboutPage = new JPanel();
        aboutPage.setLayout(new BoxLayout(aboutPage, BoxLayout.Y_AXIS));

        //set heading text
        String txt = "Welcome Back, " +  customer.getOrganizationName();
        this.headingText.setText(txt);

        JPanel profileHolder = new JPanel();
        profileHolder.setLayout(new GridLayout(0, 1));
        aboutPage.add(profileHolder);

        JLabel head = new JLabel("Account Information");
        head.setFont(new Font("Serif", Font.BOLD, 25));
        profileHolder.add(head);

        JLabel name = new JLabel("Organization: " + customer.getOrganizationName());
        name.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(name);

        JLabel email = new JLabel("Email Address: " + loginDetails.getEmailAddress());
        email.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(email);

        JLabel username = new JLabel("Username: @" + customer.getUserName());
        username.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(username);

        JLabel website = new JLabel("Website: " + customer.getWebsite());
        website.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(website);

        JLabel expiry = new JLabel("Account Expiry Date: " + customer.getAccountValidTill());
        expiry.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(expiry);

        JLabel billingDate = new JLabel("Next Billing Date: " + customer.getNextBillingDate());
        billingDate.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(billingDate);

//        JLabel billingAmount = new JLabel("Billing Amount of Current Month: <amount>");
//        billingAmount.setFont(new Font("Serif", Font.BOLD, 15));
//        profileHolder.add(billingAmount);

        JLabel discount = new JLabel("Discount Offered: " + customer.getDiscountPercent());
        discount.setFont(new Font("Serif", Font.BOLD, 15));
        profileHolder.add(discount);

        JLabel head1 = new JLabel("Upcoming Bookings");
        head1.setFont(new Font("Serif", Font.BOLD, 30));
        profileHolder.add(head1);

        JTable upcomingBookings = new JTable();
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

        JLabel checkinLabel = new JLabel("Check In Date(dd/mm/yyyy: ");
        checkinLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinLabel);

        JTextField checkinDate = new JTextField();
        checkinDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinDate);

        JLabel websiteLabel = new JLabel("Check Out Date(dd/mm/yyyy): ");
        websiteLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(websiteLabel);

        JTextField website = new JTextField();
        website.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(website);

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

    protected JPanel activeBooking(){
        JPanel activeBooking = new JPanel();
        activeBooking.setLayout(new BoxLayout(activeBooking, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        activeBooking.add(head);

        JLabel heading = new JLabel("Currently Active Bookings:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        JTable activeBookings = new JTable();
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

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        head.add(scroll, BorderLayout.CENTER);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        head.add(btnHolder, BorderLayout.SOUTH);

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

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        head.add(scroll, BorderLayout.CENTER);

        return bookingHistory;
    }

}
