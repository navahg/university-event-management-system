package edu.neu.universityeventmanagementsystem.business.ui.account.register.controller;

import edu.neu.universityeventmanagementsystem.business.ui.account.register.view.RegisterPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
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
    private MainFrameController mainFrameController;

    @Autowired
    public RegisterPanelController(RegisterPanelView registerPanelView, MainFrameController mainFrameController) {
        this.registerPanelView = registerPanelView;
        this.mainFrameController =mainFrameController;
    }

    @Override
    public void prepareAndOpenForm() {
        registerAction(((javax.swing.JButton) registerPanelView.getBackButton()), (event) -> naviagateBack());
        viewPanel();
    }

    private void naviagateBack() {
        mainFrameController.removeFromLayout(registerPanelView);
    }

    private void viewPanel() {
        mainFrameController.addToLayout(registerPanelView);
    }
}
