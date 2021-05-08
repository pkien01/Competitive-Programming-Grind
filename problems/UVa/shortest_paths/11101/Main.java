/*input
4
0 0 0 1 1 1 1 0
6
4 3 4 2 3 2
2 2 2 3
3 3
0
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

    static class Point {
    	int x, y;
    	static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    	Point(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	boolean isValid() {
    		return x >= 0 && x <= 2000 && y >= 0 && y <= 2000;
    	}
    	Point move(int dir) {
    		return new Point(x + dx[dir], y + dy[dir]);
    	}
    }

    static final int inf = (int)1e9;
    static int[][] dist;
    static Queue<Point> queue; 

    static void bfs() {
    	while (!queue.isEmpty()) {
    		Point u = queue.poll();
    		for (int i = 0; i < 4; i++) {
    			Point v = u.move(i);
    			if (v.isValid() && dist[v.x][v.y] == inf) {
    				dist[v.x][v.y] = dist[u.x][u.y] + 1;
    				queue.add(v);
    			}
    		}
    	}
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        dist = new int[2001][2001];
        while (true) {
        	int n = inp.nextInt();
        	if (n == 0) break;
        	for (int i = 0; i <= 2000; i++) Arrays.fill(dist[i], inf);
        	queue = new LinkedList<>();
        	while (n-- > 0) {
        		Point p = new Point(inp.nextInt(), inp.nextInt());
        		dist[p.x][p.y] = 0;
        		queue.add(p);
        	}
        	bfs();
        	int m = inp.nextInt();
        	int ans = inf;
        	/*for (int i = 0; i <= 5; i++) {
        		for (int j = 0; j <= 5; j++) System.out.print(dist[i][j] + (j == 5? "\n": " "));
        	}*/
        	while (m-- > 0) {
        		Point p = new Point(inp.nextInt(), inp.nextInt());
        		ans = Math.min(ans, dist[p.x][p.y]);
        	}
        	System.out.println(ans);
        }
        
        //out.close();
    }
}