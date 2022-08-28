package UserInterface;

import BusinessLayer.BLBooking;
import BusinessLayer.BLBookingReceptionist;
import BusinessLayer.BLRooms;
import DataModel.Booking;
import DataModel.BookingReceptionist;
import DataModel.Room;
import DataModel.Staff;
import DatabaseLayer.DLBooking;
import DatabaseLayer.DLBookingReceptionist;
import Utility.Values;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class ReceptionistDashboard extends JPanel implements ActionListener {
    private Window window;
    private Container container;
    private JPanel contentHolder;
    private JButton home, rooms, bookings, billings;
    private JTable pendingBookings, allRooms, bookingsList, bills;
    private JComboBox roomType, availability, bRoomType, bookFilter;
    private String currentPage = "";

    private Staff staff;
    public ReceptionistDashboard(Staff staff){
        this.staff = staff;
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

        JLabel headingText = new JLabel("Welcome, " + (this.staff.getStaffGender().equals("male") ? "Mr. " : "Ms. ") +
                this.staff.getStaffFullName());
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
        btnConfirm.addActionListener(this);
        btnConfirm.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnConfirm);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Serif", Font.BOLD, 40));
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(this);
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
        addRoom.addActionListener(this);
        addRoom.setFocusable(false);
        btns.add(addRoom);

        JButton removeRoom = new JButton("Remove Room");
        removeRoom.setFont(new Font("Serif", Font.BOLD, 30));
        removeRoom.setFocusable(false);
        removeRoom.addActionListener(this);
        btns.add(removeRoom);

        JPanel filters = new JPanel();
        filters.setLayout(new FlowLayout());
        subControl.add(filters, BorderLayout.EAST);

        String[] roomTypes = {"Single Rooms", "Double Rooms", "Twin Rooms"};
        roomType = new JComboBox(roomTypes);
        roomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadRooms();
            }
        });
        roomType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(roomType);

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
        this.currentPage = "booking";
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

        String[] bookingFilter = {"Upcoming", "Active", "History"};
        bookFilter = new JComboBox(bookingFilter);
        bookFilter.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(bookFilter);

        String[] roomTypes = {"All", "Single Rooms", "Double Rooms", "Twin Rooms"};
        bRoomType = new JComboBox(roomTypes);
        bRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadBookings();
            }
        });
        bRoomType.setFont(new Font("Serif", Font.BOLD, 20));
        filters.add(bRoomType);

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
        btnActive.setFont(new Font("Serif", Font.BOLD, 40));
        btnActive.setFocusable(false);
        btnActive.addActionListener(this);
        btnActive.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnActive);

        //add action listeners to filters to make the buttons clickable only when the booking type is upcoming
        bookFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(bookFilter.getSelectedItem().toString().equals("Upcoming")){
                    btnActive.setEnabled(true);
                    btnCancel.setEnabled(true);
                }
                else {
                    btnActive.setEnabled(false);
                    btnCancel.setEnabled(false);
                }
                //now call the filter listener to change the table data
                loadBookings();
            }
        });

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

    //method to load the bookings data according to the applied filter
    private void loadBookings(){
        String bookFilt = this.bookFilter.getSelectedItem().toString();
        String roomFilt = this.bRoomType.getSelectedItem().toString();
        //check for booking type filter
        if(bookFilt.equals("Upcoming")){
            bookFilt = "guaranteed";
        }
        else if(bookFilt.equals("Active")){
            bookFilt = "active";
        }
        else{
            bookFilt = "History";
        }

        //check for room type filter
        if(roomFilt.contains("Single")){
            roomFilt = "single";
        }
        else if(roomFilt.contains("Double")){
            roomFilt = "double";
        }
        else if (roomFilt.contains("Twin")){
            roomFilt = "twin";
        }
        else{
            roomFilt = "All";
        }
        try{
            BLBookingReceptionist blBookingReceptionist = new BLBookingReceptionist();
            this.loadBookingsTable(blBookingReceptionist.getFilteredBookings(bookFilt, roomFilt), this.bookingsList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //method to load all the  pending bookings in the table
    private void loadPendingBookings(){
        try {
            BLBookingReceptionist blBookingReceptionist = new BLBookingReceptionist();
            ArrayList<BookingReceptionist> bookingReceptionists = blBookingReceptionist.getPendingBookings();
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
    private void loadBookingsTable(ArrayList<BookingReceptionist> bookingReceptionists, JTable table){
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
                    (bookingReceptionist.getCustomerType().equals("individual") ?
                            bookingReceptionist.getCustomerName() : bookingReceptionist.getOrganizationName()),
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

    //method to load rooms according to the filter applied
    private void loadRooms(){
        String roomFilt = this.roomType.getSelectedItem().toString();
        if(roomFilt.contains("Single")){
            roomFilt = "single";
        }
        else if(roomFilt.contains("Double")){
            roomFilt = "double";
        }
        else {
            roomFilt = "twin";
        }
        try{
            BLRooms blRooms = new BLRooms();
            this.loadRoomTable(blRooms.getFilteredRooms(roomFilt), this.allRooms);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //method
    private void removeRoom(){
        DefaultTableModel model = (DefaultTableModel) this.allRooms.getModel();
        //already there is one column
        if(model.getColumnCount() < 2 || this.allRooms.getSelectionModel().isSelectionEmpty()){
            return;
        }
        try{
            //now extract the data from selected row
            int row = this.allRooms.getSelectedRow();
            String id = model.getValueAt(row, 0).toString();
            BLRooms blRooms = new BLRooms();
            blRooms.removeRoom(Integer.parseInt(id));
            this.loadRooms();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadRoomTable(ArrayList<Room> rooms, JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        table.revalidate();

        model.addColumn("Room No.");
        model.addColumn("Room Type");
        model.addColumn("Price");
        model.addColumn("Telephone No.");

        Room room;
        for(int i = 0; i < rooms.size(); ++i){
            room = rooms.get(i);

            model.insertRow(model.getRowCount(), new String[]{
                    String.valueOf(room.getRoomNo()),
                    room.getRoomType(),
                    String.valueOf(room.getRoomPrice()),
                    room.getRoomTelephoneNo()
            });
        }
    }

    private void cancelBooking(){
        //check if the table is empty
        JTable table;
        String page = this.currentPage;
        if(page.equals("home") && !this.pendingBookings.getSelectionModel().isSelectionEmpty()){
            table = this.pendingBookings;
        }
        else if(page.equals("booking") && !this.bookingsList.getSelectionModel().isSelectionEmpty()){
            table = this.bookingsList;
        }
        else {
            return;
        }
        //now check if the table is empty
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //already there is one column
        if(model.getColumnCount() < 2){
            return;
        }

        try {
            //now extract the data from selected row
            int row = table.getSelectedRow();
            String id = model.getValueAt(row, 3).toString();
            String status = "cancelled";

            //get booking information
            BLBooking blBooking = new BLBooking();
            Booking booking = blBooking.getBooking(Integer.parseInt(id));
            booking.setBookingStatus(status);

            //now update the booking
            BLBooking blBooking1 = new BLBooking(booking);
            blBooking1.updateBooking();
            if(page.equals("home")){
                this.loadPendingBookings();
            }
            else {
                this.loadBookings();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void confirmBooking(){
        if(this.pendingBookings.getSelectionModel().isSelectionEmpty()){
            return;
        }
        String roomNo = JOptionPane.showInputDialog("Enter the room Number: ");
        if(roomNo.trim().equals("")) {
            return;
        }
        int room = 0;
        try{
            room = Integer.parseInt(roomNo);
        }catch (Exception e){
            confirmBooking();
        }

        try {
            //now check if the table is empty
            DefaultTableModel model = (DefaultTableModel) this.pendingBookings.getModel();
            //already there is one column
            if(model.getColumnCount() < 2){
                return;
            }
            //get the booking id from selected row
            int row = this.pendingBookings.getSelectedRow();

            //check if the room is available
            BLBookingReceptionist blBookingReceptionist = new BLBookingReceptionist();
            boolean available = blBookingReceptionist.isAvailable(room, model.getValueAt(row, 5).toString(), model.getValueAt(row, 6).toString());
            if(!available){
                JOptionPane.showMessageDialog(this.window, "The room is not available for the given date.",
                        "!Room Not Available", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt(model.getValueAt(row, 3).toString());
            BLBooking blBooking = new BLBooking();
            Booking booking = blBooking.getBooking(id);
            booking.setRoomNo(room);
            booking.setBookingStatus("guaranteed");

            //now update the booking
            blBooking = new BLBooking(booking);
            blBooking.updateBooking();
            this.loadPendingBookings();
        }catch (Exception e) {
            if (e.getMessage().contains("InvalidRoom")) {
                JOptionPane.showMessageDialog(this.window, "Room Not Available in the database, please add it to the database",
                        "Invalid Room Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void setAsActive(){
        if(this.bookingsList.getSelectionModel().isSelectionEmpty()){
            return;
        }
        try {
            //now check if the table is empty
            DefaultTableModel model = (DefaultTableModel) this.bookingsList.getModel();
            //already there is one column
            if (model.getColumnCount() < 2) {
                return;
            }
            //get the booking id from selected row
            int row = this.bookingsList.getSelectedRow();
            int id = Integer.parseInt(model.getValueAt(row, 3).toString());
            BLBooking blBooking = new BLBooking();
            Booking booking = blBooking.getBooking(id);
            booking.setBookingStatus("active");

            //now update the booking
            blBooking = new BLBooking(booking);
            blBooking.updateBooking();
            this.loadBookings();
        }catch (Exception e){
            e.printStackTrace();
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

        else if(cmd.equals("Cancel")){
            this.cancelBooking();
        }
        else if(cmd.equals("Confirm")){
            this.confirmBooking();
        }

        else if(cmd.equals("Add Room")){

        }
        else if(cmd.equals("Remove Room")){
            this.removeRoom();
        }
    }
}
