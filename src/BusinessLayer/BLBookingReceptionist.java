package BusinessLayer;

import DataModel.Booking;
import DataModel.BookingReceptionist;
import DataModel.Customer;
import DatabaseLayer.DLBooking;
import DatabaseLayer.DLBookingReceptionist;
import DatabaseLayer.DLRooms;

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

    public Customer serchCustomer(String custEmail, String custType) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.searchCustomer(custEmail, custType);
        }catch (Exception e){
            throw e;
        }
    }

    public void calculateTotalPrice(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            dlBookingReceptionist.calculateTotalPrice(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }
    public void calculateDiscount(int invoiceId) throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            dlBookingReceptionist.calculateDiscount(invoiceId);
        }catch (Exception e){
            throw e;
        }
    }
}
