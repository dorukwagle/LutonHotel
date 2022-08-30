package DatabaseLayer;

import DataModel.Invoice;
import Utility.DatabaseConnector;

import java.awt.font.TextHitInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DLInvoice {
    private Invoice invoice;
    private DatabaseConnector db;
    private Connection connection;

    private void init(Invoice invoice) throws Exception{
        this.invoice = invoice;
        try{
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        }catch (Exception e){
            throw e;
        }
    }

    public DLInvoice(Invoice invoice) throws Exception{
        this.init(invoice);
    }

    public void initInvoice() throws Exception{
        try{
            String query = "INSERT INTO invoice(actual_check_in_date, service_charge, payment_status, booking_id ) " +
                    "VALUES(CURRENT_DATE(), ?, 'unpaid', ?)";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setFloat(1, this.invoice.getServiceCharge());
            statement.setInt(2, this.invoice.getBookingId());
            statement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    public Invoice getInvoice() throws Exception{
        try{
            String query = "SELECT * FROM invoice WHERE booking_id = " + this.invoice.getInvoiceId();
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setServiceCharge(rs.getFloat("service_charge"));
                invoice.setBookingId(rs.getInt("booking_id"));
                invoice.setActualCheckinDate(String.valueOf(rs.getDate("actual_check_in_date")));
                invoice.setActualCheckOutDate(String.valueOf(rs.getDate("actual_check_out_date")));
                invoice.setPaymentStatus(rs.getString("payment_status"));
                invoice.setDiscountAmount(rs.getFloat("dicount_amount"));
                invoice.setTotalPrice(rs.getFloat("total_price"));
                return invoice;
            }
            else{
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    //returns all the unpaid invoices of a customer (mainly corporate) excluding paid invoices and active bookings' invoices
    //in case of individual customer, a single invoice is returned only if any booking is active
    public ArrayList<Invoice> getCustomerInvoices(int customerId) throws Exception{
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        return invoices;
    }
}
