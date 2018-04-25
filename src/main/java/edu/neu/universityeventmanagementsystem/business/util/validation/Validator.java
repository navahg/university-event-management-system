package edu.neu.universityeventmanagementsystem.business.util.validation;

/**
 * Validator class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public class Validator {

    public static boolean isValidEmail(String email) {
        String[] emailSplit = email.split("@");
        if (emailSplit.length != 2) return false;

        String domain = emailSplit[1];

        return domain.contains(".") &&
                (domain.contains("edu") ||
                        domain.contains("com") ||
                        domain.contains("org"));

    }
}
