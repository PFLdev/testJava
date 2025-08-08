package date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getIntervalDate(Date origin, long intervals)
    {
        return new Date(origin.getTime() + intervals * 86400000L);
    }

    public static Date getSelectMonthBegin(Date selectDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getBeginOfGivenDay(Date givenDay){
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();

    }

    public static Timestamp getCurrentTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }



    public static void main(String[] args) {
        System.out.println(isEduDeviceSn("O84AG/W4ZR00011"));
        System.out.println(System.currentTimeMillis());
    }

    public static boolean isEduDeviceSn(String deviceSn) {
        String deviceSnEduStr = "67449/W5U700031-67449/W5U700040," +
                "67449/W5U800001-67449/W5U801000," +
                "67449/W5UB01511-67449/W5UB01520," +
                "67449/W5UB00031-67449/W5UB01510," +
                "72241/W5UB00001-72241/W5UB01490," +
                "72241/W5UB01491-72241/W5UB04490," +
                "72241/W5U700211-72241/W5U700220";
        boolean res = false;
        String[] deviceSnEduArrs = deviceSnEduStr.split(",");
        for (String deviceSnEduArr : deviceSnEduArrs) {
            String[] deviceSnEdu = deviceSnEduArr.split("-");
            if (deviceSn.compareTo(deviceSnEdu[0]) >= 0 && deviceSn.compareTo(deviceSnEdu[1]) <= 0) {
                res = true;
            }
        }
        return res;
    }
}
