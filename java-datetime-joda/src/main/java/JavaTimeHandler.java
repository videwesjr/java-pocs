import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JavaTimeHandler {

    public LocalDate nowDate() {
        return LocalDate.now();
    }

    public LocalDate ofDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    public LocalTime nowTime() {
        return LocalTime.now();
    }

    public LocalTime ofTime(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    public LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    public LocalDateTime ofDateTime(int year, int month, int day, int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public ZonedDateTime nowZoned(String zoneId) {
        return ZonedDateTime.now(ZoneId.of(zoneId));
    }

    public ZonedDateTime ofZoned(int year, int month, int day, int hour, int minute, String zoneId) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.of(zoneId));
    }

    public Instant nowInstant() {
        return Instant.now();
    }

    public Instant ofEpochMilli(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli);
    }

    public LocalDate parseDate(String text, String pattern) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
    }

    public String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
