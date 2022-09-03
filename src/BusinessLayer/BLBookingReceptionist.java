package BusinessLayer;

import DataModel.*;
import DatabaseLayer.DLBookingReceptionist;

import java.sql.Statement;
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

    //method to calculate the invoice and get information about the invoice, only for corporate customer
    public CorporateInvoice getRaisedInvoice(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getRaisedInvoice(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }

    //method to calculate the bill of individual customer
    public IndividualBill getRaisedBill(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getRaisedBill(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }

    //method to set all the invoice payment status of corporate customer as paid
    public void setAllAsPaid(int customerId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            dlBookingReceptionist.setAllAsPaid(customerId);
        }catch (Exception e){
            throw e;
        }
    }

    //calculate the total monthly bill of the corporate customer, and set all invoice as paid
    public CorporateInvoice getRaisedCorporateBill(int customerId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            ArrayList<Invoice> invoices = dlBookingReceptionist.getCorporateInvoices(customerId);
            if(invoices.size() < 1){
                return null;
            }
            //first query the database to fetch all the required data of CorporateInvoice
            CorporateInvoice corporateInvoice = dlBookingReceptionist.getRaisedInvoice(invoices.get(0).getInvoiceId());

            //now add or modify the required data
            corporateInvoice.setTotalSentInvoices(invoices.size());
            corporateInvoice.setInvoiceId(Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(5)));

            float subTotal = 0;
            float totalPrice = 0;
            float discount = 0;

            for (Invoice invoice : invoices) {
                subTotal += invoice.getTotalPrice();
                discount += invoice.getDiscountAmount();
            }
            totalPrice = subTotal - discount;
            corporateInvoice.setDiscountAmount(discount);
            corporateInvoice.setSubTotal(subTotal);
            corporateInvoice.setTotalPrice(totalPrice);

            //finally set all the invoices payment status to paid
            dlBookingReceptionist.setAllAsPaid(customerId);

            return corporateInvoice;
        }catch (Exception e){
            throw e;
        }
    }
}
