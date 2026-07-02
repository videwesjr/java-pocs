public class Main {

    public static void main(String[] args) {

        JavaTimeHandler jt = new JavaTimeHandler();
        JodaTimeHandler joda = new JodaTimeHandler();

        System.out.println("=== Creation: LocalDate / LocalTime / LocalDateTime ===");
        System.out.println("java.time now()  : " + jt.nowDate() + " | " + jt.nowTime() + " | " + jt.nowDateTime());
        System.out.println("java.time of()   : " + jt.ofDate(2026, 7, 1) + " | " + jt.ofTime(14, 30, 0)
                + " | " + jt.ofDateTime(2026, 7, 1, 14, 30));
        System.out.println("Joda      now()  : " + joda.nowDate() + " | " + joda.nowTime() + " | " + joda.nowDateTime());
        System.out.println("Joda      of()   : " + joda.ofDate(2026, 7, 1) + " | " + joda.ofTime(14, 30, 0)
                + " | " + joda.ofDateTime(2026, 7, 1, 14, 30));

        System.out.println("\n=== Time zones ===");
        System.out.println("java.time now(zone) : " + jt.nowZoned("America/Sao_Paulo"));
        System.out.println("java.time of(zone)  : " + jt.ofZoned(2026, 7, 1, 14, 30, "America/Sao_Paulo"));
        System.out.println("Joda      now(zone) : " + joda.nowZoned("America/Sao_Paulo"));
        System.out.println("Joda      of(zone)  : " + joda.ofZoned(2026, 7, 1, 14, 30, "America/Sao_Paulo"));

        System.out.println("\n=== Instants / epoch ===");
        System.out.println("java.time now()       : " + jt.nowInstant());
        System.out.println("java.time ofEpochMilli: " + jt.ofEpochMilli(0));
        System.out.println("Joda      now()       : " + joda.nowInstant());
        System.out.println("Joda      ofEpochMilli: " + joda.ofEpochMilli(0));

        System.out.println("\n=== Parsing & formatting ===");
        String pattern = "dd/MM/yyyy";
        String text = "01/07/2026";
        System.out.println("java.time parse(\"" + text + "\") : " + jt.parseDate(text, pattern));
        System.out.println("java.time format(...)       : " + jt.formatDate(jt.ofDate(2026, 7, 1), pattern));
        System.out.println("Joda      parse(\"" + text + "\") : " + joda.parseDate(text, pattern));
        System.out.println("Joda      format(...)       : " + joda.formatDate(joda.ofDate(2026, 7, 1), pattern));
    }
}
