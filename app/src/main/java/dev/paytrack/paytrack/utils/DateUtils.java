package dev.paytrack.paytrack.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public class DateUtils {

    /**
     * Parse an string to Date format
     * @param dateInString Date in 'YYYY-MM-DD' String format
     * @return The parsed date
     */
    public static Date parseStringToDate(String dateInString) {
        Date parsedDate = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            parsedDate = simpleDateFormat.parse(dateInString);
        } catch (ParseException e) {
            Log.e("ParseException", "Error while parsing string to Date");
        }

        return parsedDate;
    }

    public static boolean betweenTwoDates(Date date, Date startDate, Date endDate) {
        return (startDate.before(date) && endDate.after(endDate));
    }
}
