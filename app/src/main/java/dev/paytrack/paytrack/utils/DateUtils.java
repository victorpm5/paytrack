package dev.paytrack.paytrack.utils;

import android.util.Log;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("en_EN"));
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
  
    /**
     * Parse a date picker to String format
     * @param datePicker Date in 'YYYY-MM-DD' String format
     * @return The parsed string in 'YYYY-MM-DD' String format
     */
    public static String parseDatePickerToString(DatePicker datePicker) {
        String parsedString;

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(0);
//        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
//        Log.v("DEBUG-DATE", datePicker.getDayOfMonth() + "");
//        calendar.set(Calendar.MONTH, datePicker.getMonth());
//        Log.v("DEBUG-DATE", datePicker.getMonth() + "");
//        calendar.set(Calendar.YEAR, datePicker.getYear());
//        Log.v("DEBUG-DATE", datePicker.getYear() + "");

        String day = String.valueOf(datePicker.getDayOfMonth());
        if (datePicker.getDayOfMonth() < 10) day = "0" + day;

        String month = String.valueOf(datePicker.getMonth() + 1);
        if (datePicker.getMonth() + 1 < 10) month = "0" + month;

        String year = String.valueOf(datePicker.getYear());

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd", new Locale("en_EN"));
//        parsedString = simpleDateFormat.format(calendar.getTime());

        return year + "-" + month + "-" + day;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("en_EN"));
        return simpleDateFormat.format(date);
    }

}
