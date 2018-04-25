package edu.neu.universityeventmanagementsystem.business.ui.admin.events.controller;

import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.ManageEventView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * ManageEventController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 22, 2018
 */
@Controller
@Lazy
public class ManageEventController extends FormController {

    private ManageEventView manageEventView;
    private EventsService eventsService;

    @Autowired
    public ManageEventController(ManageEventView manageEventView,
                                 EventsService eventsService) {
        this.manageEventView = manageEventView;
        this.eventsService = eventsService;
    }

    public java.awt.Component getView() {
        prepareAndOpenForm();
        return manageEventView;
    }

    @Override
    public void prepareAndOpenForm() {

    }
}
