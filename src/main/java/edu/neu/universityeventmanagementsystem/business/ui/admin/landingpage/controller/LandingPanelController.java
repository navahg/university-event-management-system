package edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.view.LandingPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * LandingPanelController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/12/2018
 */
@Controller
public class LandingPanelController extends FormController {

    private MainFrameController mainFrameController;
    private LandingPanelView landingPanelView;
    private UsersEntity user;

    private final static Logger log = Logger.getLogger(LandingPanelController.class);

    @Autowired
    public LandingPanelController (MainFrameController mainFrameController, LandingPanelView landingPanelView) {
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