package Model;

import DataMappers.ActorMapper;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class HumanPlayer extends Actor {
    ActorMapper actorMapper;
    private Room here;
    private PrintWriter pw;
    private BufferedReader br;
    private Hashtable<String, Item> inventory;
    private String password;

    public HumanPlayer(String name, PrintWriter pw, BufferedReader br) {
        super(name);
        this.pw = pw;
        this.br = br;
    }
    public HumanPlayer(String name, PrintWriter pw, BufferedReader br,  Room r) {
        super(name);
        this.pw = pw;
        this.br = br;
        this.here=r;
    }

    public BufferedReader getBr() {
        return br;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public Room getHere() {
        return here;
    }

    public void setHere(Room here) {
        this.here = here;
    }

    public Hashtable<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory(Hashtable<String, Item> inventory) {
        this.inventory = inventory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void pickupItem(Item item) {
        items.add(item);
    }

    @Override
    public void askActor(Actor actor, Riddle riddle) {

    }

}
