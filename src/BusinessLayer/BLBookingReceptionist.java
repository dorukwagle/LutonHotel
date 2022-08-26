package BusinessLayer;

import DataModel.Booking;
import DataModel.BookingReceptionist;
import DatabaseLayer.DLBooking;
import DatabaseLayer.DLBookingReceptionist;

import java.util.ArrayList;

public class BLBookingReceptionist {

    //method to list all the bookings for receptionist
    public ArrayList<BookingReceptionist> getAllBookings() throws Exception {
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getAllBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    //returns list all the upcoming bookings
    public ArrayList<BookingReceptionist> getUpComingBookings() throws Exception {
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getUpComingBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<BookingReceptionist> getActiveBookings() throws Exception{
        try {
            DLBookingReceptionist dlBookingReceptionist = new DLBookingReceptionist();
            return dlBookingReceptionist.getActiveBookings();
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
