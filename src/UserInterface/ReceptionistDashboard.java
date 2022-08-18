package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;
import java.text.FieldPosition;

public class ReceptionistDashboard extends JPanel {
    private Window window;
    private Container container;
    private JPanel contentHolder;
    private JButton home, rooms, bookings, billings;
    public ReceptionistDashboard(){
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

        home = new JButton("Home");
        home.setFont(new Font("Serif", Font.BOLD, 20));
        home.setFocusable(false);
        home.setEnabled(false);
        btnBar.add(home);

        rooms = new JButton("Rooms");
        rooms.setFont(new Font("Serif", Font.BOLD, 20));
        rooms.setFocusable(false);
        btnBar.add(rooms);

        bookings = new JButton("Bookings");
        bookings.setFont(new Font("Serif", Font.BOLD, 20));
        bookings.setFocusable(false);
        btnBar.add(bookings);

        billings = new JButton("Billings");
        billings.setFont(new Font("Serif", Font.BOLD, 20));
        billings.setFocusable(false);
        btnBar.add(billings);

        JButton backBtn = new JButton("< Log Out");
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Serif", Font.BOLD, 20));
        topBar.add(backBtn, BorderLayout.EAST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);

        JLabel headingText = new JLabel("<mr/ms>. <Name>!> Welcome to your Receptionist Dashboard");
        headingText.setFont(new Font("Serif", Font.BOLD, 30));
        heading.add(headingText);

        contentHolder = new JPanel();
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));
        add(contentHolder);

        contentHolder.add(billingPage());
    }

    private JPanel homePage(){
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout(2,100));

        JPanel homePage = new JPanel();
        homePage.setLayout(new BoxLayout(homePage, BoxLayout.Y_AXIS));
        container.add(homePage, BorderLayout.CENTER);

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        homePage.add(head);

        JLabel heading = new JLabel("Pending bookings to be confirmed:");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        head.add(heading, BorderLayout.NORTH);

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        head.add(scroll, BorderLayout.CENTER);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        head.add(btnHolder, BorderLayout.SOUTH);

        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Serif", Font.BOLD, 40));
        btnConfirm.setFocusable(false);
        btnConfirm.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnConfirm);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        return container;
    }

    private JPanel roomsPage(){
        JPanel roomsPage = new JPanel();
        roomsPage.setLayout(new BoxLayout(roomsPage, BoxLayout.Y_AXIS));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        roomsPage.add(btnPanel);

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        btnPanel.add(controls, BorderLayout.SOUTH);

        JPanel subControl = new JPanel();
        subControl.setLayout(new BorderLayout());
        subControl.setPreferredSize(new Dimension(Values.widthPct(container, 100), Values.heightPct(container, 8)));
        controls.add(subControl);

        JPanel btns = new JPanel();
        btns.setLayout(new FlowLayout());
        subControl.add(btns, BorderLayout.WEST);

        JButton addRoom = new JButton("Add Room");
        addRoom.setFont(new Font("Serif", Font.BOLD, 30));
        addRoom.setFocusable(false);
        btns.add(addRoom);

        JButton removeRoom = new JButton("Remove Room");
        removeRoom.setFont(new Font("Serif", Font.BOLD, 30));
        removeRoom.setFocusable(false);
        btns.add(removeRoom);

        JPanel filters = new JPanel();
        filters.setLayout(new FlowLayout());
        subControl.add(filters, BorderLayout.EAST);

        String[] availableOptions = {"Available Rooms", "Booked Rooms"};
        JComboBox availability = new JComboBox(availableOptions);
        availability.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(availability);

        String[] roomTypes = {"Single Rooms", "Double Rooms", "Twin Rooms"};
        JComboBox roomtType = new JComboBox(roomTypes);
        roomtType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(roomtType);

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        roomsPage.add(scroll);

        return roomsPage;
    }

    private JPanel bookingPage(){
        JPanel bookingPage = new JPanel();
        bookingPage.setLayout(new BoxLayout(bookingPage, BoxLayout.Y_AXIS));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        bookingPage.add(btnPanel);

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        btnPanel.add(controls, BorderLayout.SOUTH);

        JPanel subControl = new JPanel();
        subControl.setLayout(new BorderLayout());
        subControl.setPreferredSize(new Dimension(Values.widthPct(container, 100), Values.heightPct(container, 8)));
        controls.add(subControl);

        JPanel filters = new JPanel();
        filters.setLayout(new FlowLayout());
        subControl.add(filters, BorderLayout.EAST);

        String[] bookingFilter = {"History", "Cancelled", "Completed", "Upcoming"};
        JComboBox bookFilter = new JComboBox(bookingFilter);
        bookFilter.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(bookFilter);

        String[] roomTypes = {"Single Rooms", "Double Rooms", "Twin Rooms"};
        JComboBox roomtType = new JComboBox(roomTypes);
        roomtType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(roomtType);

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        bookingPage.add(scroll);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        bookingPage.add(btnHolder, BorderLayout.SOUTH);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        JButton btnActive = new JButton("Set As Active");
        btnActive.setFont(new Font("Serif", Font.BOLD, 40));
        btnActive.setFocusable(false);
        btnActive.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnActive);

        return bookingPage;
    }

    private JPanel billingPage(){
        JPanel bookingPage = new JPanel();
        bookingPage.setLayout(new BoxLayout(bookingPage, BoxLayout.Y_AXIS));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        bookingPage.add(btnPanel);

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        btnPanel.add(controls, BorderLayout.SOUTH);

        JPanel subControl = new JPanel();
        subControl.setLayout(new BorderLayout());
        subControl.setPreferredSize(new Dimension(Values.widthPct(container, 100), Values.heightPct(container, 8)));
        controls.add(subControl);

        JPanel btns = new JPanel();
        btns.setLayout(new FlowLayout());
        subControl.add(btns, BorderLayout.EAST);

        JLabel emailLable = new JLabel("Email or Username:");
        emailLable.setFont(new Font("Serif", Font.BOLD, 20));
        btns.add(emailLable);

        JTextField email = new JTextField();
        email.setFont(new Font("Serif", Font.BOLD, 20));
        btns.add(email);

        String[] availableOptions = {"Individual", "Corporate"};
        JComboBox availability = new JComboBox(availableOptions);
        availability.setFont(new Font("Serif", Font.BOLD, 20));
        btns.add(availability);

        JButton search = new JButton("Search");
        search.setFont(new Font("Serif", Font.BOLD, 20));
        search.setFocusable(false);
        btns.add(search);

        JPanel btnLay = new JPanel();
        btnLay.setLayout(new BorderLayout());
        bookingPage.add(btnLay);
        JPanel btnhold = new JPanel();
        btnhold.setLayout(new FlowLayout());
        btnLay.add(btnhold, BorderLayout.WEST);

        JButton generate = new JButton("Generate Bill");
        generate.setFocusable(false);
        generate.setFont(new Font("Serif", Font.BOLD, 30));
        btnhold.add(generate);

        JTable activeBookings = new JTable();
        JScrollPane scroll = new JScrollPane(activeBookings);
        bookingPage.add(scroll);

        return bookingPage;
    }

}
