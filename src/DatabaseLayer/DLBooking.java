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
                    this.booking.setBookingId(rs.getInt(1));
                    return this.booking;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }


    //methods for querying data from booking tables for users
    public ArrayList<Booking> getUserBookings() throws Exception {
        try {
            return this.queryUserBookings("all");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getUserUpComingBookings() throws Exception {
        try {
            return this.queryUserBookings("guaranteed");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getUserActiveBookings() throws Exception{
        try {
            return this.queryUserBookings("active");
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Booking> getUserPendingBookings() throws Exception{
        try {
            return this.queryUserBookings("pending");
        } catch (Exception e){
            throw e;
        }
    }


    //method for querying the database for users
    private ArrayList<Booking> queryUserBookings(String filter) throws Exception {
        try {
            ArrayList<Booking> bookings = new ArrayList<Booking>();
            String query;
            if(filter.equals("all")) {
                query = "SELECT * FROM booking WHERE cust_id = '" + this.booking.getCustId() + "' ORDER BY booking_date DESC";
            }
            else {
                query = "SELECT * FROM booking WHERE booking_status = '" + filter + "' AND cust_id = '" + this.booking.getCustId() + "' ORDER BY booking_date DESC";
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

    public Booking updateBooking() throws Exception{
        try{
            String query = "UPDATE booking set booking_date = DATE(?), check_in_date = DATE(?), " +
                    "check_out_date = DATE(?), preferred_room_type = ?, booking_status = ?, cust_id = ?, " +
                    "staff_id = ?, room_no = ?, invoice_id = ?  WHERE booking_id = ?";

            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.booking.getBookingDate());
            statement.setString(2, this.booking.getCheckInDate());
            statement.setString(3, this.booking.getCheckOutDate());
            statement.setString(4, this.booking.getPreferredRoomType());
            statement.setString(5, this.booking.getBookingStatus());
            statement.setInt(6, this.booking.getCustId());
            statement.setInt(7, this.booking.getStaffId());
            statement.setInt(8, this.booking.getRoomNo());
            statement.setInt(9, this.booking.getInvoiceId());
            statement.setInt(10, this.booking.getBookingId());

            int updates = statement.executeUpdate();
            if (updates > 0) {
                return this.booking;
            }
            else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    public Booking getBooking() throws Exception{
        try{
            String query = "SELECT * FROM booking WHERE booking_id = " + this.booking.getBookingId();
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
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
                return booking;
            }
            else {
                return null;
            }
        } catch (Exception e){
            throw e;
        }
    }
}
