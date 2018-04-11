package edu.neu.universityeventmanagementsystem.business.ui.main.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.view.LoginPanelView;
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
    private LoginPanelView loginPanelView;

    @Autowired
    public MainFrameController(MainFrameView mainFrameView, LoginPanelView loginPanelView) {
        this.mainFrameView = mainFrameView;
        this.loginPanelView = loginPanelView;
    }

    public void prepareAndOpenFrame() {
        addAndShowLoginPanel();
        mainFrameView.setVisible(true);
    }

    private void addAndShowLoginPanel() {
        mainFrameView.addToPanel(loginPanelView);
    }
}
