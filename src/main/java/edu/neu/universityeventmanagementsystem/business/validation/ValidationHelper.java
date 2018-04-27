package edu.neu.universityeventmanagementsystem.business.validation;

import java.util.Date;

/**
 * ValidationHelper class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/24/2018
 */
public abstract class ValidationHelper {

    static final int NOT_EMPTY = 0;
    static final int NOT_NULL = 1;
    static final int ALPHANUMERIC = 2;
    static final int ALPHABETS = 3;
    static final int EMAIL = 4;
    static final int LEGAL_DATE_RANGE = 5;
    static final int PASSWORD = 6;



    static void validate(Object o, int errorIndicatorIndex, int... rules) throws ValidationError {
        String value;
        if (o instanceof String)
            value = (String) o;
        else
            value = o.toString();

        for (int s : rules) {
            String temp;
            boolean check;
            switch (s) {
                case NOT_EMPTY:
                    if ("".equals(value)) {
                        throw new ValidationError("empty!", errorIndicatorIndex);
                    }
                    break;
                case NOT_NULL:
                    if (value == null)
                        throw new ValidationError("null", errorIndicatorIndex);
                    break;
                case ALPHANUMERIC:
                    temp = value.replaceAll("[A-Za-z0-9\\s]", "");
                    if (!"".equals(temp)) {
                        throw new ValidationError("not alpha-numeric!", errorIndicatorIndex);
                    }
                    break;
                case ALPHABETS:
                    temp = value.replaceAll("[A-Za-z]", "");
                    if (!"".equals(temp)) {
                        throw new ValidationError("not alphabets!", errorIndicatorIndex);
                    }
                    break;
                case EMAIL:
                    check = value.contains("@")
                            && (value.contains(".com")
                            || value.contains(".edu")
                            || value.contains(".org")
                            || value.contains(".co.in"));
                    if (!check) {
                        throw new ValidationError("not valid email!", errorIndicatorIndex);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Rule not recognized " + s);
            }
        }
    }

    static void validate(Date start, Date end, int errorIndicatorIndex, int... rules) throws ValidationError {
        for (int rule : rules) {
            switch (rule) {
                case LEGAL_DATE_RANGE:
                    if (start == null || end == null)
                        throw new ValidationError("Both dates should be non-null", errorIndicatorIndex);
                    if (start.after(end) || start.equals(end))
                        throw new ValidationError("Illegal date range", errorIndicatorIndex);
                    break;
            }
        }
    }
    
    static void validate(String password, String confirm_password, int errorIndicatorIndex, int... rules) throws ValidationError {
        for (int rule : rules) {
            switch (rule) {
                case PASSWORD:
                    if (password == null || confirm_password == null)
                        throw new ValidationError("Password should be non-null", errorIndicatorIndex);
                    if (!password.equals(confirm_password))
                        throw new ValidationError("Password doesn't match", errorIndicatorIndex);
                    break;
            }
        }
    }

}
