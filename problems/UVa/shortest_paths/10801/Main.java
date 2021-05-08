/*input
2 30
10 5
0 1 3 5 7 9 11 13 15 20 99
4 13 15 19 20 25 30
2 30
10 1
0 5 10 12 14 20 25 30
2 4 6 8 10 12 14 22 25 28 29
3 50
10 50 100
0 10 30 40
0 20 30
0 20 50
1 1
2
0 2 4 6 8 10
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

    static class State implements Comparable<State> {
    	int elev, floor, weight;
    	State(int elev, int floor, int weight) {
    		this.elev = elev; this.floor = floor; this.weight = weight;
    	}
    	@Override
    	public int compareTo(State other) {
    		return Integer.compare(weight, other.weight);
    	}
    }
    static final int inf = (int)1e9;
    static int n, k;
    static int[] cost;
    static boolean[][] reach; 

    static int solve() {
    	int[][] dist = new int[n][100];
    	for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
    	PriorityQueue<State> pq = new PriorityQueue<>();
    	for (int i = 0; i < n; i++) {
    		if (reach[i][0]) {
    			dist[i][0] = 0;
    			pq.add(new State(i, 0, 0));
    		}
    	}
    	int ans = inf;
    	while (!pq.isEmpty()) {
    		State u = pq.poll();
    		if (u.weight > dist[u.elev][u.floor]) continue;
    		
    		if (u.floor == k) {
    			ans = Math.min(ans, dist[u.elev][u.floor]);
    		}
    		for (int i = 0; i < 100; i++) {
    			if (i == u.floor || !reach[u.elev][i]) continue;
    			int w = cost[u.elev] * Math.abs(i - u.floor);
    			if (dist[u.elev][i] > dist[u.elev][u.floor] + w) {
    				dist[u.elev][i] = dist[u.elev][u.floor] + w;
    				pq.add(new State(u.elev, i, dist[u.elev][i]));
    			}
    		}

    		for (int i = 0; i < n; i++) {
    			if (i == u.elev || !reach[i][u.floor]) continue;
    			if (dist[i][u.floor] > dist[u.elev][u.floor] + 60) {
    				dist[i][u.floor] = dist[u.elev][u.floor] + 60;
    				pq.add(new State(i, u.floor, dist[i][u.floor]));
    			}
    		}
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	String[] line_split = inp.nextLine().split(" ");
        	n = Integer.parseInt(line_split[0]); k = Integer.parseInt(line_split[1]);
        	line_split = inp.nextLine().split(" ");
        	cost = new int[n];
        	for (int i = 0; i < n; i++) cost[i] = Integer.parseInt(line_split[i]);
        	reach = new boolean[n][100];
        	for (int i = 0; i < n; i++) {
        		line_split = inp.nextLine().split(" ");
        		for (String s: line_split) reach[i][Integer.parseInt(s)] = true;
        	}     
        	int ans = solve();
        	System.out.println(ans < inf? ans : "IMPOSSIBLE");
        }
        
        //out.close();
    }
}