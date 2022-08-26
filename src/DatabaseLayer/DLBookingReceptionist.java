package DatabaseLayer;

import DataModel.Booking;
import DataModel.BookingReceptionist;
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
    public ArrayList<BookingReceptionist> getAllBookings() throws Exception {
        try {
            return this.queryBookings("all");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getUpComingBookings() throws Exception {
        try {
            return this.queryBookings("guaranteed");
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getActiveBookings() throws Exception{
        try {
            return this.queryBookings("active");
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getPendingBookings() throws Exception{
        try {
            return this.queryBookings("pending");
        } catch (Exception e){
            throw e;
        }
    }

    //method that actually communicates with database and returns the data for receptionist
    private ArrayList<BookingReceptionist> queryBookings(String filter) throws Exception {
        try {
            ArrayList<BookingReceptionist> bookingReceptionists = new ArrayList<BookingReceptionist>();
            String query = "SELECT c.cust_full_name fullname, c.contact contact, ld.email_address email, b.* FROM customer c" +
                    " INNER JOIN booking b on c.cust_id = b.cust_id INNER  JOIN login_details ld on ld.user_name = c.user_name ";
            if(filter.equals("all")) {
                query += " ORDER BY booking_date DESC";
            }
            else {
                query += " WHERE b.booking_status = '" + filter + "' ORDER BY booking_date DESC";
            }
            Statement statement = this.connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                BookingReceptionist bookingReceptionist = new BookingReceptionist();
                bookingReceptionist.setCustomerName(rs.getString("fullname"));
                bookingReceptionist.setContact(rs.getString("contact"));
                bookingReceptionist.setEmail(rs.getString("email"));
                bookingReceptionist.setBookingId(rs.getInt("booking_id"));
                bookingReceptionist.setBookingDate(rs.getDate("booking_date").toString());
                bookingReceptionist.setCheckInDate(rs.getDate("check_in_date").toString());
                bookingReceptionist.setCheckOutDate(rs.getDate("check_out_date").toString());
                bookingReceptionist.setCustId(rs.getInt("cust_id"));
                bookingReceptionist.setBookingStatus(rs.getString("booking_status"));
                bookingReceptionist.setPreferredRoomType(rs.getString("preferred_room_type"));
                bookingReceptionist.setInvoiceId(rs.getInt("invoice_id"));
                bookingReceptionist.setRoomNo(rs.getInt("room_no"));
                bookingReceptionist.setStaffId(rs.getInt("staff_id"));

                bookingReceptionists.add(bookingReceptionist);
            }
            return bookingReceptionists;
        } catch (Exception e){
            throw e;
        }
    }

}
