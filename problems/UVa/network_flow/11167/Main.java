/*input
3 1
2 2 9
2 3 5
3 5 8
2 1
4 5 9
4 8 12
5 2
2 1 3
2 3 5
2 5 7
2 1 7
4 2 6
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

    static class Monkey {
    	int amount, time_min, time_max;
    	Monkey(int amount, int time_min, int time_max) {
    		this.amount = amount; this.time_min = time_min; this.time_max = time_max;
    	}
    }
    static class Pair implements Comparable<Pair> {
    	int first, second;
    	Pair(int first, int second) {
    		this.first = first; this.second = second;
    	}
        @Override
        public int compareTo(Pair other) {
            return this.first != other.first? this.first - other.first : this.second - other.second;
        }
    	@Override
    	public String toString() {
    		return String.format("(%d,%d)", first, second);
    	}
    }

    static final int inf = (int)2e8;
    static int n, m;
    static Monkey[] arr;


    static int[][] graph;

    static int EdmondKarp(int src, int sink) {
    	int res = 0;
    	while (true) {
    		int[] par = new int[graph.length]; Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>(); queue.add(src);
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == sink) break;
    			for (int v = 0; v < graph.length; v++) {
    				if (par[v] == -1 && graph[u][v] > 0) {
    					par[v] = u;
    					queue.add(v);
    				}
    			}
    		}

    		int flow = inf;
    		for (int v = sink; v != src; v = par[v]) {
    			if (par[v] == -1) {
    				flow = 0; break;
    			}
    			flow = Math.min(flow, graph[par[v]][v]);
    		}
    		if (flow == 0) break;
    		for (int v = sink; v != src; v = par[v]) {
    			graph[par[v]][v] -= flow; graph[v][par[v]] += flow;
    		}
    		res += flow;
    	}
    	return res;
    }

    static void solve() {
    	int[] times_all = new int[n * 2];
    	for (int i = 0; i < n; i++) {
    		times_all[i*2] = arr[i].time_min; times_all[i*2 + 1] = arr[i].time_max;
    	}
    	Arrays.sort(times_all);

    	ArrayList<Integer> times = new ArrayList<>();
    	for (int i = 0; i < n*2; i++) 
    		if (times.isEmpty() || times.get(times.size() - 1) != times_all[i]) times.add(times_all[i]);

    	graph = new int[3*n + 2][3*n + 2];
    	for (int i = 0; i < n; i++) graph[0][i + 1] = arr[i].amount;
    	for (int i = 0; i < n; i++) {
    		int l = Collections.binarySearch(times, arr[i].time_min);
    		int r = Collections.binarySearch(times, arr[i].time_max);
    		for (int j = l; j < r; j++) graph[i + 1][n + 1 + j] = Math.min(times.get(j + 1) - times.get(j), arr[i].amount);
    	}
    	for (int i = 0; i < times.size() - 1; i++) 
    		graph[n + 1 + i][3*n + 1] = (times.get(i + 1) - times.get(i)) * m;

    	int sumAmount = 0;
    	for (int i = 0; i < n; i++) sumAmount += arr[i].amount;

    	int maxFlow = EdmondKarp(0, 3*n + 1);
    	if (maxFlow != sumAmount) {
    		System.out.println("No"); 
    		return;
    	}
    	System.out.println("Yes");
  
    	int[] cnt = new int[times.get(times.size() - 1) + 1];
        int[] pt = new int[times.size()];
        for (int i = 0; i < times.size(); i++) pt[i] = times.get(i);
    	for (int i = 0; i < n; i++) {
            ArrayList<Pair> segs = new ArrayList<>(); 
		    for (int j = 0; j < times.size() - 1; j++) {
		    	int curAmount = graph[n + 1 + j][i + 1];
                if (curAmount == 0) continue;
                //times.get(j) => times.get(j + 1);
                
                if (pt[j] + curAmount >= times.get(j + 1)) {
                    segs.add(new Pair(pt[j], times.get(j + 1)));
                    pt[j] = times.get(j) + curAmount - (times.get(j + 1) - pt[j]);
                    if (pt[j] != times.get(j)) segs.add(new Pair(times.get(j), pt[j]));
                }
                else {
                    segs.add(new Pair(pt[j], pt[j] + curAmount));
                    pt[j] += curAmount;
                }

		    }
            Collections.sort(segs);
            ArrayList<Pair> ans = new ArrayList<>();
            for (Pair seg: segs) {
                if (ans.isEmpty() || ans.get(ans.size() - 1).second < seg.first) 
                    ans.add(seg);
                else ans.get(ans.size() - 1).second = seg.second;
            }
            System.out.print(ans.size());
            for (Pair seg: ans) System.out.print(" " + seg);
            System.out.println();
    	}

    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	m = inp.nextInt();
        	arr = new Monkey[n]; 
        	for (int i = 0; i < n; i++) arr[i] = new Monkey(inp.nextInt(), inp.nextInt(), inp.nextInt());
        	System.out.print("Case " + test + ": ");
        	solve();
        }   
        //out.close();
    }
}