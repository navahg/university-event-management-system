package edu.neu.universityeventmanagementsystem.business.validation;

import org.springframework.stereotype.Component;

/**
 * SimpleValidator class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
@Component
public class SimpleValidator extends ValidationHelper {

    public boolean isValidEmail(String email) {
        try {
            validate(email, 0, EMAIL);
        } catch (ValidationError validationError) {
            return false;
        }
        return true;
    }
}
