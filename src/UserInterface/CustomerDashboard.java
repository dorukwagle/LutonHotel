package UserInterface;

import BusinessLayer.BLBooking;
import DataModel.Booking;
import Utility.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class CustomerDashboard extends JPanel implements ActionListener {
    protected Window window;
    protected Container container;
    protected JPanel contentHolder;
    protected JLabel headingText;
    protected JButton about, bookNow, activeBookings, pendingBookings, history, logOut;
    public CustomerDashboard(){
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

        about = new JButton("About");
        about.setFont(new Font("Serif", Font.BOLD, 20));
        about.addActionListener(this);
        about.setFocusable(false);
        about.setEnabled(false);
        btnBar.add(about);

        bookNow = new JButton("Book Now");
        bookNow.setFont(new Font("Serif", Font.BOLD, 20));
        bookNow.addActionListener(this);
        bookNow.setFocusable(false);
        btnBar.add(bookNow);

        activeBookings = new JButton("Active Bookings");
        activeBookings.setFont(new Font("Serif", Font.BOLD, 20));
        activeBookings.addActionListener(this);
        activeBookings.setFocusable(false);
        btnBar.add(activeBookings);

        pendingBookings = new JButton("Pending Bookings");
        pendingBookings.setFont(new Font("Serif", Font.BOLD, 20));
        pendingBookings.addActionListener(this);
        pendingBookings.setFocusable(false);
        btnBar.add(pendingBookings);

        history = new JButton("History");
        history.setFont(new Font("Serif", Font.BOLD, 20));
        history.addActionListener(this);
        history.setFocusable(false);
        btnBar.add(history);

        logOut = new JButton("< Log Out");
        logOut.setFocusable(false);
        logOut.addActionListener(this);
        logOut.setFont(new Font("Serif", Font.BOLD, 20));
        topBar.add(logOut, BorderLayout.EAST);

        JPanel heading = new JPanel();
        heading.setLayout(new FlowLayout());
        add(heading);

        headingText = new JLabel("");
        headingText.setFont(new Font("Serif", Font.BOLD, 25));
        heading.add(headingText);

        contentHolder = new JPanel(){
            @Override
            public Component add(Component comp){
                super.add(comp);
                reDraw();
                return comp;
            }
        };
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));
        add(contentHolder);
    }

    public void setFocused(JButton btn){
        this.about.setEnabled(true);
        this.bookNow.setEnabled(true);
        this.activeBookings.setEnabled(true);
        this.pendingBookings.setEnabled(true);
        this.history.setEnabled(true);
        btn.setEnabled(false);
    }

   private void reDraw() {
        this.revalidate();
        this.repaint();
    }

    //method to change the page, i.e. when a button the menu bar is clicked, change the page content respectively
    public void changePage(JPanel page, JButton btn){
        this.setFocused(btn);
        this.contentHolder.removeAll();
        this.contentHolder.add(page);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        //add about page
        if(command.equals("About")){
            this.changePage(this.aboutPage(), this.about);
        }
        //add book now page
        else if (command.equals("Book Now")) {
            this.changePage(this.bookNow(), this.bookNow);
        }
        //add active bookings page
        else if (command.equals("Active Bookings")) {
            this.changePage(this.activeBooking(), this.activeBookings);
        }
        //add pending bookings page
        else if (command.equals("Pending Bookings")) {
            this.changePage(this.pendingBooking(), this.pendingBookings);
        }
        //add booking history pages
        else if(command.equals("History")){
            this.changePage(this.bookingHistory(), this.history);
        }
        //logout the user, redirect to the home page
        else if (command.equals("< Log Out")) {
            this.window.removeAllChild();
            this.window.add(new HomePage());
        }
        else if (command.equals("Edit")){
            this.editBooking();
        }
        else if(command.equals("Cancel")){
            this.cancelBooking();
        }
        else if(command.equals("Request Booking")){
            this.requestBooking();
        }
    }

    protected void editBooking(Booking booking){
        JDialog dialog = new JDialog(this.window, "Edit Booking");

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

        JLabel checkinLabel = new JLabel("Check In Date(yyyy-MM-dd): ");
        checkinLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinLabel);

        JTextField checkinDate = new JTextField();
        checkinDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkinDate);

        JLabel checkoutLabel = new JLabel("Check Out Date(yyyy-MM-dd): ");
        checkoutLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkoutLabel);

        JTextField checkoutDate = new JTextField();
        checkoutDate.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(checkoutDate);

        JLabel roomLabel = new JLabel("Room Type: ");
        roomLabel.setFont(new Font("Serif", Font.BOLD, 20));
        inputHolder.add(roomLabel);

        String[] rooms = {"Single", "Double", "Twin"};
        JComboBox roomType = new JComboBox(rooms);
        roomType.setFont(new Font("Serif", Font.BOLD, 25));
        inputHolder.add(roomType);

        JPanel errPanel = new JPanel();
        errPanel.setLayout(new FlowLayout());
        bookNow.add(errPanel);
        JLabel errorMsg = new JLabel("");
        errorMsg.setFont(new Font("Serif", Font.BOLD, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        errPanel.add(errorMsg);

        JPanel btnHold = new JPanel();
        btnHold.setLayout(new FlowLayout());
        btnHold.setPreferredSize(new Dimension(Values.widthPct(this.container, 40), Values.heightPct(this.container, 10)));
        bookNow.add(btnHold);
        JButton requestBooking = new JButton("Save Booking");
        requestBooking.setFont(new Font("Serif", Font.BOLD, 40));
        requestBooking.setFocusable(false);
        requestBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Booking booking1;
                //get all the booking information and manually over write it
                try {
                    BLBooking blBooking = new BLBooking();
                    booking1 = blBooking.getBooking(booking.getBookingId());
                    booking1.setBookingStatus("pending");
                    booking1.setRoomNo(0);
                    booking1.setCheckInDate(checkinDate.getText().trim());
                    booking1.setCheckOutDate(checkoutDate.getText().trim());
                    booking1.setPreferredRoomType(roomType.getSelectedItem().toString());

                    blBooking = new BLBooking(booking1);
                    blBooking.updateBooking();
                    dialog.dispose();
                    //update table content after saving
                    changePage(pendingBooking(), pendingBookings);
                } catch (Exception e){
                    String msg = e.getMessage();
                    if(msg.contains("InvalidDate")){
                        errorMsg.setText("Please enter the valid date");
                    } else {
                        e.printStackTrace();
                    }
                }

            }
        });
        btnHold.add(requestBooking);

        dialog.add(bookNow);

        dialog.setSize(new Dimension(Values.widthPct(this.container, 60), Values.heightPct(this.container, 60)));
        dialog.setVisible(true);
    }

    protected abstract JPanel bookNow();
    protected abstract JPanel aboutPage();
    protected abstract JPanel activeBooking();
    protected abstract JPanel pendingBooking();
    protected abstract JPanel bookingHistory();
    protected abstract void requestBooking();
    protected abstract void cancelBooking();
    protected abstract void editBooking();

    protected abstract void loadTable(ArrayList<Booking> booking, JTable table);
}
