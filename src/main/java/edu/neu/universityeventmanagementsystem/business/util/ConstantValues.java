package edu.neu.universityeventmanagementsystem.business.util;

/**
 * Interface containing constant values for the application.
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @since Apr 20, 2018
 * @version 1.0
 */
public interface ConstantValues {

    interface Values {
        Integer PRIVILEGE_LIMIT_FOR_REGISTRATION = 10;
    }

    interface MinimumPrivilegeLevel {
        Integer ADMIN = 90;
        Integer STUDENT = 50;
        Integer SPECIAL_STUDENT = 60;
        Integer FACULTY = 70;
        Integer EVENT_MANAGER = 80;
        Integer STRANGER = 0;
    }

    interface EventStatus {
        String CANCELLED = "CANCELLED";
    }
}
