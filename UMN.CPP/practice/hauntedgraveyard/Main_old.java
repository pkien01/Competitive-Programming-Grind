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

    static class Hole {
    	int x_to, y_to, t;
    	Hole(int x_to, int y_to, int t) {
    		this.x_to = x_to; this.y_to = y_to; this.t = t;
    	}
    }

    static int n, m;
    static int[][] arr;
    static int numGraves, numHoles;
    static Hole[] holes;

   	final static int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
   	final static int inf = (int)2e8;

   	static boolean valid(int x, int y) {
   		return x >= 0 && x < n && y >= 0 && y < m && arr[x][y] >= 0;
   	}
    static String solve() {
    	int[][] dist = new int[n][m];
    	for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
    	dist[0][0] = 0;
    	for (int rep = 0; rep < n*m - 1; rep++) {
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				if (arr[i][j] == -1) continue;
                    if (i == n - 1 && j == m - 1) continue;
    				if (arr[i][j] == 0) { // grass
    					for (int k = 0; k < 4; k++) {
    						if (valid(i + di[k], j + dj[k]) && dist[i][j] + 1 < dist[i + di[k]][j + dj[k]]) 
    							dist[i + di[k]][j + dj[k]] = dist[i][j] + 1;

    					}
    				}
    				else { // hole
                        
    					for (int k = 0; k < 4; k++) {
    						if (valid(i + di[k], j + dj[k]) && dist[i][j] + 6 < dist[i + di[k]][j + dj[k]]) 
    							dist[i + di[k]][j + dj[k]] = dist[i][j] + 6;

    					}
    					Hole cur_hole = holes[arr[i][j] - 1];
                        if (dist[i][j] + cur_hole.t < dist[cur_hole.x_to][cur_hole.y_to])
    					   dist[cur_hole.x_to][cur_hole.y_to] = dist[i][j] + cur_hole.t;
    				}
    			}
    		}
    	}

    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			if (arr[i][j] == -1) continue;
    			if (arr[i][j] == 0) { // grass
    				for (int k = 0; k < 4; k++) {
    					if (valid(i + di[k], j + dj[k]) && dist[i][j] + 1 < dist[i + di[k]][j + dj[k]]) 
    						return "Never";
    				}
    			}
    			else {
    				for (int k = 0; k < 4; k++) {
    					if (valid(i + di[k], j + dj[k]) && dist[i][j] + 6 < dist[i + di[k]][j + dj[k]]) 
    						return "Never";

    				}
    				Hole cur_hole = holes[arr[i][j] - 1];
    				if (dist[i][j] + cur_hole.t < dist[cur_hole.x_to][cur_hole.y_to])
    					return "Never";
    			}
    		}
    	}

    	if (dist[n - 1][m - 1] >= inf) return "Impossible";
    	return String.valueOf(dist[n - 1][m - 1]);
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	arr = new int[n][m];
        	numGraves = inp.nextInt();
        	for (int i = 0; i < numGraves; i++) arr[inp.nextInt()][inp.nextInt()] = -1;
        	numHoles = inp.nextInt(); 
        	holes = new Hole[numHoles];
        	for (int i = 0; i < numHoles; i++) {
        		arr[inp.nextInt()][inp.nextInt()] = i + 1;
        		holes[i] = new Hole(inp.nextInt(), inp.nextInt(), inp.nextInt());
   			}

   			System.out.println(solve());
        }
        
        //out.close();
    }
}