package Model;

import Util.IdFactory;
import com.mysql.cj.protocol.Message;

import java.util.ArrayList;

public abstract class Actor {
    String name;
    int id;
    String description;
    ArrayList<Item> items;

    public Actor(String name) {
        this.name = name;
        this.id = IdFactory.getInstance().getActorId();
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public abstract void pickupItem(Item item);

    public abstract void askActor(Actor actor, Riddle riddle);

}
