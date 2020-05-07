import Controller.Controller;
import Workers.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        //Logger logger = LoggerFactory.getLogger(Main.class);
        // TODO: prepare data and view for the controller
        int numOfPlayers = 1;
        logger.info("This is how you configure Java Logging with SLF4J");
        logger.info("This is how you configure Java Logging with SLF4J");
        logger.info("This is how you configure Java Logging with SLF4J");
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

        controller.runGame();

    }
}
