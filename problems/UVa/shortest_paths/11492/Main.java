/*input
4
portugues frances
ingles espanhol red
espanhol portugues amigo
frances ingles date
espanhol ingles actual 4
portugues alemao
ingles espanhol red
espanhol portugues amigo
frances ingles date
espanhol ingles actual 6
portugues frances
ingles espanhol red
espanhol portugues amigo
frances ingles date
frances espanhol la
portugues ingles a
espanhol ingles actual 0
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
    static class Edge implements Comparable<Edge> {
    	int vertex, weight;
    	Edge(int vertex, int weight) {
    		this.vertex = vertex; this.weight = weight;
    	}
    	@Override 
    	public int compareTo(Edge other) {
    		return Integer.compare(weight, other.weight);
    	}
    }
    static final int inf = (int)2e8;
    static int n;
    static String src, dest;
    static String[] words;
    static String[][] lang;
    static TreeMap<String, ArrayList<Integer>> dict;

    static int solve() {
    	if (!dict.containsKey(src) || !dict.containsKey(dest)) return inf;
    	int[] dist = new int[n];
    	Arrays.fill(dist, inf);
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	for (Integer u: dict.get(src)) {
    		dist[u] = words[u].length();
    		pq.add(new Edge(u, dist[u]));
    	}
    	while (!pq.isEmpty()) {
    		Edge top = pq.poll();
    		int u = top.vertex;
    		if (top.weight > dist[u]) continue;
    		for (int i = 0; i < 2; i++) {
    			if (!dict.containsKey(lang[u][i])) continue;
    			for (Integer v: dict.get(lang[u][i])) {
    				if (v == u || words[v].charAt(0) == words[u].charAt(0)) continue;
    				int w = words[v].length();
    				if (dist[u] + w < dist[v]) {
    					dist[v] = dist[u] + w;
    					pq.add(new Edge(v, dist[v]));
    				}
    			}
    		}	
    	}
    	int ans = inf;
    	for (Integer u: dict.get(dest)) ans = Math.min(ans, dist[u]);
    	return ans;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	src = inp.next(); dest = inp.next();
        	words = new String[n];
        	lang = new String[n][2];
        	dict = new TreeMap<>();
        	for (int i = 0; i < n; i++) {
        		lang[i][0] = inp.next(); lang[i][1] = inp.next();

        		if (!dict.containsKey(lang[i][0])) dict.put(lang[i][0], new ArrayList<>());
        		dict.get(lang[i][0]).add(i);
        		if (!dict.containsKey(lang[i][1])) dict.put(lang[i][1], new ArrayList<>());
         		dict.get(lang[i][1]).add(i);

        		words[i] = inp.next();
        	}
        	int ans = solve();
        	System.out.println(ans < inf? ans : "impossivel");
        }
        
        //out.close();
    }
}