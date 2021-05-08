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
    int[] solve(int numDays, int day, int month, int year) {
    	Calendar date = new GregorianCalendar(year, month - 1, day);
    	date.add(Calendar.DATE, numDays);
    	return new int[]{date.get(Calendar.DATE), date.get(Calendar.MONTH) + 1 , date.get(Calendar.YEAR)};
    }
    public static void main(String[] args) {
        Main obj = new Main();
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