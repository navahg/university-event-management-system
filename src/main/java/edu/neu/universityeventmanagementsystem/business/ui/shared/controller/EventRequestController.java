package edu.neu.universityeventmanagementsystem.business.ui.shared.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventRequestEntity;
import edu.neu.universityeventmanagementsystem.business.entity.EventsEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.entity.UsersEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventRequestService;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.service.FactoryService;
import edu.neu.universityeventmanagementsystem.business.service.HierarchyService;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventRequestView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.EventView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

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
    private FactoryService factoryService;
    private EventsService eventsService;
    private EventRequestService eventRequestService;
    private HierarchyService hierarchyService;
    private EventRequestView eventRequestView;
    private EventsEntity event;

    @Autowired
    public EventRequestController(ApplicationContext context, EventsService eventsService,
                                  EventRequestService eventRequestService, HierarchyService hierarchyService,
                                  FactoryService factoryService) {
        this.context = context;
        this.eventsService = eventsService;
        this.eventRequestService = eventRequestService;
        this.hierarchyService = hierarchyService;
        this.factoryService = factoryService;
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
        registerAction(eventRequestView.getBtnApproveEvent(), this::approveEvent);
        registerAction(eventRequestView.getBtnRejectEvent(), this::rejectEvent);
        registerAction(eventRequestView.getBtnCancelEvent(), this::cancelEvent);
        registerAction(eventRequestView.getBtnDeleteEvent(), this::deleteEvent);
    }

    private void deleteEvent(ActionEvent actionEvent) {
        eventsService.delete(event);
    }

    private void cancelEvent(ActionEvent actionEvent) {
        eventsService.cancelEvent(event);
    }

    private void rejectEvent(ActionEvent actionEvent) {
        EventRequestEntity request = eventRequestService.findByEvent(event);
        eventsService.rejectEvent(event);
        eventRequestService.delete(request);
    }

    private void approveEvent(ActionEvent actionEvent) {
        if (event == null)
            return;

        UsersEntity currentUser = context.getBean(CurrentUserBean.class).getCurrentUser();

        HierarchyEntity hierarchy = currentUser.getRolesByIdRole().getHierarchyByIdHierarchy();
        Integer idEntity = factoryService.findOrganizationOfUser(currentUser).getValue();

        if (event.getHierarchyByIdHierarchy().equals(hierarchy) && event.getIdEntity() == idEntity) {
            EventRequestEntity request = eventRequestService.findByEvent(event);
            eventsService.approveEvent(event);
            eventRequestService.delete(request);
        } else if (event.getHierarchyByIdHierarchy().equals(hierarchy)){
            EventRequestEntity request = eventRequestService.findByEvent(event);
            request.setIdEntity(event.getIdEntity());

            eventRequestService.save(request);
        } else if (event.getHierarchyByIdHierarchy().getLevel() == hierarchy.getLevel()) {
            EventRequestEntity request = eventRequestService.findByEvent(event);
            request.setHierarchyByIdHierarchy(event.getHierarchyByIdHierarchy());
            request.setIdEntity(event.getIdEntity());
            eventRequestService.save(request);
        } else {
            EventRequestEntity request = eventRequestService.findByEvent(event);
            if (hierarchy.getLevel() == ConstantValues.HierarchyLevels.ORGANIZATION) {
                HierarchyEntity newHierarchy = hierarchyService.findByLevel(hierarchy.getLevel() - 1);
                int newIdEntity = currentUser.getCollegeMembersByIdUser().getCollegesByIdCollege().getIdCollege();

                request.setHierarchyByIdHierarchy(newHierarchy);
                request.setIdEntity(newIdEntity);

                eventRequestService.save(request);
            }
            eventRequestService.save(request);
        }
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
