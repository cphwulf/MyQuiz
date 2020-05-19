package Model;

import Exceptions.ItemAlreadyPickedException;

public class AIPlayer extends Actor {
    Item item;
    Riddle riddle;

    public AIPlayer(String name) {
        super(name);
    }

    @Override
    public void pickupItem(Item item) throws ItemAlreadyPickedException {
        if (this.item == null) {
            this.item = item;
        } else {
            throw new ItemAlreadyPickedException("Error with " +item.getDescription());
        }
    }

    @Override
    public void askActor(Actor actor, Riddle riddle) {

    }
}
