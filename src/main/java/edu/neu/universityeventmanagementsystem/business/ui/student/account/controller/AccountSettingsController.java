package edu.neu.universityeventmanagementsystem.business.ui.student.account.controller;

import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.student.account.view.AccountSettingsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * AccountSettingsController class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 20, 2018
 */
@Controller
public final class AccountSettingsController extends FormController {

    private AccountSettingsView accountSettingsView;

    @Autowired
    public AccountSettingsController(AccountSettingsView accountSettingsView) {
        this.accountSettingsView = accountSettingsView;
    }

    @Override
    public void prepareAndOpenForm() {
//        accountSettingsView.fillDetails();
    }

    public Component getView() {
        prepareAndOpenForm();
        return accountSettingsView;
    }
}
