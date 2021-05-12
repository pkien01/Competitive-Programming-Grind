/*input
3
1 0
1 0
2 1 0
2 0 1
5 7 5 10 20 25
5 7 14 21 28 100
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
    static int[] a, b;

    static ArrayList<Integer>[] adj;
    static boolean[] vis;
    static int[] match;

    static boolean kuhn(int u) {
    	if (vis[u]) return false;
    	vis[u] = true;
     	for (Integer v: adj[u]) {
    		if (match[v] == -1 || kuhn(match[v])) {
    			match[v] = u;
    			return true;
    		}
    	}
    	return false;
    }

    static int solve() {
    	adj = new ArrayList[n + m];
    	for (int i = 0; i < n + m; i++) adj[i] = new ArrayList<>();
    	for (int i = 0; i < n; i++) 
    		for (int j = 0; j < m; j++)
    			if ((a[i] == 0 && b[j] == 0) || (a[i] != 0 && b[j] % a[i] == 0)) adj[i].add(j + n);

    	vis = new boolean[n];
    	match = new int[n + m]; Arrays.fill(match, -1);
    	int ans = 0;
    	for (int v = 0; v < n; v++) {
    		Arrays.fill(vis, false);
    		if (kuhn(v)) ans++;
    	}
    	return ans;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); a = new int[n];
        	for (int i = 0; i < n; i++) a[i] = inp.nextInt();
        	m = inp.nextInt(); b = new int[m];
        	for (int i = 0; i < m; i++) b[i] = inp.nextInt();

        	System.out.println("Case " + test + ": " + solve());
        }
        
        //out.close();
    }
}