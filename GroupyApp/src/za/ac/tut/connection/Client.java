

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

    public synchronized Socket getSock() {
        return sock;
    }

    public synchronized void setSock(Socket sock) {
        this.sock = sock;
    }
    
    public synchronized boolean createGroup(String grpInfo) throws IOException{
        out.println(grpInfo);
        
        boolean results = ("true".equalsIgnoreCase(in.readLine()));
        
        return results;
    }
    
    
    public synchronized LinkedList<Group> getGroupsJoined() {
        return groupsJoined;
    }

    public synchronized void setGroupsJoined(LinkedList<Group> groupsJoined) {
        this.groupsJoined = groupsJoined;
    }
    
    public Client(InetAddress addr,int port) throws IOException {
        connectToServer(addr, port);
        groupsJoined = new LinkedList<>();
    }

    public synchronized String getUsername() {
        return username;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }
    
    
    
    public synchronized void sendMessage(Message msg,String grpName){
        String request = "message#"+grpName+"::"+msg.toString();
        out.println(request);
    }
    public String readMessage(String grpName) throws IOException{
        String msg = in.readLine();
        
        return msg;
    }
    
    public boolean startUpMessage(String msg) throws IOException{
        out.println(msg);
        
        boolean results = ("true".equalsIgnoreCase(in.readLine()));
        
        return results;
    }
    
    public synchronized void connectToServer(InetAddress addr,int port) throws IOException{
        if(sock==null){
            sock = new Socket(addr,port);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }
    }
    
    public synchronized void getAllGroupNameJoined() throws IOException{
        
        String request ="groups#"+username;
        
        out.println(request);
        
        String response = in.readLine();
        assignGrpName(response);
    }
    
    public synchronized  LinkedList<Message> getMessages(String grpName) throws IOException{
       LinkedList<Message> messages = new LinkedList<>();
       
       String request = "messages#"+grpName;
       out.println(request);
       
       String response = in.readLine();
        if(!response.isEmpty()){
            
            String messagesArr[] = response.split("#");
       
            for(int i=0;i<messagesArr.length;i++){
           
                messages.add(toMessage(messagesArr[i]));
            }
        }
       
       
       
       
       return messages;
        
    }
    private Message toMessage(String response) {
        
        String msgData[] = response.split("::");
        
        return new Message(msgData[0],msgData[1]); 
    }
    
    public synchronized void getParticipantsForEachGrp() throws IOException{
        String request;
        
        if(!groupsJoined.isEmpty()){
            int count = groupsJoined.size();
        
            for(int j=0; j<count;j++){
                request = "participants#"+groupsJoined.get(j).getGroupName();

                out.println(request);
                groupsJoined.get(j).setParticipants(in.readLine());
            }
        }
        
    }
    
    public synchronized void closeConnection()throws IOException{
        if(sock!=null){
            out.println("close");
            in.close();
            out.close();
            sock.close();
            sock = null;
        }
    }
    
    private void assignGrpName(String response){
        
        if(!response.isEmpty()){
            String grpNames[] = response.split(", ");
            for(int i=0; i<grpNames.length;i++){
                groupsJoined.add(new Group(grpNames[i]));
            }
        }
    }

    

    
}
