package BusinessLayer;

import DataModel.Booking;
import DataModel.BookingReceptionist;
import DatabaseLayer.DLBooking;
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
}
