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

    public abstract void pickupItem(Item item);

    public abstract void askActor(Actor actor, Riddle riddle);

}
