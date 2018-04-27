package edu.neu.universityeventmanagementsystem.business.ui.shared.view;

import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * AddOrCreateEventView class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 23, 2018
 */
@Component
@Lazy
public class AddOrCreateEventView extends javax.swing.JDialog {

    public static final int NAME_ERROR = 0;
    public static final int LOCATION_ERROR = 1;
    public static final int TIME_ERROR = 2;
    public static final int AUDIENCE_ERROR = 3;

    private static final Logger log = Logger.getLogger(AddOrCreateEventView.class);
    private List<JLabel> errorLabels;

    private AddOrCreateEventView() {
        this(new JFrame(), true);
    }

    /**
     * Creates new form CreateEventView
     */
    public AddOrCreateEventView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        errorLabels = new ArrayList<>(Arrays.asList(lblEventNameError, lblEventLocationError, lblEventTimeError,
                lblEventAudienceError));
        hideAllErrors();
    }

    public void resetView() {
        getContentPane().removeAll();
        initComponents();
        errorLabels = new ArrayList<>(Arrays.asList(lblEventNameError, lblEventLocationError, lblEventTimeError,
                lblEventAudienceError));
        hideAllErrors();
    }

    public void makeReadOnly() {
        txtAreaInvites.setVisible(false);
        btnAddInvitee.setVisible(false);
        btnCreate.setVisible(false);

        txtFieldEventName.setEditable(false);
        txtFieldEventLocation.setEditable(false);
        spinnerEventStart.setEnabled(false);
        spinnerEventEnd.setEnabled(false);
        spinnerMaxSeats.setEnabled(false);

        comboEventGroup.setVisible(false);
        comboEventLevel.setVisible(false);
        checkBoxOpenEvent.setEnabled(false);
        checkBoxSendNotifications.setVisible(false);
    }

    public void makeEditable() {
        txtAreaInvites.setVisible(true);
        btnAddInvitee.setVisible(true);
        btnCreate.setVisible(true);

        txtFieldEventName.setEditable(true);
        txtFieldEventLocation.setEditable(true);
        spinnerEventStart.setEnabled(true);
        spinnerEventEnd.setEnabled(true);
        spinnerMaxSeats.setEnabled(true);

        comboEventGroup.setVisible(true);
        comboEventLevel.setVisible(true);
        checkBoxOpenEvent.setEnabled(true);
        checkBoxSendNotifications.setVisible(true);
    }

    public void hideAllErrors() {
        errorLabels.forEach(label -> label.setVisible(false));
    }

    public void showError(int choice) {
        try {
            errorLabels.get(choice).setVisible(true);
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            log.error("Invalid choice", ex);
        }
    }

    public JComboBox<String> getComboEventLevel() {
        return comboEventLevel;
    }

    public String[] getInviteeList() {
        return txtAreaInvites.getText().split("\n");
    }

    public void setTxtAreaInvites(String text) {
        txtAreaInvites.setText(text);
    }

    public JButton getBtnAddInvitee() {
        return btnAddInvitee;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnCreate() {
        return btnCreate;
    }

    public JCheckBox getCheckBoxOpenEvent() {
        return checkBoxOpenEvent;
    }

    public JButton getBtnSendMail() {
        return btnSendMail;
    }

    public void toggleUserNotAvailableOptions(boolean flag) {
        btnSendMail.setVisible(flag);
        lblUsersNotAvailable.setVisible(flag);
    }

    public void addInviteeView(java.awt.Component component) {
        panelInvites.add(component);
    }

    public void refreshInviteeView() {
        panelInvites.revalidate();
        panelInvites.repaint();
    }

    public String getEventName() {
        return txtFieldEventName.getText();
    }

    public void setEventName(String name) {
        txtFieldEventName.setText(name);
    }

    public String getEventLocation() {
        return txtFieldEventLocation.getText();
    }

    public void setEventLocation(String location) {
        txtFieldEventLocation.setText(location);
    }

    public Date getEventStartDate() {
        return (Date) spinnerEventStart.getValue();
    }

    public void setEventStartDate(Date startDate) {
        spinnerEventStart.setValue(startDate);
    }

    public Date getEventEndDate() {
        return (Date) spinnerEventEnd.getValue();
    }

    public void setEventEndDate(Date startDate) {
        spinnerEventEnd.setValue(startDate);
    }

    public void clearInviteeView() {
        panelInvites.removeAll();
    }

    public boolean isOpenEvent() {
        return checkBoxOpenEvent.isSelected();
    }

    public boolean isSendNotifications() {
        return checkBoxSendNotifications.isSelected();
    }

    public void setCheckBoxOpenEvent(boolean state) {
        checkBoxOpenEvent.setSelected(state);
    }

    public int getMaxSeats() {
        return (Integer) spinnerMaxSeats.getValue();
    }

    public void setMaxSeats(int maxSeats) {
        spinnerMaxSeats.setValue(maxSeats);
    }

    public void setEventLevel(List<String> levels) {
        comboEventLevel.setModel(new DefaultComboBoxModel<>(levels.toArray(new String[0])));
    }

    public String getSelectedEventLevel() {
        return (String) comboEventLevel.getSelectedItem();
    }

    public void setEventGroup(List<String> groups) {
        comboEventGroup.setModel(new DefaultComboBoxModel<>(groups.toArray(new String[0])));
    }

    public String getSelectedGroup() {
        return (String) comboEventGroup.getSelectedItem();
    }

    public void toggleEventType(boolean isOpenEvent) {
        comboEventLevel.setEnabled(!isOpenEvent);
        comboEventGroup.setEnabled(!isOpenEvent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txtFieldEventName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txtFieldEventLocation = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        spinnerEventStart = new javax.swing.JSpinner();
        spinnerEventEnd = new javax.swing.JSpinner();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();
        checkBoxOpenEvent = new javax.swing.JCheckBox();
        checkBoxSendNotifications = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        spinnerMaxSeats = new javax.swing.JSpinner();
        javax.swing.JScrollPane panelInviteesScrollPane = new javax.swing.JScrollPane();
        txtAreaInvites = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        btnAddInvitee = new javax.swing.JButton();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        comboEventLevel = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        comboEventGroup = new javax.swing.JComboBox<>();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        panelInvites = new javax.swing.JPanel();
        lblEventNameError = new javax.swing.JLabel();
        lblEventLocationError = new javax.swing.JLabel();
        lblEventTimeError = new javax.swing.JLabel();
        lblEventAudienceError = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblUsersNotAvailable = new javax.swing.JLabel();
        btnSendMail = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(ConstantMessages.Titles.ADD_EVENT_TITLE);
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 500));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(jPanel1.getBackground(), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Event Details");

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Event Name");

        txtFieldEventName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Event Location");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        txtFieldEventLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Start Time");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        spinnerEventStart.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.DAY_OF_MONTH));

        spinnerEventEnd.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.DAY_OF_MONTH));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("End Time");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setText("Event Audience");

        jSeparator2.setForeground(new java.awt.Color(0, 102, 102));

        checkBoxOpenEvent.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxOpenEvent.setText("Open Event");

        checkBoxSendNotifications.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxSendNotifications.setText("Send Notifications");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Max Seats");

        spinnerMaxSeats.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerMaxSeats.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        txtAreaInvites.setColumns(20);
        txtAreaInvites.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaInvites.setRows(5);
        txtAreaInvites.setPreferredSize(new java.awt.Dimension(250, 100));
        panelInviteesScrollPane.setViewportView(txtAreaInvites);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Invitees");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setText("(for multiple emails use multiple lines)");

        btnAddInvitee.setBackground(jPanel1.getBackground());
        btnAddInvitee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddInvitee.setForeground(new java.awt.Color(255, 255, 255));
        btnAddInvitee.setText("Add >>");
        btnAddInvitee.setBorder(new javax.swing.border.LineBorder(jPanel1.getBackground(), 1, true));
        btnAddInvitee.setContentAreaFilled(false);
        btnAddInvitee.setOpaque(true);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Event Level");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Event Group");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(250, 100));
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(10);

        panelInvites.setLayout(new javax.swing.BoxLayout(panelInvites, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(panelInvites);

        lblEventNameError.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEventNameError.setForeground(new java.awt.Color(255, 0, 0));
        lblEventNameError.setText("Field cannot be empty");

        lblEventLocationError.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEventLocationError.setForeground(new java.awt.Color(255, 0, 0));
        lblEventLocationError.setText("Field cannot be empty");

        lblEventTimeError.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEventTimeError.setForeground(new java.awt.Color(255, 0, 0));
        lblEventTimeError.setText("End time should be after start time");

        lblEventAudienceError.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEventAudienceError.setForeground(new java.awt.Color(255, 0, 0));
        lblEventAudienceError.setText("Make the evnt global or choose the audience");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(462, 462, 462)
                        .addComponent(lblEventNameError)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(50, 50, 50))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(spinnerEventStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(spinnerEventEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFieldEventLocation)
                    .addComponent(txtFieldEventName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEventLocationError)
                    .addComponent(lblEventTimeError))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(checkBoxOpenEvent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboEventGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEventLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerMaxSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkBoxSendNotifications)))
                            .addComponent(lblEventAudienceError))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(panelInviteesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddInvitee, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(166, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtFieldEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEventNameError))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFieldEventLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEventLocationError))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerEventStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spinnerEventEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEventTimeError)))))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxOpenEvent)
                    .addComponent(checkBoxSendNotifications)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(spinnerMaxSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnAddInvitee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(comboEventLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(comboEventGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblEventAudienceError))
                    .addComponent(panelInviteesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/events_icon.png"))); // NOI18N
        jLabel1.setText("Create an event");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnCreate.setBackground(new java.awt.Color(51, 51, 51));
        btnCreate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.setContentAreaFilled(false);
        btnCreate.setOpaque(true);

        btnCancel.setBackground(new java.awt.Color(255, 102, 102));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setContentAreaFilled(false);
        btnCancel.setOpaque(true);

        lblUsersNotAvailable.setText("Users not avaliable in the system.");

        btnSendMail.setBackground(new java.awt.Color(153, 204, 255));
        btnSendMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mail_icon.png"))); // NOI18N
        btnSendMail.setText("Send a mail instead");
        btnSendMail.setBorder(null);
        btnSendMail.setContentAreaFilled(false);
        btnSendMail.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblUsersNotAvailable)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lblUsersNotAvailable)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddInvitee;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JCheckBox checkBoxOpenEvent;
    private javax.swing.JCheckBox checkBoxSendNotifications;
    private javax.swing.JComboBox<String> comboEventGroup;
    private javax.swing.JComboBox<String> comboEventLevel;
    private javax.swing.JLabel lblEventAudienceError;
    private javax.swing.JLabel lblEventLocationError;
    private javax.swing.JLabel lblEventNameError;
    private javax.swing.JLabel lblEventTimeError;
    private javax.swing.JLabel lblUsersNotAvailable;
    private javax.swing.JPanel panelInvites;
    private javax.swing.JSpinner spinnerEventEnd;
    private javax.swing.JSpinner spinnerEventStart;
    private javax.swing.JSpinner spinnerMaxSeats;
    private javax.swing.JTextArea txtAreaInvites;
    private javax.swing.JTextField txtFieldEventLocation;
    private javax.swing.JTextField txtFieldEventName;
    // End of variables declaration//GEN-END:variables
}
