package edu.neu.universityeventmanagementsystem.business.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormatTools class
 *
 * @author Raghavan Renganathan <renganathan.r@husky.neu.edu>
 * @version 1.0
 * @since 4/26/2018
 */
public class DateFormatTools {
    public static String formatDate(Date date) {
        DateFormat df;
        try {
            if (date.after((new SimpleDateFormat("dd-MM-yyyy")).parse("01-01-2018")))
                df = new SimpleDateFormat("MMM, dd");
            else
                df = new SimpleDateFormat("MMM, dd yyyy");
        } catch (ParseException e) {
            df = new SimpleDateFormat("MMM, dd yyyy");
        }
        return df.format(date);
    }
}
