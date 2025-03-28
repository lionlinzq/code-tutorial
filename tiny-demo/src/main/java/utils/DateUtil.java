/*
 * Copyright (c) 2017 by XUANWU INFORMATION TECHNOLOGY CO.
 *             All rights reserved
 */
package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author <a href="hw86xll@163.com">Wei.Huang</a>
 * @Date 2012-10-31
 * @Version 1.0.0
 */
public class DateUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * Number of hours in a standard day.
     */
    public static final int HOURS_PER_DAY = 24;
    /**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;
    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    public enum DateTimeType {
        DateTime(0), // 日期时间
        Date(1), // 日期
        Time(2), // 时间
        Month(3), // 年月
        ExcelDefaultDate(4), // Excel时间格式
        CnTxtDefaultDate(5),// 中文时间格式
        Year(6),// 中文时间格式
        DateTimeIgnoreSecond(7); //日期时间（省略秒）

        private int index;

        private DateTimeType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static DateTimeType getType(int index) {
            switch (index) {
                case 1:
                    return Date;
                case 2:
                    return Time;
                case 3:
                    return Month;
                case 4:
                    return ExcelDefaultDate;
                case 5:
                    return CnTxtDefaultDate;
                case 6:
                    return Year;
                default:
                    return DateTime;
            }
        }
    }


    public static String format(Date date, DateTimeType type) {
        if (date == null) {
            return "";
        }
        switch (type) {
            case Date:
                return new SimpleDateFormat("yyyy-MM-dd").format(date);
            case DateTimeIgnoreSecond:
                return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            case Year:
                return new SimpleDateFormat("yyyy").format(date);
            case Time:
                return new SimpleDateFormat("HH:mm:ss").format(date);
            case Month:
                return new SimpleDateFormat("yyyy-MM").format(date);
            case ExcelDefaultDate:
                return new SimpleDateFormat("yyyy/MM/dd").format(date);
            case CnTxtDefaultDate:
                return new SimpleDateFormat("yyyy年MM月dd日").format(date);
            default:
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
    }

    public static Date parse(String str, DateTimeType type) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            switch (type) {
                case Date:
                    if (str.length() < 10) {
                        return null;
                    }
                    return new SimpleDateFormat("yyyy-MM-dd").parse(str);
                case Time:
                    return new SimpleDateFormat("HH:mm:ss").parse(str);
                case ExcelDefaultDate:
                    return new SimpleDateFormat("yyyy/MM/dd").parse(str);
                case CnTxtDefaultDate:
                    return new SimpleDateFormat("yyyy年MM月dd日").parse(str);
                case Year:
                    return new SimpleDateFormat("yyyy").parse(str);
                default:
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
            }
        } catch (ParseException e) {
            logger.error("Parse date error:" + str);
            return null;
            // throw new RuntimeException("Parse date error.", e);
        }
    }

    public static Date tranDate(long time) {
        if (time == 0) {
            return null;
        }
        return new Date(time);
    }

    public static long parseDate(Date time) {
        return time == null ? 0 : time.getTime();
    }

    public static void sleepWithoutInterrupte(long time) {
        while (true) {
            try {
                Thread.sleep(time);
                break;
            } catch (InterruptedException e) {
                logger.error("Interrupted when sleep! this will be ignored!", e);
            }
        }
    }

    public static void sleepNanoWithoutInterrupte(long nanoseconds) {
        while (true) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanoseconds);
                break;
            } catch (InterruptedException e) {
                logger.error("Interrupted when sleep! this will be ignored!", e);
            }
        }
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getCurrentDayBegin() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin.getTime());
    }

    public static Date getCurDayBeginDate() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return begin.getTime();
    }

    public static String getCurrentDayEnd() {
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end.getTime());
    }

    public static Date getCurDayEndDate() {
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);
        return end.getTime();
    }

    public static int getCurMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    public static String getRealCurMonthFirstDay() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(begin.getTime());
    }

    public static String getRealCurMonthFirstDayTime() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.DAY_OF_MONTH, 1);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin.getTime());
    }

    public static String getCurMonthFirstDay() {
        Calendar begin = Calendar.getInstance();
        if (begin.get(Calendar.DAY_OF_MONTH) == 1) {
            begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
        }
        begin.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(begin.getTime());
    }

    public static String getCurMonthFirstDayTime() {
        Calendar begin = Calendar.getInstance();
        if (begin.get(Calendar.DAY_OF_MONTH) == 1) {
            begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
        }
        begin.set(Calendar.DAY_OF_MONTH, 1);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin.getTime());
    }

    public static String getCurPreviousDay() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.DAY_OF_MONTH, begin.get(Calendar.DAY_OF_MONTH) - 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(begin.getTime());
    }

    public static Date parseDate(String date) {
        try {
            if (StringUtils.isEmpty(date)) {
                return null;
            }
            if (date.length() < 10) {
                date = getCurDay();
            }
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            logger.error("Parse date error:" + date);
            return null;
        }
    }

    public static Date parseMonth(String date) {
        try {
            if (StringUtils.isEmpty(date)) {
                return null;
            }
            return new SimpleDateFormat("yyyy-MM").parse(date);
        } catch (ParseException e) {
            logger.error("Parse date error:" + date);
            return null;
        }
    }

    public static String getCurYearFirstMonth() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.MONTH, 0);
        return new SimpleDateFormat("yyyy-MM").format(begin.getTime());
    }

    /**
     * 获取当前年第一天日期   格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurYearFirstDay() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.MONTH, 0);
        begin.set(Calendar.DATE, 1);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin.getTime());
    }

    public static String getCurPreviousMonth() {
        Calendar begin = Calendar.getInstance();
        int month = begin.get(Calendar.MONTH);
        if (month > 1) {
            begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
        }
        return new SimpleDateFormat("yyyy-MM").format(begin.getTime());
    }

    public static String getCurYearMonth() {
        Calendar begin = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM").format(begin.getTime());
    }

    public static String getCurYear() {
        Calendar begin = Calendar.getInstance();
        return new SimpleDateFormat("yyyy").format(begin.getTime());
    }

    public static String getPreviousYearMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        return new SimpleDateFormat("yyyy-MM").format(c.getTime());
    }

    public static String getThreeYearMonthAgo() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        return new SimpleDateFormat("yyyy-MM").format(c.getTime());
    }

    /**
     * 获取本年的第多少周
     *
     * @return
     */
    public static int getCurWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static String getDayBeginTime() {
        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        return new SimpleDateFormat("HH:mm:ss").format(begin.getTime());
    }

    public static String getDayEndTime() {
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);
        return new SimpleDateFormat("HH:mm:ss").format(end.getTime());
    }

    public static String toMonthFirstDay(String month) {
        return month + "-01";
    }

    public static String toMonthLastDay(String month) {
        try {
            Date d = new SimpleDateFormat("yyyy-MM").parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        } catch (ParseException e) {
            logger.error("Parse date error:" + month);
            return null;
        }
    }

    public static int getDays(String beginDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdf.parse(beginDate);
            Date d2 = sdf.parse(endDate);
            return (int) ((d2.getTime() - d1.getTime()) / (3600L * 1000 * 24)) + 1;
        } catch (ParseException e) {
            logger.error("Parse date error:" + beginDate + " / " + endDate);
            return 0;
        }
    }

    public static String getCurDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static int getMonths(String beginDate, String endDate) {
        try {
            SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
            Date d1 = sdfMonth.parse(beginDate);
            Date d2 = sdfMonth.parse(endDate);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(d1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(d2);

            int beginDateYear = c1.get(Calendar.YEAR);
            int beginDateMonth = c1.get(Calendar.MONTH);
            int endDateYear = c2.get(Calendar.YEAR);
            int endDateMonth = c2.get(Calendar.MONTH);
            if (beginDateYear == endDateYear) {
                return endDateMonth - beginDateMonth + 1;
            } else {
                return (endDateYear - beginDateYear) * 12
                        + (endDateMonth - beginDateMonth) + 1;
            }
        } catch (ParseException e) {
            logger.error("Parse date error:" + beginDate + " / " + endDate);
            return 0;
        }
    }

    /***
     * 取得 n天前 or n天后 的时间
     *
     * @param date 起始时间
     * @param day  天数	 大于0时：n天后 ；小于0时：n天前
     * @return
     */
    public static Date getDateByDate(Date date, Integer day) {
        if (day == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, day);
        date = cal.getTime();
        return date;
    }

    /***
     * 取得 n小时前 or n小时后 的时间
     *
     * @param date 起始时间
     * @param hour 小时数	 大于0时：n小时后 ；小于0时：n小时前
     * @return
     */
    public static Date getDateByHour(Date date, Integer hour) {
        if (hour == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        date = cal.getTime();
        return date;
    }

    /***
     * 取得 n分钟前 or n分钟后 的时间
     *
     * @param date   起始时间
     * @param minute 分钟数	 大于0时：n分钟后 ；小于0时：n分钟前
     * @return
     */
    public static Date getDateByMinute(Date date, Integer minute) {
        if (minute == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        date = cal.getTime();
        return date;
    }

    /**
     * 取得几天前的时间
     */
    public static Date getPrefixStartDate(Integer day) {
        Date date = new Date();
        if (day == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    /**
     * 取得几天前的时间
     */
    public static Date getAfterEndDate(Integer day) {
        Date date = new Date();
        if (day == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        date = cal.getTime();
        return date;
    }

    /**
     * 获得两个时间之间的天数，不计算时分秒
     */
    public static int getDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return 0;
        }
        beginDate = parse(format(beginDate, DateTimeType.Date),
                DateTimeType.Date);
        endDate = parse(format(endDate, DateTimeType.Date), DateTimeType.Date);
        return (int) ((endDate.getTime() - beginDate.getTime()) / (3600 * 1000 * 24));
    }


    public enum TimeType {
        ALL(0), LAST_THREE_MONTHS(1), THIS_YEAR(2);
        private int index;

        private TimeType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static TimeType getType(int index) {
            switch (index) {
                case 0:
                    return ALL;
                case 1:
                    return LAST_THREE_MONTHS;
                case 2:
                    return THIS_YEAR;
                default:
                    return ALL;
            }
        }
    }

    /**
     * 最近三个月、今年内 开始时间
     *
     * @param timeType
     * @return
     */
    public static Date getBeginTime(TimeType timeType) {
        if (timeType == TimeType.ALL) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        switch (timeType) {
            case LAST_THREE_MONTHS:
                cal.add(Calendar.DATE, -90);
                return cal.getTime();
            case THIS_YEAR:
                cal.set(Calendar.MONTH, 0);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                return cal.getTime();
            default:
                return null;
        }
    }

    /***
     * 最近三个月、今年内 结束时间
     *
     * @param timeType
     * @return
     */
    public static Date getEndTime(TimeType timeType) {
        switch (timeType) {
            case ALL:
                break;
            case LAST_THREE_MONTHS:
            case THIS_YEAR:
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 999);
                return cal.getTime();
            default:
                break;
        }
        return null;
    }

    /**
     * 取得日期所在月份的第一天
     *
     * @param monthDate
     * @return
     */
    public static Date getFirstDayTimeOfMonth(Date monthDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取得日期所在月份的最后一天时间
     *
     * @param monthDate
     * @return
     */
    public static Date getLastDayTimeOfMonth(Date monthDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 校验是否合法的日期时间格式
     *
     * @param datetime
     * @return
     */
    public static boolean validateDate(String datetime) {
        if (StringUtils.isEmpty(datetime))
            return false;
        try {
            Date date = DateUtils.parseDate(datetime, "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static String getQuarter() {
        int month = getCurMonth();
        String year = getCurYear();
        String quarter = "";
        if (month > 0 && month < 4) {
            quarter = year + "/Q1";
        } else if (month > 3 && month < 7) {
            quarter = year + "/Q2";
        } else if (month > 6 && month < 10) {
            quarter = year + "/Q3";
        } else if (month > 9) {
            quarter = year + "/Q4";
        }
        return quarter;
    }

    public static String getLastQuarter() {
        int month = getCurMonth();
        String year = getCurYear();
        String quarter = "";
        if (month > 0 && month < 4) {
            quarter = (Integer.parseInt(year) - 1) + "/Q4";
        } else if (month > 3 && month < 7) {
            quarter = year + "/Q1";
        } else if (month > 6 && month < 10) {
            quarter = year + "/Q2";
        } else if (month > 9) {
            quarter = year + "/Q3";
        }
        return quarter;
    }

}
