package sample;


import javafx.application.Platform;
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
  public static int currentTarget = 0;

  public ToServer(Socket sock, ObservableList<Node> opButtons) throws Exception {
    this.sock = sock;
    out = new DataOutputStream(sock.getOutputStream());
    this.opButtons = opButtons;


    for(int i = 1; i < opButtons.size(); i++){
      int temp = i;
      Button buttom = (Button)opButtons.get(i); // yes we do mean buttom
      buttom.setOnAction(event -> {
        if (Main.myTurn) {
          Main.myTurn = false;
          try {
            System.out.println("Sending '" + buttom.getId() + "' to other client");
            currentTarget = Integer.parseInt(buttom.getId());
            out.writeBytes(buttom.getId() + "\n");
            out.flush();
          } catch (IOException e) {
            System.out.println("Exception: " + e);
          }
        } else {
          System.out.println("It's not your turn!");
        }
      });
      }
    }



} // end of class ToServer