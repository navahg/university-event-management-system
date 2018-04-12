package edu.neu.universityeventmanagementsystem.business.ui.account.login.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.ui.account.login.view.LoginPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.controller.RegisterPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrameView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;

/**
 * LoginPanelController class
 *
 * @author ragha <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/11/2018
 */
@Controller
public class LoginPanelController extends FormController {

    private MainFrameController mainFrameController;
    private LoginPanelView loginPanelView;
    private RegisterPanelController registerPanelController;
    private UserAccountsService userAccountsService;

    @Autowired
    public LoginPanelController(MainFrameController mainFrameController, LoginPanelView loginPanelView,
                                UserAccountsService userAccountsService,
                                RegisterPanelController registerPanelController) {
        this.mainFrameController = mainFrameController;
        this.loginPanelView = loginPanelView;
        this.registerPanelController = registerPanelController;
        this.userAccountsService = userAccountsService;
    }

    @Override
    public void prepareAndOpenForm() {
        registerAction((javax.swing.JButton)loginPanelView.getSignInButton(), (event) -> doSignIn());
        viewPanel();
    }

    private void doSignIn () {
        loginPanelView.supressInvalidCredentials();

        String username = loginPanelView.getUserName();
        String password = loginPanelView.getPassword();

        UserAccountsEntity account = userAccountsService.findOne(username, password);
        if (account == null) {
            loginPanelView.raiseInvalidCredentials();
            return;
        }

        JOptionPane.showMessageDialog(null, "Login Successful!");
    }

    private void viewPanel() {
        mainFrameController.addToLayout(loginPanelView);
    }

}
