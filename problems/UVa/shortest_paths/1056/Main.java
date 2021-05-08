/*input
4 4
Ashok Kiyoshi Ursala Chun Ursala Kiyoshi
Kiyoshi Chun
4 2
Ashok Chun Ursala Kiyoshi
6 5
Bubba Cooter Ashok Kiyoshi Ursala Chun
Ursala Kiyoshi Kiyoshi Chun
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

    static final int inf = (int)2e8;
    
    static int n, m;
    static TreeMap<String, Integer> id;
    static int[][] dist;

    static int solve() {
    	for (int k = 0; k < n; k++)
    		for (int i = 0; i < n; i++) 
    			for (int j = 0; j < n; j++)
    				dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

    	int ans = 0;
    	for (int i = 0; i < n; i++) 
    		for (int j = i + 1; j < n; j++) ans = Math.max(ans, dist[i][j]);
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	id = new TreeMap<>();
        	int cntId = 0;
        	dist = new int[n][n];
        	for (int i = 0; i < n; i++) 
        		for (int j = 0; j < n; j++) dist[i][j] = i == j? 0: inf;
        	while (m-- > 0) {
        		String u = inp.next(), v = inp.next();
        		if (!id.containsKey(u)) id.put(u, cntId++);
        		if (!id.containsKey(v)) id.put(v, cntId++);
        		int u_id = id.get(u), v_id = id.get(v);
        		dist[u_id][v_id] = dist[v_id][u_id] = 1;
        	}
        	int ans = solve();
        	System.out.println("Network " + test + ": " + (ans < inf? ans : "DISCONNECTED") + "\n");
        }
        
        //out.close();
    }
}