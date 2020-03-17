package sample;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class ToServer {

  Socket sock;
  BufferedReader in = null;
  DataOutputStream out = null;
  private ObservableList<Node> opButtons;

  public ToServer(Socket sock, ObservableList<Node> opButtons) throws Exception {
    this.sock = sock;
    out = new DataOutputStream(sock.getOutputStream());
    this.opButtons = opButtons;


    for(int i = 1; i < opButtons.size(); i++){
      Button buttom = (Button)opButtons.get(i); // yes we do mean buttom
      buttom.setOnAction(event -> {
        try {
          System.out.println("Sending '" + buttom.getId() + "' to other client");
          out.writeBytes( buttom.getId() + "\n");
          out.flush();
        } catch (IOException e) {
          System.out.println("Exception: " + e);
        }
      });
    }
  }


} // end of class ToServer