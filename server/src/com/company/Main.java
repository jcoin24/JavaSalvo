// http multi thread socket server

// note:  In Linux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and 
//        adjust sting variable "where" to actual location

package com.company;

import java.net.*;

public class Main {


    
    public static void main(String[] args) throws Exception {
        final int httpd = 8081;
        ServerSocket serverSock = new ServerSocket(httpd);
        System.out.println("server listening on local port 8081");

        
        while (true) {
            Socket client1 = serverSock.accept();
            System.out.println("client1 has connected to the socket");
            System.out.println("server listening on local port 8081");
            System.out.println("Waiting on second client");

            Socket client2 = serverSock.accept();
            System.out.println("client2 has connected to the socket");

            FromClient fromclient = new FromClient(client1, client2);
            ToClient   toclient = new ToClient(client1);
            new Thread(fromclient).start();
            new Thread(toclient).start();

            FromClient fromclient1 = new FromClient(client2, client1);
            ToClient   toclient1 = new ToClient(client2);
            new Thread(fromclient1).start();
            new Thread(toclient1).start();
            System.out.println("game start");

        }
    }
    
}
