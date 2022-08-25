package DatabaseLayer;

import DataModel.Staff;
import Utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DLStaff {
    private Staff staff;
    private DatabaseConnector db;
    private Connection connection;

    private void init(Staff staff) throws Exception{
        this.staff = staff;
        try {
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        }catch (Exception e){
            throw e;
        }
    }

    public DLStaff(Staff staff) throws Exception{
        this.init(staff);
    }

    public Staff getStaffInfo() throws Exception{
        try{
            String query = "SELECT * FROM staff WHERE user_name = '" + this.staff.getUserName() + "'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setUserName(this.staff.getUserName());
                staff.setStaffType(rs.getString("staff_type"));
                staff.setStaffAddress(rs.getString("staff_address"));
                staff.setStaffGender(rs.getString("staff_gender"));
                staff.setStaffContact(rs.getString("staff_contact"));
                staff.setStaffFullName(rs.getString("staff_full_name"));
                return staff;
            }
            else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }
}
