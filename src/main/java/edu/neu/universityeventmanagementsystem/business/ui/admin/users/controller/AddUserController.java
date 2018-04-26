package edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.CollegesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.RolesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UserAccountsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.AddUserView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@Lazy
public final class AddUserController extends FormController {

    private AddUserView addUserView;
    private UserAccountsService userAccountsService;
    private UsersService usersService;
    private RolesService rolesService;
    private CollegesService collegesService;
    private CurrentUserBean currentUserBean;
    private ProgramsService programsService;
    private CollegesEntity selectedCollege;

    @Autowired
    public AddUserController(AddUserView addUserView, UsersService usersService,
                             CollegesService collegesService, ProgramsService programsService,
                             UserAccountsService userAccountsService, RolesService rolesService,
                             CurrentUserBean currentUserBean) {
        this.addUserView = addUserView;
        this.collegesService = collegesService;
        this.programsService = programsService;
        this.usersService = usersService;
        this.userAccountsService = userAccountsService;
        this.rolesService = rolesService;
        this.currentUserBean =currentUserBean;
    }


    @Override
    public void prepareAndOpenForm() {
        List<String> roles = new ArrayList<>();
        RolesEntity currentUserRole = currentUserBean.getCurrentUser().getRolesByIdRole();
        rolesService.findAll().forEach(rolesEntity -> {
            if (!roles.contains(rolesEntity.getName()) && currentUserRole.getPrivilegeLevel() > rolesEntity.getPrivilegeLevel())
                roles.add(rolesEntity.getName());
        });
        addUserView.populateRoles(roles);

        List<String> colleges = new ArrayList<>();
        collegesService.findAll().forEach(collegesEntity -> colleges.add(collegesEntity.getName()));
        addUserView.populateColleges(colleges);

        registerAction(addUserView.getCollegeComboBox, this::showCorrespondingOptions);
        registerAction(addUserView.getCreateButton(), this::registerUser);
        addUserView.setVisible(true);
    }

    private void showCorrespondingOptions(ActionEvent event) {

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
