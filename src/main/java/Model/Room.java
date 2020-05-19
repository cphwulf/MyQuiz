package Model;

import java.util.Hashtable;

public class Room< T extends  Actor, I extends Item> {
    Door door;
    String name;
    private Hashtable<String, I> items;
    private Hashtable<String, T> players;
    private Hashtable<Direction, Room> exits;

    public Room(String name, Hashtable<String, I> table) {
        this.name = name;
        items = table;
        players = new Hashtable<>();
        exits = new Hashtable<>();
    }

    public void addDoor(Door door, Direction direction, Room nextRoom) {
        this.door = door;
    }

    public String getName() {
        return this.name;
    }

    public Door getDoor() {
        return this.door;
    }

    public void addItem(I item) {
        items.put(item.getName(),item);
    }

    public void addExit(Direction direction, Room room) {
        exits.put(direction,room);
    }

    public void removePlayer(T p) {
        players.remove(p.getName());

    }
    public void addPlayer(T p) {

        players.put(p.getName(),p);
    }
    public Room to(Direction direction) {
        Room newRoom = this.exits.get(direction);
        return newRoom;
    }
}
