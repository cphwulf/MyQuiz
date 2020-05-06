package Controller;

import Model.Actor;
import Model.ActorList;
import Model.HumanPlayer;
import Model.RoomList;
import Util.UI;
import Util.UIFactory;
import View.UserView;
import Workers.ClientConnection;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Controller {
    public ActorList actorList;
    public RoomList roomList;
    public UserView userView;
    public UIFactory uiFactory;
    public UI ui;
    private ArrayList<Socket> clients;
    private ArrayList<ClientConnection> connections;

    public Controller(ArrayList<Socket> clients) {
        ui = uiFactory.createUI("c");
        userView = new UserView(ui);
        uiFactory = new UIFactory();
        actorList = new ActorList();
        this.clients = clients;
        connections = new ArrayList<>();
    }
    public void runGame() {
        for (Socket s: clients) {
            ClientConnection clientConnection= new ClientConnection(s,this);
            clientConnection.start();
            connections.add(clientConnection);
        }
    }

    public HumanPlayer registerPlayer(String name, PrintWriter pw) {
        HumanPlayer humanPlayer = new HumanPlayer(name,pw, roomList.getSpawnRoom());
        actorList.addActor(humanPlayer);
        humanPlayer.getPw().println("Added to the list ...");
        return humanPlayer;
    }
}
