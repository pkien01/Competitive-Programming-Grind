/*input
12:00
9:00
8:10
0:00
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
    double solve(String timeStr) {
        String[] timeArr = timeStr.split(":");
        double hour = (double)Integer.parseInt(timeArr[0]), minu = (double)Integer.parseInt(timeArr[1]);
        if (minu > 0) hour += minu / 60;
        double res = Math.abs(hour / 12 * 360.0 - minu / 60 * 360.0);
        if (res > 180) res = 360.0 - res;
        return res; 
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextLine()) {
            String s = inp.nextLine();
            if (s.equals("0:00")) break;
            System.out.format("%.3f\n", obj.solve(s));
        }
    }
}