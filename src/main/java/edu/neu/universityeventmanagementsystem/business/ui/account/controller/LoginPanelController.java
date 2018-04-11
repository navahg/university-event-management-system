package edu.neu.universityeventmanagementsystem.business.ui.account.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.ui.account.view.LoginPanelView;
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

    private MainFrameView mainFrameView;
    private LoginPanelView loginPanelView;
    private UserAccountsService userAccountsService;

    @Autowired
    public LoginPanelController(MainFrameView mainFrameView, LoginPanelView loginPanelView, UserAccountsService userAccountsService) {
        this.mainFrameView = mainFrameView;
        this.loginPanelView = loginPanelView;
        this.userAccountsService = userAccountsService;
    }

    @Override
    public void prepareAndOpenForm() {
        registerAction((javax.swing.JButton)loginPanelView.getSignInButton(), (event) -> doSignIn());
        addPanelTo(mainFrameView);
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

    private void addPanelTo(java.awt.Component mainFrameView) {
        if (!(mainFrameView instanceof MainFrameView)) return;

        ((MainFrameView) mainFrameView).addToPanel(loginPanelView);
    }

}
