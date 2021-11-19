/*input
3 3
2
2 1
1 2
0
4 3
2
2 1
3 1
1
3 0 2 2 0
4 2
0
1
2 0 1 0 -3
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

    static class Edge {
    	int u, v, w;
    	Edge(int u, int v, int w) {
    		this.u = u; this.v = v; this.w = w;
    	}
    }

    static int n, m;
    static int numGraves, numHoles;
    static boolean[][] grave, hole;
    static ArrayList<Edge> edges;

   	final static int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
   	final static int inf = (int)1e9;

   	static boolean valid(int x, int y) {
   		return x >= 0 && x < n && y >= 0 && y < m && !grave[x][y];
   	}
   	static int coordId(int x, int y) {
   		return m*x + y;
   	}

   	static String bellmanFord() {
   		int[] dist = new int[n*m];
   		for (int i = 0; i < n; i++) Arrays.fill(dist, inf);

   		dist[coordId(0, 0)] = 0;
   		for (int rep = 0; rep < n*m - 1; rep++) {
   			for (Edge e: edges) {
   				if (dist[e.u] < inf && dist[e.u] + e.w < dist[e.v]) dist[e.v] = dist[e.u] + e.w;
   			}
   		}

   		for (Edge e: edges) {
   			if (dist[e.u] < inf && dist[e.u] + e.w < dist[e.v]) return "Never";
   		}

   		if (dist[coordId(n - 1, m - 1)] >= inf) return "Impossible";
   		return String.valueOf(dist[coordId(n - 1, m - 1)]);
   	}


    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	grave = new boolean[n][m];
        	numGraves = inp.nextInt();
        	for (int i = 0; i < numGraves; i++) grave[inp.nextInt()][inp.nextInt()] = true;

        	edges = new ArrayList<>();
    
        	numHoles = inp.nextInt();
        	hole = new boolean[n][m]; 
        	for (int i = 0; i < numHoles; i++) {
        		int x1 = inp.nextInt(), y1 = inp.nextInt(), x2 = inp.nextInt(), y2 = inp.nextInt(), t = inp.nextInt();
        		hole[x1][y1] = true;
        		edges.add(new Edge(coordId(x1, y1), coordId(x2, y2), t));
   			}

   			for (int i = 0; i < n; i++) {
        		for (int j = 0; j < m; j++) {
        			if (grave[i][j] || hole[i][j]) continue;
        			if (i == n - 1 && j == m - 1) continue;
       
        			for (int k = 0; k < 4; k++) 
        				if (valid(i + di[k], j + dj[k])) edges.add(new Edge(coordId(i, j), coordId(i + di[k], j + dj[k]), 1));
        		}
        	}

   			System.out.println(bellmanFord());
        }
        
        
        //out.close();
    }
}