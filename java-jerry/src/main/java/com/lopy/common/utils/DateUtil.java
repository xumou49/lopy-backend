package com.lopy.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@Slf4j
public final class DateUtil extends DateUtils {

    private DateUtil() {

    }

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE = "yyyy-MM-dd";

    public static final String WEB_DATE = "yyyy/MM/dd";

    public static final String DATE_NO_DASH = "yyyyMMdd";

    /**
     * @Description: LocalDateTime 转 String
     * @Author:rosh
     * @Date: 2020/9/15
     * @param dateTime 日期
     */
    public static String datetimeToString(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_TIME);
        return df.format(dateTime);
    }

    /**
     * @Description: LocalDate 转 String
     * @Author:rosh
     * @Date: 2020/9/15
     * @param date 日期
     */
    public static String dateToString(LocalDate date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE);
        return df.format(date);
    }

    public static String dateToString(LocalDateTime date, String patten) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(patten);
        return df.format(date);
    }

    public static String formatDate(Date d, String patten) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            return sdf.format(d);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static Date parseDate(String d, String patten) {
        try {
            return DateUtils.parseDate(d, patten);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getPreviousMonth(int numberOfMonth, DateTimeFormatter dtf) {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusMonths(numberOfMonth);
        return localDate.format(dtf);
    }


    public static LocalDateTime getStartDateTimeByMonth(int month) {
        LocalTime startTime = LocalTime.of(0, 0, 0);
        LocalDate date = LocalDate.now();
        LocalDate startDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate rs = startDate.plusMonths(month);
        return LocalDateTime.of(rs, startTime);
    }

    public static LocalDateTime getEndDateTimeByMonth(int month) {
        LocalTime startTime = LocalTime.of(23, 59, 59);
        LocalDate date = LocalDate.now();
        LocalDate rs = date.plusMonths(month);
        LocalDate with = rs.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(with, startTime);
    }


    public static LocalDateTime getStartDateTimeByDay(int day) {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(0, 0, 0);
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        return startDateTime.plusDays(day);
    }

    public static LocalDateTime getEndDateTimeByDay(int day) {
        LocalDate date = LocalDate.now();
        LocalTime endTime = LocalTime.of(23, 59, 59);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        return endDateTime.plusDays(day);
    }

    public static String getYyyyMmDdDateByDay(int day) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_NO_DASH);
        LocalDate localDate = LocalDate.now();
        LocalDate rs = localDate.plusDays(day);
        return rs.format(df);
    }


    public static LocalDateTime getStartDateTime(String date, String pattern) {

        LocalDate startDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
        return LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));

    }

    public static LocalDateTime getEndDateTime(String date, String pattern) {
        LocalDate startDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
        return LocalDateTime.of(startDate, LocalTime.of(23, 59, 59));
    }

    public static int countSecondsToMidNight() {
        return 86400 - LocalTime.now().toSecondOfDay();
    }

    public static String formateDate(Date d, String patten) {
        if(d == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            return sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String dateLongToString(Long time,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(sdf.format(time));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean equalYearAndMonth(LocalDate d1, LocalDate d2) {
        int m1 = d1.getMonthValue();
        int y1 = d1.getYear();
        int m2 = d2.getMonthValue();
        int y2 = d2.getYear();
        return m1 == m2 && y1 == y2;
    }

    public static String getDateMonthStr(LocalDate date) {
        String dateStr = date.format(DateTimeFormatter.ofPattern(DATE));
        return dateStr.substring(0, 7).replace("-",".");
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date generateRandomDate() {
        int year = DataUtil.generateRandomInt(2010, 2022);
        int month = DataUtil.generateRandomInt(0, 12);
        int day = DataUtil.generateRandomInt(0, 28);
        return parseDate(String.format("%s-%s-%s", year, month, day), DATE);
    }
}
