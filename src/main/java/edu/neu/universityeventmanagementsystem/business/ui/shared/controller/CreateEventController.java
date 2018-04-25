package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.InvitesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.CreateEventView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.UserView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import edu.neu.universityeventmanagementsystem.business.util.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * CreateEventController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 23, 2018
 */
@Controller
@Lazy
public class CreateEventController extends FormController {

    private CreateEventView createEventView;
    private HierarchyService hierarchyService;
    private UsersService usersService;
    private CurrentUserBean currentUserBean;
    private ArrayList<UsersEntity> invitees;
    private InvitesService invitesService;
    private ApplicationContext context;
    private EventsService eventsService;
    private EventStatusService eventStatusService;

    @Autowired
    public CreateEventController(CreateEventView createEventView,
                                 HierarchyService hierarchyService,
                                 UsersService usersService,
                                 CurrentUserBean currentUserBean,
                                 InvitesService invitesService,
                                 ApplicationContext context,
                                 EventsService eventsService,
                                 EventStatusService eventStatusService) {
        this.createEventView = createEventView;
        this.hierarchyService = hierarchyService;
        this.usersService = usersService;
        this.currentUserBean = currentUserBean;
        this.invitesService = invitesService;
        this.context = context;
        this.eventsService = eventsService;
        this.eventStatusService = eventStatusService;

        invitees = new ArrayList<>();
    }

    @Override
    public void prepareAndOpenForm() {
        populateData(currentUserBean.getCurrentUser());

        registerEvents();
        createEventView.setVisible(true);
    }

    private void registerEvents() {
        registerAction(createEventView.getComboEventLevel(), this::populateEventGroups);
        registerAction(createEventView.getBtnAddInvitee(), this::populateInvitees);
        registerAction(createEventView.getBtnCreate(), this::createEvent);
        registerAction(createEventView.getBtnCancel(), this::close);
    }

    private void close(ActionEvent event) {
        createEventView.dispose();
    }

    private void createEvent(ActionEvent event) {
        EventsEntity newEvent = eventsService.create();

        newEvent.setName(createEventView.getEventName());
        newEvent.setVenue(createEventView.getEventLocation());
        newEvent.setStartTime(new Timestamp(createEventView.getEventStartDate().getTime()));
        newEvent.setEndTime(new Timestamp(createEventView.getEventEndDate().getTime()));
        newEvent.setEventStatusByStatus(eventStatusService.findByStatusMessage("active"));
        newEvent.setHierarchyByIdHierarchy(hierarchyService.findByTableName("university"));

        newEvent.setUsersByIdCreator(currentUserBean.getCurrentUser());

        newEvent = eventsService.save(newEvent);

        sendInvites(newEvent);
    }

    private void sendInvites(EventsEntity event) {
        for (UsersEntity invitee : invitees) {
            InvitesEntity invite = invitesService.create();

            invite.setEventsByIdEvent(event);
            invite.setUsersByIdInvitee(invitee);

            invitesService.save(invite);
        }
    }

    private void populateInvitees(ActionEvent event) {
        String[] inviteesEmails = createEventView.getInviteeList();
        StringBuilder invalidEmails = new StringBuilder();

        for (String email : inviteesEmails) {
            if (Validator.isValidEmail(email)) {
                UsersEntity user = usersService.findByEmail(email);
                if (user != null && !invitees.contains(user)) {
                    invitees.add(user);
                    createEventView.addInviteeView(context.getBean(UserView.class, user));
                } else {
                    invalidEmails.append(email).append("\n");
                }
            } else {
                invalidEmails.append(email).append("\n");
            }
        }
        createEventView.refreshInviteeView();
        createEventView.setTxtAreaInvites(invalidEmails.toString());
    }

    @SuppressWarnings("unchecked")
    private void populateEventGroups(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();

        String selectedLevel = (String) comboBox.getSelectedItem();
    }

    private void populateData(UsersEntity user) {
        int privilegeLevel = user.getRolesByIdRole().getPrivilegeLevel();
        List<String> eventLevels = new ArrayList<>();

        if (privilegeLevel >= ConstantValues.MinimumPrivilegeLevel.ADMIN) {
            hierarchyService.findAll().forEach(hierarchyEntity -> eventLevels.add(hierarchyEntity.getTableName()));
        } else if (privilegeLevel >= ConstantValues.MinimumPrivilegeLevel.FACULTY){
            hierarchyService.findAll().forEach(hierarchyEntity -> eventLevels.add(hierarchyEntity.getTableName()));
        }
    }

}
