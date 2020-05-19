package Model;

import Exceptions.ItemAlreadyPickedException;
import Util.IdFactory;

import java.util.ArrayList;

public interface Agent {
    public abstract void pickupItem(Item item) throws ItemAlreadyPickedException;
    public abstract void askActor(Actor actor, Riddle riddle);
}
