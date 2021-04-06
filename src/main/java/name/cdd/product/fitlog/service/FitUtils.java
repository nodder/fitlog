package name.cdd.product.fitlog.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FitUtils {
    private FitUtils() {}

    public static long daysAgo(LocalDate localDate) {
        return LocalDate.now().toEpochDay() - localDate.toEpochDay();
    }

    public static boolean between(long small, long big, long num) {
        return small <= num && num <= big;
    }

    public static String parseDate(String diff) {
        if(diff.isEmpty()) {
            return null;
        }

        if(diff.endsWith("m")) {
            int monthDiff = Integer.parseInt(diff.substring(0, diff.length() - 1));
            return LocalDate.now().minus(monthDiff, ChronoUnit.MONTHS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {//d
            int dateDiff = Integer.parseInt(diff.substring(0, diff.length() - 1));
            return LocalDate.now().minus(dateDiff, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

    }
}
