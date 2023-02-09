package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dotonce.mainconfig.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ConvertTime {
    /**
     * example Today at 9:00 AM
     */
    public static String convertTime(Activity activity,long time) {
        CheckLanguage checkLanguage=new CheckLanguage(activity);
        String lang=checkLanguage.getLanguage();
        String date,am,pm;
        String currentDate = convertTime(System.currentTimeMillis());
        String contentDate = convertTime(time);
        String timeMinute =  convertTime(time);
        Date netDate = (new Date(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(netDate);
        calendar.add(Calendar.DATE, -1);
        if(lang.equals("en")){
            am=" AM";
            pm=" PM";
        }
        else {
            am=" صباحاً ";
            pm=" مساءً ";
        }
        String timeMinute2=timeMinute.replace("AM",am).replace("PM",pm);
        String previousDate = convertTime(calendar.getTimeInMillis());
        if (contentDate.equals(currentDate)) {
            date=activity.getString(R.string.today_at) + " " + timeMinute2;
        } else if (contentDate.equals(previousDate)) {
            date=activity.getString(R.string.yesterday_at) + " " + timeMinute2;

        } else {
            date=contentDate;
        }
        return date;
    }
    /**
     * example 25/4/2023
     */
    public static String convertDate(long timeStamp){
        String format="dd MMM yyyy";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }



    /**
     * example 25/4
     */
    public static String convertDate1(long timeStamp){
        String format="dd-MM";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }

    /**
     * example 07:44 AM
     */
    public static String convertTime(long timeStamp){
        String format="hh:mm a";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }

    /**
     * example 14:44 AM
     */
    public static String convertTime5(long timeStamp){
        String format="HH:mm";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }

    /**
     * example 23/4/19 14:44:22
     */
    public static String convertTime2(long timeStamp){
        String format="yy/MM/dd HH:mm:ss";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }

    /**
     * example 19/4 07:44 AM
     */
    public static String convertTime3(long timeStamp){
        String format="dd-MM hh:mm a";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());
    }
    /**
     * example 19/04/2023 05:44 AM
     */
    public static String convertTime4(long timeStamp){
        String format="dd-MM-yyy hh:mm a";
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        Locale locale = new Locale("en");
        SimpleDateFormat formatter=new SimpleDateFormat(format,locale);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(cal.getTime());

    }
    public static long getTimeInMinutes(long time){
        Locale locale= new Locale("en");
        SimpleDateFormat format =new SimpleDateFormat("yy/MM/dd HH:mm:ss",locale);
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(time));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }
        return diff / (60 * 1000);
    }

    public static double getTimeInHours(long time){
        Locale locale= new Locale("en");
        SimpleDateFormat format =new SimpleDateFormat("yy/MM/dd HH:mm:ss",locale);
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(time));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        double diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }

        return diff / (60 * 60 * 1000);
    }
    public static String getFullCalculatedTime(Context context, String time){

        double cal =  ConvertTime.getTimeInHours(Long.parseLong(time));
        if(cal<0){
            cal = -(cal);
        }
        Log.d("----",cal+"");
        if(cal >= 8640){
            long cal2 = (long) (((cal / 24) / 30)/12);
            return context.getResources().getQuantityString(R.plurals.ago_year,Integer.parseInt(String.valueOf(cal2)),cal2,cal2);
        }
        if(cal >= 720){
            long cal2 = (long) ((cal / 24) / 30);
            return context.getResources().getQuantityString(R.plurals.ago_month,Integer.parseInt(String.valueOf(cal2)),cal2,cal2);
        }else if(cal >=168){
            long cal2 = (long) (cal / 24) / 7;
            return context.getResources().getQuantityString(R.plurals.ago_week,Integer.parseInt(String.valueOf(cal2)),cal2,cal2);
        }else if(cal >= 24){
            long cal2 = (long) cal / 24;
            return context.getResources().getQuantityString(R.plurals.ago_day,Integer.parseInt(String.valueOf(cal2)),cal2,cal2);
        }else if(cal >= 1){
            int cal2 = (int) (cal * 1);
            return context.getResources().getQuantityString(R.plurals.ago_hour,Integer.parseInt(String.valueOf(cal2)),cal2,cal2);
        }else if(cal>= 0){
            int cal2 = (int) (cal * 60);
            if(cal == 0){
                return context.getResources().getString(R.string.now);
            }else {
                return context.getResources().getQuantityString(R.plurals.ago_minute,cal2,cal2);
            }


        }else {
            return context.getResources().getString(R.string.now);
        }

    }
}
