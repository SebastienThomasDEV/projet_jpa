package parser.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateParser {
    private static final Logger LOGGER = Logger.getLogger(DateParser.class.getName());

    private static final DateTimeFormatter[] DATE_FORMATTERS = {
            DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    };

    public static LocalDate parseDate(String date) {

        if (date == null || date.isEmpty()) {
            LOGGER.log(Level.WARNING, "Date string is null or empty.");
            return null;
        }

        date = date.trim();
        LOGGER.log(Level.INFO, "Attempting to parse date: {0}", date);

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                LOGGER.log(Level.WARNING, "Failed to parse date: {0} with formatter: {1}", new Object[]{date, formatter});
                // continue to the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + date);
    }
}
