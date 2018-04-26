package edu.neu.universityeventmanagementsystem.business.ui.admin.dashboard.controller;

import edu.neu.universityeventmanagementsystem.business.ui.admin.dashboard.view.AdminDashboardView;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.FormController;
import edu.neu.universityeventmanagementsystem.business.ui.shared.controller.InnerViewController;
import java.awt.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AdminDashboardController class
 *
 * @author  Raghavan Renganathan <renganathan.raghavan@gmail.com> <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since   Apr 26, 2018
 */
public class AdminDashboardController extends FormController implements InnerViewController {

    private AdminDashboardView adminDashboardView;
 
    @Autowired
    AdminDashboardController(AdminDashboardView adminDashboardView){
        this.adminDashboardView = adminDashboardView;
    }
    
    @Override
    public void prepareAndOpenForm() {
        
    }
    
    @Override
    public Component getView() {
        prepareAndOpenForm();
        return adminDashboardView;
    }

}
