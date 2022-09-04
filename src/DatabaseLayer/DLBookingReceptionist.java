package DatabaseLayer;

import DataModel.*;
import Utility.AuthenticationException;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    //check if the room is available for the given date
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
                    " ON r.room_no = b.room_no WHERE b.room_no = " + room + " AND (b.check_in_date > DATE('" + checkout + "') OR " +
                    "b.check_out_date < DATE('" + checkin + "')) AND (b.booking_status = 'guaranteed' OR b.booking_status = 'active')";
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

    public Customer searchCustomer(String custEmail, String custType) throws Exception{
        String query = "SELECT c.cust_id FROM login_details ld INNER JOIN customer c ON c.user_name = ld.user_name " +
                "WHERE ld.email_address = '" + custEmail + "' AND c.customer_type = '" + custType + "'";
        try{
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                Customer customer = new Customer();
                customer.setCustId(rs.getInt("cust_id"));
                return customer;
            }
            else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    //calculate the total price and save it to the database, update the invoice
    public void calculateTotalPrice(int invoiceId) throws Exception{
        try {
            String query1 = "SELECT ( DATEDIFF(i.actual_check_out_date, i.actual_check_in_date) * r.room_price + i.service_charge ) as total " +
                    "FROM invoice i INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN room r ON b.room_no = r.room_no " +
                    "WHERE i.invoice_id = " + invoiceId;
            Statement st = this.connection.createStatement();
            ResultSet rst = st.executeQuery(query1);
            rst.next();

            String query = "UPDATE invoice SET total_price = ?" +
                    "WHERE invoice_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setFloat(1, rst.getFloat("total"));
            statement.setInt(2, invoiceId);
            statement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    //calculate the discount amount and update the invoice, only for corporate customer,
    //the method calculateTotalPrice must be executed before executing this method
    public void calculateDiscountAmount(int invoiceId) throws Exception{
        try {
            String subQeury = "SELECT (i.total_price * c.discount_percent/100) AS discount " +
                    "FROM invoice i INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN customer c ON c.cust_id = b.cust_id " +
                    "WHERE i.invoice_id = " + invoiceId;
            Statement stat = this.connection.createStatement();
            ResultSet rs = stat.executeQuery(subQeury);
            rs.next();

            String query = "UPDATE invoice set discount_amount = ? " +
                    "WHERE invoice_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setFloat(1, rs.getFloat("discount"));
            statement.setInt(2, invoiceId);
            statement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    //query database for detail information about the invoice, for corporate customer
    public CorporateInvoice getRaisedInvoice(int invoiceId) throws Exception{
        try {
            String query = "SELECT c.organization_name, c.address, c.contact, i.invoice_id, i.actual_check_in_date, i.actual_check_out_date, " +
                    "i.service_charge, i.total_price, i.discount_amount, r.room_type, r.room_no, r.room_price " +
                    "FROM invoice i INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN customer c ON c.cust_id = b.cust_id " +
                    "INNER JOIN room r ON b.room_no = r.room_no " +
                    "WHERE i.invoice_id = " + invoiceId;
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                CorporateInvoice corporateInvoice = new CorporateInvoice();
                corporateInvoice.setOrgName(rs.getString("organization_name"));
                corporateInvoice.setAddress(rs.getString("address"));
                corporateInvoice.setContact(rs.getString("contact"));
                corporateInvoice.setInvoiceId(rs.getInt("invoice_id"));
                corporateInvoice.setCheckIn(rs.getString("actual_check_in_date"));
                corporateInvoice.setCheckOut(rs.getString("actual_check_out_date"));
                corporateInvoice.setServiceCharge(rs.getFloat("service_charge"));
                corporateInvoice.setTotalPrice(rs.getFloat("total_price"));
                corporateInvoice.setDiscountAmount(rs.getFloat("discount_amount"));
                corporateInvoice.setRoomType(rs.getString("room_type"));
                corporateInvoice.setRoomNo(rs.getInt("room_no"));
                corporateInvoice.setRoomPrice(rs.getFloat("room_price"));
                return corporateInvoice;
            }else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    //query the bill information for individual customer
    public IndividualBill getRaisedBill(int invoiceId) throws Exception{
        try {
            String query = "SELECT c.cust_full_name, c.address, c.contact, i.invoice_id, i.actual_check_in_date, i.actual_check_out_date, " +
                    "i.service_charge, i.total_price, r.room_type, r.room_no, r.room_price " +
                    "FROM invoice i INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN room r on r.room_no = b.room_no " +
                    "INNER JOIN customer c ON c.cust_id = b.cust_id " +
                    "WHERE i.invoice_id = " + invoiceId;
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                IndividualBill individualBill = new IndividualBill();
                individualBill.setCustomerName(rs.getString("cust_full_name"));
                individualBill.setAddress(rs.getString("address"));
                individualBill.setContact(rs.getString("contact"));
                individualBill.setBillId(rs.getInt("invoice_id"));
                individualBill.setCheckIn(rs.getString("actual_check_in_date"));
                individualBill.setCheckOut(rs.getString("actual_check_out_date"));
                individualBill.setServiceCharge(rs.getFloat("service_charge"));
                individualBill.setTotalPrice(rs.getFloat("total_price"));
                individualBill.setRoomType(rs.getString("room_type"));
                individualBill.setRoomNo(rs.getInt("room_no"));
                individualBill.setRoomPrice(rs.getFloat("room_price"));
                return individualBill;
            }else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    //method to returns all the unpaid invoices of a corporate customer
    public ArrayList<Invoice> getCorporateInvoices(int customerId) throws Exception {
        try {
            ArrayList<Invoice> invoices = new ArrayList<>();
            String query = "SELECT i.* " +
                    "FROM invoice i INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN customer c ON c.cust_id = b.cust_id " +
                    "WHERE c.cust_id = " + customerId + " AND i.payment_status = 'unpaid'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setActualCheckinDate(rs.getString("actual_check_in_date"));
                invoice.setActualCheckOutDate(rs.getString("actual_check_out_date"));
                invoice.setServiceCharge(rs.getFloat("service_charge"));
                invoice.setTotalPrice(rs.getFloat("total_price"));
                invoice.setDiscountAmount(rs.getFloat("discount_amount"));
                invoice.setPaymentStatus(rs.getString("payment_status"));
                invoice.setBookingId(rs.getInt("booking_id"));

                invoices.add(invoice);
            }
            return invoices;
        } catch (Exception e) {
            throw e;
        }
    }

    //method to update all the invoices for a given corporate customer from unpaid to paid
    public void setAllAsPaid(int customerId) throws Exception{
        try {
            String query = "UPDATE invoice i " +
                    "INNER JOIN booking b ON i.booking_id = b.booking_id INNER JOIN customer c ON c.cust_id = b.cust_id  " +
                    " set i.payment_status = 'paid' " +
                    "WHERE c.cust_id = " + customerId + " AND i.payment_status = 'unpaid'";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            throw e;
        }
    }

}
