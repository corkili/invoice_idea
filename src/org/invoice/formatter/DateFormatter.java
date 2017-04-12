package org.invoice.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 李浩然 on 2017/4/4.
 */
public class DateFormatter implements Formatter<Date> {

    private String datePattern;
    private SimpleDateFormat dateFormat;

    public DateFormatter(String datePattern) {
        this.datePattern = datePattern;
        dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "invalid date format. Please use this pattern\"" + datePattern + "\""
            );
        }
    }

    @Override
    public String print(Date object, Locale locale) {
        return dateFormat.format(object);
    }
}
