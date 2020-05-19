package DataMappers;

import Model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public class RoomMapper {

    public Hashtable<String,Room> getAllRooms() {

        Hashtable<String,Room>  rooms = new Hashtable<>();

        /*
        Room room = new Room();
        rooms.put("spawn",room);
        // TODO: add real rooms - from DB?

         */
        return rooms;
    }
}
