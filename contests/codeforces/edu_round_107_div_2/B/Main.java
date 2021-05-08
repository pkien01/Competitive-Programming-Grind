/*input
5
2 3 1
2 2 2
6 6 2
1 1 1
7 6 4
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
        	int a = inp.nextInt(), b = inp.nextInt(), c = inp.nextInt();
        	StringBuilder x = new StringBuilder(), y = new StringBuilder();
        	for (int i = 0; i < c - 1; i++) {
        		x.append('0');
        		y.append('0');
        	}
        	int minLen = Math.min(a, b);
        	int x_front = 1, y_front = 1;
        	int rem_a = a - (c - 1), rem_b = b - (c - 1);
        	while (String.valueOf(x_front).length() < rem_a) x_front *= 7;
        	while (String.valueOf(y_front).length() < rem_b) y_front *= 3;

        	System.out.println(x_front + x.reverse().toString() + " " + y_front + y.reverse().toString());
        }
        
        //out.close();
    }
}