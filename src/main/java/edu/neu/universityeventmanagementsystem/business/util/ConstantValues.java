package edu.neu.universityeventmanagementsystem.business.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    interface Enterprises {
        List<String> LIST = Collections.unmodifiableList(Arrays.asList(Hierarchy.PROGRAM, Hierarchy.COUNCIL, Hierarchy.ADMIN_WING));
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

    interface RolePrivilegeLevel {
        Integer SYSTEM_ADMIN = 100;
        Integer NETWORK_ADMIN = 99;
        Integer ENTERPRISE_ADMIN = 95;
        Integer ORGANIZATION_ADMIN = 90;
        Integer EVENT_MANAGER = 80;
        Integer OFFICE_STAFF = 70;
        Integer FACULTY = 70;
        Integer SPECIAL_STUDENT = 60;
        Integer STUDENT = 50;
        Integer GUEST = 9;
    }

    interface Roles {
        List<String> APPLICATION_LEVEL = Collections.singletonList("SYSTEM_ADMIN");
        List<String> COLLEGE_LEVEL = Collections.singletonList("NETWORK_ADMIN");
        List<String> ENTERPRISE_LEVEL = Collections.singletonList("ENTERPRISE_ADMIN");
        List<String> ORGANIZATION_LEVEL = Collections.singletonList("ORGANIZATION_ADMIN");
        List<String> PROGRAM_ROLES = Arrays.asList("STUDENT", "FACULTY", "SPECIAL_STUDENT", "ORGANIZATION_ADMIN", "EVENT_MANAGER");
        List<String> ADMINISTRATIVE_ROLES = Arrays.asList("OFFICE_STAFF", "ORGANIZATION_ADMIN", "EVENT_MANAGER");
        List<String> COUNCIL_ROLES = Arrays.asList("STUDENT", "SPECIAL_STUDENT", "ORGANIZATION_ADMIN", "EVENT_MANAGER");
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
