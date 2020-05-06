package Model;

import DataMappers.RoomMapper;

import java.util.ArrayList;
import java.util.Hashtable;

public class RoomList {
    Hashtable<String,Room> rooms;
    RoomMapper roomMapper;

    public RoomList() {
        roomMapper = new RoomMapper();
        rooms = roomMapper.getAllRooms();
    }

    public Hashtable<String,Room>  getRooms() {
        return rooms;
    }
    public Room getSpawnRoom() {
        return rooms.get("spawn");
    }
}
