/*input
8 8
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0
1 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
7 4 1 1 east
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


    static final int inf = (int)1e9;
    static int m, n;
    static boolean[][] arr;

    static class State {
    	int x, y, orient;
    	static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    	State(int x, int y, int orient) {
    		this.x = x; this.y = y; this.orient = orient;
    	}
    	State turnLeft() {
    		return new State(x, y, (orient + 4 - 1) % 4);
    	}
    	State turnRight() {
    		return new State(x, y, (orient + 1) % 4);
    	}
    	boolean isValid() {
    		if (x <= 0 || x >= m || y <= 0 || y >= n) return false;
    		if (x > 0 && y < n && arr[x - 1][y]) return false;
    		if (x < m && y > 0 && arr[x][y - 1]) return false;
    		if (x > 0 && y > 0 && arr[x - 1][y - 1]) return false;
    		if (x < m && y < n && arr[x][y]) return false;
    		return true;
    	}
    	State go(int n_steps) {
    		return new State(x + n_steps * dx[orient], y + n_steps * dy[orient], orient);
    	}
    	public String toString() {
    		return String.format("(x=%d, y=%d, orient=%d)",x, y, orient);
    	}
    }

    static int solve(State src, State dest) {
    	if (!src.isValid() || !dest.isValid()) return -1;
    	int[][][] dist = new int[m + 1][n + 1][4];
    	for (int i = 0; i <= m; i++)
    		for (int j = 0; j <= n; j++) Arrays.fill(dist[i][j], inf);
    	Queue<State> queue = new LinkedList<>();

    	dist[src.x][src.y][src.orient] = 0;
    	queue.add(src);
    	while (!queue.isEmpty()) {
    		//System.out.println(queue.toString());
    		State u = queue.poll();
    		if (u.x == dest.x && u.y == dest.y) break;
    		State v = u.turnLeft();
    		if (dist[v.x][v.y][v.orient] == inf) {
    			dist[v.x][v.y][v.orient] = dist[u.x][u.y][u.orient] + 1;
    			queue.add(v);
    		}	
    		v = u.turnRight();
    		if (dist[v.x][v.y][v.orient] == inf) {
    			dist[v.x][v.y][v.orient] = dist[u.x][u.y][u.orient] + 1;
    			queue.add(v);
    		}	
    		for (int i = 1; i <= 3; i++) {
    			v = u.go(i);
    			if (!v.isValid()) break;
    			if (dist[v.x][v.y][v.orient] == inf) {
    				dist[v.x][v.y][v.orient] = dist[u.x][u.y][u.orient] + 1;
    				queue.add(v);
    			}	
    		}
    	}
    	int ans = inf;
    	for (int i = 0; i < 4; i++) {
    		ans = Math.min(ans, dist[dest.x][dest.y][i]);
    	}
    	
    	return ans == inf? -1: ans;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        Map<String, Integer> orientId = new TreeMap<>();
        orientId.put("north", 0);
        orientId.put("east", 1);
        orientId.put("south", 2);
        orientId.put("west", 3);
        while (true) {
        	m = inp.nextInt(); n = inp.nextInt();
        	if (m == 0 && n == 0) break;
        	arr = new boolean[m][n];
        	for (int i = 0; i < m; i++)
        		for (int j = 0; j < n; j++) arr[i][j] = inp.nextInt() == 1;
        	State src = new State(inp.nextInt(), inp.nextInt(), -1);
        	State dest = new State(inp.nextInt(), inp.nextInt(), -1);
        	src.orient = orientId.get(inp.next());
        	System.out.println(solve(src, dest));
        }

        
        //out.close();
    }
}