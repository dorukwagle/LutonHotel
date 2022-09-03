package BusinessLayer;

import DataModel.*;
import DatabaseLayer.DLBookingReceptionist;

import java.util.ArrayList;

public class BLBookingReceptionist {

    public ArrayList<BookingReceptionist> getFilteredBookings(String bookFilter, String roomFilter) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getFilteredBookings(bookFilter, roomFilter);
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getPendingBookings() throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getPendingBookings();
        } catch (Exception e){
            throw e;
        }
    }

    //returns true if the room is available for the given time and the room exists in the database
    public boolean isAvailable(int room, String checkin, String checkout) throws Exception{
        try{
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.isAvailable(room, checkin, checkout);
        } catch (Exception e){
            throw e;
        }
    }

    public Customer searchCustomer(String custEmail, String custType) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.searchCustomer(custEmail, custType);
        }catch (Exception e){
            throw e;
        }
    }

    //method to calculate and store the total price in the database
    public void calculateTotalPrice(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            dlBookingReceptionist.calculateTotalPrice(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }

    //method to calculate and store the discount amount in the database, only for corporate customer
    public void calculateDiscountAmount(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            dlBookingReceptionist.calculateDiscountAmount(invoiceId);
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

    public CorporateInvoice getRaisedInvoice(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getRaisedInvoice(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }

    public IndividualBill getRaisedBill(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getRaisedInvoice(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }


}
