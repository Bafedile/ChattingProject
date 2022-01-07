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
import java.util.Arrays;
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
         // create the tables if they do not exists
         
         
        createTables();
        clientHandlers.add(this);
        
        start();
        
    }
    
    @Override
    public  void  run(){
        String request,query,email,result;
        ResultSet results;
        String requests[],userDetails[],tokens[];
        boolean response = true  ;
        
        
        try{
            while(true){
                
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
                        System.out.println(String.format("%s logged in...",socket.getInetAddress()));
                    }
                    // send the response to the client
                    writer.println(String.valueOf(response));
                    writer.flush();
                    
                    
                    
                }else if(requests[0].equalsIgnoreCase("groups")){
                    System.out.println(String.format("%s accessing groups...",socket.getInetAddress()));
                    clientUsername = requests[1];
                    query = String.format("select g.groupname from groups g,"
                            + " participants p where g.group_id=p.group_id AND p.username='%s';",clientUsername);
                    
                    // execute the query 
                    results = dbm.getData(query);
                    result = dbm.getGroupDetails(results);
                   
                    writer.println(result);
                    System.out.println(String.format("Chat groups accessed!"));
                   
                }else if(requests[0].equalsIgnoreCase("participants")){
                    System.out.println(String.format("%s accessing participants...",socket.getInetAddress()));
                    String groupName = requests[1];
                    
                    query = String.format("select p.username from groups g, participants p where g.group_id=p.group_id AND g.groupName='%s';",groupName);
                    
                    // execute the query 
                    results = dbm.getData(query);
                    result = dbm.getGroupParticipants(results);
                    writer.println(result);
                    System.out.println(String.format("Participants data accessed! "));
                }else if(requests[0].equalsIgnoreCase("messages")){
                    System.out.println(String.format("%s accessing messages...",socket.getInetAddress()));
                    query = String.format("select c.username,c.message,c.group_id from chat_messages c where c.group_id "
                            + "=(select group_id from groups where groupname='%s'); ", requests[1]);
                    
                    // execute the query 
                    results = dbm.getData(query);
                    result = dbm.getGroupMessages(results);
                    writer.println(result);
                    writer.flush();
                    System.out.println(String.format("Messages accessed and sent to client(s)!"));
                }else if(requests[0].equalsIgnoreCase("message")){
                    String messageDetails[] = requests[1].split("::");
                    String groupName=messageDetails[0],username=messageDetails[1],message=messageDetails[2];
                    query = String.format("insert into chat_messages values ((select group_id from groups where groupname='%s'),'%s','%s');",groupName,username,message);
                    
                    // execute the update
                    dbm.updateDatabase(query);
                    System.out.println("Chat messages updated!");
                }else if(requests[0].equalsIgnoreCase("createnewgroup")){
                    String groupDetails[] = requests[1].split("::");
                    int group_id = Integer.parseInt(groupDetails[0]);
                    String groupname= groupDetails[1];
                    String username = groupDetails[2];
                    
                    query  = String.format("select group_id from groups where group_id='%d';",group_id);
                    results = dbm.getData(query);
                    if(results.next()){
                        if(String.valueOf(results.getInt("group_id")).isEmpty()){
                            response = false;
                        }
                        response = false;
                    }else{
                        query = String.format("insert into groups values('%d','%s');",group_id,groupname);
                        dbm.updateDatabase(query);
                        
                        query = String.format("insert into participants values('%d','%s');",group_id,username);
                        dbm.updateDatabase(query);
                        response = true;
                        System.out.println("New group created!");
                    }
                    writer.println(response);
                    writer.flush();
                }else if(requests[0].equalsIgnoreCase("joinnewgroup")){
                    
                    String groupDetails[] = requests[1].split("::");
                    int group_id = Integer.parseInt(groupDetails[0]);
                    String groupname= groupDetails[1];
                    String username = groupDetails[2];
                    System.out.println(group_id+" "+username+" "+groupname);
                    query  = String.format("select group_id,groupname from groups where group_id='%d';",group_id);
                    results = dbm.getData(query);
                    
                    if(!results.next()){
                        response = false;
                    }else{
                        System.out.println(socket.getInetAddress()+" Joining a group...");
                        query = String.format("insert into participants values ('%d','%s');",group_id,username);
                        dbm.updateDatabase(query);
                        
                        response = true;
                       System.out.println(socket.getInetAddress()+" Successfully joined a group");
                    }
                    writer.println(response);
                    writer.flush();
                    
                    
                }
      
            }
        }catch(IOException | SQLException ioe){
            System.err.println(ioe.getMessage());
        }
        catch(NullPointerException npe){
            
        }
    }
    
    
    

    
    public void createTables()throws SQLException{
        String []tables = {"create table  if not exists chat_messages(group_id INT NOT NULL,username VARCHAR(25) NOT NULL,message VARCHAR(1000));",
            " create table if not exists groups(group_id INT NOT NULL, groupname VARCHAR(10),PRIMARY KEY(group_id));",
            "create table if not exists signup_table (username VARCHAR(25) NOT NULL, email VARCHAR(25) NOT NULL, password VARCHAR(25) NOT NULL,PRIMARY KEY(username,email));",
            "create table if not exists participants (group_id INT  NOT NULL,username VARCHAR(25));"};
        
        // send the query to the database
        String query;
        
        for(int i=0;i<tables.length;i++){
            query = tables[i];
             dbm.updateDatabase(query);
        }
       
    }
}
