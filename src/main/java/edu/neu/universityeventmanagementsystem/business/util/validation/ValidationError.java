package edu.neu.universityeventmanagementsystem.business.util.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * ValidationError class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public class ValidationError extends Throwable {
    String message;
    List<String> fields;

    ValidationError(String message, String ...fields) {
        super(message);
        this.message = message;
        this.fields = new ArrayList<>(Arrays.asList(fields));
    }

    ValidationError() {
        this("Validation failed!");
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
