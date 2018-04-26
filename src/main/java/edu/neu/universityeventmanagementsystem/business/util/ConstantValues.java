package edu.neu.universityeventmanagementsystem.business.util;

/**
 * Interface containing constant values for the application.
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since Apr 20, 2018
 */
public interface ConstantValues {

    interface Values {
        Integer PRIVILEGE_LIMIT_FOR_REGISTRATION = 10;
        Integer PRIVILEGE_LIMIT_FOR_EVENT_CREATION_WITHOUT_APPROVAL = 80;
    }

    interface Hierarchy {
        String UNIVERSITY = "university";
        String PROGRAM = "programs";
        String COUNCIL = "councils";
        String ADMIN_WING = "admin_wing";
        String CLUB = "clubs";
        String COLLEGE = "colleges";
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
        String PENDING_APPROVAL = "PENDING_APPROVAL";
        String APPROVED = "APPROVED";
        String COMPLETED = "COMPLETED";
        String CANCELLED = "CANCELLED";
        String EXPIRED = "EXPIRED";
        String[] LEGAL_STATUS_VALUES = new String[]{PENDING_APPROVAL, APPROVED, COMPLETED, CANCELLED, EXPIRED};
    }
}
