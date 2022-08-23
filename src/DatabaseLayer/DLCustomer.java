package DatabaseLayer;

import DataModel.Customer;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DLCustomer {
    private Customer customer;
    private DatabaseConnector db;
    private Connection connection;

    private void init(Customer customer) throws Exception{
        this.customer = customer;
        try{
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        }catch (Exception e){
            throw e;
        }
    }
    public DLCustomer(Customer customer) throws Exception{
        this.init(customer);
    }

    //save the customer information, called when the user registers for the first time
    public Customer save() throws Exception{
        //prepare data to return after inserting
        String query;
        PreparedStatement statement;
        try {
            //create query for individual customer
            if (this.customer.getCustomerType().equals("individual")) {
                String[] generated = {"cust_id"};
                query = "INSERT INTO customer(cust_full_name, cust_age, address, cust_gender, contact, credit_card_no, user_name, customer_type)" +
                        " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                statement = this.connection.prepareStatement(query, generated);
                statement.setString(1, this.customer.getCustFullName());
                statement.setString(2, this.customer.getCustAge());
                statement.setString(3, this.customer.getAddress());
                statement.setString(4, this.customer.getCustGender());
                statement.setString(5, this.customer.getContact());
                statement.setString(6, this.customer.getCreditCardNo());
                statement.setString(7, this.customer.getUserName());
                statement.setString(8, this.customer.getCustomerType());
                int updates = statement.executeUpdate();
                if(updates > 0){
                    ResultSet rs = statement.getGeneratedKeys();
                    if(rs.next()) {
                        this.customer.setCustId(rs.getInt(1));
                        return this.customer;
                    }
                }
            }
            //if the customer is corporate organization
            else if (this.customer.getCustomerType().equals("corporate")) {
                String[] generated = {"cust_id"};
                query = "INSERT INTO customer(organization_name, website, contact, account_valid_till, next_billing_date, discount_percent, user_name, address, customer_type)" +
                        " VALUES(?, ?, ?, DATE_ADD(CURRENT_DATE(), INTERVAL 365 DAY), adddate(last_day(subdate(now(), interval 1 month)), 1 ), ?, ?, ?, ?)";
                statement = this.connection.prepareStatement(query, generated);
                statement.setString(1, this.customer.getOrganizationName());
                statement.setString(2, this.customer.getWebsite());
                statement.setString(3, this.customer.getContact());
                statement.setFloat(4, this.customer.getDiscountPercent());
                statement.setString(5, this.customer.getUserName());
                statement.setString(6, this.customer.getAddress());
                statement.setString(7, this.customer.getCustomerType());
                //execute the query
                int updates = statement.executeUpdate();
                if(updates > 0){
                    ResultSet rs = statement.getGeneratedKeys();
                    if(rs.next()){
                        this.customer.setCustId(rs.getInt(1));
                        return this.customer;
                    }
                }
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public Customer getCustomerInfo() throws Exception{
        Customer customer = new Customer();
        String query = "SELECT * FROM customer WHERE user_name = ?";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.customer.getUserName());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                if(rs.getString("customer_type").equals("corporate")){
                    customer.setOrganizationName(rs.getString("organization_name"));
                    customer.setWebsite(rs.getString("website"));
                    customer.setContact(rs.getString("contact"));
                    customer.setCustId(rs.getInt("cust_id"));
                    customer.setAddress(rs.getString("address"));
                    customer.setUserName(rs.getString("user_name"));
                    customer.setCustomerType(rs.getString("customer_type"));
                    customer.setAccountValidTill(String.valueOf(rs.getDate("account_valid_till")));
                    customer.setNextBillingDate(String.valueOf(rs.getDate("next_billing_date")));
                    customer.setDiscountPercent(rs.getFloat("discount_percent"));
                    customer.setCustomerType(rs.getString("customer_type"));
                }
                else {
                    customer.setCustId(rs.getInt("cust_id"));
                    customer.setAddress(rs.getString("address"));
                    customer.setContact(rs.getString("contact"));
                    customer.setCustFullName(rs.getString("cust_full_name"));
                    customer.setCustGender(rs.getString("cust_gender"));
                    customer.setCreditCardNo(rs.getString("credit_card_no"));
                    customer.setUserName(rs.getString("user_name"));
                    customer.setCustAge(rs.getString("cust_age"));
                    customer.setCustomerType(rs.getString("customer_type"));
                }
            }
            return customer;

        } catch (Exception e){
            throw e;
        }
    }
}
