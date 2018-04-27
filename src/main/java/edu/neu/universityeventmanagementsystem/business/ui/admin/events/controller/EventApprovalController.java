package edu.neu.universityeventmanagementsystem.business.ui.admin.events.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.EventRequestEntity;
import edu.neu.universityeventmanagementsystem.business.entity.HierarchyEntity;
import edu.neu.universityeventmanagementsystem.business.service.EventRequestService;
import edu.neu.universityeventmanagementsystem.business.service.EventStatusService;
import edu.neu.universityeventmanagementsystem.business.service.EventsService;
import edu.neu.universityeventmanagementsystem.business.service.FactoryService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.events.view.EventApprovalView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.EventRequestController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
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

    private EventRequestService eventRequestService;
    private EventApprovalView eventApprovalView;
    private EventRequestController eventRequestController;
    private CurrentUserBean currentUserBean;
    private FactoryService factoryService;

    @Autowired
    public EventApprovalController(EventRequestService eventRequestService,
                                   EventApprovalView eventApprovalView,
                                   EventRequestController eventRequestController,
                                   CurrentUserBean currentUserBean,
                                   FactoryService factoryService) {
        this.eventRequestService = eventRequestService;
        this.eventApprovalView = eventApprovalView;
        this.eventRequestController = eventRequestController;
        this.currentUserBean = currentUserBean;
        this.factoryService = factoryService;
    }

    @Override
    public void prepareAndOpenForm() {
        eventApprovalView.resetPanel();
        populateEvents();
    }

    private void populateEvents() {
        Integer idEntity = factoryService.findOrganizationOfUser(currentUserBean.getCurrentUser()).getValue();
        HierarchyEntity hierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();

        for (EventRequestEntity request : eventRequestService.findAllByHierarchyAndIdEntity(hierarchy, idEntity)) {
            Component view = eventRequestController.getView(request.getEventsByIdEvent());
            eventApprovalView.addToPanel(view);
        }
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return eventApprovalView;
    }
}
