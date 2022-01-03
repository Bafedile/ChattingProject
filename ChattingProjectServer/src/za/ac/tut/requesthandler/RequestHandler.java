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
    private String username ="bafedile",url="jdbc:mysql://localhost/chatting_project",password = "Bafedile#5%"; 
    public RequestHandler(Socket socket)throws IOException, SQLException{
        this.socket = socket;
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dbm = new DatabaseManager(url,username,password);
        start();
        
    }
    
    @Override
    public void run(){
        String request,query,email;
        ResultSet results;
        String requests[],userDetails[];
        boolean response = false;
        try{
            while(true){
                request = reader.readLine();
                requests = request.split("#");
                userDetails = requests[1].split("::");
                System.out.println(userDetails);
                
                // check what type of request is it 
                if(requests[0].equalsIgnoreCase("signup")){
                    username = userDetails[0];
                    email = userDetails[1];
                    password = userDetails[2];
                    // check if the username and email exits in the database 
                    query = String.format("select username,email from signup_table;");
                    results = dbm.getData(query);
                    
                    if(results == null) {
                        response = false;
                        
                        // add the user details to the table 
                        query = String.format("insert into signup_table values('%s','%s','%s');",username,email,password);
                        dbm.addUser(query);
                        
                    }else{
                        //results.getString("username").equals(username) && results.getString("email").equals(email)
                        response = true;
                    }
                }else if(requests[0].equalsIgnoreCase("login")){
                    username = userDetails[0];
                    password = userDetails[1];
                    //check if the username and password exits in the database
                    query = String.format("select username,password from login_table;");
                    results = dbm.getData(query);
                    if(results.getString("username").equals(username) && results.getString("password").equals(password)){
                        response = true;
                        
                    }else{
                        response = false;
                        
                    }
                }
                
                // send the response to the client
                writer.println(String.valueOf(response));
                writer.flush();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(SQLException sql){
            System.err.println(sql.getMessage());
        }
    }
}
