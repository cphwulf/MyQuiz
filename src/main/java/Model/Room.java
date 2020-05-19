package Model;

import java.util.Hashtable;

public class Room< T extends  Actor, I extends Item> {
    Door door;
    String name;
    private Hashtable<String, I> items;
    private Hashtable<String, T> players;
    private Hashtable<Direction, Room> exits;
    private Hashtable<String, Key> keys;

    public Room(String name) {
        this.name = name;
        items = new Hashtable<>();
        players = new Hashtable<>();
        exits = new Hashtable<>();
        keys = new Hashtable<>();
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

    public Hashtable<String, Key> getKeys() {
        return keys;
    }

    public Key getKeyByName(String name) {
        return keys.get(name);
    }

    public void addKey(Key key){
        keys.put(key.getName(),key);
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
