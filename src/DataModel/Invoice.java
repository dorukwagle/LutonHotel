package DataModel;

public class Invoice {
    private String actualCheckinDate;
    private String actualCheckOutDate;
    private float serviceCharge;
    private float totalPrice;
    private float discountAmount;
    private String paymentStatus;
    private int bookingId;
    private int invoiceId;

    public Invoice() {
        this.invoiceId = 0;
        this.actualCheckinDate = "";
        this.actualCheckOutDate = "";
        this.serviceCharge = 0;
        this.totalPrice = 0;
        this.discountAmount = 0;
        this.paymentStatus = "";
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
