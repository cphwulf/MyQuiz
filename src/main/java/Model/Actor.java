package Model;

import Exceptions.ItemAlreadyPickedException;
import Util.IdFactory;
import Util.QuizConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;

public abstract class Actor {
    Long loggedInTime;
    String name;
    PrintWriter pw;
    int id;
    String description;
    ArrayList<Item> items;

    public Actor(String name) {
        this.loggedInTime = System.currentTimeMillis();
        this.name = name;
        this.id = IdFactory.getInstance().getActorId();
        items = new ArrayList<>();
        try {
            File file = new File(QuizConfig.aiPrintFile);
            this.pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public PrintWriter getPw() {
        return this.pw;
    }

    public Long getLoggedInTime() {
        return loggedInTime;
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

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public abstract void pickupItem(Item item) throws ItemAlreadyPickedException;

    public abstract void askActor(Actor actor, Riddle riddle);

}
