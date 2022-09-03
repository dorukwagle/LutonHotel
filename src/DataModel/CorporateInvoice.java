package DataModel;

public class CorporateInvoice extends IndividualBill{
    private String orgName;
    private double subTotal;
    private double discountAmount;
    private int invoiceId;
    public CorporateInvoice(){
        this.orgName = "";
        this.subTotal = 0;
        this.discountAmount = 0;
        this.invoiceId = 0;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
