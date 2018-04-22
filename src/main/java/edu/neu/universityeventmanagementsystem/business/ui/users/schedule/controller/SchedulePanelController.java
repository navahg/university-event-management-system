package edu.neu.universityeventmanagementsystem.business.ui.users.schedule.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.InvitesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.SchedulesEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.InvitesService;
import edu.neu.universityeventmanagementsystem.business.service.SchedulesService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import edu.neu.universityeventmanagementsystem.business.ui.users.schedule.view.SchedulePanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.Date;
import java.util.List;

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

    private SchedulePanelView schedulePanelView;
    private ApplicationContext context;
    private SchedulesService schedulesService;
    private InvitesService invitesService;

    @Autowired
    public SchedulePanelController(SchedulePanelView schedulePanelView,
                                   ApplicationContext context,
                                   SchedulesService schedulesService,
                                   InvitesService invitesService) {
        this.schedulePanelView = schedulePanelView;
        this.context = context;
        this.schedulesService = schedulesService;
        this.invitesService = invitesService;
    }

    @Override
    public void prepareAndOpenForm() {
        UsersEntity user = context.getBean(CurrentUserBean.class).getCurrentUser();

        List<SchedulesEntity> schedules = schedulesService.findByUser(user);
        List<InvitesEntity> invites = invitesService.findByInvitee(user);

        schedulePanelView.resetPanels(SchedulePanelView.ALL_PANES);
        prepareEvents(schedules);
        prepareInvites(invites);
    }

    private void prepareInvites(List<InvitesEntity> invites) {
        for (InvitesEntity invite : invites) {
            addInviteToThePane(invite.getEventsByIdEvent());
        }
    }

    private void prepareEvents(List<SchedulesEntity> schedules) {
        for (SchedulesEntity schedule : schedules) {
            UsersEntity currentUser = context.getBean(CurrentUserBean.class).getCurrentUser();
            EventsEntity event = schedule.getEventsByIdEvent();

            if (event.getUsersByIdCreator().equals(currentUser))
                addEventToThePane(event, EventView.EVENT_STATUS_HOST, SchedulePanelView.HOSTED_EVENTS_PANE);

            if (event.getStartTime().after(new Date()))
                addEventToThePane(event, EventView.EVENT_STATUS_REGISTERED, SchedulePanelView.UPCOMING_EVENTS_PANE);
            else
                addEventToThePane(event, EventView.EVENT_STATUS_PARTICIPATED, SchedulePanelView.PAST_EVENTS_PANE);
        }
    }

    private void addEventToThePane(EventsEntity event, String eventStatus, int where) {
        EventView eventView = context.getBean(EventView.class,
                event.getName(), eventStatus, event.getStartTime(), event.getVenue());
        schedulePanelView.addToPanel(eventView, where);
    }

    private void addInviteToThePane(EventsEntity event) {
        EventView eventView = context.getBean(EventView.class,
                event.getName(), EventView.EVENT_STATUS_REGISTER_NOW, event.getStartTime(), event.getVenue(),
                String.format("%s, %s", event.getUsersByIdCreator().getLastName(), event.getUsersByIdCreator().getFirstName()));

        schedulePanelView.addToPanel(eventView, SchedulePanelView.INVITES_PANE);
    }

    public Component getView() {
        prepareAndOpenForm();
        return schedulePanelView;
    }
}
