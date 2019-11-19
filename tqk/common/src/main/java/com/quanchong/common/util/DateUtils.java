package com.quanchong.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private final static String FORMAT_PATTERN1="yyyy-MM-dd HH:mm:ss";
    private final static String FORMAT_PATTERN2="yyyy-MM-dd";
    private final static String FORMAT_PATTERN3="HH:mm:ss";

    public static String format(Date date, String pattern){
        return format(LocalDateTime.ofInstant(date.toInstant(), TimeZone.getDefault().toZoneId()), pattern);
    }

    public static String format(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dtf);
    }

    public static String format(Long timeLong, String pattern){
        Date date = new Date();
        date.setTime(timeLong);
        return format(date, pattern);
    }

    public static Date parseDateTime(String dateStr) {
        return parseDateTime(dateStr, FORMAT_PATTERN1);
    }

    public static Date parseDateTime(String dateStr, String pattern){
        ZonedDateTime zdt = parse(dateStr, pattern).atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime parse(String dateStr){
        return parse(dateStr, FORMAT_PATTERN1);
    }

    public static LocalDateTime parse(String dateStr, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, dtf);
    }

    public static String formatDate(Long timeLong){
        return format(timeLong, FORMAT_PATTERN2);
    }

    public static String formatTime(Long timeLong){
        return format(timeLong, FORMAT_PATTERN3);
    }

    public static String formatDateTime(Long timeLong){
        return format(timeLong, FORMAT_PATTERN1);
    }

    public static String now(){
        return nowDateTime();
    }

    public static String now(String pattern){
        return format(LocalDateTime.now(), pattern);
    }

    public static String nowDate(){
        return now(FORMAT_PATTERN2);
    }

    public static String nowTime(){
        return now(FORMAT_PATTERN3);
    }

    public static String nowDateTime(){
        return now(FORMAT_PATTERN1);
    }

    public static boolean isDateTimeStr(String dateStr) {
        boolean isDT = true;
        try{
            parseDateTime(dateStr);
        }catch(Exception e){
            isDT = false;
        }
        return isDT;
    }

    public static LocalDate sub(int days){
        return sub(LocalDate.now(), days);
    }

    public static LocalDate sub(LocalDate localDate, int days){
        return localDate.minusDays(days);
    }

    public static LocalDate add(int days){
        return add(LocalDate.now(), days);
    }

    public static LocalDate add(LocalDate localDate, int days){
        return localDate.plusDays(days);
    }

    public static String padTimeStart() throws Exception{
        return padTimeStart(LocalDate.now());
    }

    public static String padTimeStart(LocalDate localDate) throws Exception{
        return pad(localDate, " 00:00:00");
    }

    public static String padTimeEnd() throws Exception{
        return padTimeEnd(LocalDate.now());
    }

    public static String padTimeEnd(LocalDate localDate) throws Exception{
        return pad(localDate, " 23:59:59");
    }

    public static String pad(String pad) throws Exception{
        return pad(LocalDate.now(), pad);
    }

    public static String pad(LocalDate localDate, String pad) throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String padTime = localDate.format(dtf) + pad;
        try{
            parseDateTime(padTime);
        }catch (Exception e){
            throw new Exception("pad不合法,格式应为HH:mm:ss");
        }
        return padTime;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(pad("909adjgflaj"));
    }
}
