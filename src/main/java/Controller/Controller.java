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
    //protected Hashtable<String, Key> keys = new Hashtable<>();
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

        Room room1 = new Room("Town Hall");
        Room room2 = new Room("Train Station");
        Room room3 = new Room("Bus Station");
        Room room4 = new Room("Bus Station");
        Room room5 = new Room("Bus Station");
        this.spawnPoint = room1;
        // create key
        Key k1 = new Key(1, "geokey");
        k1.setMessage("Hvilken af disse byer ligger ved havet?");
        k1.setAnswers(1,"Baku");
        k1.setAnswers(2,"TelAviv");
        k1.setAnswers(3,"Rotterdam");
        k1.setSolution(1);
        Key k2 = new Key(1, "gossipkey");
        Key k3 = new Key(1, "mathkey");

        room1.addKey(k1);
        //keys.put(k2.getName(),k2);
        Door door1 = new Door(k1, "GeoDoor", "Get out with geo");
        Door door2 = new Door(k2, "MathDoor", "Get out with math");
        //Door door3 = new Door(k, "Exit", "Get out");
        room1.addDoor(door1, NORTH, room2);
        room1.addExit(NORTH,room2);
        room1.addDoor(door2, SOUTH, room2);
        Room tobias = new Room ("Tobias's kolegie");
//SetKey
        Key kTobias = new Key (2, "geokey");
        kTobias.setMessage("Hvilket land startede korona?");
        kTobias.setAnswers(1, "Danmark");
        kTobias.setAnswers(2, "Kina");
        kTobias.setAnswers(3, "USA");
        kTobias.setSolution(2);
        tobias.addKey(kTobias);
//addDoor
        Door tobiasDoor = new Door(kTobias, "KoronaDoor", "Get out with korona");
        room1.addDoor(tobiasDoor, WEST, tobias);
        room1.addExit(WEST,tobias);
        // ALI
        Room AliJens = new Room ("AliJens's kolegie");
//SetKey
        Key kAliJens = new Key (2, "geokey");
        kAliJens.setMessage("Hvad er hovedstaden til Kyrgyzstan?");
        kAliJens.setAnswers(1, "Dushanbe");
        kAliJens.setAnswers(2, "Bishkek");
        kAliJens.setAnswers(3, "Tashkent");
        kAliJens.setSolution(2);
        AliJens.addKey(kAliJens);
//addDoor
        Door AliJensDoor = new Door(kAliJens, "AliJensDoor", "Get out with a spaceship");
        room1.addDoor(AliJensDoor, NORTHEAST, AliJens);
        room1.addExit(NORTHEAST,AliJens);
        // Peter & co
        Room gruppe2 = new Room ("Fairytale");
//SetKey
        Key kgruppe2 = new Key (2, "geokey");
        kgruppe2.setMessage("From where does H.C Andersen originate?");
        kgruppe2.setAnswers(1, "Odense");
        kgruppe2.setAnswers(2, "Andeby");
        kgruppe2.setAnswers(3, "Horsens");
        kgruppe2.setSolution(1);
        gruppe2.addKey(kgruppe2);
//addDoor
        Door gruppe2Door = new Door(kgruppe2, "Fairytaledoor", "Ugly duckling");
        room1.addDoor(gruppe2Door, EAST, gruppe2);
        room1.addExit(EAST,gruppe2);


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
        Room targetRoom = hereRoom.to(Direction.getDirectionFromString(tokens[0]));
        //Key k = (keys.get("geokey"));
        Key k = targetRoom.getKeyByName("geokey");
        pw.println("ready ..");
        pw.println(k.printRiddle());

        do {
            counter++;
            ans = Integer.parseInt(br.readLine());
            if (ans == k.getSolution()) {
                // do the move
                hereRoom.removePlayer(player);
                player.setHere(targetRoom);
                Long diff = System.currentTimeMillis() - player.getLoggedInTime();
                pw.println("You are now in the next room. It took you " + diff);
                pw.println("viz " + targetRoom.getName());
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
