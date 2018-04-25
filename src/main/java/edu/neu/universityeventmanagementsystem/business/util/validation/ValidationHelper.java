package edu.neu.universityeventmanagementsystem.business.util.validation;

import java.util.List;
import java.util.Objects;

/**
 * ValidationHelper class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public abstract class ValidationHelper {

    boolean isAnyNullOrEmpty(List<String> values) {
        for (String value : values) {
            if (value == null || Objects.equals("", value))
                return true;
        }
        return false;
    }
}
