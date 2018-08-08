package hello.util;


import hello.exception.NostraException;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * agus w on 3/7/16.
 */
public class NostraDateUtil {

    private static final Logger log = LoggerFactory.getLogger(NostraDateUtil.class);

    final static String DEFAULT_FORMAT = "yyyy-MMM-dd";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String DD_MMM_YYYY = "dd-MMM-yyyy";
    public final static String DDMMYY = "dd/MM/yy";
    public final static String DDMMYYYY = "ddMMyyyy";
    public final static String DDMMYYHHMMSS = "dd/MM/yy – HH:mm:ss";
    public final static String DD_MM_YYYY = "dd-MM-yyyy";
    public final static String YYYYMMDDHHMMSSSSS =  "yyyyMMddHHmmssSSS";
    public final static String YYYYMMDDHHMMSS =  "yyyyMMddHHmmss";
    public final static String YYYYMMDD =  "yyyyMMdd";
    public static final String TIMESTAMP = "yyyyMMddHHmmsss";

//    public static String getToday(Date date) {
//        return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
//    }

    public static Date getMinDate() {

        Date result = null;
        try {
            result = new SimpleDateFormat(DD_MM_YYYY).parse("01-01-1970");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Date getMaxDate() {
        Date result = null;
        try {
            result = new SimpleDateFormat(DD_MM_YYYY).parse("31-12-2200");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Date getToday() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    public static String getToday(String format) {
        return dateToString(getToday(), format);
    }

    public static Date getYesterday() {
        return getYesterday(new Date());
    }

    public static Date getYesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar = resetTime(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        return calendar.getTime();
    }

    public static Date getTomorrow() {
        return getTomorrow(new Date());
    }

    public static Date getTomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar = resetTime(calendar);
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }

    public static Date stringToDate(String date, String format) {

        Date result;
        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new NostraException("Format Date " + DEFAULT_FORMAT + " ex: " + dateToString(new Date()));
        }

        return result;

    }

    public static Date stringToDate(String date) {
        return stringToDate(date, DEFAULT_FORMAT);
    }
    

    public static String dateToString(Date date, String format) {

        String result;
        DateFormat dateFormat = new SimpleDateFormat(format);
        result = dateFormat.format(date);

        return result;

    }

    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_FORMAT);
    }
    
    public static String dateToStringAge(Date date) {
        return dateToString(date, YYYY_MM_DD);
    }

    public static String getYear() {
        return getYear(getToday(), YEAR_FORMAT);
    }

    public static String getYear(Date date) {
        return getYear(date, YEAR_FORMAT);
    }

    public static String getYear(Date date, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return dateFormat.format(calendar.getTime());

    }

    public static Date getEndDate(String days) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt(days));

        return calendar.getTime();
    }

//    public static Long countDaysSince(Date start) {
//        Calendar cal = resetTime(Calendar.getInstance());
//        Date todayDate = cal.getTime();
//
////        cal.setTime(start);
////        Date sinceDate = cal.getTime();
//
//        log.info("today: {}", todayDate);
//        long diff = todayDate.getTime() - start.getTime();
//        log.info("start: {}", start);
////        diff = Days.daysBetween(getToday(), start).getDays();
//
//        return diff;
//    }

    public static int countDaysSince(Date start) {
        return count(start, getToday());
    }

    public static int countDaysBetween(Date start, Date end) {
        return count(start, end);
    }

    private static int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    private static int count(Date start, Date end) {
        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);
        return Days.daysBetween(startDate.toLocalDate(), endDate.toLocalDate()).getDays();
    }

    public static Date addDays(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }


    private static Calendar resetTime(Calendar toReset) {
        toReset.set(Calendar.HOUR_OF_DAY, 0);
        toReset.set(Calendar.MINUTE, 0);
        toReset.set(Calendar.SECOND, 0);
        toReset.set(Calendar.MILLISECOND, 0);

        return toReset;
    }

    public static Boolean sameMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(date1);
        calendar2.setTime(date2);

        Boolean result = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) ? true : false;
        return result;
//        if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) return true;
    }
    
    public static int getAge(Date dateOfBirth) throws Exception {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new Exception("Tanggal lahir tidak boleh hari mendatang");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of
        // leap year) then decrement age one year
        if ((birthDate.get(Calendar.DAY_OF_YEAR)
                - today.get(Calendar.DAY_OF_YEAR) > 3)
                || (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;

            // If birth date and todays date are of same month and birth day of
            // month is greater than todays day of month then decrement age
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
                && (birthDate.get(Calendar.DAY_OF_MONTH) > today
                        .get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }
    
    public static int getUmur(String tanggal) {
	    int umur = 0;
	    Date tglLahir = null;
	    long time;
	    try {
	        time = new SimpleDateFormat(YYYY_MM_DD).parse(tanggal).getTime();
	        tglLahir = new Date(time);
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    try {
	        umur = getAge(tglLahir);

	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return umur;

	}
    
   

}
