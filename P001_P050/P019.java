package P001_P050;

import java.util.Calendar;

/*
 You are given the following information, but you may prefer 
 to do some research for yourself.

 1 Jan 1900 was a Monday.
 Thirty days has September,
 April, June and November.
 All the rest have thirty-one,
 Saving February alone,
 Which has twenty-eight, rain or shine.
 And on leap years, twenty-nine.
 
 A leap year occurs on any year evenly divisible by 4, 
 but not on a century unless it is divisible by 400.
 How many Sundays fell on the first of the month during the twentieth century 
 (1 Jan 1901 to 31 Dec 2000)?
 */
public class P019 {

    public static int solve(int dayOfMonth, int dayOfWeek, int d1, int m1, int y1, int d2, int m2, int y2) {
        int days = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(y1, m1 - 1, d1);
        while (!(d1 == d2 && m1 == m2 && y1 == y2)) {
            d1 = cal.get(Calendar.DAY_OF_MONTH);
            m1 = cal.get(Calendar.MONTH) + 1;
            y1 = cal.get(Calendar.YEAR);
            if (dayOfMonth == d1 && cal.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                System.out.println(++days + ": " + d1 + "-" + m1 + "-" + y1);
            }
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(P019.solve(1, Calendar.SUNDAY, 1, 1, 1901, 31, 12, 2000));
    }
}
