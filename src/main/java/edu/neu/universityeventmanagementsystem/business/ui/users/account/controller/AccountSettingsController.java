package edu.neu.universityeventmanagementsystem.business.ui.users.account.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.users.account.view.AccountSettingsView;
import edu.neu.universityeventmanagementsystem.business.validation.AccountSettingsFormValidator;
import edu.neu.universityeventmanagementsystem.business.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Optional;

/**
 * AccountSettingsController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 20, 2018
 */
@Controller
@Lazy
public final class AccountSettingsController extends FormController implements InnerViewController {

    private AccountSettingsView accountSettingsView;
    private UserAccountsService userAccountsService;
    private UsersService usersService;
    private AccountSettingsFormValidator accountSettingsFormValidator;
    private CurrentUserBean currentUserBean;

    @Autowired
    public AccountSettingsController(AccountSettingsView accountSettingsView,
                                     UserAccountsService userAccountsService,
                                     UsersService usersService,
                                     AccountSettingsFormValidator accountSettingsFormValidator,
                                     CurrentUserBean currentUserBean) {
        this.accountSettingsView = accountSettingsView;
        this.userAccountsService = userAccountsService;
        this.usersService = usersService;
        this.accountSettingsFormValidator = accountSettingsFormValidator;
        this.currentUserBean = currentUserBean;
    }

    @Override
    public void prepareAndOpenForm() {
        accountSettingsView.setCurrentUser(currentUserBean.getCurrentUser());
        accountSettingsView.fillDetails();
        registerEvents();
    }

    private void registerEvents() {
        registerAction(accountSettingsView.getBtnEdit(), event -> accountSettingsView.makeFieldsEditable());
        registerAction(accountSettingsView.getBtnChangePassword(), event -> accountSettingsView.toggleChangePasswordView(true));
        registerAction(accountSettingsView.getBtnCancelPassword(), event -> accountSettingsView.toggleChangePasswordView(false));
        registerAction(accountSettingsView.getBtnSave(), this::saveDetails);
        registerAction(accountSettingsView.getBtnSavePassword(), this::savePassword);
    }

    private void savePassword(ActionEvent event) {
        accountSettingsView.hideErrors();

        String password = accountSettingsView.getOldPassword();
        String username = currentUserBean.getCurrentUser().getUserName();

        UserAccountsEntity accountsEntity = userAccountsService.findOne(username, password);

        if (accountsEntity == null) {
            accountSettingsView.showError(AccountSettingsView.PASSWORD_ERROR);
            return;
        }

        if (!Objects.equals(accountSettingsView.getNewPassword(), accountSettingsView.getConfirmPassword()) ||
                Objects.equals(accountSettingsView.getNewPassword(), "")) {
            accountSettingsView.showError(AccountSettingsView.PASSWORD_NO_MATCH_ERROR);
            return;
        }

        accountsEntity.setPassword(accountSettingsView.getNewPassword());

        userAccountsService.save(accountsEntity);

        accountSettingsView.toggleChangePasswordView(false);
    }

    private void saveDetails(ActionEvent event) {
        accountSettingsView.hideErrors();

        Optional<ValidationError> result = accountSettingsFormValidator.validate(accountSettingsView);

        if (result.isPresent()) {
            for (Integer which : result.get().getErroneousFields()) {
                accountSettingsView.showError(which);
            }
            return;
        }

        String firstName = accountSettingsView.getFirstName();
        String middleName = accountSettingsView.getMiddleName();
        String lastName = accountSettingsView.getLastName();

        String email = accountSettingsView.getEmail();

        UsersEntity user = currentUserBean.getCurrentUser();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);

        usersService.save(user);

        accountSettingsView.makeFieldsReadOnly();
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return accountSettingsView;
    }
}
