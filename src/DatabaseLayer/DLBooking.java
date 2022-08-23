package DatabaseLayer;

import DataModel.Booking;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DLBooking {
    private Booking booking;
    private DatabaseConnector db;
    private Connection connection;

    private void init(Booking booking) throws Exception {
        this.booking = booking;
        try {
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        } catch (Exception e) {
            throw e;
        }
    }

    public DLBooking(Booking booking) throws Exception {
        this.init(booking);
    }

    public Booking requestBooking() throws Exception {
        String[] generated = {"booking_id"};
        String query = "INSERT INTO booking(booking_date, check_in_date, check_out_date, preferred_room_type, booking_status, cust_id) " +
                " VALUES (CURRENT_DATE(), DATE(?), DATE(?), ?, 'pending', ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query, generated);
            statement.setString(1, this.booking.getCheckInDate());
            statement.setString(2, this.booking.getCheckOutDate());
            statement.setString(3, this.booking.getPreferredRoomType());
            statement.setInt(4, this.booking.getCustId());

            int updates = statement.executeUpdate();
            if (updates > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    this.booking.setBookingId(rs.getInt("booking_id"));
                    return this.booking;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public ArrayList<Booking> getAllBookings() throws Exception {
        try {
            return this.queryBookings("all");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getUpComingBookings() throws Exception {
        try {
            return this.queryBookings("guaranteed");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getActiveBookings() throws Exception{
        try {
            return this.queryBookings("active");
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Booking> getPendingBookings() throws Exception{
        try {
            return this.queryBookings("pending");
        } catch (Exception e){
            throw e;
        }
    }
    private ArrayList<Booking> queryBookings(String filter) throws Exception {
        try {
            ArrayList<Booking> bookings = new ArrayList<Booking>();
            String query;
            if(filter.equals("all")) {
                query = "SELECT * FROM booking ORDER BY booking_date DESC";
            }
            else {
                query = "SELECT * FROM booking WHERE booking_status = '" + filter + "' ORDER BY check_in_date";
            }
            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setBookingDate(rs.getDate("booking_date").toString());
                booking.setCheckInDate(rs.getDate("check_in_date").toString());
                booking.setCheckOutDate(rs.getDate("check_out_date").toString());
                booking.setCustId(rs.getInt("cust_id"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setPreferredRoomType(rs.getString("preferred_room_type"));
                booking.setInvoiceId(rs.getInt("invoice_id"));
                booking.setRoomNo(rs.getInt("room_no"));
                booking.setStaffId(rs.getInt("staff_id"));

                bookings.add(booking);
            }
            return bookings;
        } catch (Exception e){
            throw e;
        }
    }

}
