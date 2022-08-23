package BusinessLayer;

import DataModel.Booking;
import DataModel.Customer;
import DatabaseLayer.DLBooking;
import Utility.AuthenticationException;
import Utility.InputException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
