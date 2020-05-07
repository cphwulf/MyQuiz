package Controller;

import Model.*;
import Util.UI;
import Util.UIFactory;
import View.UserView;
import Workers.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    public ActorList actorList;
    public QueueHandler queueHandler;
    public RoomList roomList;
    public UserView userView;
    public UIFactory uiFactory;
    public UI ui;
    private ArrayList<Socket> clients;
    private ArrayList<ClientConnection> connections;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

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
    public void runGame() {
        logger.info("into runGame");
        for (Socket s: clients) {
            ClientConnection clientConnection= new ClientConnection(s,this);
            clientConnection.start();
            connections.add(clientConnection);
        }
    }

    public void process(Actor actor, String lineFromClient) {
        System.out.println(actor.getName() + "Says " + lineFromClient);
        actor.getPw().println("You said " + lineFromClient );
        String[] lineArr = lineFromClient.split(" ");
        String[] restArr = Arrays.copyOfRange(lineArr,1,lineArr.length);
        switch (lineArr[0]) {
            case "Move":
                processMove(actor, restArr);
        }
    }

    public void processMove(Actor actor, String[] tokens) {
        actor.getPw().println("You want to move to " + tokens[0]);
        actor.getPw().println("You want to move to " + tokens[0]);
    }

    public HumanPlayer registerPlayer(String name, PrintWriter pw) {
        HumanPlayer humanPlayer = new HumanPlayer(name,pw, roomList.getSpawnRoom());
        actorList.addActor(humanPlayer);
        humanPlayer.getPw().println("Added to the list ...");
        return humanPlayer;
    }
}
