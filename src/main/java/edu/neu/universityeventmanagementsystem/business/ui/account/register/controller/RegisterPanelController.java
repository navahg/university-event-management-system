package edu.neu.universityeventmanagementsystem.business.ui.account.register.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.RolesService;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.view.RegisterPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import edu.neu.universityeventmanagementsystem.business.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RegisterPanelController class
 *
 * @author ragha <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/12/2018
 */
@Controller
@Lazy
public final class RegisterPanelController extends FormController {

    private RegisterPanelView registerPanelView;
    private MainFrameController mainFrameController;
    private UserAccountsService userAccountsService;
    private UsersService usersService;
    private RolesService rolesService;
    private EmailService emailService;

    @Autowired
    public RegisterPanelController(RegisterPanelView registerPanelView, MainFrameController mainFrameController,
                                   UserAccountsService userAccountsService, UsersService usersService,
                                   RolesService rolesService, EmailService emailService) {
        this.registerPanelView = registerPanelView;
        this.mainFrameController = mainFrameController;
        this.userAccountsService = userAccountsService;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.emailService = emailService;
    }

    @Override
    public void prepareAndOpenForm() {
        List<String> roles = new ArrayList<>();

        rolesService.findAll().stream()
                .filter(rolesEntity -> rolesEntity.getPrivilegeLevel() < ConstantValues.Values.PRIVILEGE_LIMIT_FOR_REGISTRATION)
                .forEach(filteredRolesEntity -> roles.add(filteredRolesEntity.getName()));

        registerPanelView.populateRoles(roles);
        registerAction(((javax.swing.JButton) registerPanelView.getBackButton()), (event) -> navigateBack());
        registerAction(((javax.swing.JButton) registerPanelView.getRegisterButton()), (event) -> registerUser());
        viewPanel();
    }

    private void navigateBack() {
        mainFrameController.removeFromLayout(registerPanelView);
    }

    private void registerUser() {
        UserAccountsEntity newUserAccount = userAccountsService.create();
        UsersEntity newUser = usersService.create();

        newUser.setFirstName(registerPanelView.getFirstName());
        newUser.setMiddleName(registerPanelView.getMiddleName());
        newUser.setLastName(registerPanelView.getLastName());
        newUser.setEmail(registerPanelView.getEmail());
        newUser.setUserName(registerPanelView.getUserName());
        newUser.setRolesByIdRole(rolesService.findByName(registerPanelView.getRole()));
        newUser = usersService.save(newUser);

        newUserAccount.setUserName(registerPanelView.getUserName());
        newUserAccount.setPassword(registerPanelView.getPassword());
        newUserAccount.setUsersByIdUser(newUser);
        userAccountsService.save(newUserAccount);

        try {
            sendConfirmationMail(newUser, newUserAccount);
            JOptionPane.showMessageDialog(null, "Account registered successfully and a mail has been sent!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Account registered successfully! But cannot send confirmation mail.");
        }

        navigateBack();
    }

    private void sendConfirmationMail(UsersEntity user, UserAccountsEntity account) throws MessagingException {
        String subject = ConstantMessages.EmailSubjects.SUCCESSFUL_REGISTRATION;
        String message = String.format(ConstantMessages.EmailBodyTemplates.SUCCESSFUL_REGISTRATION,
                user.getFirstName(), account.getUserName(), account.getPassword());

        emailService.sendEmail(user.getEmail(), subject, message);
    }

    private void viewPanel() {
        mainFrameController.addToLayout(registerPanelView);
    }
}
