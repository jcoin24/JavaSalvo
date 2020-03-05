package sample;


import java.io.*;
import java.net.*;

public class FromServer implements Runnable {

  Socket sock;
  BufferedReader in = null;

  public FromServer(Socket sock)throws Exception {
    this.sock = sock;
    in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
  }

  public void run() {
    String input;
    try {
      while ((input=in.readLine()) != null) {
        System.out.println(input);
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
    }
    finally {
      try {
        in.close();
      } catch (Exception e) {
        System.out.println("Exception: " + e);
      }
    }
  }

} // end of class FromServer
