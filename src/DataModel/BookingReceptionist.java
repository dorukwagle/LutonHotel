package DataModel;

public class BookingReceptionist extends Booking{
    private String customerName;
    private String contact;
    private String email;

    public BookingReceptionist(){
        this.customerName = "";
        this.contact = "";
        this.email = "";
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
