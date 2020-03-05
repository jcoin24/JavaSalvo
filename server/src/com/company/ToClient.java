// note:  In LInux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and
//        adjust sting variable "where" to actual location

package com.company;

import java.io.*;
import java.net.*;

public class ToClient implements Runnable {
    
    Socket sock;
    BufferedReader in = null;
    DataOutputStream out = null;


    public ToClient(Socket sock)throws Exception {
        this.sock = sock;
        out = new DataOutputStream(sock.getOutputStream());
    }

    public void run() {
        try {
            while (true) {
                //Thread.sleep((int)(Math.random()*1000));
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
    
} // end of class ToClient
