

package za.ac.tut.connection;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 *
 * @author VM JELE
 */
public class Client {
    
    private PrintWriter out;
    private String username;
    private BufferedReader in;
    private Socket sock=null;
    private LinkedList<Group> groupsJoined;
    
    public Client(){
        groupsJoined = new LinkedList<>();
    }

    public LinkedList<Group> getGroupsJoined() {
        return groupsJoined;
    }

    public void setGroupsJoined(LinkedList<Group> groupsJoined) {
        this.groupsJoined = groupsJoined;
    }
    
    public Client(InetAddress addr,int port) throws IOException {
        connectToServer(addr, port);
        groupsJoined = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    public void sendMessage(String msg){
        out.println(msg);
    }
    public String readMessage() throws IOException{
        String msg = in.readLine();
        
        return msg;
    }
    
    public boolean startUpMessage(String msg) throws IOException{
        out.println(msg);
        
        boolean results = ("true".equalsIgnoreCase(in.readLine()));
        
        return results;
    }
    
    public void connectToServer(InetAddress addr,int port) throws IOException{
        if(sock==null){
            sock = new Socket(addr,port);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }
    }
    
    public void getAllGroupNameJoined() throws IOException{
        
        String request ="groups#"+username;
        
        out.println(request);
        
        String response = in.readLine();
        assignGrpName(response);
        System.out.println(response);
    }
    
    public LinkedList<Message> getMessages(String grpName) throws IOException{
       LinkedList<Message> messages = new LinkedList<>();
       
       String request = "messages#"+grpName;
       out.println(request);
       
       String response = in.readLine();
        System.out.println(response);
       String messagesArr[] = response.split("#");
       
       for(int i=0;i<messagesArr.length;i++){
           
            messages.add(toMessage(messagesArr[i]));
        }
       
       
       
       
       return messages;
        
    }
    private Message toMessage(String response) {
        
        String msgData[] = response.split("::");
        
        return new Message(msgData[0],msgData[1]); 
    }
    
    public void getParticipantsForEachGrp() throws IOException{
        String request;
        
        int count = groupsJoined.size();
        
        
        for(int j=0; j<count;j++){
            request = "participants#"+groupsJoined.get(j).getGroupName();
            
            out.println(request);
            groupsJoined.get(j).setParticipants(in.readLine());
            System.out.println(groupsJoined.get(j).getParticipants());
        }
        
    }
    
    public void closeConnection()throws IOException{
        if(sock!=null){
            out.println("close");
            in.close();
            out.close();
            sock.close();
            sock = null;
        }
    }
    
    private void assignGrpName(String response){
        
        String grpNames[] = response.split(", ");
        for(int i=0; i<grpNames.length;i++){
            groupsJoined.add(new Group(grpNames[i]));
        }
    }

    

    
}
