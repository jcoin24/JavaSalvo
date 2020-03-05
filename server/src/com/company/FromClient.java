// note:  In LInux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and
//        adjust sting variable "where" to actual location

package com.company;

import java.io.*;
import java.net.*;

public class FromClient implements Runnable {
    
    Socket client1;
    Socket client2;
    BufferedReader client1_in = null;
    DataOutputStream client1_out = null;
    BufferedReader client2_in = null;
    DataOutputStream client2_out = null;

    public FromClient(Socket client1, Socket client2)throws Exception {
        this.client1 = client1;
        this.client2 = client2;
        client1_in = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        client1_out = new DataOutputStream(client1.getOutputStream());

        client2_in = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        client2_out = new DataOutputStream(client2.getOutputStream());

    }

    public void run() {
        String input;
        try {
            while ((input=client1_in.readLine()) != null) {
                System.out.println(input);
                client2_out.writeBytes(input + "\n");
                client2_out.flush();

            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        finally {
            try {
                client1_in.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
    
} // end of class FromClient
