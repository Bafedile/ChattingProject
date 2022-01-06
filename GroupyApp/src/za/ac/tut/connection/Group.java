/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.connection;

import java.util.LinkedList;

/**
 *
 * @author Student
 */
public class Group {
    
    
    private String groupName;
    private String participants;
    private LinkedList<Message> messages;
    
    public Group(String groupName){
        this.groupName = groupName;
    }
    
    

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
    
    
}
