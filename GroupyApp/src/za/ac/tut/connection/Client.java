

package za.ac.tut.connection;

import java.io.*;
import java.net.*;

/**
 *
 * @author VM JELE
 */
public class Client {
    
    private PrintWriter out;
    private BufferedReader in;
    private Socket sock=null;
    
    public Client(InetAddress addr,int port) throws IOException {
        connectToServer(addr, port);
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
        
        boolean results = ("true".equals(in.readLine()));
        
        return results;
    }
    
    public void connectToServer(InetAddress addr,int port) throws IOException{
        if(sock==null){
            sock = new Socket(addr,port);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
}
