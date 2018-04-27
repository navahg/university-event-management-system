package edu.neu.universityeventmanagementsystem.business.util;

/**
 * Interface containing constant strings for the application.
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 10, 2018
 */
public interface ConstantMessages {

    interface Titles {
        String APPLICATION_TITLE = "University Event Management System";
        String LOADER_TITLE = "Loading application...";
        String ADD_USER_TITLE = "Add new user";
        String ADD_EVENT_TITLE = "Create new event";

        String ADMIN_DASHBOARD = "Dashboard";
        String ADMIN_INFRASTRUCTURE = "List of Infrastructures";
        String ADMIN_USERS = "List of Users";
        String ADMIN_EVENTS = "Manage Events";

        String STUDENT_DASHBOARD = "Dashboard";
        String STUDENT_SCHEDULE = "Schedule of Events";
        String STUDENT_EVENTS = "Manage Events";
        String STUDENT_ACCOUNT_SETTINGS = "Account Settings";

        String NEW_EVENT_NOTIFICATION = "New Event Added.";
    }

    interface EmailSubjects {
        String SUCCESSFUL_REGISTRATION = "Registration Successful.";
        String EVENT_INIVITE = "You have been invited to an event.";
    }

    interface EmailBodyTemplates {
        String SUCCESSFUL_REGISTRATION = "Hi %s,\n\n" +
                                         "Congratulations!\n\n" +
                                         "You have been successfully registered into Northeastern University Event Management System.\n" +
                                         "Below are your login credentials:\n" +
                                         "Username: %s\n" +
                                         "Password: %s\n\n" +
                                         "Thanks,\nEvent Management Team,\nNortheastern University";
        String EVENT_INVITE = "Hello!\n\n" +
                              "You have been invited to an event at Northeastern University! Below are the details:\n\n" +
                              "Event Name: %s\n" +
                              "Event Venue: %s\n" +
                              "Event Date: %s\n" +
                              "Invited by: %s\n\n" +
                              "Thanks,\nEvent Management Team,\nNortheastern University";
    }
}
