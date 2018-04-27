package edu.neu.universityeventmanagementsystem.business.ui.admin.dashboard.controller;

import edu.neu.universityeventmanagementsystem.business.beans.CurrentUserBean;
import edu.neu.universityeventmanagementsystem.business.entity.*;
import edu.neu.universityeventmanagementsystem.business.service.*;
import edu.neu.universityeventmanagementsystem.business.ui.admin.dashboard.view.AdminDashboardView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.view.ChartView;
import edu.neu.universityeventmanagementsystem.business.util.ConstantValues;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

/**
 * AdminDashboardController class
 *
 * @author  Raghavan Renganathan <renganathan.raghavan@gmail.com> <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 26, 2018
 */
@Controller
@Lazy
public class AdminDashboardController extends FormController implements InnerViewController {

    private AdminDashboardView adminDashboardView;
    private CurrentUserBean currentUserBean;
    private HierarchyService hierarchyService;
    private EventsService eventsService;
    private CollegesService collegesService;
    private ApplicationContext context;
    private FactoryService factoryService;
    private EventRequestService eventRequestService;

    private int eventsToday;
    private int totalUsers;
    private int eventsPendingApproval;
    private int totalEvents;
 
    @Autowired
    AdminDashboardController(AdminDashboardView adminDashboardView,
                             CurrentUserBean currentUserBean,
                             HierarchyService hierarchyService,
                             EventsService eventsService,
                             CollegesService collegesService,
                             ApplicationContext context,
                             FactoryService factoryService,
                             EventRequestService eventRequestService){
        this.adminDashboardView = adminDashboardView;
        this.currentUserBean = currentUserBean;
        this.hierarchyService = hierarchyService;
        this.eventsService = eventsService;
        this.collegesService = collegesService;
        this.context = context;
        this.factoryService = factoryService;
        this.eventRequestService = eventRequestService;

        totalEvents = 0;
        eventsPendingApproval = 0;
        totalUsers = 0;
        eventsToday = 0;
    }
    
    @Override
    public void prepareAndOpenForm() {
        eventsToday = 0;
        totalEvents = 0;
        eventsPendingApproval = 0;
        totalUsers = 0;
        if (currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel() >=
                ConstantValues.RolePrivilegeLevel.ENTERPRISE_ADMIN) {
            PieDataset dataset = prepareChartData();
            adminDashboardView.addChart(context.getBean(ChartView.class, "Events Distribution", dataset));
        }

        calculateEventStats();
        calculatePendingEvents();
        calculateUsers();
        adminDashboardView.setStats(eventsToday, totalEvents, totalUsers, eventsPendingApproval);
    }

    private void calculateUsers() {
        HierarchyEntity hierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
        if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.UNIVERSITY)) {
            for (CollegesEntity college : collegesService.findAll()) {
                totalUsers += factoryService.getAllUsers(ConstantValues.Hierarchy.COLLEGE, college.getIdCollege()).size();
            }
        } else if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.COLLEGE)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            totalUsers += factoryService.getAllUsers(ConstantValues.Hierarchy.COLLEGE, college.getIdCollege()).size();
        } else if (Objects.equals(currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel(),
                ConstantValues.RolePrivilegeLevel.ENTERPRISE_ADMIN)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            HierarchyEntity h = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
            int id;

            if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.PROGRAM)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.PROGRAM);
                for (ProgramsEntity program : college.getProgramsByIdCollege()) {
                    id = program.getIdProgram();
                    totalUsers += factoryService.getAllUsers(h.getTableName(), id).size();
                }
            } else if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.COUNCIL)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COUNCIL);
                for (CouncilsEntity council : college.getCouncilsByIdCollege()) {
                    id = council.getIdCouncil();
                    totalUsers += factoryService.getAllUsers(h.getTableName(), id).size();
                }
            } else {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.ADMIN_WING);
                for (AdminWingEntity adminWing : college.getAdminWingsByIdCollege()) {
                    id = adminWing.getIdAdminWing();
                    totalUsers += factoryService.getAllUsers(h.getTableName(), id).size();
                }
            }
        } else {
            Map.Entry<String, Integer> result = factoryService.findOrganizationOfUser(currentUserBean.getCurrentUser());
            totalUsers += factoryService.getAllUsers(result.getKey(), result.getValue()).size();
        }
    }

    private void calculatePendingEvents() {
        Integer idEntity = factoryService.findOrganizationOfUser(currentUserBean.getCurrentUser()).getValue();
        HierarchyEntity hierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
        eventsPendingApproval = eventRequestService.findAllByHierarchyAndIdEntity(hierarchy, idEntity).size();
    }

    private void calculateEventStats() {
        HierarchyEntity hierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
        if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.UNIVERSITY)) {
            for (CollegesEntity college : collegesService.findAll()) {
                HierarchyEntity h;
                int id;
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COLLEGE);
                id = college.getIdCollege();
                calculateTotalEventByHierarchyAndEntity(h, id);
            }
        } else if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.COLLEGE)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            HierarchyEntity h;
            int id;
            h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COLLEGE);
            id = college.getIdCollege();
            calculateTotalEventByHierarchyAndEntity(h, id);

        } else if (Objects.equals(currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel(),
                ConstantValues.RolePrivilegeLevel.ENTERPRISE_ADMIN)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            HierarchyEntity h = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
            int id;

            if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.PROGRAM)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.PROGRAM);
                for (ProgramsEntity program : college.getProgramsByIdCollege()) {
                    id = program.getIdProgram();
                    calculateTotalEventByHierarchyAndEntity(h, id);
                }
            } else if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.COUNCIL)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COUNCIL);
                for (CouncilsEntity council : college.getCouncilsByIdCollege()) {
                    id = council.getIdCouncil();
                    calculateTotalEventByHierarchyAndEntity(h, id);
                }
            } else {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.ADMIN_WING);
                for (AdminWingEntity adminWing : college.getAdminWingsByIdCollege()) {
                    id = adminWing.getIdAdminWing();
                    calculateTotalEventByHierarchyAndEntity(h, id);
                }
            }
        } else {
            Map.Entry<String, Integer> result = factoryService.findOrganizationOfUser(currentUserBean.getCurrentUser());
            calculateTotalEventByHierarchyAndEntity(hierarchyService.findByTableName(result.getKey()), result.getValue());
        }
    }

    private void calculateTotalEventByHierarchyAndEntity(HierarchyEntity h, int id) {
        for (EventsEntity events : eventsService.findAllByHierarchyAndIdEntity(h, id)) {
            ++totalEvents;
            Calendar eventStart = Calendar.getInstance();
            eventStart.setTime(events.getStartTime());
            Calendar now = Calendar.getInstance();
            if (eventStart.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))
                ++eventsToday;
        }
    }

    private PieDataset prepareChartData() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        HierarchyEntity hierarchy = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
        if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.UNIVERSITY)) {
            for (CollegesEntity college : collegesService.findAll()) {
                HierarchyEntity h;
                int id;
                int events = 0;
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COLLEGE);
                id = college.getIdCollege();

                events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();

                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COUNCIL);
                for (CouncilsEntity council : college.getCouncilsByIdCollege()) {
                    id = council.getIdCouncil();

                    events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
                }

                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.PROGRAM);
                for (ProgramsEntity program : college.getProgramsByIdCollege()) {
                    id = program.getIdProgram();

                    events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
                }

                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.ADMIN_WING);
                for (AdminWingEntity adminWing : college.getAdminWingsByIdCollege()) {
                    id = adminWing.getIdAdminWing();

                    events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
                }

                dataset.setValue(college.getName(), new Double(events));
            }
        } else if (Objects.equals(hierarchy.getTableName(), ConstantValues.Hierarchy.COLLEGE)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            HierarchyEntity h;
            int id;
            int events = 0;
            h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COUNCIL);
            for (CouncilsEntity council : college.getCouncilsByIdCollege()) {
                id = council.getIdCouncil();

                events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
            }

            dataset.setValue(ConstantValues.Hierarchy.COUNCIL, new Double(events));

            events = 0;
            h = hierarchyService.findByTableName(ConstantValues.Hierarchy.PROGRAM);
            for (ProgramsEntity program : college.getProgramsByIdCollege()) {
                id = program.getIdProgram();

                events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
            }

            dataset.setValue(ConstantValues.Hierarchy.PROGRAM, new Double(events));

            events = 0;
            h = hierarchyService.findByTableName(ConstantValues.Hierarchy.ADMIN_WING);
            for (AdminWingEntity adminWing : college.getAdminWingsByIdCollege()) {
                id = adminWing.getIdAdminWing();

                events += eventsService.findAllByHierarchyAndIdEntity(h, id).size();
            }

            dataset.setValue(ConstantValues.Hierarchy.ADMIN_WING, new Double(events));
        } else if (Objects.equals(currentUserBean.getCurrentUser().getRolesByIdRole().getPrivilegeLevel(),
                ConstantValues.RolePrivilegeLevel.ENTERPRISE_ADMIN)) {
            CollegesEntity college = currentUserBean.getCurrentUser().getCollegeMembersByIdUser().getCollegesByIdCollege();
            HierarchyEntity h = currentUserBean.getCurrentUser().getRolesByIdRole().getHierarchyByIdHierarchy();
            int id;

            if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.PROGRAM)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.PROGRAM);
                for (ProgramsEntity program : college.getProgramsByIdCollege()) {
                    id = program.getIdProgram();
                    dataset.setValue(program.getName(), new Double(eventsService.findAllByHierarchyAndIdEntity(h, id).size()));
                }
            } else if (Objects.equals(h.getTableName(), ConstantValues.Hierarchy.COUNCIL)) {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.COUNCIL);
                for (CouncilsEntity council : college.getCouncilsByIdCollege()) {
                    id = council.getIdCouncil();
                    dataset.setValue(council.getName(), new Double(eventsService.findAllByHierarchyAndIdEntity(h, id).size()));
                }
            } else {
                h = hierarchyService.findByTableName(ConstantValues.Hierarchy.ADMIN_WING);
                for (AdminWingEntity adminWing : college.getAdminWingsByIdCollege()) {
                    id = adminWing.getIdAdminWing();
                    dataset.setValue(adminWing.getName(), new Double(eventsService.findAllByHierarchyAndIdEntity(h, id).size()));
                }
            }
        }
        return dataset;
    }

    @Override
    public Component getView() {
        prepareAndOpenForm();
        return adminDashboardView;
    }

}
