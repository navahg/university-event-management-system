package edu.neu.universityeventmanagementsystem.business.ui.account.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.view.LoginPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrameView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    @Autowired
    public LoginPanelController(MainFrameView mainFrameView, LoginPanelView loginPanelView) {
        this.mainFrameView = mainFrameView;
        this.loginPanelView = loginPanelView;
    }

    @Override
    public void prepareAndOpenForm() {
        registerAction((javax.swing.JButton)loginPanelView.getSignInButton(), (event) -> doSignIn());
        addPanelTo(mainFrameView);
    }

    private void doSignIn () {

    }

    private void addPanelTo(java.awt.Component mainFrameView) {
        if (!(mainFrameView instanceof MainFrameView)) return;

        ((MainFrameView) mainFrameView).addToPanel(loginPanelView);
    }

}
