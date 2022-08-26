package DatabaseLayer;

import DataModel.Booking;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DLBookingReceptionist {
    private DatabaseConnector db;
    private Connection connection;

    private void init() throws Exception {
        try {
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        } catch (Exception e) {
            throw e;
        }
    }

    public DLBookingReceptionist() throws Exception {
        this.init();
    }

    //method for querying the data from booking table for receptionist
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

    //method that actually communicates with database and returns the data for receptionist
    private ArrayList<Booking> queryBookings(String filter) throws Exception {
        try {
            ArrayList<Booking> bookings = new ArrayList<Booking>();
            String query;
            if(filter.equals("all")) {
                query = "SELECT * FROM booking ORDER BY booking_date DESC";
            }
            else {
                query = "SELECT * FROM booking WHERE booking_status = '" + filter + "' ORDER BY booking_date DESC";
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
