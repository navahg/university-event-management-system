package edu.neu.universityeventmanagementsystem.business.ui.account.register.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.register.view.RegisterPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.view.MainFrameView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * RegisterPanelController class
 *
 * @author ragha <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/12/2018
 */
@Controller
public class RegisterPanelController extends FormController {

    private RegisterPanelView registerPanelView;
    private MainFrameView mainFrameView;

    @Autowired
    public RegisterPanelController(RegisterPanelView registerPanelView, MainFrameView ) {
        this.registerPanelView = registerPanelView;
    }

    @Override
    public void prepareAndOpenForm() {

    }
}
