/*input
1 31 12 2999
40 1 2 2004
60 31 12 1999
60 31 12 2999
146097 31 12 1999
999999 1 1 2000
1 1 2 2020
0 0 0 0
*/

import java.util.*;
import java.io.*;


class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    final int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int[] monthDaysSum; 
    void init() {
        monthDaysSum = new int[12];
        for (int i = 0; i < 12; i++) monthDaysSum[i] = (i > 0? monthDaysSum[i - 1] : 0) + monthDays[i];
    }
    boolean isLeap(int yr) {
        if (yr % 400 == 0) return true;
        return yr % 4 == 0 && yr % 100 != 0;
    }
    int yearsToDays(int years) {
        int leaps = (int)(years / 4) - (int)(years / 100) + (int)(years / 400);
        return 366 * (leaps + 1) + 365 * (years - leaps);
    }
    int monthsToDays(int month, int year) {
        if (month == 0) return 0;
        int res = monthDaysSum[month - 1];
        if (isLeap(year) && month >= 2) res++;
        return res;
    }
    int dateToDays(int day, int month, int year) {
        return day + monthsToDays(month - 1, year) + yearsToDays(year - 1);
    }
    int[] daysToDate(int days) {
        int[] res = new int[3];
        int l = 1, r = days / 365;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (yearsToDays(mid) >= days) r = mid - 1;
            else l = mid + 1;
        }
        days -= yearsToDays(r);
        //System.out.println(days);
        res[2] = r + 1;
        l = 0; r = 12;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (monthsToDays(mid, res[2]) >= days) r = mid - 1;
            else l = mid + 1;
        }
        days -= monthsToDays(r, res[2]);
        //System.out.format("monthsToDays(%d, %d) = %d\n", r, res[2], monthsToDays(r, res[2]));
        res[1] = r + 1;
        res[0] = days;
        return res;
    }

    int[] solve(int numDays, int day, int month, int year) {
        //System.out.println(dateToDays(day, month, year));
    	return daysToDate(dateToDays(day, month, year) + numDays);
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.init();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextInt()) {
            int numDays = inp.nextInt(), day = inp.nextInt(), month = inp.nextInt(), year = inp.nextInt();
            if (numDays == 0 && day == 0 && month == 0 && year == 0) break;
            int[] ans = obj.solve(numDays, day, month, year);
            System.out.format("%d %d %d\n", ans[0], ans[1], ans[2]);
        }
    }
}