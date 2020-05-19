package Controller;

import Exceptions.NoSuchDirectionException;
import Model.*;
import Util.UI;
import Util.UIFactory;
import View.UserView;
import Workers.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import static Model.Direction.*;

public class Controller {
    protected Hashtable<String, Key> keys = new Hashtable<>();
    protected ActorList actorList;
    protected QueueHandler queueHandler;
    protected RoomList roomList;
    protected UserView userView;
    protected UIFactory uiFactory;
    protected UI ui;
    protected Room spawnPoint;
    protected ArrayList<Socket> clients;
    protected ArrayList<ClientConnection> connections;

    public Controller(ArrayList<Socket> clients) {
        ui = uiFactory.createUI("c");
        userView = new UserView(ui);
        uiFactory = new UIFactory();
        actorList = new ActorList();
        this.clients = clients;
        roomList = new RoomList();
        connections = new ArrayList<>();
        queueHandler = new QueueHandler(10);
    }
    public void initGame() {
        Room room1 = new Room("Town Hall",keys);
        Room room2 = new Room("Train Station",keys);
        Room room3 = new Room("Bus Station",keys);
        Room room4 = new Room("Bus Station",keys);
        Room room5 = new Room("Bus Station",keys);
        this.spawnPoint = room1;
        Key k1 = new Key(1, "geokey");
        k1.setMessage("Hvilken af disse byer ligger ved havet?");
        k1.setAnswers(1,"Baku");
        k1.setAnswers(2,"TelAviv");
        k1.setAnswers(3,"Rotterdam");
        k1.setSolution(1);
        Key k2 = new Key(1, "gossipkey");
        Key k3 = new Key(1, "mathkey");
        keys.put(k1.getName(),k1);
        keys.put(k2.getName(),k2);
        Door door1 = new Door(k1, "GeoDoor", "Get out with geo");
        Door door2 = new Door(k2, "MathDoor", "Get out with math");
        //Door door3 = new Door(k, "Exit", "Get out");
        room1.addDoor(door1, NORTH, room2);
        room1.addDoor(door2, SOUTH, room2);
    }


    public void runGame() {
        for (Socket s: clients) {
            ClientConnection clientConnection= new ClientConnection(s,this);
            clientConnection.start();
            connections.add(clientConnection);
        }
    }

    public void process(HumanPlayer player, String lineFromClient) throws NoSuchDirectionException, IOException {
        System.out.println(player.getName() + "Says " + lineFromClient);
        player.getPw().println("You said " + lineFromClient );
        String[] lineArr = lineFromClient.split(" ");
        String[] restArr = Arrays.copyOfRange(lineArr,1,lineArr.length);
        switch (lineArr[0]) {
            case "Move":
                processMove(player, restArr);
                return;
                /*
            case "Fight":
                processFight(actor, restArr);
                break;
            case "PickupItem":
                processPickupItem(actor, restArr);
                break;

                 */
        }
    }

    public void processMove(HumanPlayer player, String[] tokens) throws NoSuchDirectionException, IOException {
        int counter = 0;
        int ans = 0;
        int limit = 3;
        PrintWriter pw  = player.getPw();
        BufferedReader br  = player.getBr();
        player.getPw().println("You want to move " + tokens[0]);
        Direction dir = Direction.getDirectionFromString(tokens[0]);
        Room hereRoom = player.getHere();
        Door d = hereRoom.getDoor();
        Key k = (keys.get("geokey"));
        pw.println("ready ..");
        pw.println(k.printRiddle());

        do {
            counter++;
            ans = Integer.parseInt(br.readLine());
            if (ans == k.getSolution()) {
                // do the move
                Room targetRoom = hereRoom.to(Direction.getDirectionFromString(tokens[0]));
                hereRoom.removePlayer(player);
                player.setHere(targetRoom);
                Long diff = System.currentTimeMillis() - player.getLoggedInTime();
                pw.println("You are now in the next room. It took you " + diff);
                return;
            } else {
                pw.println("Wrong answer .. try  again");
            }
        }
        while (counter <= limit);
        System.out.println("Cl: " + ans);
        return;
    }

    public HumanPlayer registerPlayer(String name, PrintWriter pw, BufferedReader br) {
        HumanPlayer humanPlayer = new HumanPlayer(name,pw,br, roomList.getSpawnRoom());
        humanPlayer.setHere(this.spawnPoint);
        actorList.addActor(humanPlayer);
        humanPlayer.getPw().println("Added to the list ...");
        return humanPlayer;
    }
    public String[] showHelp() {
        String[] z =
                { "ACTION               OBJECT        INDIRECT OBJECT",
                        "[{m,move,go,walk}]   <direction>",
                        "{t,take,get}         <object>",
                        "{d,drop}",
                        "{g,give}             <object>      to <player>",
                        "{l,look,describe}    [<object>]",
                        "{i,inventory}",
                        "{e,exits}",
                        "{s,say,talk}         [<string>]",
                        "{y,yell,shout}       <string>",
                        "{w,whisper}          <string>      to <player>",
                        "{u,use}              <object in inventory>",
                        "SERVER ACTION",
                        "help",
                        "who",
                        "quit" };
        return z;
    }
}
