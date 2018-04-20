package edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller;

import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.UsersView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * UsersController class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 15, 2018
 */
@Controller
public final class UsersController extends FormController {

    private UsersView usersView;
    private UsersService usersService;
    private AddUserController addUserController;
    private String selectedViewByRole;
    private static final String ADMIN_ROLE = "SYSTEM_ADMIN";
    private static final String STUDENT_ROLE = "STUDENT";
    private static final String GUEST_ROLE = "GUEST";

    @Autowired
    public UsersController(UsersView usersView, UsersService usersService, AddUserController addUserController) {
        this.usersView = usersView;
        this.usersService = usersService;
        this.addUserController = addUserController;
        selectedViewByRole = STUDENT_ROLE;
    }

    @Override
    public void prepareAndOpenForm() {
        registerEvents();
    }

    private void registerEvents() {
        usersView.getPanelButtons().forEach(button -> {
            registerAction((javax.swing.JButton) button, this::changeView);
        });
        registerAction((javax.swing.JButton) usersView.getAddUserButton(), this::openAddUserForm);
    }

    private void openAddUserForm(ActionEvent event) {
        addUserController.prepareAndOpenForm();
    }

    private void changeView(ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();
        usersView.setActiveButton((javax.swing.JButton) event.getSource());

        switch (view) {
            case "Admins":
                selectedViewByRole = ADMIN_ROLE;
                break;
            case "Students":
                selectedViewByRole = STUDENT_ROLE;
                break;
            case "Guests":
                selectedViewByRole = GUEST_ROLE;
                break;
            default:
        }
        usersView.populateTable(usersService.findByRole(selectedViewByRole));
    }

    public Component getView() {
        prepareAndOpenForm();
        return usersView;
    }
}
