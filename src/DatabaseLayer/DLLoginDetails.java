package DatabaseLayer;

import DataModel.LoginDetails;
import UserInterface.Login;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DLLoginDetails {
    private LoginDetails loginDetails;
    private DatabaseConnector db;
    private Connection connection;

    private void init(LoginDetails loginDetails) throws Exception{
        this.loginDetails = loginDetails;
        try{
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        }catch (Exception e){
            throw e;
        }
    }
    public DLLoginDetails(LoginDetails loginDetails) throws Exception{
        this.init(loginDetails);
    }

    public LoginDetails save() throws Exception{
        try {
            String query = "INSERT INTO login_details(user_name, user_password, email_address, user_role) VALUES(?, ?, ?, ?);";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.loginDetails.getUserName());
            statement.setString(2, this.loginDetails.getUserPassword());
            statement.setString(3, this.loginDetails.getEmailAddress());
            statement.setString(4, this.loginDetails.getUserRole());
            //execute the query
            statement.executeUpdate();
            return this.loginDetails;
        } catch (Exception e){
            throw e;
        }
    }

    public boolean userNameExists() throws Exception{
        try{
            String query = "SELECT * FROM login_details WHERE user_name = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.loginDetails.getUserName());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            throw e;
        }
    }
    public boolean emailExists() throws Exception{
        try{
            String query = "SELECT * FROM login_details WHERE email_address = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.loginDetails.getEmailAddress());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            throw e;
        }
    }

    public LoginDetails checkLogin() throws Exception{
        try{
            boolean isEmail = !this.loginDetails.getEmailAddress().equals("");
            boolean isUserName = !this.loginDetails.getUserName().equals("");

            String query = "SELECT * FROM login_details WHERE" + (isEmail? "email_address" : "user_name" ) + " = ? AND user_password = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, (isUserName ? this.loginDetails.getUserName() : this.loginDetails.getEmailAddress()));
            statement.setString(2, this.loginDetails.getUserPassword());

            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                LoginDetails ld = new LoginDetails();
                ld.setUserName(rs.getString("user_name"));
                ld.setEmailAddress(rs.getString("email_address"));
                ld.setUserRole(rs.getString("user_role"));
                ld.setUserPassword(rs.getString("user_password"));
                return this.loginDetails;
            }
            else {
                //the username of password is incorrect so return null
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }
}


