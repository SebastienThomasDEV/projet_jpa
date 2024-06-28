package parser.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {

    public static Date parseDate(String date) {

        if (date == null || date.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return java.sql.Date.valueOf(localDate);
    }
}
