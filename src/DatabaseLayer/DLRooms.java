package DatabaseLayer;

import DataModel.Room;
import DataModel.Staff;
import Utility.DatabaseConnector;

import javax.swing.plaf.nimbus.State;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DLRooms {
    private Room room;
    private DatabaseConnector db;
    private Connection connection;

    public DLRooms(Room room) throws Exception{
        this.room = room;
        try{
            this.db = DatabaseConnector.getInstance();
            this.connection = this.db.getConnection();
        } catch (Exception e){
            throw e;
        }
    }

    public void save() throws Exception{
        String query = "INSERT INTO rooms(room_no, room_type, room_price, room_availability, room_telephone_no) VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, this.room.getRoomNo());
            statement.setString(2, this.room.getRoomType().toLowerCase());
            statement.setFloat(3, this.room.getRoomPrice());
            statement.setString(4, this.room.getRoomAvailability().toLowerCase());
            statement.setString(5, this.room.getRoomTelephoneNo());

            statement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Room> getFilteredRooms(String availFilt, String roomFilt) throws Exception{
        ArrayList<Room> rooms = new ArrayList<Room>();
        String query = "SELECT * FROM room WHERE room_availability = '" + availFilt + "' AND room_type = '" + roomFilt + "'";
        try{
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                Room room = new Room();
                room.setRoomNo(rs.getInt("room_no"));
                room.setRoomAvailability(rs.getString("room_availability"));
                room.setRoomPrice(rs.getFloat("room_price"));
                room.setRoomType(rs.getString("room_type"));
                room.setRoomTelephoneNo(rs.getString("room_telephone_no"));

                rooms.add(room);
            }
            return rooms;
        }catch (Exception e){
            throw e;
        }
    }
}
