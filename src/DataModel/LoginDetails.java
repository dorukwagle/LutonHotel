package DataModel;

public class LoginDetails {
    private String userName;
    private String userPassword;
    private String userRole;
    private String emailAddress;

    //this variable is to store whether the data is coming from login form or register form so the Business layer and database layer can identify,
    //and operate on it accordingly, this is not stored in the database
    private String modelType; //either 'register' or 'login'
    public LoginDetails() {
        this.userName = "";
        this.userPassword = "";
        this.userRole = "";
        this.emailAddress = "";
        this.modelType = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setModelType(String modelType){
        this.modelType = modelType;
    }

    public String getModelType(){
        return modelType;
    }
}
