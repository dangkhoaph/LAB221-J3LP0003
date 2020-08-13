package khoaphd.dtos;

import java.io.Serializable;

/**
 *
 * @author KhoaPHD
 */
public class RoomTypeDTO implements Serializable {
    
    private String roomTypeID, roomType;

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}