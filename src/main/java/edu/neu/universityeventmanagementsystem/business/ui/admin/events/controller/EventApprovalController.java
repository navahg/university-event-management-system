package edu.neu.universityeventmanagementsystem.business.ui.admin.events.controller;

import edu.neu.universityeventmanagementsystem.business.entity.EventStatusEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventStatusService;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.EventApprovalView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.EventRequestController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * EventApprovalController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 25, 2018
 */
@Controller
@Lazy
public class EventApprovalController extends FormController implements InnerViewController {

    private EventsService eventsService;
    private EventStatusService eventStatusService;
    private EventApprovalView eventApprovalView;
    private EventRequestController eventRequestController;

    @Autowired
    public EventApprovalController(EventsService eventsService,
                                  EventStatusService eventStatusService,
                                  EventApprovalView eventApprovalView,
                                  EventRequestController eventRequestController) {
        this.eventsService = eventsService;
        this.eventStatusService = eventStatusService;
        this.eventApprovalView = eventApprovalView;
        this.eventRequestController = eventRequestController;
    }

    @Override
    public void prepareAndOpenForm() {
        eventApprovalView.resetPanel();
        populateEvents();
    }

    private void populateEvents() {
        EventStatusEntity pendingApprovalStatus = eventStatusService.findByStatusMessage(ConstantValues.EventStatus.PENDING_APPROVAL);
        for (EventsEntity event : eventsService.findAllByEventStatus(pendingApprovalStatus)) {
            Component view = eventRequestController.getView(event);
            eventApprovalView.addToPanel(view);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return eventApprovalView;
    }
}
