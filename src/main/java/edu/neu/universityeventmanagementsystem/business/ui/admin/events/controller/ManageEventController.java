package edu.neu.universityeventmanagementsystem.business.ui.admin.events.controller;

import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.ManageEventView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * ManageEventController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 22, 2018
 */
@Controller
@Lazy
public class ManageEventController extends FormController implements InnerViewController {

    private ManageEventView manageEventView;
    private EventsService eventsService;
    private ApplicationContext context;

    @Autowired
    public ManageEventController(ManageEventView manageEventView,
                                 EventsService eventsService,
                                 ApplicationContext context) {
        this.manageEventView = manageEventView;
        this.eventsService = eventsService;
        this.context = context;
    }

    @Override
    public java.awt.Component getView() {
        prepareAndOpenForm();
        return manageEventView;
    }

    @Override
    public void prepareAndOpenForm() {
        manageEventView.resetPanels(ManageEventView.ALL_PANELS);
        populateEvents();
    }

    private void populateEvents() {
        for (EventsEntity eventsEntity : eventsService.findAll()) {
            if (eventsEntity.getStartTime().before(new Date())) {
                addEventToThePane(eventsEntity, ManageEventView.PAST_EVENTS_PANEL);
            } else {
                addEventToThePane(eventsEntity, ManageEventView.UPCOMING_EVENTS_PANEL);
            }
        }
    }

    private void addEventToThePane(EventsEntity event, int where) {
        EventView eventView = context.getBean(EventView.class, event, event.getEventStatusByStatus().getStatusMessage());
        manageEventView.addToPanel(eventView, where);
    }
}
