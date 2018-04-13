package edu.neu.universityeventmanagementsystem.business.ui.main.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.login.controller.LoginPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.view.RegisterPanelView;
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

    @Autowired
    public MainFrameController(MainFrameView mainFrameView) {
        this.mainFrameView = mainFrameView;
    }

    @Override
    public void prepareAndOpenForm() {
        mainFrameView.setVisible(true);
    }

    public void addToLayout (java.awt.Component component) {
        mainFrameView.addToPanel(component);
    }

    public void removeFromLayout(java.awt.Component component) {
        mainFrameView.removeFromPanel(component);
    }
}
