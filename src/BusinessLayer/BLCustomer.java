package BusinessLayer;

import DataModel.Customer;
import DataModel.User;
import DatabaseLayer.DLCustomer;
import DatabaseLayer.DLUser;
import Utility.InputException;

import javax.swing.*;
import java.text.DecimalFormat;

public class BLCustomer {
    private Customer customer;

    public BLCustomer(Customer customer) throws InputException {
        try{
            this.setCustomer(customer);
        } catch (InputException e){
            throw e;
        }
    }

    public void setCustomer(Customer customer) throws InputException{
        try{
            if(this.validateCustomer(customer));{
                this.customer = customer;
            }
        } catch (InputException e){
            throw e;
        }
    }

    private boolean validateCustomer(Customer customer) throws InputException{
        //since there are two type of customers
        // it needs to be validated differently
        try{
            if (customer.getCustomerType().equals("corporate"))
                if (this.corporateValidation(customer))
                    this.customer = customer;
            else if (customer.getCustomerType().equals("individual"))
                if (this.individualValidation(customer))
                    this.customer = customer;
        } catch (InputException e){
            throw e;
        }
        return true;
    }

    //method for validating corporate customer data
    private boolean corporateValidation(Customer customer) throws InputException{
        //check if any of the required values are null or empty
        boolean isNull = customer.getContact().equals("") ||
                customer.getOrganizationName().equals("") ||
                customer.getAddress().equals("") ||
                customer.getWebsite().equals("") ||
                customer.getUserName().equals("");

        if(isNull) {
            throw new InputException("Null: Empty fields");
        }
        else if(!(customer.getContact().length() >= 10)) {
            throw new InputException("InvalidContact: must be atleast 10 digit");
        }
        return true;
    }

    //method for validating individual customer data
    private boolean individualValidation(Customer customer) throws InputException{
        //check if any of the required values are null or empty
        boolean isNull = customer.getContact().equals("") ||
                customer.getCustFullName().equals("") ||
                customer.getAddress().equals("") ||
                customer.getCustAge().equals("") ||
                customer.getCustGender().equals("") ||
                customer.getUserName().equals("");

        if(isNull) {
            throw new InputException("Null: fields");
        }
        else if(!(customer.getContact().length() >= 10)) {
            throw new InputException("InvalidContact: must be atleast 10 digit");
        }
        else if(!customer.getCustFullName().contains(" ")) {
            throw new InputException("FullName: full name not given");
        }
        else if(!(customer.getCreditCardNo().equals("") || customer.getCreditCardNo().length() == 16)) {
            throw new InputException("CreditCard: Invalid credit card number");
        }

        return true;
    }

    //method to save the customer details to database and returns the customer object after saving
    public Customer save() throws Exception{
        try{
             DLCustomer dlCustomer = new DLCustomer(this.customer);
            return dlCustomer.save();
        } catch (Exception e){
            throw e;
        }
    }

    //method to fetch the information about the user when the user logs in, or just fetch the user information for other use
    //like renewal of corporate customer account
    public Customer getCustomerInfo() throws Exception{
        //method to obtain full user information during login or account renewal
        //the customer will contain, user_name and cust_id only
        try{
            DLCustomer dlCustomer = new DLCustomer(this.customer);
            return dlCustomer.getCustomerInfo();
        }catch (Exception e){
            throw e;
        }
    }

    //method to calculate discount for the corporate customer
    public Customer setUpDiscount(){
        DecimalFormat df = new DecimalFormat("0.00");
        //generate random percentage value, maximum is 15 minimum is 5
        float random = 5 + (float) (Math.random() * (15 - 5) );
        String discount  =  df.format(random);
        this.customer.setDiscountPercent(Float.parseFloat(discount));
        return this.customer;
    }

}
