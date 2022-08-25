package UserInterface;

import BusinessLayer.BLBooking;
import DataModel.Booking;
import DataModel.BookingReceptionist;
import DataModel.Staff;
import Utility.Values;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReceptionistDashboard extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    private JPanel contentHolder;
    private JButton home, rooms, bookings, billings;
    private JTable pendingBookings, allRooms, bookingsList, bills;
    private JComboBox roomtType, availability, bRoomtType, bookFilter;
    private String currentPage = "";
    public ReceptionistDashboard(Staff staff){
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
        home.addActionListener(this);
        btnBar.add(home);

        rooms = new JButton("Rooms");
        rooms.setFont(new Font("Serif", Font.BOLD, 20));
        rooms.setFocusable(false);
        rooms.addActionListener(this);
        btnBar.add(rooms);

        bookings = new JButton("Bookings");
        bookings.setFont(new Font("Serif", Font.BOLD, 20));
        bookings.setFocusable(false);
        bookings.addActionListener(this);
        btnBar.add(bookings);

        billings = new JButton("Billings");
        billings.setFont(new Font("Serif", Font.BOLD, 20));
        billings.setFocusable(false);
        billings.addActionListener(this);
        btnBar.add(billings);

        JButton backBtn = new JButton("< Log Out");
        backBtn.setFocusable(false);
        backBtn.addActionListener(this);
        backBtn.setFont(new Font("Serif", Font.BOLD, 20));
        topBar.add(backBtn, BorderLayout.EAST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);

        JLabel headingText = new JLabel("Welcome, " + (staff.getStaffGender().equals("male") ? "Mr. " : "Ms. ") + staff.getStaffFullName());
        headingText.setFont(new Font("Serif", Font.BOLD, 30));
        heading.add(headingText);
        contentHolder = new JPanel(){
            @Override
            public Component add(Component comp){
                super.add(comp);
                revalidate();
                repaint();
                return comp;
            }
        };
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));
        add(contentHolder);

        contentHolder.add(this.homePage());
    }

    private JPanel homePage(){
        this.currentPage = "home";
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

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("No Pending Bookings");
        pendingBookings = new JTable(model);
        pendingBookings.setRowHeight(30);
        pendingBookings.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(pendingBookings);
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

        this.loadPendingBookings();
        return container;
    }

    private JPanel roomsPage(){
        this.currentPage = "rooms";
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
        availability = new JComboBox(availableOptions);
        availability.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(availability);

        String[] roomTypes = {"Single Rooms", "Double Rooms", "Twin Rooms"};
        roomtType = new JComboBox(roomTypes);
        roomtType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(roomtType);

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("No Rooms To Show");
        allRooms = new JTable(model);
        allRooms.setRowHeight(30);
        allRooms.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(allRooms);
        roomsPage.add(scroll);

        this.loadRooms();
        return roomsPage;
    }

    private JPanel bookingPage(){
        this.currentPage = "bookingReceptionist";
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
        bookFilter = new JComboBox(bookingFilter);
        bookFilter.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(bookFilter);

        String[] roomTypes = {"Single Rooms", "Double Rooms", "Twin Rooms"};
        bRoomtType = new JComboBox(roomTypes);
        bRoomtType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(bRoomtType);

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("No Bookings To Show");
        bookingsList = new JTable(model);
        bookingsList.setRowHeight(30);
        bookingsList.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(bookingsList);
        bookingPage.add(scroll);

        JPanel btnHolder = new JPanel();
        btnHolder.setLayout(new FlowLayout());
        bookingPage.add(btnHolder, BorderLayout.SOUTH);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(this);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        JButton btnActive = new JButton("Set As Active");
        btnActive.setFont(new Font("Serif", Font.BOLD, 40))
        btnActive.setFocusable(false);
        btnActive.addActionListener(this);
        btnActive.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnActive);

        this.loadBookings();
        return bookingPage;
    }

    private JPanel billingPage(){
        this.currentPage = "billing";
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

        btns.setLayout(new GridLayout(1, 0, 10, 0));
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
        search.addActionListener(this);
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
        generate.addActionListener(this);
        generate.setFont(new Font("Serif", Font.BOLD, 30));
        btnhold.add(generate);

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("No Bills");
        bills = new JTable(model);
        bills.setRowHeight(30);
        bills.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(bills);
        bookingPage.add(scroll);

        return bookingPage;
    }

    private void setFocused(JButton btn){
        this.home.setEnabled(true);
        this.rooms.setEnabled(true);
        this.bookings.setEnabled(true);
        this.billings.setEnabled(true);
        btn.setEnabled(false);
    }

    //method to change the page, i.e. when a button the menu bar is clicked, change the page content respectively
    private void changePage(JPanel page, JButton btn){
        this.setFocused(btn);
        this.contentHolder.removeAll();
        this.contentHolder.add(page);
    }

    //method to load all the  pending bookings in the table
    public void loadPendingBookings(){
        try {
            BLBooking blBooking = new BLBooking();
            ArrayList<BookingReceptionist> bookingReceptionists = blBooking.getUserPendingBookings(this.customer.getCustId());
            if(!(bookingReceptionists.size() > 0)){
                return;
            }
            //finally load data to the table
            this.loadBookingsTable(bookingReceptionists, this.pendingBookings);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //method to load the table data
    protected void loadBookingsTable(ArrayList<BookingReceptionist> bookingReceptionists, JTable table){
        //remove existing data from table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        table.revalidate();

        //add new data to the table
        model.addColumn("FullName");
        model.addColumn("Contact");
        model.addColumn("Email");
        model.addColumn("BookingID");
        model.addColumn("BookedOn");
        model.addColumn("CheckIn");
        model.addColumn("CheckOut");
        model.addColumn("RoomType");
        model.addColumn("RoomNumber");
        model.addColumn("Status");
        //now add new data to the model
        BookingReceptionist bookingReceptionist;
        for(int i = 0; i < bookingReceptionists.size(); ++i){
            bookingReceptionist = bookingReceptionists.get(i);

            model.insertRow(model.getRowCount(), new String[] {
                    bookingReceptionist.getCustomerName(),
                    bookingReceptionist.getContact(),
                    bookingReceptionist.getEmail(),
                    String.valueOf(bookingReceptionist.getBookingId()),
                    bookingReceptionist.getBookingDate(),
                    bookingReceptionist.getCheckInDate(),
                    bookingReceptionist.getCheckOutDate(),
                    bookingReceptionist.getPreferredRoomType(),
                    String.valueOf(bookingReceptionist.getRoomNo()),
                    bookingReceptionist.getBookingStatus()
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmd = actionEvent.getActionCommand();
        //firs check for navigation command to change the page
        if(cmd.equals("< Log Out")){
            this.window.removeAllChild();
            this.window.add(new HomePage());
        }
        else if(cmd.equals("Home")){
            this.changePage(this.homePage(), this.home);
        }
        else if(cmd.equals("Rooms")){
            this.changePage(this.roomsPage(), this.rooms);
        }
        else if (cmd.equals("Bookings")) {
            this.changePage(this.bookingPage(), this.bookings);
        }
        else if(cmd.equals("Billings")){
            this.changePage(this.billingPage(), this.billings);
        }

    }
}
