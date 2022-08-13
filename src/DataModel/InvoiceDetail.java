package DataModel;

public class InvoiceDetail {
    private int detailId;
    private String actualCheckinDate;
    private String actualCheckOutDate;
    private float serviceCharge;
    private byte invoiceGenerated;
    private int bookingId;
    private int invoiceId;

    public InvoiceDetail() {
        this.detailId = 0;
        this.actualCheckinDate = "";
        this.actualCheckOutDate = "";
        this.serviceCharge = 0;
        this.invoiceGenerated = 0;
        this.bookingId = 0;
        this.invoiceId = 0;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
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

    public byte getInvoiceGenerated() {
        return invoiceGenerated;
    }

    public void setInvoiceGenerated(byte invoiceGenerated) {
        this.invoiceGenerated = invoiceGenerated;
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
}
