package net.marco27.api.base.util.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateUtilsImpl implements DateUtils {

    @Override
    public long getTimestampDifferenceInSeconds(final String timestamp) {
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(timestamp, DATE_TIME_FORMATTER);
        final Instant nowThreshold = Instant.now(Clock.systemUTC());
        return Duration.between(nowThreshold, zonedDateTime.toInstant()).getSeconds();
    }

    @Override
    public String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern(DATE_HOURS_PATTERN).format(LocalDateTime.now());
    }

    @Override
    public String getFormattedDate(long millis) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), TimeZone.getDefault().toZoneId());
        return DateTimeFormatter.ofPattern(DATE_HOURS_PATTERN).format(localDateTime);
    }

}
