/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupy.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import za.ac.tut.connection.Client;
import za.ac.tut.connection.Message;

/**
 *
 * @author Student
 */
public class Chat1 extends javax.swing.JFrame {

    private Client client;
    private final JPopupMenu  grpMenu = new JPopupMenu();
    private final JPopupMenu  chatMenu = new JPopupMenu();
    
    public Chat1(Client client){
        
            this.client = client;
            
            this.client.setGroupsJoined(new LinkedList<>());
            initComponents();
            updateGrpJoined();
            
            loggedInUsername.setText(client.getUsername());
            createGrpMenu(this);
            createChatMenu();
    }
    
    public Chat1() {
        
        initComponents();
        createGrpMenu(this);
        createChatMenu();
    }
    
    
    private void updateGrpJoined() {
        try {
            client.getAllGroupNameJoined();
            client.getParticipantsForEachGrp();
            updategrpJoined();
            
            } catch (IOException ex) {
            Logger.getLogger(Chat1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    private void updategrpJoined() {
       int count = client.getGroupsJoined().size();
       
       for(int i=0;i<count;i++){
           addGrpPanel(i);
       }
        
    }
    private void updateChatFrameMessage(String grpName) throws IOException {
        chatFrameMessagePanel.removeAll();
        LinkedList<Message> messages = client.getMessages(grpName);
        
        if(!messages.isEmpty()){
            
            for(int i=0;i<messages.size();i++){
                System.out.println(messages.get(i).getStrMsg());
                addMessageToChatFrame(messages.get(i));
            }
        
        }
        System.out.println(chatFrameMessagePanel.getComponentCount());
    }
    
    private void addMessageToChatFrame(Message get) {
        JPanel panel =new JPanel();
        
        panel.setSize(new Dimension(200,75));
        panel.setBackground(Color.CYAN);
        panel.setLayout(new GridLayout(2,1));
        
        
        
        JLabel username = new JLabel(get.getSenderName());
        username.setPreferredSize(new Dimension(200,18));
        panel.add(username);
        
        
        JTextArea message = new JTextArea(get.getStrMsg());
        message.setBackground(Color.CYAN);
        message.setBorder(null);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        
        
        panel.add(message);
        panel.updateUI();
        panel.setVisible(true);
        
        chatFrameMessagePanel.add(panel);
        chatFrameMessagePanel.updateUI();
        
    }

    
    
    private void addGrpPanel(int i){
        
            JPanel panel  = new JPanel();
            panel.setVisible(true);
            panel.setPreferredSize(new Dimension(200, 45) );
            panel.setLayout( new java.awt.FlowLayout());
            panel.setBackground(Color.WHITE);
            JLabel grpNameLabel = new JLabel(client.getGroupsJoined().get(i).getGroupName()),
                    participantsHeaderLabel = new  JLabel("Participants: "),
                    participantsLabel = new  JLabel(client.getGroupsJoined().get(i).getParticipants());
            
            grpNameLabel.setPreferredSize(new Dimension(200,18));
            grpNameLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18));
            grpNameLabel.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            
            
            participantsHeaderLabel.setPreferredSize(new Dimension(90,18));
            participantsHeaderLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18));
            participantsHeaderLabel.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            
            participantsLabel.setPreferredSize(new Dimension(110,18));
            participantsLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
            participantsLabel.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            
            //adding Mouse event to the Panel
            panel.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt){
                    try {
                        System.out.println("now Cliking!!!!");
                        chatFrameGrpNameLabel.setText(grpNameLabel.getText());
                        chatFrameParticipantsListLabel.setText(participantsLabel.getText());
                        
                        
                        chatFrameGrpNameLabel.updateUI();
                        chatFrameGrpNameLabel.setVisible(true);
                        
                        chatFrameParticipantsListLabel.updateUI();
                        chatFrameParticipantsListLabel.setVisible(true);
                        
                        updateChatFrameMessage(grpNameLabel.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(Chat1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                

            });
        
    
            //JLabel label
            panel.add(grpNameLabel);
            panel.add(participantsHeaderLabel);
            panel.add(participantsLabel);
            panel.updateUI();
            grpJoinedPanel.add(panel);
            grpJoinedPanel.updateUI();

            System.out.println(grpJoinedPanel.getComponentCount());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mainViewPanel = new javax.swing.JPanel();
        grpsPanel = new javax.swing.JPanel();
        grpJoinedLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grpJoinedPanel = new javax.swing.JPanel();
        grpsMoreMenu = new javax.swing.JLabel();
        chatFramePanel = new javax.swing.JPanel();
        chatFrameHeaderPanel = new javax.swing.JPanel();
        chatFrameParticipantsListLabel = new javax.swing.JLabel();
        chatFrameGrpNameLabel = new javax.swing.JLabel();
        chatFramePartipantsLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatMessageByUser = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatFrameMessagePanel = new javax.swing.JPanel();
        chatFrameMoreMenu = new javax.swing.JLabel();
        loggedInUsername = new javax.swing.JLabel();
        usernameHeaderLabel = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3dot.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        mainViewPanel.setBackground(new java.awt.Color(254, 215, 164));

        grpsPanel.setBackground(new java.awt.Color(200, 200, 200));
        grpsPanel.setPreferredSize(new java.awt.Dimension(300, 390));

        grpJoinedLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        grpJoinedLabel.setText("Groups Joined: ");

        jScrollPane1.setHorizontalScrollBar(null);

        grpJoinedPanel.setLayout(new java.awt.GridLayout(100, 1, 0, 5));
        jScrollPane1.setViewportView(grpJoinedPanel);

        grpsMoreMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3Dot.png"))); // NOI18N
        grpsMoreMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grpsMoreMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout grpsPanelLayout = new javax.swing.GroupLayout(grpsPanel);
        grpsPanel.setLayout(grpsPanelLayout);
        grpsPanelLayout.setHorizontalGroup(
            grpsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(grpJoinedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(grpsMoreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        grpsPanelLayout.setVerticalGroup(
            grpsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpsPanelLayout.createSequentialGroup()
                .addGroup(grpsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grpsMoreMenu)
                    .addGroup(grpsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(grpJoinedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        chatFramePanel.setBackground(new java.awt.Color(246, 244, 244));
        chatFramePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chatFrameHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        chatFrameHeaderPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        chatFrameHeaderPanel.setPreferredSize(new java.awt.Dimension(100, 45));
        chatFrameHeaderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatFrameParticipantsListLabel.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        chatFrameParticipantsListLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chatFrameHeaderPanel.add(chatFrameParticipantsListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 320, 25));

        chatFrameGrpNameLabel.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        chatFrameGrpNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatFrameHeaderPanel.add(chatFrameGrpNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 156, 25));

        chatFramePartipantsLabel1.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        chatFramePartipantsLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatFramePartipantsLabel1.setText("Participants:");
        chatFrameHeaderPanel.add(chatFramePartipantsLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 120, 25));

        jScrollPane2.setViewportView(chatMessageByUser);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chatFrameMessagePanel.setBackground(new java.awt.Color(254, 215, 164));
        chatFrameMessagePanel.setLayout(new java.awt.GridLayout(1000, 1, 0, 10));
        jScrollPane3.setViewportView(chatFrameMessagePanel);

        chatFrameMoreMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3Dot.png"))); // NOI18N
        chatFrameMoreMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatFrameMoreMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout chatFramePanelLayout = new javax.swing.GroupLayout(chatFramePanel);
        chatFramePanel.setLayout(chatFramePanelLayout);
        chatFramePanelLayout.setHorizontalGroup(
            chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(chatFramePanelLayout.createSequentialGroup()
                .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatFramePanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(chatFrameHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(chatFramePanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatFramePanelLayout.createSequentialGroup()
                    .addGap(541, 541, 541)
                    .addComponent(chatFrameMoreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        chatFramePanelLayout.setVerticalGroup(
            chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatFramePanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(chatFrameHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatFramePanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatFramePanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))))
            .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatFramePanelLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(chatFrameMoreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(539, Short.MAX_VALUE)))
        );

        loggedInUsername.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        loggedInUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        usernameHeaderLabel.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        usernameHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameHeaderLabel.setText("Username: ");

        javax.swing.GroupLayout mainViewPanelLayout = new javax.swing.GroupLayout(mainViewPanel);
        mainViewPanel.setLayout(mainViewPanelLayout);
        mainViewPanelLayout.setHorizontalGroup(
            mainViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainViewPanelLayout.createSequentialGroup()
                .addGroup(mainViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainViewPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(usernameHeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loggedInUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(grpsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(chatFramePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainViewPanelLayout.setVerticalGroup(
            mainViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainViewPanelLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(mainViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameHeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedInUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(grpsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(chatFramePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!chatMessageByUser.getText().isEmpty() && !chatFrameGrpNameLabel.getText().isEmpty()){
            Message msg = new Message(client.getUsername(), chatMessageByUser.getText());
            addMessageToChatFrame(msg);
            client.sendMessage(msg,chatFrameGrpNameLabel.getText());
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void grpsMoreMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grpsMoreMenuMouseClicked
        // TODO add your handling code here:
        int posX =  MouseInfo.getPointerInfo().getLocation().x;
        int posY = MouseInfo.getPointerInfo().getLocation().y;
        
        grpMenu.show(this, posX-125, posY);
        
        
    }//GEN-LAST:event_grpsMoreMenuMouseClicked

    private void chatFrameMoreMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatFrameMoreMenuMouseClicked
        // TODO add your handling code here:
        int posX =  MouseInfo.getPointerInfo().getLocation().x;
        int posY = MouseInfo.getPointerInfo().getLocation().y;
        
        chatMenu.show(this, posX-70, posY);
    }//GEN-LAST:event_chatFrameMoreMenuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chatFrameGrpNameLabel;
    private javax.swing.JPanel chatFrameHeaderPanel;
    private javax.swing.JPanel chatFrameMessagePanel;
    private javax.swing.JLabel chatFrameMoreMenu;
    private javax.swing.JPanel chatFramePanel;
    private javax.swing.JLabel chatFrameParticipantsListLabel;
    private javax.swing.JLabel chatFramePartipantsLabel1;
    private javax.swing.JTextPane chatMessageByUser;
    private javax.swing.JLabel grpJoinedLabel;
    private javax.swing.JPanel grpJoinedPanel;
    private javax.swing.JLabel grpsMoreMenu;
    private javax.swing.JPanel grpsPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel loggedInUsername;
    private javax.swing.JPanel mainViewPanel;
    private javax.swing.JLabel usernameHeaderLabel;
    // End of variables declaration//GEN-END:variables

    private void createGrpMenu(JFrame frame){
        JMenuItem menu1 = new JMenuItem("Create new Group"),menu2= new JMenuItem("Join new Group");
        System.out.println("Creating menu Items\n");
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CreateGroup(client).setVisible(true);
            }
        });
        
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new JoinNewGroup(client).setVisible(true);
            }
        });
        grpMenu.add(menu1);
        grpMenu.add(menu2);
        
        System.out.println(grpMenu.getComponentCount()+" value");
    }
    private void createChatMenu(){
        JMenuItem menu1 = new JMenuItem("Refresh"),menu2= new JMenuItem("Sign Out");
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!chatFrameGrpNameLabel.getText().isEmpty()){
                        updateChatFrameMessage(chatFrameGrpNameLabel.getText());
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("shutting program.....");
                System.exit(0);
//                Thread.currentThread().stop();
            }
        });
        chatMenu.add(menu1);
        chatMenu.add(menu2);
        
        
    }

    
    
}
