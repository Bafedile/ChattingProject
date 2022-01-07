/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectserver;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.time.LocalTime;
import za.ac.tut.chatrequesthandler.ChatRequestHandler;
import za.ac.tut.requesthandler.RequestHandler;
/**
 *
 * @author bafedile
 */
public class ChattingProjectServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declare and initialize the variables 
        Socket socket = null;
        ServerSocket serverSocket = null;
        LocalTime time;
        try{
            // start the server
            System.out.println("Starting Server...");
            serverSocket = new ServerSocket(1234);
            System.out.println("Server Started");
            
            while(true){
                // wait for client connections 
                System.out.println("Waiting For Client Connection...");
                socket = serverSocket.accept();
                
                time = LocalTime.now();
                System.out.println(String.format("New Client Connected [%s] at [%s]",socket.getInetAddress(),time));
                
                // handle the socket 
                new RequestHandler(socket);
                
            }
            
        }catch(IOException | SQLException ioe){
            ioe.printStackTrace();
        }
    }
    
}
