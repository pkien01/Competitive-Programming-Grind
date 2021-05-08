/*input
4
0 1 2
-100 1 3
1 1 4
0 0
-1
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

    static int n;
    static int[] energy;
    static ArrayList<Integer>[] adj;

    static final int inf = (int)2e8;
    static boolean solve() {
    	int[] dist = new int[n];
    	Arrays.fill(dist, -inf); dist[0] = 100;

    	for (int i = 0; i < n - 1; i++) {
    		for (int u = 0; u < n; u++) {
    			for (Integer v: adj[u]) {
    				if (dist[u] + energy[v] > 0 && dist[u] + energy[v] > dist[v]) 
    					dist[v] = dist[u] + energy[v];
    			}
    		}
    	}
    	
    	for (int i = 0; i < 2; i++) {
    		for (int u = 0; u < n; u++) {
    			if (dist[u] == -inf) continue;
    			for (Integer v: adj[u]) 
    				if (dist[u] + energy[v] > 0 && dist[u] + energy[v] > dist[v]) dist[v] = inf;
    		}
    	}
    	return dist[n - 1] > 0;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); 
        	if (n == -1) break;
        	energy = new int[n];
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) {
        		energy[i] = inp.nextInt();
        		int m = inp.nextInt();
        		adj[i] = new ArrayList<>();
        		while (m-- > 0) adj[i].add(inp.nextInt() - 1);
        	}
        	System.out.println(solve()? "winnable" : "hopeless");
        }
        
        
        //out.close();
    }
}