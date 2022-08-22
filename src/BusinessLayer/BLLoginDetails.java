package BusinessLayer;

import DataModel.LoginDetails;
import DatabaseLayer.DLLoginDetails;
import UserInterface.Login;
import Utility.AuthenticationException;
import Utility.InputException;
import com.mysql.cj.log.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BLLoginDetails {
    private LoginDetails loginDetails;
    private DLLoginDetails dlLoginDetails;

    public BLLoginDetails(LoginDetails loginDetails) throws InputException {
        try {
            this.setLoginDetails(loginDetails);
        } catch (InputException e){
            throw e;
        }
    }

    public void setLoginDetails(LoginDetails loginDetails) throws InputException {
        try {
            if(this.validateLoginDetails(loginDetails)){
                this.loginDetails = loginDetails;
            }
        } catch (InputException e){
            throw e;
        }
    }

    private boolean validateLoginDetails(LoginDetails loginDetails) throws InputException{
        //check if any value is null in case of register form
        boolean email = loginDetails.getEmailAddress().equals("");
        boolean userName = loginDetails.getUserName().equals("");
        boolean isNull = (loginDetails.getModelType().equals("login") ? (userName) : (email || userName)) ||
                loginDetails.getUserPassword().equals("");

        //check if the email address is valid
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(loginDetails.getEmailAddress());
        boolean validEmail = matcher.find();
        //if the data is coming from login form, there may not be email address
        validEmail = !loginDetails.getModelType().equals("register") || validEmail;

        if(isNull){
            throw new InputException("Null: Empty fields");
        }
        else if(!validEmail){
            throw new InputException("EmailAddress: Invalid email address");
        }
        else if(loginDetails.getUserPassword().length() < 4){
            throw new InputException("Password: password minimum length is 4");
        }

        return true;
    }

    //method to save the login details to the database and return the login details object after saving
    public LoginDetails save() throws Exception{
        try{
            //now validate the username and email from the database
            //username and email should be unique
            dlLoginDetails = new DLLoginDetails(this.loginDetails);
            if(dlLoginDetails.userNameExists()){
                throw new AuthenticationException("UserName: duplicate username");
            }
            else if(dlLoginDetails.emailExists()){
                throw new AuthenticationException("Email: duplicate email");
            }
            return dlLoginDetails.save();
        } catch (Exception ae){
            throw ae;
        }
    }

    //method to login i.e. checks whether the username/email and password matches the record in the login details
    //and finally returns new login details object with all the login details and user role if the login is successful
    public LoginDetails checkLogin() throws Exception{
        try {
            dlLoginDetails = new DLLoginDetails(this.loginDetails);
            return dlLoginDetails.checkLogin();
        }catch (Exception e){
            throw e;
        }
    }
}
