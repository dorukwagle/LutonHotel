package DataModel;

public class LoginDetails {
    private String userName;
    private String userPassword;
    private String userRole;
    private String emailAddress;

    public LoginDetails() {
        this.userName = "";
        this.userPassword = "";
        this.userRole = "";
        this.emailAddress = "";
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
}
