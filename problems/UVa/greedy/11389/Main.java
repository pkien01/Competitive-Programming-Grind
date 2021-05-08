/*input
2 20 5
10 15
10 15
2 20 5
10 10
10 10
0 0 0
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

    static int n, d, r;
    static int[] morning, evening;

    static int solve() {
    	Arrays.sort(morning);
    	Arrays.sort(evening);
    	int res = 0;
    	for (int i = 0; i < n; i++) res += Math.max(morning[i] + evening[n - i - 1] - d, 0) * r;
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); d = inp.nextInt(); r = inp.nextInt();
        	if (n == 0 && d == 0 && r == 0) break;
        	morning = new int[n]; 
        	for (int i = 0; i < n; i++) morning[i] = inp.nextInt();
        	evening = new int[n];
        	for (int i = 0; i < n; i++) evening[i] = inp.nextInt();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}