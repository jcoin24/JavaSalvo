/*
  need to add hit detection logic here
  then send hit stuff to other client
 */


package sample;


import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.MissingFormatArgumentException;

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
        try{
          int target = Integer.parseInt(input);
          Button tempButton = (Button) Main.myList.get(target);
          if(tempButton.getText().compareTo("+") == 0){

            Main.shipList.get(0).setHealth();
            if (Main.shipList.get(0).getHealth() == 0){
              out.writeBytes("Sunk a battleship\n");
              System.out.println("Your battleship was sunk");
            }
            out.writeBytes("Hit! \n");
            System.out.println("Hit");

            Main.playerHealth = --Main.playerHealth;

          }else if(tempButton.getText().compareTo("*") == 0){

            Main.shipList.get(1).setHealth();
            if (Main.shipList.get(1).getHealth() == 0){
              out.writeBytes("Sunk a Cruiser\n");
              System.out.println("Your Cruiser was sunk");
            }
            out.writeBytes("Hit! \n");
            System.out.println("Hit");

            Main.playerHealth = --Main.playerHealth;

          }else{
            out.writeBytes("Miss! \n");
            System.out.println("Miss");
          }

          if(Main.playerHealth == 0){
            System.out.println("Your Ships are all suck!\n You have sadly Lost\n");
            out.writeBytes("All enemy ships sunk you have won\n");
          }

          out.flush();
        }catch (Exception e){
          //System.out.println("Failed to read enemy input");
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