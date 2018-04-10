package edu.neu.universityeventmanagementsystem.business.ui.main.controller;

import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * MainController class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
@Controller
public class MainController {

    private MainFrame mainFrame;

    @Autowired
    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void openFrame() {
        mainFrame.setVisible(true);
    }
}
