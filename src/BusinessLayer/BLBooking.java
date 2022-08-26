package BusinessLayer;

import DataModel.Booking;
import DataModel.Customer;
import DatabaseLayer.DLBooking;
import Utility.AuthenticationException;
import Utility.InputException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BLBooking {
    private Booking booking;

    public BLBooking(Booking booking) throws Exception{
        try{
            this.setBooking(booking);
        } catch (Exception e){
            throw e;
        }
    }

    public BLBooking(){
        this.booking = new Booking();
    }

    public void setBooking(Booking booking) throws Exception{
        try{
            //validate the date
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            date.setLenient(false);
            date.parse(booking.getCheckInDate());
            date.parse(booking.getCheckOutDate());
            this.booking = booking;
        }catch (ParseException e){
            throw new InputException("InvalidDate: Date format is not valid");
        }
    }

    public Booking requestBooking() throws Exception{
        try{
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.requestBooking();
        } catch (Exception e){
            throw e;
        }
    }

    //method to change or edit or update the booking
    public Booking updateBooking() throws Exception{
        try {
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.updateBooking();
        } catch (Exception e){
            throw e;
        }
    }

    //methods for querying data from booking tables for users
    public ArrayList<Booking> getUserBookings(int userId) throws Exception {
        try {
            this.booking.setCustId(userId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getUserBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getUserUpComingBookings(int userId) throws Exception {
        try {
            this.booking.setCustId(userId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getUserUpComingBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getUserActiveBookings(int userId) throws Exception{
        try {
            this.booking.setCustId(userId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getUserActiveBookings();
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Booking> getUserPendingBookings(int userId) throws Exception{
        try {
            this.booking.setCustId(userId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getUserPendingBookings();
        } catch (Exception e){
            throw e;
        }
    }

    public Booking getBooking(int bookingId) throws Exception{
        try{
            this.booking.setBookingId(bookingId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getBooking();
        } catch (Exception e){
            throw e;
        }
    }
}
