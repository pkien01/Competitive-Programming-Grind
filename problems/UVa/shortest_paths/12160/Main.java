/*input
0000 9999 1
1000
0000 9999 1
0001
5234 1212 3
1023 0101 0001
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
    static final int inf = (int)1e9;
    static int src, dest, n;
    static int[] arr;
    static int solve() {
    	int[] dist = new int[10000];
    	Arrays.fill(dist, inf);
    	Queue<Integer> queue = new LinkedList<>();
    	dist[src] = 0;
    	queue.add(src);
    	while (!queue.isEmpty()) {
    		int u = queue.poll();
    		if (u == dest) return dist[u];
    		for (int i = 0; i < n; i++) {
    			int v = (u + arr[i]) % 10000;
    			if (dist[v] == inf) {
    				dist[v] = dist[u] + 1;
    				queue.add(v);
    			}
    		}
    	}
    	return dist[dest] == inf? -1: dist[dest];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	src = inp.nextInt(); dest = inp.nextInt(); n = inp.nextInt();
        	if (src == 0 && dest == 0 && n == 0) break;
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	int ans = solve();
        	System.out.println("Case " + test + ": " + (ans == -1? "Permanently Locked" : ans));
        }
        
        //out.close();
    }
}