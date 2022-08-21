package Utility;

public class AuthenticationException extends Exception{
    public AuthenticationException(){
        super("DataMismatch: the provided data is either duplicate or not found");
    }

    public AuthenticationException(String exception){
        super(exception);
    }
}
