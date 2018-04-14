package edu.neu.universityeventmanagementsystem.business.ui.admin.infrastructure.controller;

import edu.neu.universityeventmanagementsystem.business.entity.CollegesEntity;
import edu.neu.universityeventmanagementsystem.business.service.CollegesService;
import edu.neu.universityeventmanagementsystem.business.ui.admin.infrastructure.view.InfrastructureView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * InfrastructureController class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/13/2018
 */
@Controller
public class InfrastructureController extends FormController {

    private InfrastructureView infrastructureView;
    private CollegesService collegesService;

    @Autowired
    public InfrastructureController(InfrastructureView infrastructureView, CollegesService collegesService) {
        this.infrastructureView = infrastructureView;
        this.collegesService = collegesService;
    }

    @Override
    public void prepareAndOpenForm() {
        registerEvents();
        List<String> colleges = new ArrayList<>();
        collegesService.findAll().forEach((collegesEntity -> colleges.add(collegesEntity.getName())));
        infrastructureView.populateLists(colleges, InfrastructureView.MAIN_LIST_INDEX);
    }

    public Component getView() {
        prepareAndOpenForm();
        return infrastructureView;
    }

    private void registerEvents() {
        registerAction((javax.swing.JList)infrastructureView.getListComponent(InfrastructureView.MAIN_LIST_INDEX),
                this::populatePrograms);
    }

    private void populatePrograms(javax.swing.event.ListSelectionEvent event) {
        JList list = (JList) event.getSource();

        String selected = list.getSelectedValue().toString();

        CollegesEntity college = collegesService.findOneByName(selected);
        if (college == null) return;

        List<String> programs = new ArrayList<>();
        college.getProgramsByIdCollege().forEach((programsEntity -> programs.add(programsEntity.getName())));
        infrastructureView.populateLists(programs, InfrastructureView.SUB_LIST_INDEX);
    }
}
