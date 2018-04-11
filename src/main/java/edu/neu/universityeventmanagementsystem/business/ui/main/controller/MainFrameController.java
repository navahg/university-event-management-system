package edu.neu.universityeventmanagementsystem.business.ui.main.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.controller.LoginPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrameView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * MainFrameController class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
@Controller
public class MainFrameController extends FormController {

    private MainFrameView mainFrameView;
    private LoginPanelController loginPanelController;

    @Autowired
    public MainFrameController(MainFrameView mainFrameView, LoginPanelController loginPanelController) {
        this.mainFrameView = mainFrameView;
        this.loginPanelController = loginPanelController;
    }

    @Override
    public void prepareAndOpenForm() {
        showLoginPanel();
        mainFrameView.setVisible(true);
    }

    private void showLoginPanel() {
        loginPanelController.prepareAndOpenForm();
    }
}
