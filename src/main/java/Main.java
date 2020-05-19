import Controller.Controller;
import Model.AIPlayer;
import Model.Actor;
import Model.HumanPlayer;
import Model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Logger logger = LoggerFactory.getLogger(Main.class);
        // TODO: prepare data and view for the controller
        int numOfPlayers = 4;
        int counter = 0;
        ArrayList<Socket> clients = new ArrayList<>();
        try {
            System.out.println("Waiting for client  "+ counter);
            ServerSocket ss = new ServerSocket(5555);
            while(counter < numOfPlayers) {
                counter++;
                Socket client = ss.accept();
                clients.add(client);
            }
        } catch (IOException e) {
        }
        Controller controller = new Controller(clients);
        controller.initGame();
        controller.runGame();
    }
}
