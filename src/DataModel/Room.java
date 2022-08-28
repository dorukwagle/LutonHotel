package DataModel;

public class Room {
    private int roomNo;
    private String roomType;
    private float roomPrice;
    private String roomTelephoneNo;

    //while adding new room, this constructor is called
    public Room(int roomNo, String roomType, float roomPrice, String roomAvailability, String roomTelephoneNo) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomTelephoneNo = roomTelephoneNo;
    }

    public Room() {
        this.roomNo = 0;
        this.roomType = "";
        this.roomPrice = 0f;
        this.roomTelephoneNo = "";
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomTelephoneNo() {
        return roomTelephoneNo;
    }

    public void setRoomTelephoneNo(String roomTelephoneNo) {
        this.roomTelephoneNo = roomTelephoneNo;
    }
}
