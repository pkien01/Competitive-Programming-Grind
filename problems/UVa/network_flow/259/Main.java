/*input

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

    static final int inf = 20;
    static int n = 38, total_users;
    static int[][] graph;
    static char[] ans;

    static boolean solve() {
    	int maxFlow = 0;
    	while (true) {
    		int[] par = new int[n];
    		Arrays.fill(par, -1);
    		Queue<Integer> queue = new LinkedList<>();
    		queue.add(0); par[0] = 0;
    		while (!queue.isEmpty()) {
    			int u = queue.poll();
    			if (u == n - 1) break;
    			for (int v = 0; v < n; v++) {
    				if (par[v] == -1 && graph[u][v] > 0) {
    					par[v] = u;
    					queue.add(v);
    				}
    			}
    		}

    		int flow = inf;
    		for (int v = n - 1; v != 0; v = par[v]) {
                if (par[v] == -1) {
                    flow = 0; break;
                }
                flow = Math.min(flow, graph[par[v]][v]);
            }
            if (flow == 0) break;
    		for (int v = n - 1; v != 0; v = par[v]) {
    			graph[par[v]][v] -= flow; graph[v][par[v]] += flow;
    		}
    		maxFlow += flow;
    	}
        //System.out.println(maxFlow);
    	if (maxFlow != total_users) return false;
    	ans = new char[10];
    	Arrays.fill(ans, '_');
    	for (int comp = 0; comp < 10; comp++) 
    		for (int app = 1; app <= 26; app++) 
    			if (graph[comp + 26 + 1][app] == 1) ans[comp] = (char)(app - 1 + 'A');

 		return true; 
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	graph = new int[n][n];
            for (int comp = 0; comp < 10; comp++) graph[comp + 1 + 26][n - 1] = 1;
        	total_users = 0;
         	while (inp.hasNextLine()) {
        		String line = inp.nextLine();
        		if (line.isEmpty()) break;
        		int app = (int)line.charAt(0) - 'A' + 1, users = (int)line.charAt(1) - '0';
        		graph[0][app] = users;
        		total_users += users;
        		for (int i = 3; i < line.length() - 1; i++) {
        			int comp = (int)line.charAt(i) - '0';
        			graph[app][comp + 1 + 26] = 1;
                    //System.out.println(app + " " + (comp + 1 + 26));
        		}
        	}	
            System.out.println(solve()? String.valueOf(ans) : "!");
        }
        
        //out.close();
    }
}