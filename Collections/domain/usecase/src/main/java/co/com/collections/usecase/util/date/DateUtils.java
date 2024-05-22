package co.com.collections.usecase.util.date;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static List<LocalDateTime> getDatesInMonth(int year, int month) {
        List<LocalDateTime> dates = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(year, month);
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            dates.add(LocalDateTime.of(year, month, day, 0, 0));
        }
        return dates;
    }
}
