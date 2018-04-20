package edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.admin.infrastructure.controller.InfrastructureController;
import edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.view.LandingPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller.UsersController;
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
public final class LandingPanelController extends FormController {

    private MainFrameController mainFrameController;
    private LandingPanelView landingPanelView;
    private InfrastructureController infrastructureController;
    private UsersController usersController;
    private UsersEntity user;

    private final static Logger log = Logger.getLogger(LandingPanelController.class);

    @Autowired
    public LandingPanelController (MainFrameController mainFrameController, LandingPanelView landingPanelView,
                                   InfrastructureController infrastructureController,
                                   UsersController usersController) {
        this.mainFrameController = mainFrameController;
        this.landingPanelView = landingPanelView;
        this.infrastructureController = infrastructureController;
        this.usersController = usersController;
        user = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (user == null) {
            log.error("Current user is null");
            return;
        }
        registerAction((javax.swing.JButton) landingPanelView.getLogoutButton(), event -> doLogout());
        registerPanelEvents();
        landingPanelView.setUserText(user.getFirstName() + " " + user.getLastName());
        viewPanel();
    }

    private void registerPanelEvents() {
        landingPanelView.getPanelButtons().forEach(button -> {
            registerAction((javax.swing.JButton) button, this::changeView);
        });
    }

    private void changeView(java.awt.event.ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();
        landingPanelView.setActiveButton((javax.swing.JButton) event.getSource());
        switch (view) {
            case "Dashboard":
                break;
            case "Infrastructures":
                landingPanelView.setContentPanel(infrastructureController.getView());
                break;
            case "Users":
                landingPanelView.setContentPanel(usersController.getView());
                break;
            case "Events":
                break;
            default:
        }
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