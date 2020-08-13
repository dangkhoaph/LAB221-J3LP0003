package khoaphd.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author KhoaPHD
 */
public class DateConversion {
    
    public static Date convertStringToDate(String dateString, String pattern)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        java.util.Date date = format.parse(dateString);
        Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }
    
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String s = dateFormat.format(date);
        return s;
    }
}
