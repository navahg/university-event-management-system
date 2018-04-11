package edu.neu.universityeventmanagementsystem.business.ui.main.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.view.LoginPanel;
import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrameView;
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
public class MainFrameController {

    private MainFrameView mainFrameView;
    private LoginPanel loginPanel;

    @Autowired
    public MainFrameController(MainFrameView mainFrameView, LoginPanel loginPanel) {
        this.mainFrameView = mainFrameView;
        this.loginPanel = loginPanel;
    }

    public void prepareAndOpenFrame() {
        addAndShowLoginPanel();
        mainFrameView.setVisible(true);
    }

    private void addAndShowLoginPanel() {
        mainFrameView.addToPanel(loginPanel);
    }
}
