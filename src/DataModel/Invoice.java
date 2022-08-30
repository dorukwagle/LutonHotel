package DataModel;

public class Invoice {
    private String actualCheckinDate;
    private String actualCheckOutDate;
    private float serviceCharge;
    private float total_price;
    private float discount_amount;
    private String payment_status;
    private int bookingId;
    private int invoiceId;

    public Invoice() {
        this.invoiceId = 0;
        this.actualCheckinDate = "";
        this.actualCheckOutDate = "";
        this.serviceCharge = 0;
        this.total_price = 0;
        this.discount_amount = 0;
        this.payment_status = "";
        this.bookingId = 0;
    }

    public String getActualCheckinDate() {
        return actualCheckinDate;
    }

    public void setActualCheckinDate(String actualCheckinDate) {
        this.actualCheckinDate = actualCheckinDate;
    }

    public String getActualCheckOutDate() {
        return actualCheckOutDate;
    }

    public void setActualCheckOutDate(String actualCheckOutDate) {
        this.actualCheckOutDate = actualCheckOutDate;
    }

    public float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public float getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(float discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
}
