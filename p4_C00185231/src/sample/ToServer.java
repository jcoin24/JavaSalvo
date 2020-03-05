package sample;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class ToServer implements Runnable {

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
          out.writeBytes( buttom.getId() + "\n");
          out.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void run() {
    try {
      while (true) {
        Thread.sleep((int)(Math.random()*1000));
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
    } finally {
      try {
        out.close();
      } catch (Exception e) {
        System.out.println("Exception: " + e);
      }
    }
  }

} // end of class ToServer
