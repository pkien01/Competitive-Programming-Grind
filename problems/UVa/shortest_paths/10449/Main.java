/*input
4 1 20 19 18
4
1 2
2 4
4 3
3 2
3
2
3
4
5 1 20 5 6 4
5
1 2
2 3
3 4
4 5
5 3
1
2
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

    static int cube(int x) {
    	return x*x*x;
    }

    static int n;
    static int[] arr;
    static ArrayList<Integer>[] adj;

    static final int inf = (int)2e8;
    static int[] dist;
    static void BellmanFord() {
    	dist = new int[n];
    	Arrays.fill(dist, inf); dist[0] = 0;
    	for (int i = 0; i < n - 1; i++) {
    		for (int u = 0; u < n; u++) {
    			for (Integer v: adj[u]) {
    				int uv = cube(arr[v] - arr[u]);
    				if (dist[u] < inf && dist[u] + uv < dist[v]) dist[v] = dist[u] + uv;
    			}
    		}
    	}

    	//System.out.println(Arrays.toString(dist));

    	for (int i = 0; i < 2; i++) {
    		for (int u = 0; u < n; u++) {
    			for (Integer v: adj[u]) {
    				int uv = cube(arr[v] - arr[u]);
    				if (dist[u] + uv < dist[v]) {
    					dist[v] = -inf;
    				}
    			}
    		}
    	}

    	//System.out.println(Arrays.toString(dist));
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
     	for (int test = 1; inp.hasNextInt(); test++) {
        	n = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	int m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1; 
        		adj[u].add(v); 
        	}
        	BellmanFord();
        	int q = inp.nextInt();
        	System.out.println("Set #" + test);
        	while (q-- > 0) {
        		int dest = inp.nextInt() - 1;
        		System.out.println(dist[dest] >= inf ||  dist[dest] < 3? "?" : dist[dest]);
        	}
        }
        
        //out.close();
    }
}