import java.time.LocalDate;

public class Main {

    private static final JavaTimeHandler jt = new JavaTimeHandler();
    private static final JodaTimeHandler joda = new JodaTimeHandler();

    public static void main(String[] args) {
        runCreation();
        runTimeZones();
        runInstants();
        runParsingAndFormatting();
        runArithmetic();
        runComparison();
        runDateRanges();
    }

    private static void runCreation() {
        System.out.println("=== Creation: LocalDate / LocalTime / LocalDateTime ===");
        System.out.println("java.time now()  : " + jt.nowDate() + " | " + jt.nowTime() + " | " + jt.nowDateTime());
        System.out.println("java.time of()   : " + jt.ofDate(2026, 7, 1) + " | " + jt.ofTime(14, 30, 0)
                + " | " + jt.ofDateTime(2026, 7, 1, 14, 30));
        System.out.println("Joda      now()  : " + joda.nowDate() + " | " + joda.nowTime() + " | " + joda.nowDateTime());
        System.out.println("Joda      of()   : " + joda.ofDate(2026, 7, 1) + " | " + joda.ofTime(14, 30, 0)
                + " | " + joda.ofDateTime(2026, 7, 1, 14, 30));
    }

    private static void runTimeZones() {
        System.out.println("\n=== Time zones ===");
        System.out.println("java.time now(zone) : " + jt.nowZoned("America/Sao_Paulo"));
        System.out.println("java.time of(zone)  : " + jt.ofZoned(2026, 7, 1, 14, 30, "America/Sao_Paulo"));
        System.out.println("Joda      now(zone) : " + joda.nowZoned("America/Sao_Paulo"));
        System.out.println("Joda      of(zone)  : " + joda.ofZoned(2026, 7, 1, 14, 30, "America/Sao_Paulo"));
    }

    private static void runInstants() {
        System.out.println("\n=== Instants / epoch ===");
        System.out.println("java.time now()       : " + jt.nowInstant());
        System.out.println("java.time ofEpochMilli: " + jt.ofEpochMilli(0));
        System.out.println("Joda      now()       : " + joda.nowInstant());
        System.out.println("Joda      ofEpochMilli: " + joda.ofEpochMilli(0));
    }

    private static void runParsingAndFormatting() {
        System.out.println("\n=== Parsing & formatting ===");
        String pattern = "dd/MM/yyyy";
        String text = "01/07/2026";
        System.out.println("java.time parse(\"" + text + "\") : " + jt.parseDate(text, pattern));
        System.out.println("java.time format(...)       : " + jt.formatDate(jt.ofDate(2026, 7, 1), pattern));
        System.out.println("Joda      parse(\"" + text + "\") : " + joda.parseDate(text, pattern));
        System.out.println("Joda      format(...)       : " + joda.formatDate(joda.ofDate(2026, 7, 1), pattern));
    }

    private static void runArithmetic() {
        System.out.println("\n=== Adding/subtracting: Period & Duration ===");
        System.out.println("java.time plus Period(1y2m10d) : " + jt.plusPeriod(jt.ofDate(2026, 7, 1), 1, 2, 10));
        System.out.println("java.time minus Period(1y2m10d): " + jt.minusPeriod(jt.ofDate(2026, 7, 1), 1, 2, 10));
        System.out.println("Joda      plus Period(1y2m10d) : " + joda.plusPeriod(joda.ofDate(2026, 7, 1), 1, 2, 10));
        System.out.println("Joda      minus Period(1y2m10d): " + joda.minusPeriod(joda.ofDate(2026, 7, 1), 1, 2, 10));
        System.out.println("java.time plus Duration(3h30m) : "
                + jt.plusDuration(jt.ofDateTime(2026, 7, 1, 14, 30), 3, 30));
        System.out.println("Joda      plus Duration(3h30m) : "
                + joda.plusDuration(joda.ofDateTime(2026, 7, 1, 14, 30), 3, 30));
    }

    private static void runComparison() {
        System.out.println("\n=== Comparing dates ===");
        LocalDate jtA = jt.ofDate(2026, 7, 1);
        LocalDate jtB = jt.ofDate(2026, 12, 25);
        System.out.println("java.time isBefore/isAfter/compareTo : " + jt.isBefore(jtA, jtB) + " / "
                + jt.isAfter(jtA, jtB) + " / " + jt.compare(jtA, jtB));
        org.joda.time.LocalDate jodaA = joda.ofDate(2026, 7, 1);
        org.joda.time.LocalDate jodaB = joda.ofDate(2026, 12, 25);
        System.out.println("Joda      isBefore/isAfter/compareTo : " + joda.isBefore(jodaA, jodaB) + " / "
                + joda.isAfter(jodaA, jodaB) + " / " + joda.compare(jodaA, jodaB));
    }

    private static void runDateRanges() {
        System.out.println("\n=== Date ranges ===");
        LocalDate date = jt.ofDate(2026, 7, 1);
        LocalDate rangeStart = jt.ofDate(2026, 1, 1);
        LocalDate rangeEnd = jt.ofDate(2026, 12, 31);
        System.out.println("java.time manual range check : " + jt.isWithinRange(date, rangeStart, rangeEnd));
        org.joda.time.DateTime intervalStart = joda.ofDateTime(2026, 1, 1, 0, 0);
        org.joda.time.DateTime intervalEnd = joda.ofDateTime(2026, 12, 31, 23, 59);
        System.out.println("Joda      Interval.contains  : "
                + joda.isWithinInterval(joda.ofDateTime(2026, 7, 1, 14, 30), intervalStart, intervalEnd));
    }
}
