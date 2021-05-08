/*input
2
5
1 2
2 3
3 4
4 5
5 6
5
2 1
2 2
3 4
3 1
2 4
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
    static int[] deg;
    static int[][] graph;
    static ArrayList<Integer> euler;

    static void dfs(int u) {
    	//euler.add(u);
    	for (int v = 0; v < 50; v++) {
    		if (graph[u][v] > 0) {
    			graph[u][v]--;
    			graph[v][u]--;
    			dfs(v);
    			euler.add(u + 1);
    		}
    	}
    }

    static void solve() {
    	boolean hasTour = true;
        int src = -1;
    	for (int i = 0; i < 50; i++) {
    		if (deg[i] % 2 != 0) {
    			hasTour = false; break;
    		}
            if (deg[i] > 0) src = i;
    	}
        if (src == -1) hasTour = false;
    	if (hasTour) {
    		euler = new ArrayList<>();
    		euler.add(src + 1);
    		dfs(src);
    		//System.out.println(euler);
    		if (euler.size() == n + 1) {
    			for (int i = 0; i < euler.size() - 1; i++) System.out.println(euler.get(i) + " " + euler.get(i + 1));
    		}
    		else System.out.println("some beads may be lost");
    	}
    	else System.out.println("some beads may be lost");
    	
    }		
    public static void main(String[] args) {      
       	//runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	graph = new int[50][50]; deg = new int[50];
        	for (int i = 0; i < n; i++) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		graph[u][v]++; graph[v][u]++;
                deg[u]++; deg[v]++;
        	}
        	if (test != 1) System.out.println();
        	System.out.println("Case #" + test);
        	solve();
        }
        
        //out.close();
    }
}