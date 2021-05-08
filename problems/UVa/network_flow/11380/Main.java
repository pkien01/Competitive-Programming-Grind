/*input
3 4 2
*~~#
...@
.~.*
3 5 1
~~*~~
#.@.#
~~*~~
1 4 2
**#~
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
    static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    static final int inf = (int)2e6;

    static class Pair {
    	int first, second;
    	Pair(int first, int second) {
    		this.first = first; this.second = second;
    	}
    	@Override
    	public String toString() {
    		return String.format("(%d,%d)", first, second);
    	}
    }

    static int n, m, woodCap;
    static char[][] arr;

    static int getId(int x, int y) {
    	if (x < 0 || x >= n || y < 0 || y >= m) return -1;
    	return x * m + y + 1;
    }
    static int findIndex(ArrayList<Pair> list, int key) {
    	for (int i = 0; i < list.size(); i++)
    		if (list.get(i).first == key) return i;
    	return -1;
    }

    static int solve() {
    	ArrayList<Pair>[] adj = new ArrayList[2 * n * m + 2];
    	for (int i = 0; i <= 2 * n * m + 1; i++) adj[i] = new ArrayList<>();

    	final int supersource = 0, supersink = n * m + 1;
    	for (int x = 0; x < n; x++) {
    		for (int y = 0; y < m; y++) {
    			if (arr[x][y] == '~') continue;
    			int inId = getId(x, y), outId = n * m + 1 + inId;
    			int cap = arr[x][y] == '.' || arr[x][y] == '*'? 1 : inf;
    			adj[inId].add(new Pair(outId, cap));
    			adj[outId].add(new Pair(inId, 0));
    			for (int i = 0; i < 4; i++) {
    				int nextId = getId(x + dx[i], y + dy[i]);
    				if (nextId == -1 || arr[x + dx[i]][y + dy[i]] == '~')  continue;
    				adj[outId].add(new Pair(nextId, inf));
    				adj[nextId].add(new Pair(outId, 0));
    			}
    			if (arr[x][y] == '*') {
    				adj[supersource].add(new Pair(inId, 1));
    				adj[inId].add(new Pair(supersource, 0));
    			}
    			else if (arr[x][y] == '#') {
    				adj[outId].add(new Pair(supersink, woodCap));
    				adj[supersink].add(new Pair(outId, 0));
    			}
    		}
    	}
    	//for (int i = 0; i <= n * m + 1; i++) System.out.println(adj[i]);
    	int ans = 0;
    	while (true) {
    		int[] par = new int[2 * n * m + 2];
    		Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>();
    		queue.add(supersource);
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == supersink) break;
    			for (int i = 0; i < adj[u].size(); i++) {
    				int v = adj[u].get(i).first, w = adj[u].get(i).second;
    				if (par[v] == -1 && w > 0) {
    					par[v] = u;
    					queue.add(v);
    				}
    			}
    		}

    		int flow = inf;
    		for (int v = supersink; v != supersource; v = par[v]) {
    			if (par[v] == -1) {
    				flow = 0; break;
    			}
    			int idx_v = findIndex(adj[par[v]], v);
    			int w = adj[par[v]].get(idx_v).second;
    			flow = Math.min(flow, w);
    		}
    		//System.out.println(flow);
    		if (flow == 0) break;
    		for (int v = supersink; v != supersource; v = par[v]) {
    			int idx_v = findIndex(adj[par[v]], v);
    			adj[par[v]].get(idx_v).second -= flow;
    			int idx_par = findIndex(adj[v], par[v]);
    			//System.out.println(par[v] + "->" + v + ": " + adj[par[v]].get(idx_v).second);
    			adj[v].get(idx_par).second += flow;
    		}
    		//System.out.println();
    		ans += flow;
    	}
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); m = inp.nextInt(); woodCap = inp.nextInt();
        	arr = new char[n][m];
        	for (int i = 0; i < n; i++) arr[i] = inp.next().toCharArray();
        	System.out.println(solve());
        }

        
        //out.close();
    }
}