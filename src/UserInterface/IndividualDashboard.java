package UserInterface;

import BusinessLayer.BLBooking;
import BusinessLayer.BLCustomer;
import DataModel.Booking;
import DataModel.Customer;
import DataModel.LoginDetails;
import Utility.Values;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class IndividualDashboard extends CustomerDashboard {
    private Customer customer;
    private LoginDetails loginDetails;

    private JTable upcomingBookings, activeBookings, pendingBookings, bookingsHistory;
    private JLabel creditCard, errorMsg;
    private JDateChooser checkinDate, checkoutDate;
    private JTextField credit;
    private JComboBox roomType;

    private String currentPage = "";

    public IndividualDashboard(Customer customer, LoginDetails loginDetails){
        this.customer = customer;
        this.loginDetails = loginDetails;
        this.contentHolder.add(this.aboutPage());
    }

    protected JPanel aboutPage(){
        this.currentPage = "about";
        JPanel aboutPage = new JPanel();
        aboutPage.setLayout(new BoxLayout(aboutPage, BoxLayout.Y_AXIS));

        //set heading text
        String txt = "Welcome, " + (customer.getCustGender().equals("male") ? "Mr." : "Ms.") + customer.getCustFullName();
        this.headingText.setText(txt);

        JPanel profileHolder = new JPanel();
        profileHolder.setLayout(new GridLayout(0, 1));
        profileHolder.setBackground(Color.WHITE);
        aboutPage.add(profileHolder);

        JLabel head = new JLabel("Profile Details");
        head.setFont(new Font("Serif", Font.BOLD, 25));
        profileHolder.add(head);

        JLabel name = new JLabel("Name: " + customer.getCustFullName());
        name.setFont(new Font("Serif", Font.BOLD, 17));
        profileHolder.add(name);

        JLabel username = new JLabel("Username: @" + customer.getUserName());
        username.setFont(new Font("Serif", Font.BOLD, 17));
        profileHolder.add(username);

        JLabel gender = new JLabel("gender: " + customer.getCustGender());
        gender.setFont(new Font("Serif", Font.BOLD, 17));
        profileHolder.add(gender);

        JLabel email = new JLabel("Email Address: " + loginDetails.getEmailAddress());
        email.setFont(new Font("Serif", Font.BOLD, 17));
        profileHolder.add(email);

        creditCard = new JLabel("Credit Card No.: " + customer.getCreditCardNo());
        creditCard.setFont(new Font("Serif", Font.BOLD, 17));
        profileHolder.add(creditCard);


        JLabel head1 = new JLabel("Upcoming Bookings");
        head1.setFont(new Font("Serif", Font.BOLD, 30));
        profileHolder.add(head1);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No Upcoming bookings"};
        DefaultTableModel model = new DefaultTableModel(data, column){
          @Override
          public boolean isCellEditable(int row, int column){
              return false;
          }
        };
        upcomingBookings = new JTable(model);
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
        btnCancel.addActionListener(this);
        btnCancel.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnCancel);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Serif", Font.BOLD, 40));
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(this);
        btnEdit.setPreferredSize(new Dimension(Values.widthPct(this.container, 20), Values.heightPct(this.container, 8)));
        btnHolder.add(btnEdit);

        this.loadUpcomingBookings();
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

        JLabel checkinLabel = new JLabel("Check In Date: ");
        checkinLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinLabel);

        checkinDate = new JDateChooser();
        checkinDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinDate);

        JLabel checkoutLabel = new JLabel("Check Out Date: ");
        checkoutLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkoutLabel);

        checkoutDate = new JDateChooser();
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

        JPanel errPanel = new JPanel();
        errPanel.setLayout(new FlowLayout());
        bookNow.add(errPanel);
        errorMsg = new JLabel("");
        errorMsg.setFont(new Font("Serif", Font.BOLD, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        errPanel.add(errorMsg);

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
        this.currentPage = "active";
        JPanel activeBooking = new JPanel();
        activeBooking.setLayout(new BoxLayout(activeBooking, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        activeBooking.add(head);

        JLabel heading = new JLabel("Currently Active Bookings:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No Active bookings"};
        DefaultTableModel model = new DefaultTableModel(data, column){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        activeBookings = new JTable(model);
        activeBookings.setRowHeight(30);
        activeBookings.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(activeBookings);
        head.add(scroll, BorderLayout.CENTER);

        this.loadActiveBookings();

        return activeBooking;
    }

    protected JPanel pendingBooking(){
        this.currentPage = "pending";
        JPanel pendingBooking = new JPanel();
        pendingBooking.setLayout(new BoxLayout(pendingBooking, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        pendingBooking.add(head);

        JLabel heading = new JLabel("Your Pending Bookings:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No Pending bookings"};
        DefaultTableModel model = new DefaultTableModel(data, column){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        pendingBookings = new JTable(model);
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

        this.loadPendingBookings();
        return pendingBooking;
    }

    protected JPanel bookingHistory(){
        this.currentPage = "history";
        JPanel bookingHistory = new JPanel();
        bookingHistory.setLayout(new BoxLayout(bookingHistory, BoxLayout.Y_AXIS));

        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        bookingHistory.add(head);

        JLabel heading = new JLabel("Booking History:");
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        head.add(heading, BorderLayout.NORTH);

        String[][] data = {{"Please click on 'Book Now' to make new a booking"}};
        String[] column = {"No Booking History"};
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn(column);
        model.addRow(data);
        bookingsHistory = new JTable(model);
        bookingsHistory.setRowHeight(30);
        bookingsHistory.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(bookingsHistory);
        head.add(scroll, BorderLayout.CENTER);

        this.loadBookingHistory();
        return bookingHistory;
    }

    //method to load all the upcoming bookings in the table
    private void loadUpcomingBookings(){
        try {
            BLBooking blBooking = new BLBooking();
            ArrayList<Booking> bookings = blBooking.getUserUpComingBookings(this.customer.getCustId());
            if(!(bookings.size() > 0)){
                return;
            }
            //finally load data to the table
            this.loadTable(bookings, this.upcomingBookings);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //method to load all the active bookings
    public void loadActiveBookings(){
        try {
            BLBooking blBooking = new BLBooking();
            ArrayList<Booking> bookings = blBooking.getUserActiveBookings(this.customer.getCustId());
            if(!(bookings.size() > 0)){
                return;
            }
            //finally load data to the table
            this.loadTable(bookings, this.activeBookings);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //method to load all the  pending bookings in the table
    public void loadPendingBookings(){
        try {
            BLBooking blBooking = new BLBooking();
            ArrayList<Booking> bookings = blBooking.getUserPendingBookings(this.customer.getCustId());
            if(!(bookings.size() > 0)){
                return;
            }
            //finally load data to the table
            this.loadTable(bookings, this.pendingBookings);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //method to load all the booking history in the table
    public void loadBookingHistory(){
        try {
            BLBooking blBooking = new BLBooking();
            ArrayList<Booking> bookings = blBooking.getUserBookings(this.customer.getCustId());
            if(!(bookings.size() > 0)){
                return;
            }
            //finally load data to the table
            this.loadTable(bookings, this.bookingsHistory);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //method to load the table data
    protected void loadTable(ArrayList<Booking> bookings, JTable table){
        //remove existing data from table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        table.revalidate();

        //add new data to the table
        model.addColumn("FullName");
        model.addColumn("ID");
        model.addColumn("BookedOn");
        model.addColumn("CheckIn");
        model.addColumn("CheckOut");
        model.addColumn("RoomType");
        model.addColumn("RoomNumber");
        model.addColumn("Status");
        //now add new data to the model
        Booking booking;
        for(int i = 0; i < bookings.size(); ++i){
            booking = bookings.get(i);
            model.insertRow(model.getRowCount(), new String[] {
                    this.customer.getCustFullName(),
                    String.valueOf(booking.getBookingId()),
                    booking.getBookingDate(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    booking.getPreferredRoomType(),
                    String.valueOf(booking.getRoomNo()),
                    booking.getBookingStatus()
            });
        }
    }
    //override methods for handling button clicks
    @Override
    protected void requestBooking() {
        String checkin = checkinDate.getJCalendar().getYearChooser().getYear() + "-" +
                checkinDate.getJCalendar().getMonthChooser().getMonth() + "-" +
                checkinDate.getJCalendar().getDayChooser().getDay();

        String checkout = checkoutDate.getJCalendar().getYearChooser().getYear() + "-" +
                checkoutDate.getJCalendar().getMonthChooser().getMonth() + "-" +
                checkoutDate.getJCalendar().getDayChooser().getDay();

        Booking booking = new Booking();
        booking.setCustId(this.customer.getCustId());
        booking.setPreferredRoomType(this.roomType.getSelectedItem().toString());
        booking.setCheckInDate(checkin);
        booking.setCheckOutDate(checkout);

        //set credit card info as well
        this.customer.setCreditCardNo(credit.getText().trim());
        //use BLCustomer layer to update credit card

        try {
            BLBooking blBooking = new BLBooking(booking);
            this.customer.setModelType("login");
            BLCustomer blCustomer = new BLCustomer(this.customer);
            this.customer = blCustomer.updateCreditCard();
            booking = blBooking.requestBooking();

            if(booking != null){
                this.changePage(this.pendingBooking(), super.pendingBookings);
            }
        }catch (Exception e){
            String msg = e.getMessage();
            if(msg.contains("InvalidDate")){
                this.errorMsg.setText("Please enter a valid date");
            }
            else if(msg.contains("InvalidCreditCard")){
                this.errorMsg.setText("Please enter a valid credit card number");
            }
            else {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void cancelBooking() {
        //check if the table is empty
        JTable table;
        String page = this.currentPage;
        if(page.equals("about") && !this.upcomingBookings.getSelectionModel().isSelectionEmpty()){
            table = this.upcomingBookings;
        }
        else if(page.equals("pending") && !this.pendingBookings.getSelectionModel().isSelectionEmpty()){
            table = this.pendingBookings;
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
            String id = model.getValueAt(row, 1).toString();
            String status = "cancelled";

            //get booking information
            BLBooking blBooking = new BLBooking();
            Booking booking = blBooking.getBooking(Integer.parseInt(id));
            booking.setBookingStatus(status);

            //now update the booking
            BLBooking blBooking1 = new BLBooking(booking);
            blBooking1.updateBooking();
            if(page.equals("about")){
                this.loadUpcomingBookings();
            }
            else {
                this.loadPendingBookings();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void editBooking() {
        //check if the table is empty
        JTable table;
        String page = this.currentPage;
        if(page.equals("about") && !this.upcomingBookings.getSelectionModel().isSelectionEmpty()){
            table = this.upcomingBookings;
        }
        else if(page.equals("pending") && !this.pendingBookings.getSelectionModel().isSelectionEmpty()){
            table = this.pendingBookings;
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

        //get the booking id from selected row
        int row = table.getSelectedRow();
        String id = model.getValueAt(row, 1).toString();

        //create a booking object with booking id and pass it to editBooking method
        Booking booking = new Booking();
        booking.setBookingId(Integer.parseInt(id));
        this.editBooking(booking);
    }
}
