package edu.neu.universityeventmanagementsystem.business.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ValidationError class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public class ValidationError extends Throwable {
    private String message;
    private List<Integer> erroneousFields;

    ValidationError(String message, Integer... erroneousFields) {
        super(message);
        this.message = message;
        this.erroneousFields = new ArrayList<>(Arrays.asList(erroneousFields));
    }

    ValidationError() {
        this("Validation failed!");
    }

    public Iterable<Integer> getErroneousFields() {
        return erroneousFields;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
