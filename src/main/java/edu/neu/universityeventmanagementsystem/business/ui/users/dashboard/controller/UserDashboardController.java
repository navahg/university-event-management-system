package edu.neu.universityeventmanagementsystem.business.ui.users.dashboard.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventParticipantsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.entity.NotificationsEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.service.FactoryService;
import edu.neu.universityeventmanagementsystem.business.service.HierarchyService;
import edu.neu.universityeventmanagementsystem.business.service.NotificationsService;
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

import java.awt.*;
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
    private ApplicationContext context;

    @Autowired
    public UserDashboardController(UserDashboardView userDashboardView,
                                   EventsService eventsService,
                                   NotificationsService notificationsService,
                                   CurrentUserBean currentUserBean,
                                   FactoryService factoryService,
                                   HierarchyService hierarchyService,
                                   NotificationController notificationController,
                                   ApplicationContext context) {
        this.userDashboardView = userDashboardView;
        this.eventsService = eventsService;
        this.notificationsService = notificationsService;
        this.currentUserBean = currentUserBean;
        this.factoryService = factoryService;
        this.hierarchyService = hierarchyService;
        this.notificationController = notificationController;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        userDashboardView.clearAllPanels();
        populateNotifications();
        populateEvents();
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

            if (Objects.equals(status, ConstantValues.EventStatus.PENDING_APPROVAL))
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
            userDashboardView.addToPanel(eventView, UserDashboardView.EVENTS_PANEL);
        }
    }

    private void populateNotifications() {
        for (NotificationsEntity notification : notificationsService.findAllByIdUser(currentUserBean.getCurrentUser())) {
            String status = notification.getEventsByIdEvent().getEventStatusByStatus().getStatusMessage();

            if (Objects.equals(status, ConstantValues.EventStatus.PENDING_APPROVAL))
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
