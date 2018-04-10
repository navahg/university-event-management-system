package edu.neu.universityeventmanagementsystem;

import edu.neu.universityeventmanagementsystem.business.ui.main.controller.MainController;
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
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(UniversityEventManagementSystemApplication.class)
                        .headless(false).run(args);
        MainController mainController = context.getBean(MainController.class);
        mainController.openFrame();
    }
}
