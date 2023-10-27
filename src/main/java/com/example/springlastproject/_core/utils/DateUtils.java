package com.example.springlastproject._core.utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateUtils {
    public static Date convertToSqlDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }
}
