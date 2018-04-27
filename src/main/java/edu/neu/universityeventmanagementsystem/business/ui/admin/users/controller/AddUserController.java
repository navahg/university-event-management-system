package edu.neu.universityeventmanagementsystem.business.ui.admin.users.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.admin.users.view.AddUserView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import edu.neu.universityeventmanagementsystem.business.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

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

    private static final String SELECT_COLLEGE = "SELECT A COLLEGE";
    private static final String SELECT_ENTERPRISE = "SELECT AN ENTERPRISE";
    private static final String SELECT_ORGANIZATION = "SELECT AN ORGANIZATION";
    private static final String SELECT_ROLE = "SELECT A ROLE";

    private AddUserView addUserView;
    private UserAccountsService userAccountsService;
    private CollegesService collegesService;
    private ProgramsService programsService;
    private CouncilsService councilsService;
    private AdminWingService adminWingService;
    private CollegeMembersService collegeMembersService;
    private ProgramMembersService programMembersService;
    private CouncilMembersService councilMembersService;
    private AdminWingMembersService adminWingMembersService;
    private HierarchyService hierarchyService;
    private UsersService usersService;
    private RolesService rolesService;
    private CurrentUserBean currentUserBean;
    private FactoryService factoryService;
    private EmailService emailService;

    private CollegesEntity selectedCollege;
    private int selectedRoleLevel;
    private List<String> restrictedToCollege;
    private List<String> restrictedToEnterprise;
    private List<String> restrictedToOrganization;

    @Autowired
    public AddUserController(AddUserView addUserView, UsersService usersService,
                             CollegesService collegesService, ProgramsService programsService,
                             CouncilsService councilsService, AdminWingService adminWingService,
                             CollegeMembersService collegeMembersService, ProgramMembersService programMembersService,
                             CouncilMembersService councilMembersService, AdminWingMembersService adminWingMembersService,
                             HierarchyService hierarchyService, UserAccountsService userAccountsService,
                             RolesService rolesService, CurrentUserBean currentUserBean,
                             FactoryService factoryService, EmailService emailService) {
        this.addUserView = addUserView;
        this.collegesService = collegesService;
        this.programsService = programsService;
        this.councilsService = councilsService;
        this.adminWingService = adminWingService;
        this.collegeMembersService = collegeMembersService;
        this.programMembersService = programMembersService;
        this.councilMembersService = councilMembersService;
        this.adminWingMembersService = adminWingMembersService;
        this.hierarchyService = hierarchyService;
        this.usersService = usersService;
        this.userAccountsService = userAccountsService;
        this.rolesService = rolesService;
        this.currentUserBean =currentUserBean;
        this.factoryService = factoryService;
        this.emailService = emailService;

        restrictedToCollege = new ArrayList<>();
        restrictedToEnterprise = new ArrayList<>();
        restrictedToOrganization = new ArrayList<>();

        selectedRoleLevel = 0;
    }


    @Override
    public void prepareAndOpenForm() {
        restrictedToCollege = new ArrayList<>();
        restrictedToEnterprise = new ArrayList<>();
        restrictedToOrganization = new ArrayList<>();

        List<String> roles = new ArrayList<>();
        RolesEntity currentUserRole = currentUserBean.getCurrentUser().getRolesByIdRole();
        roles.add(SELECT_ROLE);
        rolesService.findAll().forEach(rolesEntity -> {
            if (!roles.contains(rolesEntity.getName()) && currentUserRole.getPrivilegeLevel() > rolesEntity.getPrivilegeLevel())
                roles.add(rolesEntity.getName());
        });
        addUserView.populateRoles(roles);

        registerAction(addUserView.getComboBox(AddUserView.ROLE), this::roleChangeListener);
        registerAction(addUserView.getComboBox(AddUserView.COLLEGE), this::collegeChangeListener);
        registerAction(addUserView.getComboBox(AddUserView.ENTERPRISE), this::enterpriseChangeListener);

        registerAction(addUserView.getCreateButton(), this::registerUser);
        addUserView.setVisible(true);
    }

    private void enterpriseChangeListener(ActionEvent event) {
        String selectedEnterprise = (String) ((JComboBox) event.getSource()).getSelectedItem();
        if (Objects.equals(selectedEnterprise, SELECT_ENTERPRISE) || !restrictedToOrganization.isEmpty())
            return;

        List<String> result = new ArrayList<>();
        result.add(SELECT_ORGANIZATION);
        result.addAll(factoryService.getAllOrganizations(selectedCollege, selectedEnterprise));

        addUserView.populateOrganization(result);
    }

    private void collegeChangeListener(ActionEvent event) {
        String selectedCollegeName = (String) ((JComboBox) event.getSource()).getSelectedItem();
        if (Objects.equals(selectedCollegeName, SELECT_COLLEGE) || !restrictedToEnterprise.isEmpty())
            return;

        selectedCollege = collegesService.findOneByName(selectedCollegeName);

        List<String> result = new ArrayList<>();
        result.add(SELECT_ENTERPRISE);
        result.addAll(ConstantValues.Enterprises.LIST);

        addUserView.populateEnterprise(result);
    }

    private void roleChangeListener(ActionEvent event) {
        String role = (String) ((JComboBox) event.getSource()).getSelectedItem();

        if (Objects.equals(role, SELECT_ROLE))
            return;

        selectedRoleLevel = 0;
        if (ConstantValues.Roles.COLLEGE_LEVEL.contains(role)) {
            addUserView.setCollegeDetailsVisible(true);
            addUserView.setEnterpriseDetailsVisible(false);
            addUserView.setOrganizationDetailsVisible(false);
            selectedRoleLevel = 1;
        } else if (ConstantValues.Roles.ENTERPRISE_LEVEL.contains(role)) {
            addUserView.setCollegeDetailsVisible(true);
            addUserView.setEnterpriseDetailsVisible(true);
            addUserView.setOrganizationDetailsVisible(false);
            selectedRoleLevel = 2;
        } else if (ConstantValues.Roles.PROGRAM_ROLES.contains(role) ||
                   ConstantValues.Roles.ADMINISTRATIVE_ROLES.contains(role) ||
                   ConstantValues.Roles.COUNCIL_ROLES.contains(role)) {
            addUserView.setCollegeDetailsVisible(true);
            addUserView.setEnterpriseDetailsVisible(true);
            addUserView.setOrganizationDetailsVisible(true);
            selectedRoleLevel = 3;
        } else {
            addUserView.setCollegeDetailsVisible(false);
            addUserView.setEnterpriseDetailsVisible(false);
            addUserView.setOrganizationDetailsVisible(false);
        }

        fillInNeededValues();
    }

    private void fillInNeededValues() {
        String currentUserRole = currentUserBean.getCurrentUser().getRolesByIdRole().getName();

        if (restrictedToCollege.isEmpty()) {
            List<String> colleges = new ArrayList<>();
            colleges.add(SELECT_COLLEGE);
            collegesService.findAll().forEach(collegesEntity -> colleges.add(collegesEntity.getName()));
            addUserView.populateColleges(colleges);
        }

        if (ConstantValues.Roles.APPLICATION_LEVEL.contains(currentUserRole)) return;

        String collegeName = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege().getName();
        addUserView.populateColleges(Arrays.asList(SELECT_COLLEGE, collegeName));
        restrictedToCollege = Collections.singletonList(collegeName);

        if (ConstantValues.Roles.COLLEGE_LEVEL.contains(currentUserRole)) return;

        String currentUserHierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy().getTableName();
        addUserView.populateEnterprise(Arrays.asList(SELECT_ENTERPRISE, currentUserHierarchy));
        restrictedToEnterprise = Collections.singletonList(currentUserHierarchy);

        if (ConstantValues.Roles.ENTERPRISE_LEVEL.contains(currentUserRole)) return;

        Map.Entry<String, String> userOrganization = factoryService.findOrganizationNameOfUser(currentUserBean.getCurrentUser());
        String currentUserOrganization = Objects.nonNull(userOrganization) ? userOrganization.getValue() : "";

        addUserView.populateOrganization(Arrays.asList(SELECT_ORGANIZATION, currentUserOrganization));
        restrictedToOrganization = Collections.singletonList(currentUserOrganization);
    }

    private void registerUser(ActionEvent actionEvent) {
        UserAccountsEntity newUserAccount = userAccountsService.create();
        UsersEntity newUser = usersService.create();

        String role = addUserView.getRole();

        RolesEntity selectedRole = getRole(role);
        if (Objects.isNull(selectedRole)) {
            JOptionPane.showMessageDialog(null, "Invalid role given!");
            return;
        }

        newUser.setFirstName(addUserView.getFirstName());
        newUser.setMiddleName(addUserView.getMiddleName());
        newUser.setLastName(addUserView.getLastName());
        newUser.setEmail(addUserView.getEmail());
        newUser.setUserName(addUserView.getUserName());
        newUser.setRolesByIdRole(selectedRole);
        newUser = usersService.save(newUser);

        newUserAccount.setUserName(addUserView.getUserName());
        newUserAccount.setPassword(addUserView.getPassword());
        newUserAccount.setUsersByIdUser(newUser);
        userAccountsService.save(newUserAccount);

        if (selectedRoleLevel > 0) {
            CollegeMembersEntity collegeMembersEntity = collegeMembersService.create();
            collegeMembersEntity.setCollegesByIdCollege(selectedCollege);
            collegeMembersEntity.setUsersByIdUser(newUser);
            collegeMembersService.save(collegeMembersEntity);
        }

        if (selectedRoleLevel > 2) {
            try {
                addToCorrespondingOrganization(newUser);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Unable to map to organization!");
            }
        }

        try {
            sendConfirmationMail(newUser, newUserAccount);
            JOptionPane.showMessageDialog(null, "Account registered successfully and a mail has been sent!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Account registered successfully! But cannot send confirmation mail.");
        }

        addUserView.dispose();
    }

    private void addToCorrespondingOrganization(UsersEntity user) throws NullPointerException {
        String enterprise = addUserView.getComboxBoxValue(AddUserView.ENTERPRISE);
        String organization = addUserView.getComboxBoxValue(AddUserView.ORGANIZATION);

        if (Objects.equals(ConstantValues.Hierarchy.PROGRAM, enterprise)) {
            ProgramsEntity programsEntity = programsService.findByName(organization);

            ProgramMembersEntity programMembersEntity = programMembersService.create();
            programMembersEntity.setProgramsByIdProgram(programsEntity);
            programMembersEntity.setUsersByIdUser(user);

            programMembersService.save(programMembersEntity);
        } else if (Objects.equals(ConstantValues.Hierarchy.COUNCIL, enterprise)) {
            CouncilsEntity councilsEntity = councilsService.findByName(organization);

            CouncilMembersEntity councilMembersEntity = councilMembersService.create();
            councilMembersEntity.setCouncilsByIdCouncil(councilsEntity);
            councilMembersEntity.setUsersByIdUser(user);

            councilMembersService.save(councilMembersEntity);
        } else if (Objects.equals(ConstantValues.Hierarchy.ADMIN_WING, enterprise)) {
            AdminWingEntity adminWingEntity = adminWingService.findByName(organization);

            AdminWingMembersEntity adminWingMembersEntity = adminWingMembersService.create();
            adminWingMembersEntity.setAdminWingByIdAdminWing(adminWingEntity);
            adminWingMembersEntity.setUsersByIdUser(user);

            adminWingMembersService.save(adminWingMembersEntity);
        }
    }

    private RolesEntity getRole(String role) {
        if (ConstantValues.Roles.ENTERPRISE_LEVEL.contains(role) ||
                ConstantValues.Roles.ORGANIZATION_LEVEL.contains(role)) {
            String hierarchy = addUserView.getComboxBoxValue(AddUserView.ENTERPRISE);
            HierarchyEntity hierarchyEntity = hierarchyService.findByTableName(hierarchy);

            for (RolesEntity rolesEntity : rolesService.findAllByName(role)) {
                if (rolesEntity.getHierarchyByIdHierarchy().equals(hierarchyEntity))
                    return rolesEntity;
            }
            return null;
        } else {
            return rolesService.findByName(role);
        }
    }

    private void sendConfirmationMail(UsersEntity user, UserAccountsEntity account) throws MessagingException {
        String subject = ConstantMessages.EmailSubjects.SUCCESSFUL_REGISTRATION;
        String message = String.format(ConstantMessages.EmailBodyTemplates.SUCCESSFUL_REGISTRATION,
                user.getFirstName(), account.getUserName(), account.getPassword());

        emailService.sendEmail(user.getEmail(), subject, message);
    }
}
