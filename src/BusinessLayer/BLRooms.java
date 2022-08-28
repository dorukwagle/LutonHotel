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
    public ArrayList<Room> getFilteredRooms(String roomTypeFilt) throws Exception{
        try{
            DLRooms dlRooms = new DLRooms(this.room);
            return dlRooms.getFilteredRooms(roomTypeFilt);
        }catch (Exception e){
            throw e;
        }
    }

    public void removeRoom(int id) throws Exception{
        try{
            this.room.setRoomNo(id);
            new DLRooms(this.room).removeRoom();
        }catch (Exception e){
            throw e;
        }
    }
}
