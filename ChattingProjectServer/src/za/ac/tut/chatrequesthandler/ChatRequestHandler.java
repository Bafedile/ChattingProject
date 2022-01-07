/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.chatrequesthandler;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import za.ac.tut.databasemanager.DatabaseManager;

/**
 *
 * @author Student
 */
public class ChatRequestHandler extends Thread {
    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;
    private DatabaseManager dbm = null;
    private String clientUsername;
    public static ArrayList<ChatRequestHandler> clientHandlers = new ArrayList<>();
    
    public ChatRequestHandler(Socket socket) throws IOException{
        this.socket = socket;
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientHandlers.add(this);
        clientUsername = reader.readLine();
        start();
    }
    
    @Override
    public void run(){
        
        
            String message;
            
            while(true){
                try {
                    message= reader.readLine();
                    broadcastMessage(message);  
                }catch (IOException ex) {
                    closeEverything(socket,reader,writer);
                    break;
                }
            }
    }
    
    public void closeEverything(Socket socket,BufferedReader reader,PrintWriter writer){
        removeClientHandler();
        try{
            if(reader!=null){
                reader.close();
            }if(writer!=null){
                writer.close();
            }if(socket !=null){
                socket.close();
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" has left the chat!");
    }
    
    
    public void broadcastMessage(String messageToSend){
        for(ChatRequestHandler clientHandler: clientHandlers){
            if(!clientHandler.clientUsername.equals(clientUsername)){
                clientHandler.writer.println(messageToSend);
                clientHandler.writer.flush();
                
            }
        }
    }
    
    
}
