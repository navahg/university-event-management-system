package edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.users.schedule.controller.SchedulePanelController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.users.account.controller.AccountSettingsController;
import edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.view.UsersLandingPanelView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;

@Controller
@Lazy
public class UsersLandingPanelController extends FormController {

    private MainFrameController mainFrameController;
    private UsersLandingPanelView usersLandingPanelView;
    private CurrentUserBean currentUserBean;
    private ApplicationContext context;

    private final static Logger log = Logger.getLogger(UsersLandingPanelController.class);


    @Autowired
    public UsersLandingPanelController(MainFrameController mainFrameController,
                                         UsersLandingPanelView usersLandingPanelView,
                                         CurrentUserBean currentUserBean,
                                         ApplicationContext context) {
        this.mainFrameController = mainFrameController;
        this.usersLandingPanelView = usersLandingPanelView;
        this.currentUserBean = currentUserBean;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        UsersEntity user = currentUserBean.getCurrentUser();

        if (user == null) {
            log.error("Current user is null");
            return;
        }
        registerAction((javax.swing.JButton) usersLandingPanelView.getLogoutButton(), event -> doLogout());
        usersLandingPanelView.setUserText(user.getFirstName());
        showContentsBasedOnPrivilege(user);
        registerPanelEvents();
        viewPanel();
    }

    private void showContentsBasedOnPrivilege(UsersEntity user) {
        usersLandingPanelView.showOnlyPrivileged(user.getRolesByIdRole().getPrivilegeLevel());
    }

    private void doLogout () {
        currentUserBean.setCurrentUser(null);
        mainFrameController.removeFromLayout(usersLandingPanelView);
    }

    private void registerPanelEvents() {
        usersLandingPanelView.getPanelButtons().forEach(button -> {
            registerAction((javax.swing.JButton) button, this::changeView);
        });
    }

    private void changeView(ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();
        int privilegeLevel = currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel();

        usersLandingPanelView.setActiveButton((javax.swing.JButton) event.getSource());
        switch (view) {
            case "Dashboard":
                break;
            case "Schedule & Invites":
                usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_SCHEDULE);
                usersLandingPanelView.setContentPanel((context.getBean(SchedulePanelController.class)).getView());
                break;
            case "Events":
                break;
            case "Account Settings":
                usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_ACCOUNT_SETTINGS);
                usersLandingPanelView.setContentPanel((context.getBean(AccountSettingsController.class)).getView());
                break;
            default:
        }
    }

    private void viewPanel() {
        mainFrameController.addToLayout(usersLandingPanelView);
    }

}
