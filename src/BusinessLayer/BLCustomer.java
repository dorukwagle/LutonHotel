package BusinessLayer;

import DataModel.Customer;
import DataModel.User;
import DatabaseLayer.DLCustomer;
import DatabaseLayer.DLUser;
import Utility.AuthenticationException;
import Utility.InputException;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            this.validateCustomer(customer);
        } catch (InputException e){
            throw e;
        }
    }

    private boolean validateCustomer(Customer customer) throws InputException{
        //if the user is logging in, the data does not need to be validated
        if(customer.getModelType().equals("login")){
            this.customer = customer;
            return true;
        }
        //since there are two type of customers
        // it needs to be validated differently during registration
        try{
            if (customer.getCustomerType().equals("corporate")) {
                if (this.corporateValidation(customer)) {
                    this.customer = customer;
                }
            }
            else if (customer.getCustomerType().equals("individual")) {
                if (this.individualValidation(customer)) {
                    this.customer = customer;
                }
            }
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
        //check if the url is valid
        Pattern pattern = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(customer.getWebsite());
        boolean validUrl = matcher.find();
        if(!validUrl){
            throw new InputException("InvalidUrl: website is not valid");
        }
        //check if contact is a number not a string
        try{
            long num = Long.parseLong(customer.getContact());
        }catch (Exception e){
            throw new InputException("InvalidContact: contact is not number");
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
            throw new InputException("Null: Empty fields");
        }
        else if(!(customer.getContact().length() >= 10)) {
            throw new InputException("InvalidContact: must be atleast 10 digit");
        }
        else if(!customer.getCustFullName().contains(" ")) {
            throw new InputException("FullName: full name not given");
        }
        //check if credit card contains valid length
        if(!customer.getCreditCardNo().equals("")) {
            if(customer.getCreditCardNo().length() < 13 ) {
                throw new InputException("CreditCard: Invalid credit card number");
            }
            try{
                Long.parseLong(customer.getCreditCardNo());
            }catch (Exception e){
                throw new InputException("CreditCard: Invalid credit card number");
            }
        }
        //check if contact is a number not a string
        try{
            long num = Long.parseLong(customer.getContact());
        }catch (Exception e){
            throw new InputException("InvalidContact: contact is not number");
        }
        try {
            short age = Short.parseShort(customer.getCustAge());
            if(age < 1 || age > 150 ){
                throw new InputException("InvalidAge: not a valid age");
            }
        }catch (Exception e){
            throw new InputException("InvalidAge: not a valid age");
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
    public void setUpDiscount(){
        DecimalFormat df = new DecimalFormat("0.00");
        //generate random percentage value, maximum is 15 minimum is 5
        float random = 5 + (float) (Math.random() * (15 - 5) );
        String discount  =  df.format(random);
        this.customer.setDiscountPercent(Float.parseFloat(discount));
    }

    public Customer updateCreditCard()throws Exception{
        if(customer.getCreditCardNo().trim().length() < 13 ){
            throw new InputException("InvalidCreditCard");
        }
        try{
            Long.parseLong(customer.getCreditCardNo().trim());
        }catch (Exception e){
            throw new InputException("InvalidCreditCard");
        }
        try {
            DLCustomer dlCustomer = new DLCustomer(customer);
            return dlCustomer.updateCreditCard();
        } catch (Exception e){
            throw e;
        }
    }
}
