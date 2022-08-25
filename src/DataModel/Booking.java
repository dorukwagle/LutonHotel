package DataModel;

public class Booking {
    protected int bookingId;
    protected String bookingDate;
    protected String checkInDate;
    protected String checkOutDate;
    protected String preferredRoomType;
    protected String bookingStatus;
    protected int custId;
    protected int roomNo;
    protected int staffId;
    protected int invoiceId;

    public Booking() {
        this.bookingId = 0;
        this.bookingDate = "";
        this.checkInDate = "";
        this.checkOutDate = "";
        this.preferredRoomType = "";
        this.bookingStatus = "";
        this.custId = 0;
        this.roomNo = 0;
        this.staffId = 0;
        this.invoiceId = 0;
    }

    public Booking(String checkInDate, String checkOutDate, String preferredRoomType) {
        this();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.preferredRoomType = preferredRoomType;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getPreferredRoomType() {
        return preferredRoomType;
    }

    public void setPreferredRoomType(String preferredRoomType) {
        this.preferredRoomType = preferredRoomType;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
