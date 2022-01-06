/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupyapp;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
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
    
    private void updateGrpJoined() {
        try {
            client.getAllGroupNameJoined();
            client.getParticipantsForEachGrp();
            updategrpJoined();
            } catch (IOException ex) {
            Logger.getLogger(Chat1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public Chat1(Client client){
        
            this.client = client;
            initComponents();
            updateGrpJoined();
            
            
        
        
    }
    
    public Chat1() {
        initComponents();
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
        
        
        for(int i=0;i<messages.size();i++){
            addMessageToChatFrame(messages.get(i));
        }
        
        System.out.println(chatFrameMessagePanel.getComponentCount());
    }
    
    private void addMessageToChatFrame(Message get) {
        JPanel panel =  new JPanel();
        panel.setSize(new Dimension(400,75));
        JTextArea messageArea = new JTextArea();
        JLabel username = new JLabel(get.getSenderName());
        username.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18));
        messageArea.setSize(new Dimension(400,60));
        
        messageArea.setText(get.getStrMsg());
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);
        messageArea.setEditable(false);
        
        panel.add(username);
        panel.add(messageArea);
        panel.setVisible(true);
        panel.updateUI();
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
                        updateChatFrameMessage(grpNameLabel.getText());
                        
                        chatFrameGrpNameLabel.updateUI();
                        chatFrameGrpNameLabel.setVisible(true);
                        
                        chatFrameParticipantsListLabel.updateUI();
                        chatFrameParticipantsListLabel.setVisible(true);
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
        jPanel3 = new javax.swing.JPanel();
        grpJoinedLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grpJoinedPanel = new javax.swing.JPanel();
        chatFramePanel = new javax.swing.JPanel();
        chatFrameHeaderPanel = new javax.swing.JPanel();
        chatFramePartipantsLabel = new javax.swing.JLabel();
        chatFrameParticipantsListLabel = new javax.swing.JLabel();
        chatFrameGrpNameLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatFrameMessagePanel = new javax.swing.JPanel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3dot.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        mainViewPanel.setBackground(new java.awt.Color(254, 215, 164));
        mainViewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(200, 200, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 390));

        grpJoinedLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        grpJoinedLabel.setText("Groups Joined: ");

        jScrollPane1.setHorizontalScrollBar(null);

        grpJoinedPanel.setLayout(new java.awt.GridLayout(100, 1, 0, 5));
        jScrollPane1.setViewportView(grpJoinedPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(grpJoinedLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(grpJoinedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
        );

        mainViewPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 231, -1, 370));

        chatFramePanel.setBackground(new java.awt.Color(246, 244, 244));
        chatFramePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chatFrameHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        chatFrameHeaderPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        chatFrameHeaderPanel.setPreferredSize(new java.awt.Dimension(100, 45));
        chatFrameHeaderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatFramePartipantsLabel.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        chatFramePartipantsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatFramePartipantsLabel.setText("Participants:");
        chatFrameHeaderPanel.add(chatFramePartipantsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 120, 25));

        chatFrameParticipantsListLabel.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        chatFrameParticipantsListLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chatFrameHeaderPanel.add(chatFrameParticipantsListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 320, 25));

        chatFrameGrpNameLabel.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        chatFrameGrpNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatFrameHeaderPanel.add(chatFrameGrpNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 156, 25));

        jScrollPane2.setViewportView(jTextPane1);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chatFrameMessagePanel.setBackground(new java.awt.Color(254, 215, 164));
        chatFrameMessagePanel.setPreferredSize(new java.awt.Dimension(600, 450));
        chatFrameMessagePanel.setLayout(new java.awt.GridLayout(1000, 1));
        jScrollPane3.setViewportView(chatFrameMessagePanel);

        javax.swing.GroupLayout chatFramePanelLayout = new javax.swing.GroupLayout(chatFramePanel);
        chatFramePanel.setLayout(chatFramePanelLayout);
        chatFramePanelLayout.setHorizontalGroup(
            chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatFramePanelLayout.createSequentialGroup()
                .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatFramePanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(chatFrameHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(chatFramePanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatFramePanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        chatFramePanelLayout.setVerticalGroup(
            chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatFramePanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(chatFrameHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(chatFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        mainViewPanel.add(chatFramePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 600, 600));

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
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JPanel chatFramePanel;
    private javax.swing.JLabel chatFrameParticipantsListLabel;
    private javax.swing.JLabel chatFramePartipantsLabel;
    private javax.swing.JLabel grpJoinedLabel;
    private javax.swing.JPanel grpJoinedPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel mainViewPanel;
    // End of variables declaration//GEN-END:variables

    
    
}
