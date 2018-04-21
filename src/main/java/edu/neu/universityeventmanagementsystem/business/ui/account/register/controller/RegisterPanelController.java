package edu.neu.universityeventmanagementsystem.business.ui.account.register.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.RolesService;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.account.register.view.RegisterPanelView;
import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

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

    @Autowired
    public RegisterPanelController(RegisterPanelView registerPanelView, MainFrameController mainFrameController,
                                   UserAccountsService userAccountsService, UsersService usersService,
                                   RolesService rolesService) {
        this.registerPanelView = registerPanelView;
        this.mainFrameController = mainFrameController;
        this.userAccountsService = userAccountsService;
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @Override
    public void prepareAndOpenForm() {
        List<String> roles = new ArrayList<>();

        rolesService.findAll().stream()
                .filter(rolesEntity -> rolesEntity.getPrivilegeLevel() < ConstantValues.Values.PRIVILEGE_LIMIT_FOR_REGISTRATION)
                .forEach(filteredRolesEntity -> roles.add(filteredRolesEntity.getName()));

        registerPanelView.populateRoles(roles);
        registerAction(((javax.swing.JButton) registerPanelView.getBackButton()), (event) -> naviagateBack());
        registerAction(((javax.swing.JButton) registerPanelView.getRegisterButton()), (event) -> registerUser());
        viewPanel();
    }

    private void naviagateBack() {
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

        JOptionPane.showMessageDialog(null, "Account registered successfully!");

        naviagateBack();
    }

    private void viewPanel() {
        mainFrameController.addToLayout(registerPanelView);
    }
}
