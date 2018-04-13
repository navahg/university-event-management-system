package edu.neu.universityeventmanagementsystem;

import edu.neu.universityeventmanagementsystem.business.ui.account.login.controller.LoginPanelController;
import edu.neu.universityeventmanagementsystem.business.ui.loader.controller.LoaderFrameController;
import edu.neu.universityeventmanagementsystem.business.ui.loader.view.LoaderFrameView;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * University Event Management System Application start here
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
@SpringBootApplication
@EnableAutoConfiguration
public class UniversityEventManagementSystemApplication {

    public static void main(String[] args) {
        // Showing loading application
        LoaderFrameController loaderFrameController = new LoaderFrameController(new LoaderFrameView());
        loaderFrameController.prepareAndOpenForm();

        // Starting Spring application
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(UniversityEventManagementSystemApplication.class)
                        .headless(false).run(args);

        // Closing the loader screen
        loaderFrameController.closeFrame();

        // Opening the main application
        LoginPanelController loginPanelController = context.getBean(LoginPanelController.class);
        loginPanelController.prepareAndOpenForm();
    }
}
