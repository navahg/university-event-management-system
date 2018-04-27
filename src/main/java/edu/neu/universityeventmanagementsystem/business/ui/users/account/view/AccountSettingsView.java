package edu.neu.universityeventmanagementsystem.business.ui.users.account.view;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * AccountSettingsView class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 19, 2018
 */
@Component
@Lazy
public final class AccountSettingsView extends javax.swing.JPanel {

    public static final int NAME_ERROR = 0;
    public static final int EMAIL_ERROR = 1;
    public static final int PASSWORD_ERROR = 2;
    public static final int PASSWORD_NO_MATCH_ERROR = 3;

    private static final Logger log = Logger.getLogger(AccountSettingsView.class);

    private UsersEntity currentUser;

    /**
     * Creates new form AccountSettingsView
     */
    public AccountSettingsView() {
        initComponents();
        makeFieldsReadOnly();
        toggleChangePasswordView(false);
        hideErrors();
    }

    public void setCurrentUser(UsersEntity currentUser) {
        this.currentUser = currentUser;
    }

    public void hideErrors() {
        lblInvalidEmailError.setVisible(false);
        lblNameError.setVisible(false);
        lblPasswordDoNotMatchError.setVisible(false);
        lblWrongPasswordError.setVisible(false);
    }

    public void showError(int which) {
        switch (which) {
            case NAME_ERROR:
                lblNameError.setVisible(true);
                break;
            case EMAIL_ERROR:
                lblInvalidEmailError.setVisible(true);
                break;
            case PASSWORD_ERROR:
                lblWrongPasswordError.setVisible(true);
                break;
            case PASSWORD_NO_MATCH_ERROR:
                lblPasswordDoNotMatchError.setVisible(true);
                break;
        }
    }

    public void makeFieldsReadOnly() {
        btnSave.setVisible(false);
        btnEdit.setVisible(true);
        txtFieldFirstName.setEditable(false);
        txtFieldMiddleName.setEditable(false);
        txtFieldLastName.setEditable(false);
        txtFieldEmail.setEditable(false);
    }

    public void makeFieldsEditable() {
        btnSave.setVisible(true);
        btnEdit.setVisible(false);
        txtFieldFirstName.setEditable(true);
        txtFieldMiddleName.setEditable(true);
        txtFieldLastName.setEditable(true);
        txtFieldEmail.setEditable(true);
    }

    public void toggleChangePasswordView(boolean flag) {
        changePasswordPanel.setVisible(flag);
        btnChangePassword.setVisible(!flag);
        btnSavePassword.setVisible(flag);
        btnCancelPassword.setVisible(flag);
    }

    public JButton getBtnChangePassword() {
        return btnChangePassword;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnSavePassword() {
        return btnSavePassword;
    }

    public JButton getBtnCancelPassword() {
        return btnCancelPassword;
    }

    public void fillDetails() {
        if (currentUser == null) {
            log.debug("User entity is null.");
            return;
        }

        int privilegeLevel = currentUser.getRolesByIdRole().getPrivilegeLevel();

        txtFieldUserName.setText(currentUser.getUserName());

        if (privilegeLevel >= ConstantValues.MinimumPrivilegeLevel.STUDENT) {
            txtFieldCollege.setText(currentUser.getProgramMembersByIdUser().getProgramsByIdProgram().getCollegesByIdCollege().getName());
            txtFieldOrganization.setText(currentUser.getProgramMembersByIdUser().getProgramsByIdProgram().getName());
        } else {
            txtFieldCollege.setVisible(false);
            txtFieldOrganization.setVisible(false);
            lblCollege.setVisible(false);
            lblOrganization.setVisible(false);
        }

        txtFieldFirstName.setText(currentUser.getFirstName());
        txtFieldMiddleName.setText(currentUser.getMiddleName());
        txtFieldLastName.setText(currentUser.getLastName());

        txtFieldEmail.setText(currentUser.getEmail());
    }

    public String getOldPassword() {
        return new String(pwdFieldOld.getPassword());
    }

    public String getNewPassword() {
        return new String(pwdFieldNew.getPassword());
    }

    public String getConfirmPassword() {
        return new String(pwdFieldConfirm.getPassword());
    }

    public String getEmail() {
        return txtFieldEmail.getText();
    }

    public String getFirstName() {
        return txtFieldFirstName.getText();
    }

    public String getLastName() {
        return txtFieldLastName.getText();
    }

    public String getMiddleName() {
        return txtFieldMiddleName.getText();
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

        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txtFieldFirstName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txtFieldEmail = new javax.swing.JTextField();
        txtFieldUserName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txtFieldMiddleName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txtFieldLastName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txtFieldCollege = new javax.swing.JTextField();
        lblCollege = new javax.swing.JLabel();
        txtFieldOrganization = new javax.swing.JTextField();
        lblOrganization = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        btnSavePassword = new javax.swing.JButton();
        changePasswordPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        lblPasswordDoNotMatchError = new javax.swing.JLabel();
        pwdFieldOld = new javax.swing.JPasswordField();
        pwdFieldConfirm = new javax.swing.JPasswordField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        pwdFieldNew = new javax.swing.JPasswordField();
        lblWrongPasswordError = new javax.swing.JLabel();
        lblInvalidEmailError = new javax.swing.JLabel();
        lblNameError = new javax.swing.JLabel();
        btnCancelPassword = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1116, 700));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("First Name");

        txtFieldFirstName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Email");

        txtFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtFieldUserName.setEditable(false);
        txtFieldUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Username");

        txtFieldMiddleName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Middle Name");

        txtFieldLastName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Last Name");

        txtFieldCollege.setEditable(false);
        txtFieldCollege.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCollege.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCollege.setText("College");

        txtFieldOrganization.setEditable(false);
        txtFieldOrganization.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblOrganization.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOrganization.setText("Organization");

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit_icon_48px.png"))); // NOI18N
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusPainted(false);
        btnEdit.setPreferredSize(new java.awt.Dimension(48, 48));

        btnSave.setVisible(false);
        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save_icon_48px.png"))); // NOI18N
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new java.awt.Dimension(48, 48));

        btnChangePassword.setBackground(new java.awt.Color(153, 102, 255));
        btnChangePassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePassword.setText("Change Password");
        btnChangePassword.setContentAreaFilled(false);
        btnChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangePassword.setOpaque(true);

        btnSavePassword.setBackground(new java.awt.Color(0, 204, 204));
        btnSavePassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSavePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnSavePassword.setText("Save Password");
        btnSavePassword.setContentAreaFilled(false);
        btnSavePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSavePassword.setOpaque(true);

        changePasswordPanel.setPreferredSize(new java.awt.Dimension(660, 110));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("New Password");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Confirm Password");

        lblPasswordDoNotMatchError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblPasswordDoNotMatchError.setForeground(new java.awt.Color(255, 0, 51));
        lblPasswordDoNotMatchError.setText("Passwords do not match");

        pwdFieldOld.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pwdFieldConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Current Password");

        pwdFieldNew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblWrongPasswordError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblWrongPasswordError.setForeground(new java.awt.Color(255, 0, 51));
        lblWrongPasswordError.setText("Wrong password");

        javax.swing.GroupLayout changePasswordPanelLayout = new javax.swing.GroupLayout(changePasswordPanel);
        changePasswordPanel.setLayout(changePasswordPanelLayout);
        changePasswordPanelLayout.setHorizontalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(pwdFieldOld, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWrongPasswordError))
                .addGap(18, 18, 18)
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPasswordDoNotMatchError)
                    .addGroup(changePasswordPanelLayout.createSequentialGroup()
                        .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(pwdFieldNew, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(pwdFieldConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        changePasswordPanelLayout.setVerticalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(changePasswordPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pwdFieldOld, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, changePasswordPanelLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(pwdFieldNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, changePasswordPanelLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(pwdFieldConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWrongPasswordError)
                    .addComponent(lblPasswordDoNotMatchError))
                .addContainerGap())
        );

        lblInvalidEmailError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblInvalidEmailError.setForeground(new java.awt.Color(255, 0, 51));
        lblInvalidEmailError.setText("Invalid Email");

        lblNameError.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNameError.setForeground(new java.awt.Color(255, 0, 51));
        lblNameError.setText("First and Last are mandatory");

        btnCancelPassword.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelPassword.setText("Cancel");
        btnCancelPassword.setContentAreaFilled(false);
        btnCancelPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelPassword.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSavePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtFieldMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNameError))
                            .addComponent(jLabel10)))
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInvalidEmailError))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCollege)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFieldCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblOrganization, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(txtFieldOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCollege))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrganization))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNameError))))
                .addGap(13, 13, 13)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInvalidEmailError))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSavePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelPassword;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JLabel lblCollege;
    private javax.swing.JLabel lblInvalidEmailError;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblOrganization;
    private javax.swing.JLabel lblPasswordDoNotMatchError;
    private javax.swing.JLabel lblWrongPasswordError;
    private javax.swing.JPasswordField pwdFieldConfirm;
    private javax.swing.JPasswordField pwdFieldNew;
    private javax.swing.JPasswordField pwdFieldOld;
    private javax.swing.JTextField txtFieldCollege;
    private javax.swing.JTextField txtFieldEmail;
    private javax.swing.JTextField txtFieldFirstName;
    private javax.swing.JTextField txtFieldLastName;
    private javax.swing.JTextField txtFieldMiddleName;
    private javax.swing.JTextField txtFieldOrganization;
    private javax.swing.JTextField txtFieldUserName;
    // End of variables declaration//GEN-END:variables

}
