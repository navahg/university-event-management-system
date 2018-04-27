package edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.users.account.controller.AccountSettingsController;
import edu.neu.universityeventmanagementsystem.business.ui.users.dashboard.controller.UserDashboardController;
import edu.neu.universityeventmanagementsystem.business.ui.users.events.controller.ManageEventsController;
import edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.view.UsersLandingPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.users.schedule.controller.SchedulePanelController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;

@Controller
@Lazy
public class UsersLandingPanelController extends FormController {

    private final static Logger log = Logger.getLogger(UsersLandingPanelController.class);
    private MainFrameController mainFrameController;
    private UsersLandingPanelView usersLandingPanelView;
    private CurrentUserBean currentUserBean;
    private ApplicationContext context;


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
        usersLandingPanelView.reset();
        UsersEntity user = currentUserBean.getCurrentUser();

        if (user == null) {
            log.error("Current user is null");
            return;
        }
        registerAction(usersLandingPanelView.getLogoutButton(), event -> doLogout());
        usersLandingPanelView.setUserText(user.getFirstName());
        showContentsBasedOnPrivilege(user);
        registerPanelEvents();
        loadDefaultView();
        viewPanel();
    }

    private void showContentsBasedOnPrivilege(UsersEntity user) {
        usersLandingPanelView.showOnlyPrivileged(user.getRolesByIdRole().getPrivilegeLevel());
    }

    private void doLogout() {
        currentUserBean.setCurrentUser(null);
        usersLandingPanelView.reset();
        mainFrameController.removeFromLayout(usersLandingPanelView);
    }

    private void registerPanelEvents() {
        usersLandingPanelView.getPanelButtons().forEach(button -> {
            registerAction(button, this::changeView);
        });
    }

    private void loadDefaultView() {
        usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_DASHBOARD);
        usersLandingPanelView.setContentPanel((context.getBean(UserDashboardController.class)).getView());
    }

    private void changeView(ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();

        usersLandingPanelView.setActiveButton((javax.swing.JButton) event.getSource());
        switch (view) {
            case "Dashboard":
                usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_DASHBOARD);
                usersLandingPanelView.setContentPanel((context.getBean(UserDashboardController.class)).getView());
                break;
            case "Schedule & Invites":
                usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_SCHEDULE);
                usersLandingPanelView.setContentPanel((context.getBean(SchedulePanelController.class)).getView());
                break;
            case "Events":
                usersLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_EVENTS);
                usersLandingPanelView.setContentPanel((context.getBean(ManageEventsController.class)).getView());
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
