package BusinessLayer;
import DataModel.User;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import DatabaseLayer.DLUser;
import Utility.InputException;

public class BLUser {
    User user;

    public BLUser(){
        this.user = new User();
    }

    public BLUser(User user) throws InputException{
        try{
            this.setUser(user);
        } catch (InputException e){
            throw e;
        }
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) throws InputException{
        try{
            if(this.validateUser(user))
                this.user = user;
        } catch (InputException ex){
            throw ex;
        }
    }

    private boolean validateUser(User user) throws InputException{
        if(user.getName() == null || user.getName().length() == 0) {
            throw new InputException("User name cannot be empty");
        }
        if(user.getAddress() == null || user.getAddress().length() == 0) {
            throw new InputException("User address cannot be empty");
        }
        return  true;
    }

    public User save() throws Exception{
        //this functions saves the user detail to database and returns the user object after saving
        try{
            DLUser dlUser = new DLUser(this.user);
            return dlUser.save();
        } catch (Exception e){
            throw e;
        }
    }

    public User update() throws Exception{
        //this functions saves the user detail to database and returns the user object after saving
        try{
            DLUser dlUser = new DLUser(this.user);
            return dlUser.update();
        } catch (Exception e){
            throw e;
        }
    }

    public void delete() throws Exception{
        //this functions saves the user detail to database and returns the user object after saving
        try{
            DLUser dlUser = new DLUser(this.user);
            dlUser.delete();
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<User> getAllUser() throws Exception{
        try{
            DLUser dlUser = new DLUser(this.user);
            return dlUser.getAllUser();
        } catch (Exception e){
            throw e;
        }
    }

    public ArrayList<User> searchUsers(String[] keys, String[] values) throws Exception{
        try{
            DLUser dlUser = new DLUser(this.user);
            return dlUser.searchUser(keys, values);
        } catch (Exception e){
            throw e;
        }
    }
}
