package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventsPanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * EventsPanelController class
 *
 * @author  Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 19, 2018
 */
@Controller
public final class EventsPanelController extends FormController {

    private EventsPanelView eventsPanelView;

    @Autowired
    public EventsPanelController(EventsPanelView eventsPanelView) {
        this.eventsPanelView = eventsPanelView;
    }

    @Override
    public void prepareAndOpenForm() {

    }

    public Component getView() {
        prepareAndOpenForm();
        return eventsPanelView;
    }
}
