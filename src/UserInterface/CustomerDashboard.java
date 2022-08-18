package UserInterface;

import Utility.Values;

import javax.swing.*;
import java.awt.*;

public abstract class CustomerDashboard extends JPanel {
    protected Window window;
    protected Container container;
    protected JPanel contentHolder;

    protected JButton about, bookNow, activeBookings, pendingBookings, history;
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
        about.setFocusable(false);
        about.setEnabled(false);
        btnBar.add(about);

        bookNow = new JButton("Book Now");
        bookNow.setFont(new Font("Serif", Font.BOLD, 20));
        bookNow.setFocusable(false);
        btnBar.add(bookNow);

        activeBookings = new JButton("Active Bookings");
        activeBookings.setFont(new Font("Serif", Font.BOLD, 20));
        activeBookings.setFocusable(false);
        btnBar.add(activeBookings);

        pendingBookings = new JButton("Pending Bookings");
        pendingBookings.setFont(new Font("Serif", Font.BOLD, 20));
        pendingBookings.setFocusable(false);
        btnBar.add(pendingBookings);

        history = new JButton("History");
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

        JLabel headingText = new JLabel("Welcome Back, <<mr/ms>. <Name>!> / <organization name>");
        headingText.setFont(new Font("Serif", Font.BOLD, 25));
        heading.add(headingText);

        contentHolder = new JPanel();
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

    public abstract JPanel bookNow();
    public abstract JPanel aboutPage();
    public abstract JPanel activeBooking();
    public abstract JPanel pendingBooking();
    public abstract JPanel bookingHistory();
}
