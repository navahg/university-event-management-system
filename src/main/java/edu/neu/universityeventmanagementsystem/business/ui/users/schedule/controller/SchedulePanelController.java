package edu.neu.universityeventmanagementsystem.business.ui.users.schedule.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.EventParticipantsService;
import edu.neu.universityeventmanagementsystem.business.service.InvitesService;
import edu.neu.universityeventmanagementsystem.business.service.SchedulesService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.AddOrCreateEventController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import edu.neu.universityeventmanagementsystem.business.ui.users.schedule.view.SchedulePanelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * SchedulePanelController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 19, 2018
 */
@Controller
@Lazy
public final class SchedulePanelController extends FormController implements InnerViewController {

    private SchedulePanelView schedulePanelView;
    private ApplicationContext context;
    private SchedulesService schedulesService;
    private InvitesService invitesService;
    private EventParticipantsService eventParticipantsService;
    private AddOrCreateEventController addOrCreateEventController;

    @Autowired
    public SchedulePanelController(SchedulePanelView schedulePanelView,
                                   ApplicationContext context,
                                   SchedulesService schedulesService,
                                   InvitesService invitesService,
                                   EventParticipantsService eventParticipantsService,
                                   AddOrCreateEventController addOrCreateEventController) {
        this.schedulePanelView = schedulePanelView;
        this.context = context;
        this.schedulesService = schedulesService;
        this.invitesService = invitesService;
        this.eventParticipantsService = eventParticipantsService;
        this.addOrCreateEventController = addOrCreateEventController;
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
                addEventToThePane(event, event.getEventStatusByStatus().getStatusMessage(), SchedulePanelView.HOSTED_EVENTS_PANE);

            if (event.getEndTime().after(new Date()))
                addEventToThePane(event, EventView.EVENT_STATUS_REGISTERED, SchedulePanelView.UPCOMING_EVENTS_PANE);
            else
                addEventToThePane(event, EventView.EVENT_STATUS_PARTICIPATED, SchedulePanelView.PAST_EVENTS_PANE);
        }
    }

    private void addEventToThePane(EventsEntity event, String eventStatus, int where) {
        EventView eventView = context.getBean(EventView.class, event, eventStatus);
        registerMouseClick(eventView.getLblEventName(), this::showEventDetails);
        schedulePanelView.addToPanel(eventView, where);
    }

    private void addInviteToThePane(EventsEntity event) {
        EventView eventView = context.getBean(EventView.class, event, EventView.EVENT_STATUS_REGISTER_NOW);
        registerMouseClick(eventView.getLblEventStatus(), this::registerForEvent);
        registerMouseClick(eventView.getLblEventName(), this::showEventDetails);
        schedulePanelView.addToPanel(eventView, SchedulePanelView.INVITES_PANE);
    }

    private void showEventDetails(MouseEvent event) {
        if (!(event.getSource() instanceof JLabel))
            return;
        if (!(((JLabel) event.getSource()).getParent().getParent() instanceof EventView))
            return;

        EventView selectedEventView = (EventView) ((JLabel) event.getSource()).getParent().getParent();

        addOrCreateEventController.prepareAndOpenForm(selectedEventView.getEvent());
    }

    private void registerForEvent(MouseEvent event) {
        if (!(event.getSource() instanceof JLabel))
            return;

        boolean isRegisterNowLabel = Objects.equals(((JLabel) event.getSource()).getText(), EventView.EVENT_STATUS_REGISTER_NOW);

        if (!isRegisterNowLabel)
            return;

        if (!(((JLabel) event.getSource()).getParent().getParent() instanceof EventView))
            return;

        EventView selectedEventView = (EventView) ((JLabel) event.getSource()).getParent().getParent();

        removeFromInviteAndAddToSchedule(selectedEventView);
    }

    private void removeFromInviteAndAddToSchedule(EventView view) {
        EventsEntity event = view.getEvent();
        UsersEntity user = context.getBean(CurrentUserBean.class).getCurrentUser();

        eventParticipantsService.add(event, user);

        SchedulesEntity newSchedule = schedulesService.create();
        newSchedule.setEventsByIdEvent(event);
        newSchedule.setUsersByIdUser(user);

        schedulesService.save(newSchedule);

        invitesService.deleteByInviteeAndEvent(user, event);

        schedulePanelView.removeFromPanel(view, SchedulePanelView.INVITES_PANE);
        schedulePanelView.addToPanel(view, SchedulePanelView.UPCOMING_EVENTS_PANE);
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return schedulePanelView;
    }
}
