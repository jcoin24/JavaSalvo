// http multi thread socket server

// note:  In Linux, copy test.html to /home/user-clid/httpd/test.html
//        when running, access http://localhost:8081/test.html and 
//        adjust sting variable "where" to actual location

package com.company;

import java.net.*;

public class Main {

    public static String output;
    
    public static void main(String[] args) throws Exception {
        boolean turn = true;
        boolean gameStart = false;
        final int httpd = 8081;
        ServerSocket serverSock = new ServerSocket(httpd);
        System.out.println("server listening on local port 8081");

        
        while (true) {
            Socket client1 = serverSock.accept();
            FromClient fromClient1 = new FromClient(client1);
            ToClient   toClient1 = new ToClient(client1);
            new Thread(fromClient1).start();
            new Thread(toClient1).start();
            System.out.println("client1 has connected to the socket");
            System.out.println("Waiting on second client");

            Socket client2 = serverSock.accept();
            System.out.println("client2 has connected to the socket");
            FromClient fromClient2 = new FromClient(client2);
            ToClient   toClient2 = new ToClient(client2);
            new Thread(fromClient2).start();
            new Thread(toClient2).start();
            System.out.println("game start");
        }
    }
    
}
