package jp.co.greensys.takeout.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarUtil {
    private static final TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");

    public static Date getDateOfToday(int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar.getTime();
    }
}