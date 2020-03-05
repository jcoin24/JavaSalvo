package sample;


import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class ToServer implements Runnable {

  Socket sock;
  BufferedReader in = null;
  DataOutputStream out = null;

  public ToServer(Socket sock) throws Exception {
    this.sock = sock;
    out = new DataOutputStream(sock.getOutputStream());
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
