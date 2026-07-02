import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTimeHandler {

    public LocalDate nowDate() {
        return LocalDate.now();
    }

    public LocalDate ofDate(int year, int month, int day) {
        return new LocalDate(year, month, day);
    }

    public LocalTime nowTime() {
        return LocalTime.now();
    }

    public LocalTime ofTime(int hour, int minute, int second) {
        return new LocalTime(hour, minute, second);
    }

    public DateTime nowDateTime() {
        return DateTime.now();
    }

    public DateTime ofDateTime(int year, int month, int day, int hour, int minute) {
        return new DateTime(year, month, day, hour, minute);
    }

    public DateTime nowZoned(String zoneId) {
        return DateTime.now(DateTimeZone.forID(zoneId));
    }

    public DateTime ofZoned(int year, int month, int day, int hour, int minute, String zoneId) {
        return new DateTime(year, month, day, hour, minute, DateTimeZone.forID(zoneId));
    }

    public Instant nowInstant() {
        return Instant.now();
    }

    public Instant ofEpochMilli(long epochMilli) {
        return new Instant(epochMilli);
    }

    public LocalDate parseDate(String text, String pattern) {
        return LocalDate.parse(text, DateTimeFormat.forPattern(pattern));
    }

    public String formatDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return date.toString(formatter);
    }
}
