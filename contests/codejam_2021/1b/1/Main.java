/*input
1
0 11 719
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming/io";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static final BigDecimal cycle = new BigDecimal(360L * 12L * (long)1e10);
    static double acc = 0.01;

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();

        for (int test = 1; test <= numTests; test++) {
        	long a = inp.nextLong(), b = inp.nextLong(), c = inp.nextLong();

        	BigDecimal hour = (new BigDecimal(a)).divide(cycle, 1000, BigDecimal.ROUND_HALF_UP), min = (new BigDecimal(b)).divide(cycle, 1000, BigDecimal.ROUND_HALF_UP),  sec = (new BigDecimal(c)).divide(cycle, 1000, BigDecimal.ROUND_HALF_UP);
        	hour = hour.multiply(new BigDecimal(12));
        	min = min.multiply(new BigDecimal(60));
        	sec = sec.multiply(new BigDecimal(60));
        	BigDecimal nano = sec.remainder(BigDecimal.ONE).multiply(new BigDecimal(1e9));
        	System.out.println(nano);
        	System.out.println("Case #" + test + ": " + hour.longValue() + " " + min.longValue() + " " + sec.longValue() + " " + nano.longValue());
        }
        
        //out.close();
    }
}