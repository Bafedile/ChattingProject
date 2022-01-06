/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.requesthandler;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import za.ac.tut.databasemanager.DatabaseManager;

/**
 *
 * @author bafedile
 */
public class RequestHandler extends Thread{
    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;
    private DatabaseManager dbm = null;
    private String databaseUsername ="root",url="jdbc:mysql://localhost/chatting_project",databasePassword = ""; 
    private String clientUsername,password;
    public static ArrayList<RequestHandler> clientHandlers = new ArrayList<>();
    public RequestHandler(Socket socket)throws IOException, SQLException{
        this.socket = socket;
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dbm = new DatabaseManager(url,databaseUsername,databasePassword);
        clientHandlers.add(this);
        
        start();
        
    }
    
    @Override
    public void run(){
        String request,query,email,result;
        ResultSet results;
        String requests[],userDetails[],tokens[];
        boolean response = true  ;
        try{
            while(socket.isConnected()){
                request = reader.readLine();
                requests = request.split("#");
                
                
                // check what type of request is it 
                if(requests[0].equalsIgnoreCase("signup")){
                    userDetails = requests[1].split("::");
                    clientUsername = userDetails[0];
                    email = userDetails[1];
                    password = userDetails[2];
                    
                    // check if the username and email exits in the database 
                    query = String.format("select username,email from signup_table where username ='%s';",clientUsername);
                    results = dbm.getData(query);
                    result = dbm.readSignupData(results);
                    
                    if(!result.contains(clientUsername) && !result.contains(email)){
                        
                        // add the user details to the table 
                        query = String.format("insert into signup_table values('%s','%s','%s');",clientUsername,email,password);
                        dbm.addUser(query);
                        System.out.println("\nAdded new user to signup_table\n");
                        
                        
                        response = true;
                        broadcastMessage(String.format("SERVER: %s joined the chat",clientUsername));
                        
                    }else{
                            response = false;
                    }
                    
                    // send the response to the client
                    writer.println(String.valueOf(response));
                    writer.flush();
                }
                else if(requests[0].equalsIgnoreCase("login")){
                    userDetails = requests[1].split("::");
                    clientUsername = userDetails[0];
                    password = userDetails[1];
                    
                    //check if the username and password exits in the database
                    query = String.format("select username,password from signup_table where username ='%s';",clientUsername);
                    results = dbm.getData(query);
                    result = dbm.readLoginData(results);
                    tokens = result.split(",");
                    
                    
                    response = tokens[0].equals(clientUsername) && tokens[1].equals(password);
                    
                    if(response ==true){
                        broadcastMessage(String.format("SERVER: %s joined the chat",clientUsername));
                    }
                    // send the response to the client
                    writer.println(String.valueOf(response));
                    writer.flush();
                    
                    //////
                    
                    
                }else if(requests[0].equalsIgnoreCase("groups")){
                    clientUsername = requests[1];
                    query = String.format("select g.groupname from groupnames g,"
                            + " participants p where g.group_id=p.group_id AND p.username='%s';",clientUsername);
                    
                    // execute the query 
                    results = dbm.getData(query);
                    result = dbm.getGroupDetails(results);
                     writer.println(result);
                   
                }else if(requests[0].equalsIgnoreCase("participants")){
                    String groupName = requests[1];
                    
                    query = String.format("select p.username from groupnames g, participants p where g.group_id=p.group_id AND g.groupName='%s';",groupName);
                    
                    // execute the query 
                    results = dbm.getData(query);
                    result = dbm.getGroupParticipants(results);
                    writer.println(result);
                }else if(requests[0].equalsIgnoreCase("getGroupMsg")){
//                    query = String.format("select username,messag", tokens)
                }
                
                
//                else if(requests[0].equalsIgnoreCase("msg")){
//                    String messageFromClient;
//                    while(socket.isConnected()){
//                        try{
//                            messageFromClient = reader.readLine();
//                            broadcastMessage(messageFromClient);
//                        }catch(IOException e){
//                            closeEverything(socket,reader,writer);
//                            break;
//                        }
//                    }
//                }
                // send the response to the client
//                writer.println(String.valueOf(response));
//                writer.flush();
                
            }
        }catch(IOException | SQLException ioe){
            System.err.println(ioe.getMessage());
        }
        catch(NullPointerException npe){
            
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
        for(RequestHandler clientHandler: clientHandlers){
            if(!clientHandler.clientUsername.equals(clientUsername)){
                clientHandler.writer.println(messageToSend);
                clientHandler.writer.flush();
                
            }
        }
    }
}
