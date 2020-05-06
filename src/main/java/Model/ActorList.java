package Model;

import DataMappers.ActorMapper;

import java.util.Hashtable;

public class ActorList {
    Hashtable<String, Actor> actors;
    ActorMapper actorMapper;

    public ActorList() {
        actors = new Hashtable<>();
        actorMapper = new ActorMapper();
    }

    public Actor checkActorCreds(String name, String password){
        Actor actor = null;

        return actor;
    }
    public void addActor(Actor actor) {
        actors.put(actor.getName(), actor);
    }
}
