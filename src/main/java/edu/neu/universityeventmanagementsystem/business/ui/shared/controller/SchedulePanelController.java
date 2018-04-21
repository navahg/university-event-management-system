package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventEntityView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.SchedulePanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * SchedulePanelController class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 19, 2018
 */
@Controller
@Lazy
public final class SchedulePanelController extends FormController {

    private SchedulePanelView eventsPanelView;
    private ApplicationContext context;

    @Autowired
    public SchedulePanelController(SchedulePanelView eventsPanelView,
                                 ApplicationContext context) {
        this.eventsPanelView = eventsPanelView;
        this.context = context;
    }

    @Override
    public void prepareAndOpenForm() {
        for (int i = 0; i < 10; i++)
            addEventToThePane("test-" + i);
        eventsPanelView.revalidate();
        eventsPanelView.repaint();
    }

    private void addEventToThePane(String type) {
        EventEntityView event = context.getBean(EventEntityView.class, type, "Registered");
        eventsPanelView.addToPanel(event, type);
    }

    public Component getView() {
        prepareAndOpenForm();
        return eventsPanelView;
    }
}
