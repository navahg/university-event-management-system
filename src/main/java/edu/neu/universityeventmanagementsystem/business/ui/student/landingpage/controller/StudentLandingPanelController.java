package edu.neu.universityeventmanagementsystem.business.ui.student.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.admin.infrastructure.controller.InfrastructureController;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.student.landingpage.view.StudentLandingPanelView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentLandingPanelController extends FormController {

    private MainFrameController mainFrameController;
    private StudentLandingPanelView landingPanelView;
    private UsersEntity user;

    private final static Logger log = Logger.getLogger(StudentLandingPanelController.class);


    @Autowired
    public StudentLandingPanelController(MainFrameController mainFrameController, StudentLandingPanelView landingPanelView,
                                         InfrastructureController infrastructureController) {
        this.mainFrameController = mainFrameController;
        this.landingPanelView = landingPanelView;
        user = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (user == null) {
            log.error("Current user is null");
            return;
        }
        registerAction((javax.swing.JButton) landingPanelView.getLogoutButton(), event -> doLogout());
        landingPanelView.setUserText(user.getFirstName() + " " + user.getLastName());
        viewPanel();
    }


    public void setUser(UsersEntity user) {
        this.user = user;
    }

    private void doLogout () {
        mainFrameController.removeFromLayout(landingPanelView);
    }

    private void viewPanel() {
        mainFrameController.addToLayout(landingPanelView);
    }

}
