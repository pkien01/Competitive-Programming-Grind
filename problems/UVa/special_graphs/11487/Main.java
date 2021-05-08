/*input
3
B..
.A.
#.C
3
B..
DA.
..C
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

  
    static final int inf = (int)1e9, mod = 20437;
    static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};

    static int n, maxFood;
    static char[][] arr;

	static int[][][][] dp;

	static class Point {
    	int x, y, cur_food;
    	Point(int x, int y, int cur_food) {
    		this.x = x; this.y = y; this.cur_food = cur_food;
    	}
    	Point(int x, int y) {
    		this(x, y, -1);
    	}
    	boolean equals(Point other) {
    		return x == other.x && y == other.y && cur_food == other.cur_food;
    	}
    }
    static Point[] foods;

    static boolean validPos(int x, int y) {
    	return x >= 0 && x < n && y >= 0 && y < n && arr[x][y] != '#';
    }
    static int solve(int x, int y, int cur_food, int rem_dist) {
    	if (rem_dist == 0) return cur_food == maxFood && arr[x][y] == (char)(maxFood + 'A')? 1 : 0; 
    	if (dp[x][y][cur_food][rem_dist] != -1) return dp[x][y][cur_food][rem_dist];
    	//System.out.println(x + " " + y + " " + (char)(cur_food + 1 + 'A') +  " " + rem_dist);
    	int res = 0;
    	for (int i = 0; i < 4; i++) {
    		if (!validPos(x + dx[i], y + dy[i])) continue;
    		if (arr[x + dx[i]][y + dy[i]] == '.') res = (res + solve(x + dx[i], y + dy[i], cur_food, rem_dist - 1)) % mod;
    		else { 
    			int foodNum = (int)arr[x + dx[i]][y + dy[i]] - 'A';
    			if (foodNum  == cur_food + 1) 
    				res = (res + solve(x + dx[i], y + dy[i], cur_food + 1, rem_dist - 1)) % mod;
    			else if (foodNum <= cur_food)
    				res = (res + solve(x + dx[i], y + dy[i], cur_food, rem_dist - 1)) % mod;
    		}
    	}
    	dp[x][y][cur_food][rem_dist] = res;
    	return res;
    }

    static int bfs() {
    	Queue<Point> queue = new LinkedList<>();
    	queue.add(new Point(foods[0].x, foods[0].y, 0));
    	int[][][] dist = new int[n][n][26];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++) Arrays.fill(dist[i][j], inf);
    	dist[foods[0].x][foods[0].y][0] = 0;

    	while (!queue.isEmpty()) {
    		Point u = queue.poll();
    		if (u.equals(new Point(foods[maxFood].x, foods[maxFood].y, maxFood))) break;
    		for (int i = 0; i < 4; i++) {
    			Point v = new Point(u.x + dx[i], u.y + dy[i]);
    			if (!validPos(v.x, v.y)) continue;
    			if ((int)arr[v.x][v.y] - 'A' == u.cur_food + 1) v.cur_food = u.cur_food + 1;
    			else if ((int)arr[v.x][v.y] - 'A' <= u.cur_food) v.cur_food = u.cur_food;
    			else continue;
    			if (dist[v.x][v.y][v.cur_food] == inf) {
    				dist[v.x][v.y][v.cur_food] = dist[u.x][u.y][u.cur_food] + 1;
    				queue.add(v);
    			}
    		}
    	}
    	return dist[foods[maxFood].x][foods[maxFood].y][maxFood];
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        for (int test = 1;; test++) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	arr = new char[n][n];
        	foods = new Point[26];
        	maxFood = 0;
        	for (int i = 0; i < n; i++) {
        		arr[i] = inp.next().toCharArray();
        		for (int j = 0; j < n; j++) {
        			if (arr[i][j] >= 'A' && arr[i][j] <= 'Z') {
        				foods[(int)arr[i][j] - 'A'] = new Point(i, j);
        				maxFood = Math.max(maxFood, (int)arr[i][j] - 'A');
        			}
        		}
        	}
        	System.out.print("Case " + test + ": ");
        	int totalDist = bfs();
        	if (totalDist == inf) {
        		System.out.println("Impossible"); continue;
        	}
        	dp = new int[n][n][maxFood + 1][totalDist + 1];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < n; j++)
        			for (int k = 0; k <= maxFood; k++) Arrays.fill(dp[i][j][k], -1);

        	System.out.println(totalDist + " " + solve(foods[0].x, foods[0].y, 0, totalDist));

        }
        //out.close();
    }
}