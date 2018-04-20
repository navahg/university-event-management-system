package edu.neu.universityeventmanagementsystem.business.ui.account.login.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.RolesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.ui.account.login.view.LoginPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.controller.RegisterPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.controller.LandingPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.student.landingpage.controller.StudentLandingPanelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * LoginPanelController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/11/2018
 */
@Controller
public final class LoginPanelController extends FormController {

    private MainFrameController mainFrameController;
    private LoginPanelView loginPanelView;
    private RegisterPanelController registerPanelController;
    private LandingPanelController adminLandingPanelController;
    private UserAccountsService userAccountsService;
    private StudentLandingPanelController studentLandingPanelController;
    private CurrentUserBean currentUserBean;

    @Autowired
    public LoginPanelController(MainFrameController mainFrameController, LoginPanelView loginPanelView,
                                UserAccountsService userAccountsService,
                                RegisterPanelController registerPanelController,
                                LandingPanelController adminLandingPanelController,
                                StudentLandingPanelController studentLandingPanelController,
                                CurrentUserBean currentUserBean) {
        this.mainFrameController = mainFrameController;
        this.loginPanelView = loginPanelView;
        this.registerPanelController = registerPanelController;
        this.adminLandingPanelController = adminLandingPanelController;
        this.userAccountsService = userAccountsService;
        this.studentLandingPanelController = studentLandingPanelController;
        this.currentUserBean = currentUserBean;
    }

    @Override
    public void prepareAndOpenForm() {
        mainFrameController.prepareAndOpenForm();
        registerAction((javax.swing.JButton) loginPanelView.getSignInButton(), (event) -> doSignIn());
        registerAction((javax.swing.JButton) loginPanelView.getSignUpButton(), (event) -> showRegisterView());
        viewPanel();
    }

    private void doSignIn() {
        loginPanelView.suppressInvalidCredentials();

        String username = loginPanelView.getUserName();
        String password = loginPanelView.getPassword();

        UserAccountsEntity account = userAccountsService.findOne(username, password);
        if (account == null) {
            loginPanelView.raiseInvalidCredentials();
            return;
        }

        loginPanelView.resetView();
        currentUserBean.setCurrentUser(account.getUsersByIdUser());
        showRespectiveView();
    }

    private void showRespectiveView() {
        if (currentUserBean.getCurrentUser() == null) return;

        if (currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel() == RolesEntity.SYSTEM_ADMIN) {
            adminLandingPanelController.prepareAndOpenForm();
        } else {
            studentLandingPanelController.prepareAndOpenForm();
        }
    }

    private void showRegisterView() {
        registerPanelController.prepareAndOpenForm();
    }

    private void viewPanel() {
        mainFrameController.addToLayout(loginPanelView);
    }

}
