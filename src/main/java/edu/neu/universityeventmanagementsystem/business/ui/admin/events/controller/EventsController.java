package edu.neu.universityeventmanagementsystem.business.ui.admin.events.controller;

import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.EventsView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.CreateEventController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * EventsController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 22, 2018
 */
@Controller
@Lazy
public class EventsController extends FormController implements InnerViewController {

    private static final String MANAGE_EVENTS = "Manage";
    private static final String APPROVAL = "Approval Requests";

    private String currentView;

    private EventsView eventsView;
    private ApplicationContext context;
    private CreateEventController createEventController;

    @Autowired
    public EventsController(EventsView eventsView,
                            ApplicationContext context,
                            CreateEventController createEventController) {
        currentView = MANAGE_EVENTS;

        this.eventsView = eventsView;
        this.context = context;
        this.createEventController = createEventController;
    }

    @Override
    public void prepareAndOpenForm() {
        renderView();
        registerEvents();
    }

    private void renderView() {
        if (Objects.equals(currentView, APPROVAL))
            eventsView.setContent(null);
        else
            eventsView.setContent(context.getBean(ManageEventController.class).getView());
    }

    private void registerEvents() {
        eventsView.getPanelButtons().forEach(component -> {
            registerAction((javax.swing.JButton) component, this::changeView);
        });
        registerAction((javax.swing.JButton) eventsView.getAddEventButton(), this::showAddEventForm);
    }

    private void showAddEventForm(ActionEvent event) {
        createEventController.prepareAndOpenForm();
    }

    private void changeView(ActionEvent event) {
        String view = ((javax.swing.JButton) event.getSource()).getText();
        eventsView.setActiveButton((javax.swing.JButton) event.getSource());

        currentView = view;

        renderView();
    }

    @Override
    public java.awt.Component getView() {
        prepareAndOpenForm();
        return eventsView;
    }
}
