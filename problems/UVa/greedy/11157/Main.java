/*input
3
1 10
B-5
1 10
S-5
2 10
B-3 S-6
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
    static int n, d;
    static char[] type;
    static int[] dist;

    static int solve() {
        boolean[] drown = new boolean[n + 2];
    	int res = 0;
    	for (int i = 0; i <= n; i++) {
            if (type[i] == 'S') drown[i] = true;
            if (type[i + 1] == 'S') {
                res = Math.max(res, dist[i + 2] - dist[i]);        
                i++;
            }   
            else  res = Math.max(res, dist[i + 1] - dist[i]);
    	}
        int prev = n + 1;
        for (int i = n; i >= 0; i--) {
            if (!drown[i]) {
                res = Math.max(res, dist[prev] - dist[i]);
                prev = i;
            }
        }
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); d = inp.nextInt();
        	type = new char[n + 2]; dist = new int[n + 2];
            dist[0] = 0;
        	for (int i = 1; i <= n; i++) {
        		String[] stone = inp.next().split("-");
        		type[i] = stone[0].charAt(0);
        		dist[i] = Integer.parseInt(stone[1]);
        	}
            dist[n + 1] = d;
        	System.out.println("Case " + test + ": " + solve());
        }
        
        //out.close();
    }
}