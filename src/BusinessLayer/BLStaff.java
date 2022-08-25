package BusinessLayer;

import DataModel.Staff;
import DatabaseLayer.DLStaff;

public class BLStaff {
    private Staff staff;

    public BLStaff(Staff staff){
        this.staff = staff;
    }

    public BLStaff(){

    }

    public Staff getStaffInfo(String username) throws Exception{
        try {
            Staff staff = new Staff();
            staff.setUserName(username);
            DLStaff dlStaff = new DLStaff(staff);
            return dlStaff.getStaffInfo();
        } catch (Exception e){
            throw e;
        }
    }
}
