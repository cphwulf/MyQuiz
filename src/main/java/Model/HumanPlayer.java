package Model;

import DataMappers.ActorMapper;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HumanPlayer extends Actor{
    ActorMapper actorMapper;

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void pickupItem(Item item) {
        items.add(item);
    }

    @Override
    public void askActor(Actor actor, Riddle riddle) {

    }
    public HumanPlayer checkUserCreds(String name, String password){

    }
}
