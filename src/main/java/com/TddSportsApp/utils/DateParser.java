package com.TddSportsApp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateParser {
    static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Parse error: " + e);
            return null;
        }
    }
}
