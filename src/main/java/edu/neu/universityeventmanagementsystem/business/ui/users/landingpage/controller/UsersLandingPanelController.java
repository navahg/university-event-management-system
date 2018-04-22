package edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.users.schedule.controller.SchedulePanelController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.users.account.controller.AccountSettingsController;
import edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.view.UsersLandingPanelView;
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

    private MainFrameController mainFrameController;
    private UsersLandingPanelView studentLandingPanelView;
    private CurrentUserBean currentUserBean;
    private ApplicationContext context;

    private final static Logger log = Logger.getLogger(UsersLandingPanelController.class);


    @Autowired
    public UsersLandingPanelController(MainFrameController mainFrameController,
                                         UsersLandingPanelView studentLandingPanelView,
                                         CurrentUserBean currentUserBean,
                                         ApplicationContext context) {
        this.mainFrameController = mainFrameController;
        this.studentLandingPanelView = studentLandingPanelView;
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
        registerAction((javax.swing.JButton) studentLandingPanelView.getLogoutButton(), event -> doLogout());
        studentLandingPanelView.setUserText(user.getFirstName());
        registerPanelEvents();
        viewPanel();
    }

    private void doLogout () {
        currentUserBean.setCurrentUser(null);
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
            case "Schedule & Invites":
                studentLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_SCHEDULE);
                studentLandingPanelView.setContentPanel((context.getBean(SchedulePanelController.class)).getView());
                break;
            case "Events":
                break;
            case "Account Settings":
                studentLandingPanelView.setTitle(ConstantMessages.Titles.STUDENT_ACCOUNT_SETTINGS);
                studentLandingPanelView.setContentPanel((context.getBean(AccountSettingsController.class)).getView());
                break;
            default:
        }
    }

    private void viewPanel() {
        mainFrameController.addToLayout(studentLandingPanelView);
    }

}
