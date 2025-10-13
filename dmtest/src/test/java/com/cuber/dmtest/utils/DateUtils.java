package com.cuber.dmtest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    public static String getCurrentDateTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
}
