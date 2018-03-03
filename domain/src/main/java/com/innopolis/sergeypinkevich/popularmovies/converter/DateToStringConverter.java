package com.innopolis.sergeypinkevich.popularmovies.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Sergey Pinkevich
 */

public class DateToStringConverter {

    public static String getStringFromDate(String date) {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date temp = null;
        try {
            temp = originalFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(temp);
    }
}
