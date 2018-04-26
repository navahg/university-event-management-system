/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.view;

import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Srinithi
 */
@Component
@Lazy
public class UsersLandingPanelView extends javax.swing.JPanel {

    private final static Color ACTIVE = new Color(6, 136, 136);
    private final static Color INACTIVE = new Color(5, 119, 119);

    /**
     * Creates new form LandingPanelView
     */
    public UsersLandingPanelView() {
        initComponents();
    }

    public javax.swing.JButton getLogoutButton() {
        return btnLogout;
    }

    public void setUserText(String name) {
        lblUserFullName.setText(name);
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public void setContentPanel(java.awt.Component component) {
        contentPanel.removeAll();
        contentPanel.add(component);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void setActiveButton(javax.swing.JButton activeButton) {
        getPanelButtons().forEach(button -> button.setBackground(INACTIVE));
        activeButton.setBackground(ACTIVE);
    }

    public List<javax.swing.JButton> getPanelButtons() {
        return new ArrayList<>(Arrays.asList(btnDashboard, btnSchedule, btnEvents, btnAccountSettings));
    }

    public void showOnlyPrivileged(int privilegeLevel) {
        if (privilegeLevel < ConstantValues.MinimumPrivilegeLevel.SPECIAL_STUDENT) {
            btnEvents.setVisible(false);
        } else {
            btnEvents.setVisible(true);
        }
    }

    public void reset() {
        contentPanel.removeAll();
        lblTitle.setText("Dashboard");
        setActiveButton(btnDashboard);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        lblUserFullName = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnSchedule = new javax.swing.JButton();
        btnEvents = new javax.swing.JButton();
        btnAccountSettings = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(1116, 700));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 768));

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
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home_icon.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDashboard.setOpaque(true);

        btnSchedule.setBackground(new java.awt.Color(5, 119, 119));
        btnSchedule.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSchedule.setForeground(new java.awt.Color(255, 255, 255));
        btnSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/invite_icon_48px.png"))); // NOI18N
        btnSchedule.setText("Schedule & Invites");
        btnSchedule.setBorderPainted(false);
        btnSchedule.setContentAreaFilled(false);
        btnSchedule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSchedule.setFocusPainted(false);
        btnSchedule.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnSchedule.setOpaque(true);

        btnEvents.setBackground(new java.awt.Color(5, 119, 119));
        btnEvents.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEvents.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/events_icon.png"))); // NOI18N
        btnEvents.setText("Events");
        btnEvents.setBorderPainted(false);
        btnEvents.setContentAreaFilled(false);
        btnEvents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEvents.setFocusPainted(false);
        btnEvents.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEvents.setOpaque(true);

        btnAccountSettings.setBackground(new java.awt.Color(5, 119, 119));
        btnAccountSettings.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAccountSettings.setForeground(new java.awt.Color(255, 255, 255));
        btnAccountSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_setting_icon_48px.png"))); // NOI18N
        btnAccountSettings.setText("Account Settings");
        btnAccountSettings.setBorderPainted(false);
        btnAccountSettings.setContentAreaFilled(false);
        btnAccountSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAccountSettings.setFocusPainted(false);
        btnAccountSettings.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAccountSettings.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(lblUserFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(btnEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAccountSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(lblUserFullName)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnAccountSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        lblTitle.setText("Dashboard");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccountSettings;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEvents;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSchedule;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserFullName;
    // End of variables declaration//GEN-END:variables
}
