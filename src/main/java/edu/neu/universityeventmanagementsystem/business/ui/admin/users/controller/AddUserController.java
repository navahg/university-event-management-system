package edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.RolesService;
import edu.neu.universityeventmanagementsystem.business.service.UserAccountsService;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.AddUserView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * AddUserController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 18, 2018
 */
@Controller
public final class AddUserController extends FormController {

    private AddUserView addUserView;
    private UserAccountsService userAccountsService;
    private UsersService usersService;
    private RolesService rolesService;

    @Autowired
    public AddUserController(AddUserView addUserView, UsersService usersService,
                             UserAccountsService userAccountsService, RolesService rolesService) {
        this.addUserView = addUserView;
        this.usersService = usersService;
        this.userAccountsService = userAccountsService;
        this.rolesService = rolesService;
    }


    @Override
    public void prepareAndOpenForm() {
        List<String> roles = new ArrayList<>();
        rolesService.findAll().forEach(rolesEntity -> roles.add(rolesEntity.getName()));
        addUserView.populateRoles(roles);
        registerAction((javax.swing.JButton) addUserView.getCreateButton(), this::registerUser);
        addUserView.setVisible(true);
    }

    private void registerUser(ActionEvent actionEvent) {
        UserAccountsEntity newUserAccount = userAccountsService.create();
        UsersEntity newUser = usersService.create();

        newUser.setFirstName(addUserView.getFirstName());
        newUser.setMiddleName(addUserView.getMiddleName());
        newUser.setLastName(addUserView.getLastName());
        newUser.setEmail(addUserView.getEmail());
        newUser.setUserName(addUserView.getUserName());
        newUser.setRolesByIdRole(rolesService.findByName(addUserView.getRole()));
        newUser = usersService.save(newUser);

        newUserAccount.setUserName(addUserView.getUserName());
        newUserAccount.setPassword(addUserView.getPassword());
        newUserAccount.setUsersByIdUser(newUser);
        userAccountsService.save(newUserAccount);

        JOptionPane.showMessageDialog(null, "Account registered successfully!");

        addUserView.dispose();
    }
}
