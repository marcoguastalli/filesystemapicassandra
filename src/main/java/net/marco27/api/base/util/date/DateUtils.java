package net.marco27.api.base.util.date;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public interface DateUtils {

    String DATE_HOURS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);

    /** Return the seconds of difference between the input timestamp and now
     * 
     * @param timestamp in DATE_TIME_FORMATTER pattern
     * @return the seconds of difference */
    long getTimestampDifferenceInSeconds(final String timestamp);

    /** @return now in DATE_HOURS_PATTERN pattern */
    String getCurrentTimestamp();

    /** Convert the input millis in a LocalDateTime and returs the DATE_HOURS_PATTERN corresponding pattern
     * 
     * @param millis to be converted
     * @return a String */
    String getFormattedDate(long millis);
}
