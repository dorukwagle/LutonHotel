package DatabaseLayer;

import DataModel.Booking;
import DataModel.BookingReceptionist;
import Utility.AuthenticationException;
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
            String filter = " WHERE b.booking_status = 'pending' ";
            return this.queryBookings(filter);
        } catch (Exception e){
            throw e;
        }
    }

    //method that actually communicates with database and returns the data for receptionist
    private ArrayList<BookingReceptionist> queryBookings(String filterQuery) throws Exception {
        try {
            ArrayList<BookingReceptionist> bookingReceptionists = new ArrayList<BookingReceptionist>();
            String querySuffix = "SELECT c.cust_full_name fullname, c.contact contact, c.customer_type customerType, c.organization_name orgName, " +
                    "ld.email_address email, b.* FROM customer c INNER JOIN booking b on c.cust_id = b.cust_id " +
                    "INNER JOIN login_details ld on ld.user_name = c.user_name ";
            String queryPrefix = " ORDER BY booking_date DESC";
            String query = querySuffix + filterQuery + queryPrefix;

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
                bookingReceptionist.setCustomerType(rs.getString("customerType"));
                bookingReceptionist.setOrganizationName(rs.getString("orgName"));

                bookingReceptionists.add(bookingReceptionist);
            }
            return bookingReceptionists;
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getFilteredBookings(String bookFilter, String roomFilter) throws Exception{
        try {
            String where = " WHERE ";
            String and = " AND ";
            String roomFilt = ( roomFilter.equals("All") ? " " :
                    " b.preferred_room_type = '" + roomFilter + "' ");

            String bookFilt = (bookFilter.equals("History") ? " " :
                " b.booking_status = '" + bookFilter + "' " );

            String filterQuery;
            //if bothe the filters are empty, means no filter is needed, query all the data
            if(roomFilt.equals(" ") && bookFilt.equals(" ")){
                filterQuery = " ";
            }
            //if both the filters are given than, apply both the filters using 'WHERE' and 'AND'
            else if(!roomFilt.equals(" ") && !bookFilt.equals(" ")){
                filterQuery = where + roomFilt + and + bookFilt;
            }
            //if only one of the filter is applied then apply only one filter with WHERE
            else{
                filterQuery = (roomFilt.equals(" ") ? where + bookFilt : where + roomFilt);
            }
            return this.queryBookings(filterQuery);
        }catch (Exception e){
            throw e;
        }
    }

    public boolean isAvailable(int room, String checkin, String checkout) throws Exception{
        try{
            //first check if the room exists in the room table
            String query1 = "SELECT * FROM room WHERE room_no = " + room;
            ResultSet rs1 = this.connection.createStatement().executeQuery(query1);
            if(!rs1.next()){
                throw new AuthenticationException("InvalidRoom");
            }
            //now check if the room is available for the particular date
            String query = "SELECT * FROM booking b INNER JOIN room r " +
                    " ON r.room_no = b.room_no WHERE b.room_no = " + room + " AND (b.check_in_date > DATE(" + checkout + ") OR " +
                    "b.check_out_date < DATE(" + checkin + ")) AND (b.booking_status = 'guaranteed' OR b.booking_status = 'active')";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            throw e;
        }
    }
}
