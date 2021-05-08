/*input
3
vodka
wine
beer
2
wine vodka
beer wine
5
wine
beer
rum
apple-juice
cachaca
6
beer cachaca
apple-juice beer
apple-juice rum
beer rum
beer wine
wine cachaca
10
cachaca
rum
apple-juice
tequila
whiskey
wine
vodka
beer
martini
gin
11
beer whiskey
apple-juice gin
rum cachaca
vodka tequila
apple-juice martini
rum gin
wine whiskey
apple-juice beer
beer rum
wine vodka
beer tequila
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

    static int n, m;
    static Map<String, Integer> nameId;
   	static String[] name;
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer> ans;


    static void solve_bfs() {
    	int[] deg = new int[n];
    	for (int u = 0; u < n; u++) {
    		for (Integer v: adj[u]) deg[v]++;
    	}
    	PriorityQueue<Integer> q = new PriorityQueue<>();
    	for (int i = 0; i < n; i++) if (deg[i] == 0) q.add(i);

    	ans = new ArrayList<>();
    	while (!q.isEmpty()) {
    		int u = q.poll(); 
    		ans.add(u);
    		for (Integer v: adj[u]) {
    			deg[v]--;
    			if (deg[v] == 0) q.add(v);
    		}
    	}
    }

    static boolean[] vis;
    static void solve_dfs(int u) {
    	if (vis[u]) return;
    	vis[u] = true;
    	for (Integer v: adj[u]) solve_dfs(v);
    	ans.add(u);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1; inp.hasNextInt(); test++) {
        	n = inp.nextInt(); 
        	name = new String[n];
        	nameId = new TreeMap<>();
        	for (int i = 0; i < n; i++) {
        		name[i] = inp.next();
        		nameId.put(name[i], i);
        	}
        	m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	for (int i = 0; i < m; i++) {
        		int u = nameId.get(inp.next()), v = nameId.get(inp.next());
        		adj[u].add(v);
        	}

    		/* In the case there is no relation between two beverages 
    		Dilbert should start drinking the one that appears first in the input*/
        	solve_bfs();
        	//vis = new boolean[n];
        	//for (int i = 0; i < n; i++)
        		//solve_dfs(i);
        	//Collections.reverse(ans);
        	System.out.format("Case #%d: Dilbert should drink beverages in this order: ", test);
        	for (int i = 0; i < n; i++) System.out.print(name[ans.get(i)] + (i == n - 1? ".\n": " "));
        	System.out.println();
        }
        
        //out.close();
    }
}