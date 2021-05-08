/*input
2 3
5
4
7
8
4
2 1
5
5
10
0 0
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
    static int[] knights, dragons;

    static int solve() {
    	if (knights.length < dragons.length) return -1;
    	Arrays.sort(knights); Arrays.sort(dragons);
    	int res = 0;
    	for (int i = 0, j = 0; i < dragons.length; i++) {
    		while (j < knights.length && knights[j] < dragons[i]) j++;
    		//System.out.println(knights[j]);
    		if (j >= knights.length) return -1;
    		res += knights[j];
    		j++;
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	dragons = new int[n];
        	for (int i = 0; i < n; i++) dragons[i] = inp.nextInt();
        	knights = new int[m];
        	for (int i = 0; i < m; i++) knights[i] = inp.nextInt();
        	int ans = solve();
        	System.out.println(ans >= 0? ans: "Loowater is doomed!");
        }
        
        //out.close();
    }
}