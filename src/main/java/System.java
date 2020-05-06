import Controller.Controller;
import Workers.ClientConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class System {
    public static void main(String args) {
        // TODO: prepare data and view for the controller
        int numOfPlayers = 3;
        int counter = 0;
        ArrayList<Socket> clients = new ArrayList<>();
        try {
            ServerSocket ss = new ServerSocket(5555);
            while(counter < numOfPlayers) {
                Socket client = ss.accept();
                clients.add(client);
            }
        } catch (IOException e) {
        }
        Controller controller = new Controller(clients);

        controller.runGame();

    }
}
