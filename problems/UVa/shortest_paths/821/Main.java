/*input
1 2 2 4 1 3 3 1 4 3 0 0
1 2 1 4 4 2 2 7 7 1 0 0
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
    static final int inf = (int)2e7;
    static int[][] dist;
    static void floyd() {
    	for (int k = 0; k < 100; k++) 
    		for (int i = 0; i < 100; i++)
    			for (int j = 0; j < 100; j++) dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
	static void bfs() {
		for (int src = 0; src < 100; src++) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(src); 
			boolean[] vis = new boolean[100];
			while (!queue.isEmpty()) {
				int u = queue.poll();
				vis[u] = true;
				for (int v = 0; v < 100; v++) {
					if (dist[u][v] == 1 && !vis[v]) {
						if (dist[src][v] == inf) dist[src][v] = dist[src][u] + 1; 
						queue.add(v);
					}
				}
			}
		}
	}
	static double solve() {
		int sum = 0, npaths = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (i != j && dist[i][j] != inf) {
					sum += dist[i][j]; npaths++;
				}
			}
		}
		return (double) sum / npaths;
	}
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	dist = new int[100][100];
        	for (int i = 0; i < 100; i++) {
        		Arrays.fill(dist[i], inf);
        		dist[i][i] = 0;
        	}
        	int u = inp.nextInt(), v = inp.nextInt();
        	if (u == 0 && v == 0) break;
        	dist[u - 1][v - 1] = 1;
        	while (true) {
        		u = inp.nextInt(); v = inp.nextInt();
        		if (u == 0 && v == 0) break;
        		dist[u - 1][v - 1] = 1;
        	}
        	floyd();
        	System.out.format("Case %d: average length between pages = %.3f clicks\n", test, solve());

        }
        
        //out.close();
    }
}