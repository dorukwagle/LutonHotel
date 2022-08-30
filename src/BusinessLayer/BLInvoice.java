package BusinessLayer;

import DataModel.Invoice;
import DatabaseLayer.DLInvoice;

public class BLInvoice {
    private Invoice invoice;

    public BLInvoice(Invoice invoice){
        this.invoice = invoice;
        //also set service charge
        this.setServiceCharge();
    }

    public void initInvoice() throws Exception{
        try {
            DLInvoice dlInvoice = new DLInvoice(this.invoice);
            dlInvoice.initInvoice();
        }catch (Exception e){
            throw e;
        }
    }

    public void setServiceCharge(){
        float serviceCharge = 200f;
        this.invoice.setServiceCharge(serviceCharge);
    }
}
