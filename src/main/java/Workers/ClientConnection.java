package Workers;

import Controller.Controller;
import Model.HumanPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread{
  private Socket socket;
  private Controller controller;

  public ClientConnection(Socket s, Controller controller) {
    this.socket=s;
    this.controller = controller;
  }

  public void run() {
    PrintWriter pw = null;
    BufferedReader br = null;
    String  lineFromClient = "";
    try {
      pw = new PrintWriter(socket.getOutputStream(),true);
      br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }catch (IOException e) {
      e.printStackTrace();
    }
    try {
        pw.println("What is your name?");
        lineFromClient = br.readLine();
        HumanPlayer p = controller.registerPlayer(lineFromClient, pw);
    } catch (IOException e) {
      System.out.println("Error");
    }

  }

}
