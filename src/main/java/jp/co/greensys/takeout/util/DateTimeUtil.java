package jp.co.greensys.takeout.util;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Tokyo");

    public static long getDateOfToday(int hour, int minute) {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        ZonedDateTime dateTime = ZonedDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DATE),
            hour,
            minute,
            0,
            0,
            ZoneId.of("Asia/Tokyo")
        );
        dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return dateTime.toInstant().toEpochMilli();
    }

    public static String toString(long dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        format.setTimeZone(TIME_ZONE);
        return format.format(dateTime);
    }

    public static String toString(String format, long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TIME_ZONE);
        return sdf.format(dateTime);
    }

    public static List<String> getDeliveryDate() {
        List<String> deliveryDate = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 3; i++) {
            calendar.add(Calendar.DATE, i);
            deliveryDate.add(toString("yyyy/MM/dd", calendar.getTimeInMillis()));
        }
        return deliveryDate;
    }
}
