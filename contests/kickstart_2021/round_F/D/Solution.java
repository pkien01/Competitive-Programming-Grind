/*input
3
4 3 3
1 3 1
1 1 1
2 4 1
2 3 1
0 1
1 2
2 3
4 5 3
1 3 1
1 1 1
2 4 1
2 3 1
0 1
1 2
2 3
3 0
0 2
4 1 2
0 4 1
0 4 1
0 4 2
0 4 2
0 1
*/
import java.util.*;
import java.io.*;


public class Solution {
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

    static class Room {
    	int l, r, val;
    	Room(int l, int r, int val) {
    		this.l = l; this.r = r; this.val = val;
    	}
    }

    static int n, m;
    static long k;
    static Room[] rooms;
    static ArrayList<Integer>[] adj;
    static long[][] dp;

    static boolean reachable(int from, int to, int mask) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[n];
        queue.add(from); vis[from] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v: adj[u]) {
                if (v == to) return true;
                if (!vis[v] && (mask >> v & 1) == 1) {
                    vis[v] = true;
                    queue.add(v);
                }
            }
        }
        return vis[to];
    }
    static long solve(int u, int mask) {
    	long sum = 0L;
    	for (int i = 0; i < n; i++) 
    		if ((mask >> i & 1) == 1) sum += rooms[i].val;

        if (sum > k) return 0l;
    	if (sum == k) return 1L;
        if (mask == (1 << n) - 1) return 0l;
    	if (dp[u][mask] != -1L) return dp[u][mask];
    	long res = 0;
        /*
    	for (Integer v: adj[u]) {
            if (v == prev) continue;
    		if ((mask >> v & 1) == 1) {
    			res += solve(v, u, mask);
    		    continue;
            }
    		else if (sum >= rooms[v].l && sum <= rooms[v].r) {
    			res += solve(v, u, mask | (1 << v));
    		}
    	}*/

        for (int v = 0; v < n; v++) {
            if (v != u && reachable(u, v, mask) && (mask >> v & 1) == 0) {
                if (sum >= rooms[v].l &&  sum <= rooms[v].r)
                    res += solve(v, mask | (1 << v));
            }
        }
    	dp[u][mask] = res;
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); m = inp.nextInt(); k = inp.nextLong();
        	rooms = new Room[n];
        	for (int i = 0; i < n; i++) rooms[i] = new Room(inp.nextInt(), inp.nextInt(), inp.nextInt());
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	for (int i = 0; i < m; i++) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		adj[u].add(v); adj[v].add(u);
        	}
            dp = new long[n][1 << n];
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1L);
            long ans = 0;
            for (int i = 0; i < n; i++) {
                long cur_ans = solve(i, (1 << i));
                //System.out.println(i + ": " + cur_ans);
                ans += cur_ans;
            }
        	System.out.format("Case #%d: %d\n", test, ans);
        }
        
        //out.close();
    }
}