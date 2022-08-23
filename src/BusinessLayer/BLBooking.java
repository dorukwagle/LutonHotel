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
            DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
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

    public Booking setBookingStatus(String status) throws Exception{
        try {
            this.booking.setBookingStatus(status);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.setBookingStatus();
        } catch (Exception e){
            throw e;
        }
    }

    public Booking setRoomNo(int roomId) throws Exception{
        try {
            this.booking.setRoomNo(roomId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.setRoomNo();
        } catch (Exception e){
            throw e;
        }
    }

    public Booking setInvoiceId(int invoiceId) throws Exception{
        try{
            this.booking.setInvoiceId(invoiceId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.setInvoiceId();
        } catch (Exception e){
            throw e;
        }
    }

    //method to change or edit the booking
    public Booking updateBooking(int bookingId) throws Exception{
        try {
            this.booking.setBookingId(bookingId);
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.updateBooking();
        } catch (Exception e){
            throw e;
        }
    }

    //method to list all the bookings for receptionist
    public ArrayList<Booking> getAllBookings() throws Exception {
        try {
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getAllBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    //returns list all the upcoming bookings
    public ArrayList<Booking> getUpComingBookings() throws Exception {
        try {
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getUpComingBookings();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Booking> getActiveBookings() throws Exception{
        try {
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getActiveBookings();
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Booking> getPendingBookings() throws Exception{
        try {
            DLBooking dlBooking = new DLBooking(this.booking);
            return dlBooking.getPendingBookings();
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
}
