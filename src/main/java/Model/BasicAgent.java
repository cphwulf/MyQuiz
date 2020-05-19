package Model;

import Exceptions.ItemAlreadyPickedException;

public class BasicAgent implements Agent{

    @Override
    public void pickupItem(Item item) throws ItemAlreadyPickedException {

    }

    @Override
    public void askActor(Actor actor, Riddle riddle) {

    }

    public boolean fight(Agent adversary) {
        return false;
    }
}
