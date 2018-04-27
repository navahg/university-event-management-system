package edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.RolesEntity;
import edu.neu.universityeventmanagementsystem.business.service.UsersService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.UsersView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Currency;

/**
 * UsersController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 15, 2018
 */
@Controller
@Lazy
public final class UsersController extends FormController implements InnerViewController {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String STUDENT_ROLE = "STUDENT";
    private static final String FACULTY_ROLE = "FACULTY";
    private static final String OFFICE_STAFFS = "STAFFS";
    private static final String EVENT_MANAGER = "MANAGER";
    private static final String GUEST_ROLE = "GUEST";
    private UsersView usersView;
    private UsersService usersService;
    private AddUserController addUserController;
    private CurrentUserBean currentUserBean;
    private String selectedViewByRole;

    @Autowired
    public UsersController(UsersView usersView, UsersService usersService,
                           AddUserController addUserController, CurrentUserBean currentUserBean) {
        this.usersView = usersView;
        this.usersService = usersService;
        this.addUserController = addUserController;
        this.currentUserBean = currentUserBean;
        selectedViewByRole = ADMIN_ROLE;
    }

    @Override
    public void prepareAndOpenForm() {
        registerEvents();
        usersView.populateTable(usersService.findByRolesLike(selectedViewByRole));
    }

    private void registerEvents() {
        usersView.getPanelButtons().forEach(button -> {
            registerAction(button, this::changeView);
        });
        registerAction(usersView.getAddUserButton(), this::openAddUserForm);
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
            case "Faculties":
                selectedViewByRole = FACULTY_ROLE;
                break;
            case "Office-Staffs":
                selectedViewByRole = OFFICE_STAFFS;
                break;
            case "Event Managers":
                selectedViewByRole = EVENT_MANAGER;
                break;
            case "Guests":
                selectedViewByRole = GUEST_ROLE;
                break;
            default:
        }
        usersView.populateTable(usersService.findByRolesLike(selectedViewByRole));
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return usersView;
    }
}
