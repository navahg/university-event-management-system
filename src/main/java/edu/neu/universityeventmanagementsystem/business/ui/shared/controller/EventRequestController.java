package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventRequestView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * EventRequestController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/25/2018
 */
@Controller
@Lazy
public class EventRequestController extends FormController implements InnerViewController {

    private ApplicationContext context;
    private EventRequestView eventRequestView;
    private EventsEntity event;

    @Autowired
    public EventRequestController(ApplicationContext context) {
        this.context = context;
        event = null;
    }

    @Override
    public void prepareAndOpenForm() {
        if (event != null) {
            UsersEntity currentUser = context.getBean(CurrentUserBean.class).getCurrentUser();
            int roleValue = currentUser.getRolesByIdRole().getPrivilegeLevel() < ConstantValues.MinimumPrivilegeLevel.ADMIN ?
                    EventRequestView.USER_ROLE : EventRequestView.ADMIN_ROLE;

            eventRequestView = context.getBean(EventRequestView.class, event, roleValue);

            EventView eventView = context.getBean(EventView.class, event, event.getEventStatusByStatus().getStatusMessage());
            eventRequestView.setEventView(eventView);

            registerEvents(eventRequestView);
        } else {
            eventRequestView = context.getBean(EventRequestView.class);
        }
    }

    private void registerEvents(EventRequestView eventRequestView) {

    }

    @Override
    public Component getView() {
        prepareAndOpenForm();

        if (eventRequestView != null)
            return eventRequestView;

        return context.getBean(EventRequestView.class);
    }

    public Component getView(EventsEntity event) {
        this.event = event;
        return getView();
    }
}
