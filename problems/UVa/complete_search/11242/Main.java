/*input
2 4
40 50
12 14 16 19
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
    static int n, m;
    static int[] front, rear;
    static double solve() {
    	ArrayList<Double> ratios = new ArrayList<>();
        for (int i = 0; i < n; i++)
        	for (int j = 0; j < m; j++) ratios.add((double)rear[j] / front[i]);
        Collections.sort(ratios);
    	double ans = 0.0;
    	for (int i = 0; i < ratios.size() - 1; i++) 
    		if (ratios.get(i) != ratios.get(i + 1)) ans = Math.max(ans, ratios.get(i + 1) / ratios.get(i));
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	m = inp.nextInt();
        	front = new int[n]; rear = new int[m];
        	for (int i = 0; i < n; i++) front[i] = inp.nextInt();
        	for (int i = 0; i < m; i++) rear[i] = inp.nextInt();
        	System.out.format("%.2f\n", solve());
        }
    }
}