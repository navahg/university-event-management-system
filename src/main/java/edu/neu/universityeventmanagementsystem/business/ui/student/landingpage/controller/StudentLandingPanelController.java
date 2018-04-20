package edu.neu.universityeventmanagementsystem.business.ui.student.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.EventsPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.student.account.controller.AccountSettingsController;
import edu.neu.universityeventmanagementsystem.business.ui.student.landingpage.view.StudentLandingPanelView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;

@Controller
public class StudentLandingPanelController extends FormController {

    private MainFrameController mainFrameController;
    private StudentLandingPanelView studentLandingPanelView;
    private EventsPanelController eventsPanelController;
    private AccountSettingsController accountSettingsController;
    private UsersEntity user;

    private final static Logger log = Logger.getLogger(StudentLandingPanelController.class);


    @Autowired
    public StudentLandingPanelController(MainFrameController mainFrameController, StudentLandingPanelView studentLandingPanelView,
                                         EventsPanelController eventsPanelController,
                                         AccountSettingsController accountSettingsController) {
        this.mainFrameController = mainFrameController;
        this.studentLandingPanelView = studentLandingPanelView;
        this.eventsPanelController = eventsPanelController;
        this.accountSettingsController = accountSettingsController;
        user = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (user == null) {
            log.error("Current user is null");
            return;
        }
        registerAction((javax.swing.JButton) studentLandingPanelView.getLogoutButton(), event -> doLogout());
        studentLandingPanelView.setUserText(user.getFirstName() + " " + user.getLastName());
        registerPanelEvents();
        viewPanel();
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    private void doLogout () {
        mainFrameController.removeFromLayout(studentLandingPanelView);
    }

    private void registerPanelEvents() {
        studentLandingPanelView.getPanelButtons().forEach(button -> {
            registerAction((javax.swing.JButton) button, this::changeView);
        });
    }

    private void changeView(ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();
        studentLandingPanelView.setActiveButton((javax.swing.JButton) event.getSource());
        switch (view) {
            case "Dashboard":
                break;
            case "Events":
                studentLandingPanelView.setContentPanel(eventsPanelController.getView());
                break;
            case "Account Settings":
                studentLandingPanelView.setContentPanel(accountSettingsController.getView());
                break;
            default:
        }
    }

    private void viewPanel() {
        mainFrameController.addToLayout(studentLandingPanelView);
    }

}
