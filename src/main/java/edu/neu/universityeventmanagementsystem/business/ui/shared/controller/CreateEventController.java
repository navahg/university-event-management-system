package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.CreateEventView;
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
    private SchedulesService schedulesService;
    private FactoryService factoryService;
    private SimpleValidator simpleValidator;
    private EventValidator eventValidator;
    private NotificationsService notificationsService;

    @Autowired
    public CreateEventController(CreateEventView createEventView,
                                 HierarchyService hierarchyService,
                                 UsersService usersService,
                                 CurrentUserBean currentUserBean,
                                 InvitesService invitesService,
                                 ApplicationContext context,
                                 EventsService eventsService,
                                 EventStatusService eventStatusService,
                                 SchedulesService schedulesService,
                                 FactoryService factoryService,
                                 SimpleValidator simpleValidator,
                                 EventValidator eventValidator,
                                 NotificationsService notificationsService) {
        this.createEventView = createEventView;
        this.hierarchyService = hierarchyService;
        this.usersService = usersService;
        this.currentUserBean = currentUserBean;
        this.invitesService = invitesService;
        this.context = context;
        this.eventsService = eventsService;
        this.eventStatusService = eventStatusService;
        this.schedulesService = schedulesService;
        this.factoryService = factoryService;
        this.simpleValidator = simpleValidator;
        this.eventValidator = eventValidator;
        this.notificationsService = notificationsService;

        invitees = new ArrayList<>();
    }

    @Override
    public void prepareAndOpenForm() {
        populateData();

        registerEvents();
        createEventView.setVisible(true);
    }

    private void registerEvents() {
        registerAction(createEventView.getComboEventLevel(), this::populateEventGroups);
        registerAction(createEventView.getBtnAddInvitee(), this::populateInvitees);
        registerAction(createEventView.getBtnCreate(), this::createEvent);
        registerAction(createEventView.getBtnCancel(), this::close);
        registerAction(createEventView.getCheckBoxOpenEvent(), this::changeEventType);
    }

    private void changeEventType(ActionEvent event) {
        createEventView.toggleEventType(((JCheckBox) event.getSource()).isSelected());
    }

    private void close(ActionEvent event) {
        createEventView.dispose();
    }

    private void createEvent(ActionEvent event) {
        createEventView.hideAllErrors();

        EventsEntity newEvent = eventsService.create();
        UsersEntity user = currentUserBean.getCurrentUser();
        int privilegeLevel = user.getRolesByIdRole().getPrivilegeLevel();

        newEvent.setName(createEventView.getEventName());
        newEvent.setVenue(createEventView.getEventLocation());
        newEvent.setStartTime(new Timestamp(createEventView.getEventStartDate().getTime()));
        newEvent.setEndTime(new Timestamp(createEventView.getEventEndDate().getTime()));
        newEvent.setUsersByIdCreator(user);
        newEvent.setMaxSeats(createEventView.getMaxSeats());

        if (createEventView.isOpenEvent()) {
            newEvent.setHierarchyByIdHierarchy(hierarchyService.findByTableName(ConstantValues.Hierarchy.UNIVERSITY));
            newEvent.setIdEntity(0);
        } else {
            newEvent.setHierarchyByIdHierarchy(hierarchyService.findByTableName(createEventView.getSelectedEventLevel()));
            newEvent.setIdEntity(factoryService.findEntityId(createEventView.getSelectedEventLevel(), createEventView.getSelectedGroup()));
        }

        if (privilegeLevel >= ConstantValues.Values.PRIVILEGE_LIMIT_FOR_EVENT_CREATION_WITHOUT_APPROVAL) {
            newEvent.setEventStatusByStatus(eventStatusService.findByStatusMessage(ConstantValues.EventStatus.APPROVED));
        } else {
            newEvent.setEventStatusByStatus(eventStatusService.findByStatusMessage(ConstantValues.EventStatus.PENDING_APPROVAL));
        }

        Optional<ValidationError> validationError = eventValidator.validate(newEvent);

        if (validationError.isPresent()) {
            validationError.get().getErroneousFields().forEach(createEventView::showError);
            return;
        }

        newEvent = eventsService.save(newEvent);

        if (createEventView.isSendNotifications())
            sendNotifications(newEvent);
        sendInvites(newEvent);
        addToCreatorsSchedule(newEvent);

        createEventView.dispose();
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
        String[] inviteesEmails = createEventView.getInviteeList();
        StringBuilder invalidEmails = new StringBuilder();

        for (String email : inviteesEmails) {
            if (simpleValidator.isValidEmail(email)) {
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

        List<String> eventGroups = factoryService.getAllNameFromTable(selectedLevel);
        eventGroups.sort(String::compareToIgnoreCase);
        createEventView.setEventGroup(eventGroups);
    }

    private void populateData() {
        List<String> eventLevels = new ArrayList<>();

        for (HierarchyEntity hierarchyEntity : hierarchyService.findAll()) {
            eventLevels.add(hierarchyEntity.getTableName());
        }

        eventLevels.sort(String::compareToIgnoreCase);
        createEventView.setEventLevel(eventLevels);
    }

}
