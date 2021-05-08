/*input
5
12 -4
-10 4
9
3
-2 -1 -2
0
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
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	if (n == 0) break;
        	int sum = 0, ans = (int)-1e9;
        	while (n-- > 0) {
        		int x = inp.nextInt();
        		sum += x;
        		ans = Math.max(ans, sum);
        		if (sum < 0) sum = 0;
        	}
        	System.out.println(ans > 0? "The maximum winning streak is " + ans + ".": "Losing streak.");
        }
        
        //out.close();
    }
}