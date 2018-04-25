package edu.neu.universityeventmanagementsystem.business.ui.users.events.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.ManageEventView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.users.events.view.EventRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * ManageEventsController class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 25, 2018
 */
@Controller
@Lazy
public class ManageEventsController extends FormController implements InnerViewController {

    private EventsService eventsService;
    private ManageEventView manageEventView;
    private CurrentUserBean currentUserBean;
    private ApplicationContext context;

    @Autowired
    public ManageEventsController(EventsService eventsService,
                                  ManageEventView manageEventView,
                                  CurrentUserBean currentUserBean,
                                  ApplicationContext context) {
        this.eventsService = eventsService;
        this.manageEventView = manageEventView;
        this.currentUserBean = currentUserBean;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        populateEvents();
    }

    private void populateEvents() {
        UsersEntity user = currentUserBean.getCurrentUser();

        for(EventsEntity event : eventsService.findAllByCreator(user)) {
            EventRequestView view = context.getBean(EventRequestView.class, event, EventRequestView.EVENT_CONFIRMATION_PENDING);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return manageEventView;
    }
}
