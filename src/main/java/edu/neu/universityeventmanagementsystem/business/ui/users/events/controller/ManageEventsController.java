package edu.neu.universityeventmanagementsystem.business.ui.users.events.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.CreateEventController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.EventRequestController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.users.events.view.ManageEventsView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * ManageEventsController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 25, 2018
 */
@Controller
@Lazy
public class ManageEventsController extends FormController implements InnerViewController {

    private EventsService eventsService;
    private ManageEventsView manageEventsView;
    private CurrentUserBean currentUserBean;
    private EventRequestController eventRequestController;
    private CreateEventController createEventController;

    @Autowired
    public ManageEventsController(EventsService eventsService,
                                  ManageEventsView manageEventsView,
                                  CurrentUserBean currentUserBean,
                                  EventRequestController eventRequestController,
                                  CreateEventController createEventController) {
        this.eventsService = eventsService;
        this.manageEventsView = manageEventsView;
        this.currentUserBean = currentUserBean;
        this.eventRequestController = eventRequestController;
        this.createEventController = createEventController;
    }

    @Override
    public void prepareAndOpenForm() {
        manageEventsView.resetPanels(ManageEventsView.ALL_PANELS);
        populateEvents();
        registerAction(manageEventsView.getBtnAddEvent(), this::showAddEventForm);
    }

    private void showAddEventForm(ActionEvent event) {
        createEventController.prepareAndOpenForm();
    }

    private void populateEvents() {
        UsersEntity user = currentUserBean.getCurrentUser();

        for (EventsEntity event : eventsService.findAllByCreator(user)) {
            Component view = eventRequestController.getView(event);
            EventStatusEntity status = event.getEventStatusByStatus();
            if (Objects.equals(status.getStatusMessage(), ConstantValues.EventStatus.PENDING_APPROVAL) ||
                    Objects.equals(status.getStatusMessage(), ConstantValues.EventStatus.EXPIRED))
                manageEventsView.addToPanel(view, ManageEventsView.UNAPPROVED_EVENTS_PANEL);
            else
                manageEventsView.addToPanel(view, ManageEventsView.APPROVED_EVENTS_PANEL);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return manageEventsView;
    }
}
