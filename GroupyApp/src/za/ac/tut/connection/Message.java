/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.connection;

/**
 *
 * @author VM JELE
 */
public class Message {
    String senderName;
    String strMsg;

    public Message(String senderName, String strMsg) {
        this.senderName = senderName;
        this.strMsg = strMsg;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getStrMsg() {
        return strMsg;
    }

    public void setStrMsg(String strMsg) {
        this.strMsg = strMsg;
    }
    
    
    
}
