package BusinessLayer;

import DataModel.Room;
import DatabaseLayer.DLRooms;

import java.util.ArrayList;

public class BLRooms {
    private Room room;

    public BLRooms(){
        this.room = new Room();
    }

    public BLRooms(Room room){
        this.room = room;
    }

    public void save() throws Exception{
        try {
            DLRooms dlRooms = new DLRooms(this.room);
            dlRooms.save();
        }catch (Exception e){
            throw e;
        }
    }
    public ArrayList<Room> getFilteredRooms(String availFilter, String roomTypeFilt) throws Exception{
        try{
            DLRooms dlRooms = new DLRooms(this.room);
            return dlRooms.getFilteredRooms(availFilter, roomTypeFilt);
        }catch (Exception e){
            throw e;
        }
    }
}
