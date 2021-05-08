/*input
2
12
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
    static void solve(int k) {
    	StringBuilder output = new StringBuilder();
    	int ans = 0;
    	for (int y = k + 1; y <= k * 2; y++) {
    		if ((y * k) % (y - k) != 0) continue;
    		int x = y * k / (y - k);
    		ans++;
    		output.append(String.format("1/%d = 1/%d + 1/%d\n", k, x, y));
    	}
    	System.out.println(ans);
    	System.out.print(output.toString());
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int k = inp.nextInt();
        	solve(k);
        }
    }
}