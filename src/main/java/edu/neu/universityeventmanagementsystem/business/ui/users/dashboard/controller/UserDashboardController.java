package edu.neu.universityeventmanagementsystem.business.ui.users.dashboard.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.NotificationController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import edu.neu.universityeventmanagementsystem.business.ui.users.dashboard.view.UserDashboardView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * UserDashboardController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 26, 2018
 */
@Controller
@Lazy
public class UserDashboardController extends FormController implements InnerViewController {

    private UserDashboardView userDashboardView;
    private NotificationsService notificationsService;
    private EventsService eventsService;
    private FactoryService factoryService;
    private HierarchyService hierarchyService;
    private CurrentUserBean currentUserBean;
    private NotificationController notificationController;
    private EventParticipantsService eventParticipantsService;
    private SchedulesService schedulesService;
    private InvitesService invitesService;
    private ApplicationContext context;

    @Autowired
    public UserDashboardController(UserDashboardView userDashboardView,
                                   EventsService eventsService,
                                   NotificationsService notificationsService,
                                   CurrentUserBean currentUserBean,
                                   FactoryService factoryService,
                                   HierarchyService hierarchyService,
                                   NotificationController notificationController,
                                   EventParticipantsService eventParticipantsService,
                                   SchedulesService schedulesService,
                                   InvitesService invitesService,
                                   ApplicationContext context) {
        this.userDashboardView = userDashboardView;
        this.eventsService = eventsService;
        this.notificationsService = notificationsService;
        this.currentUserBean = currentUserBean;
        this.factoryService = factoryService;
        this.hierarchyService = hierarchyService;
        this.notificationController = notificationController;
        this.eventParticipantsService = eventParticipantsService;
        this.schedulesService = schedulesService;
        this.invitesService = invitesService;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        userDashboardView.clearAllPanels();
        populateNotifications();
        populateEvents();
        populateStats();
    }

    private void populateStats() {
        int totalEventsAttended = eventParticipantsService.findByUser(currentUserBean.getCurrentUser()).size();
        int totalEventsHosted = eventsService.findAllByCreator(currentUserBean.getCurrentUser()).size();

        userDashboardView.setTotalEventsAttended(String.valueOf(totalEventsAttended));
        userDashboardView.setTotalEventsHosted(String.valueOf(totalEventsHosted));
    }

    private void populateEvents() {
        Map.Entry<String, Integer> organization = factoryService.findOrganizationOfUser(currentUserBean.getCurrentUser());
        String hierarchy = organization.getKey();
        Integer id = organization.getValue();

        HierarchyEntity hierarchyEntity = hierarchyService.findByTableName(hierarchy);
        HierarchyEntity global = hierarchyService.findByTableName(ConstantValues.Hierarchy.UNIVERSITY);

        List<EventsEntity> eventsList = eventsService.findAllByHierarchyAndIdEntity(global, 0);
        eventsList.addAll(eventsService.findAllByHierarchyAndIdEntity(hierarchyEntity, id));

        for (EventsEntity event : eventsList) {
            String status = event.getEventStatusByStatus().getStatusMessage();

            if (!Objects.equals(status, ConstantValues.EventStatus.APPROVED))
                continue;

            for (EventParticipantsEntity entity : event.getEventParticipantsByIdEvent()) {
                if (entity.getUsersByIdUser().equals(currentUserBean.getCurrentUser())) {
                    if (Objects.equals(ConstantValues.EventStatus.COMPLETED, status))
                        status = EventView.EVENT_STATUS_PARTICIPATED;
                    else
                        status = EventView.EVENT_STATUS_REGISTERED;
                }
            }

            if (Objects.equals(status, ConstantValues.EventStatus.APPROVED))
                status = EventView.EVENT_STATUS_REGISTER_NOW;

            EventView eventView = context.getBean(EventView.class, event, status);
            registerMouseClick(eventView.getLblEventStatus(), this::registerForEvent);
            userDashboardView.addToPanel(eventView, UserDashboardView.EVENTS_PANEL);
        }
    }

    private void registerForEvent(MouseEvent event) {
        if (!(event.getSource() instanceof JLabel))
            return;
        boolean isRegisterNowLabel = Objects.equals(((JLabel) event.getSource()).getText(), EventView.EVENT_STATUS_REGISTER_NOW);
        if (!isRegisterNowLabel)
            return;
        if (!(((JLabel) event.getSource()).getParent().getParent() instanceof EventView))
            return;
        EventView selectedEventView = (EventView) ((JLabel) event.getSource()).getParent().getParent();
        addToEventParticipant(selectedEventView);
        removeFromInviteAndAddToSchedule(selectedEventView);
    }

    private void removeFromInviteAndAddToSchedule(EventView view) {
        EventsEntity event = view.getEvent();
        UsersEntity user = context.getBean(CurrentUserBean.class).getCurrentUser();

        eventParticipantsService.add(event, user);

        SchedulesEntity newSchedule = schedulesService.create();
        newSchedule.setEventsByIdEvent(event);
        newSchedule.setUsersByIdUser(user);

        schedulesService.save(newSchedule);

        invitesService.deleteByInviteeAndEvent(user, event);
    }

    private void addToEventParticipant(EventView view) {
        EventsEntity event = view.getEvent();
        UsersEntity user = context.getBean(CurrentUserBean.class).getCurrentUser();

        eventParticipantsService.add(event, user);
    }

    private void populateNotifications() {
        for (NotificationsEntity notification : notificationsService.findAllByIdUser(currentUserBean.getCurrentUser())) {
            String status = notification.getEventsByIdEvent().getEventStatusByStatus().getStatusMessage();

            if (!Objects.equals(status, ConstantValues.EventStatus.APPROVED))
                continue;

            userDashboardView.addToPanel(notificationController.getView(notification), UserDashboardView.NOTIFICATIONS_PANEL);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return userDashboardView;
    }
}
