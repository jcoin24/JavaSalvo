/*
  need to add hit detection logic here
  then send hit stuff to other client
 */


package sample;


import java.io.*;
import java.net.*;

public class FromServer implements Runnable {

  Socket sock;
  BufferedReader in = null;
  DataOutputStream out = null;

  public FromServer(Socket sock)throws Exception {
    this.sock = sock;
    in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    out = new DataOutputStream(sock.getOutputStream());
  }

  public void run() {
    String input;
    try {
      while ((input=in.readLine()) != null) {
        System.out.println("Client received: " + input);

        // this is where we check if the guess is a hit or a miss
        // for now I just left the conditional as a regular expression that
        // returns true if the input is an integer with 1 or more digits
        // I'm not sure how exactly you're using the array of buttoms to determine
        // hits so I'll let you do that
        if(input.matches("[0-9]+")){
          out.writeBytes("Hit!" + "\n");
          out.flush();
        }

      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
    }
    finally {
      try {
        in.close();
        out.close();
      } catch (Exception e) {
        System.out.println("Exception: " + e);
      }
    }
  }

} // end of class FromServer