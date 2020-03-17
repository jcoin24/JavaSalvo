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

    Socket client1;
    Socket client2;
    BufferedReader in;
    DataOutputStream out;

    public FromClient(Socket client1, Socket client2)throws Exception {
        this.client1 = client1;
        this.client2 = client2;
        in = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        out = new DataOutputStream(client2.getOutputStream());
    }

    public void run() {
        String input;
        try {
            while ((input=in.readLine()) != null) {
                System.out.println("Server received: " + input);
                out.writeBytes(input + "\n");
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {}
        }
    }

} // end of class FromClient


