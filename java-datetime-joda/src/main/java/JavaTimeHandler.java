import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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

    public LocalDate plusPeriod(LocalDate date, int years, int months, int days) {
        return date.plus(Period.of(years, months, days));
    }

    public LocalDate minusPeriod(LocalDate date, int years, int months, int days) {
        return date.minus(Period.of(years, months, days));
    }

    public LocalDateTime plusDuration(LocalDateTime dateTime, long hours, long minutes) {
        return dateTime.plus(Duration.ofHours(hours).plusMinutes(minutes));
    }

    public LocalDateTime minusDuration(LocalDateTime dateTime, long hours, long minutes) {
        return dateTime.minus(Duration.ofHours(hours).plusMinutes(minutes));
    }

    public boolean isBefore(LocalDate a, LocalDate b) {
        return a.isBefore(b);
    }

    public boolean isAfter(LocalDate a, LocalDate b) {
        return a.isAfter(b);
    }

    public int compare(LocalDate a, LocalDate b) {
        return a.compareTo(b);
    }

    public boolean isWithinRange(LocalDate date, LocalDate start, LocalDate end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }

    public Date toLegacyDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public ZonedDateTime fromLegacyDate(Date date, ZoneId zone) {
        return ZonedDateTime.ofInstant(date.toInstant(), zone);
    }

    public Calendar toCalendar(ZonedDateTime zonedDateTime) {
        return GregorianCalendar.from(zonedDateTime);
    }

    public ZonedDateTime fromCalendar(Calendar calendar) {
        return ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }

    public DateTime toJoda(ZonedDateTime zonedDateTime) {
        return new DateTime(zonedDateTime.toInstant().toEpochMilli(), DateTimeZone.forID(zonedDateTime.getZone().getId()));
    }

    public long toEpochMilli(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant().toEpochMilli();
    }
}
