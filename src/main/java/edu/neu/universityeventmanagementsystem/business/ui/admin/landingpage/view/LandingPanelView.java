/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.view;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LandingPanelView class
 *
 * @author Raghavan Renganathan <renganathan.raghavan@gmail.com> <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 12, 2018
 */
@Component
@Lazy
public final class LandingPanelView extends javax.swing.JPanel {

    private final static Color ACTIVE = new Color(6, 136, 136);
    private final static Color INACTIVE = new Color(5, 119, 119);

    /**
     * Creates new form LandingPanelView
     */
    public LandingPanelView() {
        initComponents();
    }

    public java.awt.Component getLogoutButton() {
        return btnLogout;
    }

    public void setUserText(String name) {
        lblUserFullName.setText(name);
    }

    public void setContentPanel(java.awt.Component component) {
        contentPanel.removeAll();
        contentPanel.add(component);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public List<java.awt.Component> getPanelButtons() {
        return new ArrayList<>(Arrays.asList(btnDashboard, btnInfrastructures, btnEvents, btnUsers));
    }

    public void setActiveButton(java.awt.Component activeButton) {
        getPanelButtons().forEach(button -> button.setBackground(INACTIVE));
        activeButton.setBackground(ACTIVE);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        lblUserFullName = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnInfrastructures = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnEvents = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 768));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome");

        lblUserFullName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUserFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserFullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserFullName.setText("John Doe");

        btnDashboard.setBackground(new java.awt.Color(6, 136, 136));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home_icon.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDashboard.setOpaque(true);

        btnInfrastructures.setBackground(new java.awt.Color(5, 119, 119));
        btnInfrastructures.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnInfrastructures.setForeground(new java.awt.Color(255, 255, 255));
        btnInfrastructures.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/college_icon.png"))); // NOI18N
        btnInfrastructures.setText("Infrastructures");
        btnInfrastructures.setBorderPainted(false);
        btnInfrastructures.setContentAreaFilled(false);
        btnInfrastructures.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInfrastructures.setFocusPainted(false);
        btnInfrastructures.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnInfrastructures.setOpaque(true);

        btnUsers.setBackground(new java.awt.Color(5, 119, 119));
        btnUsers.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/users_icon.png"))); // NOI18N
        btnUsers.setText("Users");
        btnUsers.setBorderPainted(false);
        btnUsers.setContentAreaFilled(false);
        btnUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsers.setFocusPainted(false);
        btnUsers.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnUsers.setOpaque(true);

        btnEvents.setBackground(new java.awt.Color(5, 119, 119));
        btnEvents.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEvents.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/events_icon.png"))); // NOI18N
        btnEvents.setText("Events");
        btnEvents.setBorderPainted(false);
        btnEvents.setContentAreaFilled(false);
        btnEvents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEvents.setFocusPainted(false);
        btnEvents.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEvents.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(lblUserFullName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInfrastructures, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(lblUserFullName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnInfrastructures, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.setPreferredSize(new java.awt.Dimension(1066, 700));

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout_icon.png"))); // NOI18N
        btnLogout.setToolTipText("Logout");
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEvents;
    private javax.swing.JButton btnInfrastructures;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUsers;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel lblUserFullName;
    // End of variables declaration//GEN-END:variables

}
