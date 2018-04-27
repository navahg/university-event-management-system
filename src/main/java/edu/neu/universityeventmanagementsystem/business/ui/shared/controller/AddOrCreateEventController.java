package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.AddOrCreateEventView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.UserView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantMessages;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import edu.neu.universityeventmanagementsystem.business.validation.EventValidator;
import edu.neu.universityeventmanagementsystem.business.validation.SimpleValidator;
import edu.neu.universityeventmanagementsystem.business.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * CreateEventController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 23, 2018
 */
@Controller
@Lazy
public class AddOrCreateEventController extends FormController {

    private AddOrCreateEventView addOrCreateEventView;
    private HierarchyService hierarchyService;
    private UsersService usersService;
    private CurrentUserBean currentUserBean;
    private ArrayList<UsersEntity> invitees;
    private InvitesService invitesService;
    private ApplicationContext context;
    private EventsService eventsService;
    private EventStatusService eventStatusService;
    private EventParticipantsService eventParticipantsService;
    private SchedulesService schedulesService;
    private FactoryService factoryService;
    private SimpleValidator simpleValidator;
    private EventValidator eventValidator;
    private EventRequestService eventRequestService;
    private NotificationsService notificationsService;

    @Autowired
    public AddOrCreateEventController(AddOrCreateEventView addOrCreateEventView,
                                      HierarchyService hierarchyService,
                                      UsersService usersService,
                                      CurrentUserBean currentUserBean,
                                      InvitesService invitesService,
                                      ApplicationContext context,
                                      EventsService eventsService,
                                      EventStatusService eventStatusService,
                                      EventParticipantsService eventParticipantsService,
                                      SchedulesService schedulesService,
                                      FactoryService factoryService,
                                      SimpleValidator simpleValidator,
                                      EventValidator eventValidator,
                                      EventRequestService eventRequestService,
                                      NotificationsService notificationsService) {
        this.addOrCreateEventView = addOrCreateEventView;
        this.hierarchyService = hierarchyService;
        this.usersService = usersService;
        this.currentUserBean = currentUserBean;
        this.invitesService = invitesService;
        this.context = context;
        this.eventsService = eventsService;
        this.eventStatusService = eventStatusService;
        this.eventParticipantsService = eventParticipantsService;
        this.schedulesService = schedulesService;
        this.factoryService = factoryService;
        this.simpleValidator = simpleValidator;
        this.eventValidator = eventValidator;
        this.eventRequestService = eventRequestService;
        this.notificationsService = notificationsService;

        invitees = new ArrayList<>();
    }

    @Override
    public void prepareAndOpenForm() {
        addOrCreateEventView.makeEditable();
        populateData();

        registerEvents();
        addOrCreateEventView.setVisible(true);
    }

    public void prepareAndOpenForm(EventsEntity eventsEntity) {
        addOrCreateEventView.resetView();
        addOrCreateEventView.makeReadOnly();
        populateData(eventsEntity);
        addOrCreateEventView.setVisible(true);
    }

    private void registerEvents() {
        registerAction(addOrCreateEventView.getComboEventLevel(), this::populateEventGroups);
        registerAction(addOrCreateEventView.getBtnAddInvitee(), this::populateInvitees);
        registerAction(addOrCreateEventView.getBtnCreate(), this::createEvent);
        registerAction(addOrCreateEventView.getBtnCancel(), this::close);
        registerAction(addOrCreateEventView.getCheckBoxOpenEvent(), this::changeEventType);
    }

    private void changeEventType(ActionEvent event) {
        addOrCreateEventView.toggleEventType(((JCheckBox) event.getSource()).isSelected());
    }

    private void close(ActionEvent event) {
        addOrCreateEventView.dispose();
    }

    private void createEvent(ActionEvent event) {
        addOrCreateEventView.hideAllErrors();

        EventsEntity newEvent = eventsService.create();
        UsersEntity user = currentUserBean.getCurrentUser();
        int privilegeLevel = user.getRolesByIdRole().getPrivilegeLevel();

        newEvent.setName(addOrCreateEventView.getEventName());
        newEvent.setVenue(addOrCreateEventView.getEventLocation());
        newEvent.setStartTime(new Timestamp(addOrCreateEventView.getEventStartDate().getTime()));
        newEvent.setEndTime(new Timestamp(addOrCreateEventView.getEventEndDate().getTime()));
        newEvent.setUsersByIdCreator(user);
        newEvent.setMaxSeats(addOrCreateEventView.getMaxSeats());

        if (addOrCreateEventView.isOpenEvent()) {
            newEvent.setHierarchyByIdHierarchy(hierarchyService.findByTableName(ConstantValues.Hierarchy.UNIVERSITY));
            newEvent.setIdEntity(0);
        } else {
            newEvent.setHierarchyByIdHierarchy(hierarchyService.findByTableName(addOrCreateEventView.getSelectedEventLevel()));
            newEvent.setIdEntity(factoryService.findEntityId(addOrCreateEventView.getSelectedEventLevel(), addOrCreateEventView.getSelectedGroup()));
        }

        if (privilegeLevel >= ConstantValues.Values.PRIVILEGE_LIMIT_FOR_EVENT_CREATION_WITHOUT_APPROVAL) {
            newEvent.setEventStatusByStatus(eventStatusService.findByStatusMessage(ConstantValues.EventStatus.APPROVED));
        } else {
            newEvent.setEventStatusByStatus(eventStatusService.findByStatusMessage(ConstantValues.EventStatus.PENDING_APPROVAL));
        }

        Optional<ValidationError> validationError = eventValidator.validate(newEvent);

        if (validationError.isPresent()) {
            validationError.get().getErroneousFields().forEach(addOrCreateEventView::showError);
            return;
        }

        newEvent = eventsService.save(newEvent);

        if (addOrCreateEventView.isSendNotifications())
            sendNotifications(newEvent);
        sendInvites(newEvent);
        addToCreatorsSchedule(newEvent);

        if (privilegeLevel < ConstantValues.Values.PRIVILEGE_LIMIT_FOR_EVENT_CREATION_WITHOUT_APPROVAL)
            addForApproval(newEvent);

        addOrCreateEventView.dispose();
    }

    private void addForApproval(EventsEntity event) {
        UsersEntity creator = event.getUsersByIdCreator();
        EventRequestEntity eventRequest = eventRequestService.create();

        eventRequest.setEventsByIdEvent(event);
        eventRequest.setHierarchyByIdHierarchy(creator.getRolesByIdRole().getHierarchyByIdHierarchy());
        eventRequest.setIdEntity(factoryService.findOrganizationOfUser(creator).getValue());

        eventRequestService.save(eventRequest);
    }

    private void sendNotifications(EventsEntity event) {
        List<UsersEntity> users = factoryService.getAllUsers(event.getHierarchyByIdHierarchy().getTableName(), event.getIdEntity());

        for (UsersEntity user : users) {
            NotificationsEntity notification = notificationsService.create();

            notification.setUsersByIdUser(user);
            notification.setEventsByIdEvent(event);
            notification.setMessage(ConstantMessages.Titles.NEW_EVENT_NOTIFICATION);

            notificationsService.save(notification);
        }
    }

    private void addToCreatorsSchedule(EventsEntity event) {
        if (currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel() <= ConstantValues.MinimumPrivilegeLevel.FACULTY) {
            SchedulesEntity newSchedule = schedulesService.create();

            newSchedule.setUsersByIdUser(currentUserBean.getCurrentUser());
            newSchedule.setEventsByIdEvent(event);

            schedulesService.save(newSchedule);
        }
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
        String[] inviteesEmails = addOrCreateEventView.getInviteeList();
        StringBuilder invalidEmails = new StringBuilder();

        for (String email : inviteesEmails) {
            if (simpleValidator.isValidEmail(email)) {
                UsersEntity user = usersService.findByEmail(email);
                if (user != null && !invitees.contains(user)) {
                    invitees.add(user);
                    addOrCreateEventView.addInviteeView(context.getBean(UserView.class, user));
                } else {
                    invalidEmails.append(email).append("\n");
                }
            } else {
                invalidEmails.append(email).append("\n");
            }
        }
        addOrCreateEventView.refreshInviteeView();
        addOrCreateEventView.setTxtAreaInvites(invalidEmails.toString());
    }

    @SuppressWarnings("unchecked")
    private void populateEventGroups(ActionEvent event) {
        JComboBox<String> comboBox = (JComboBox<String>) event.getSource();

        String selectedLevel = (String) comboBox.getSelectedItem();

        List<String> eventGroups = factoryService.getAllNameFromTable(selectedLevel);
        eventGroups.sort(String::compareToIgnoreCase);
        addOrCreateEventView.setEventGroup(eventGroups);
    }

    private void populateData() {
        List<String> eventLevels = new ArrayList<>();

        for (HierarchyEntity hierarchyEntity : hierarchyService.findAll()) {
            eventLevels.add(hierarchyEntity.getTableName());
        }

        eventLevels.sort(String::compareToIgnoreCase);
        addOrCreateEventView.setEventLevel(eventLevels);
    }

    private void populateData(EventsEntity eventsEntity) {
        addOrCreateEventView.setEventName(eventsEntity.getName());
        addOrCreateEventView.setEventLocation(eventsEntity.getVenue());

        addOrCreateEventView.setEventStartDate(eventsEntity.getStartTime());
        addOrCreateEventView.setEventEndDate(eventsEntity.getEndTime());

        addOrCreateEventView.setCheckBoxOpenEvent(eventsEntity.getHierarchyByIdHierarchy().getLevel() ==
                ConstantValues.HierarchyLevels.UNIVERSITY);

        for (EventParticipantsEntity eventParticipantsEntity : eventParticipantsService.findAllByEvent(eventsEntity)) {
            addOrCreateEventView.addInviteeView(context.getBean(UserView.class, eventParticipantsEntity.getUsersByIdUser()));
        }
    }

}
