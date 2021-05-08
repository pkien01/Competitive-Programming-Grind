/*input
5
1 1 1
2 2 3
3 5 11
100 100 7312
1000000 1000000 1000000000000
*/
import java.util.*;
import java.io.*;

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
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	long n = inp.nextLong(), m = inp.nextLong(), idx = inp.nextLong() - 1;
        	long y = idx / n, x = idx % n;
        	//System.out.println(x + " " + y);
        	System.out.println(x * m + y + 1);
        }
        
        //out.close();
    }
}