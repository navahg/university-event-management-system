package edu.neu.universityeventmanagementsystem.business.ui.account.login.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.ui.account.login.view.LoginPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.controller.RegisterPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.admin.landingpage.controller.AdminLandingPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.users.landingpage.controller.UsersLandingPanelController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * LoginPanelController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/11/2018
 */
@Controller
@Lazy
public final class LoginPanelController extends FormController {

    private MainFrameController mainFrameController;
    private LoginPanelView loginPanelView;
    private UserAccountsService userAccountsService;
    private CurrentUserBean currentUserBean;
    private ApplicationContext context;

    @Autowired
    public LoginPanelController(MainFrameController mainFrameController, LoginPanelView loginPanelView,
                                UserAccountsService userAccountsService,
                                CurrentUserBean currentUserBean,
                                ApplicationContext context) {
        this.mainFrameController = mainFrameController;
        this.loginPanelView = loginPanelView;
        this.userAccountsService = userAccountsService;
        this.currentUserBean = currentUserBean;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        mainFrameController.prepareAndOpenForm();
        registerAction(loginPanelView.getSignInButton(), (event) -> doSignIn());
        registerAction(loginPanelView.getSignUpButton(), (event) -> showRegisterView());
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

        if (currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel() >= ConstantValues.MinimumPrivilegeLevel.ADMIN) {
            (context.getBean(AdminLandingPanelController.class)).prepareAndOpenForm();
        } else {
            (context.getBean(UsersLandingPanelController.class)).prepareAndOpenForm();
        }
    }

    private void showRegisterView() {
        (context.getBean(RegisterPanelController.class)).prepareAndOpenForm();
    }

    private void viewPanel() {
        mainFrameController.addToLayout(loginPanelView);
    }

}
