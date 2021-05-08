/*input
2
2 e1 e2
2 e3 e4
1
e3 e1
3
3 one two three
2 four five
3 six seven eight
2
one four
five six
1
3 x y zee
0
2
2 alpha beta
1 gamma
1
gamma beta
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
    static class Edge {
    	int first, second;
    	Edge(int first, int second) {
    		this.first = first; this.second = second;
    	}
    }
    
    static int n;
    static TreeMap<String, Integer> id;
    static ArrayList<String> names;
    static boolean[][] graph;

    static ArrayList<Edge> solve() {
    	for (int k = 0; k < n; k++)
    		for (int i = 0; i < n; i++)
    			for (int j = 0; j < n; j++) graph[i][j] = graph[i][j] || (graph[i][k] && graph[k][j]);

    	ArrayList<Edge> ans = new ArrayList<>();
    	for (int i = 0; i < n; i++)
    		for (int j = i + 1; j < n; j++) if (!graph[i][j] && !graph[j][i]) ans.add(new Edge(i, j));
    	
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	int nc = inp.nextInt();
        	if (nc == 0) break;
        	id = new TreeMap<>();
        	names = new ArrayList<>();
        	ArrayList<Edge> edges = new ArrayList<>();
        	while (nc-- > 0) {
        		int ne = inp.nextInt();
        		int prev = -1;
        		while (ne-- > 0) {
        			String s = inp.next();
        			if (!id.containsKey(s)) {
        				id.put(s, names.size());
        				names.add(s);
        			}
        			if (prev != -1) edges.add(new Edge(prev, id.get(s)));
        			prev = id.get(s);
        		}
        	}
        	int nm = inp.nextInt();
        	while (nm-- > 0) edges.add(new Edge(id.get(inp.next()), id.get(inp.next())));

        	n = names.size();
        	graph = new boolean[n][n];
        	for (Edge e: edges) graph[e.first][e.second] = true;
        	ArrayList<Edge> ans = solve();
        	if (ans.size() == 0) {
        		System.out.format("Case %d, no concurrent events.\n", test);
        	}
        	else {
        		System.out.format("Case %d, %d concurrent events:\n", test, ans.size());
        		for (int i = 0; i < ans.size() && i < 2; i++) {
        			System.out.format("(%s,%s) ", names.get(ans.get(i).first), names.get(ans.get(i).second));
        		}
        		System.out.println();
        	}
        }
        
        //out.close();
    }
}