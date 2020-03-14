// note:  In LInux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and
//        adjust sting variable "where" to actual location

// note:  In LInux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and
//        adjust sting variable "where" to actual location

package com.company;

import java.io.*;
import java.net.*;

public class FromClient implements Runnable {

    Socket sock;
    BufferedReader in;
    DataOutputStream out;

    public FromClient(Socket sock)throws Exception {
        this.sock = sock;
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new DataOutputStream(sock.getOutputStream());
    }

    public void run() {
        String input;
        try {
            while ((input=in.readLine()) != null) {
                out.writeBytes(input + "\n");
                System.out.println("Input received from client: " + input);
            }
        } catch (Exception e) {
            System.out.println("Exception From client 1: " + e);
        }
        finally {
            try {
                in.close();
            } catch (Exception e) {}
        }
    }

} // end of class FromClient


