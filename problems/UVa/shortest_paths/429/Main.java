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

    static int n;
    static ArrayList<String> words;
    static Map<String, Integer> mp;
    static ArrayList<Integer>[] adj;

    static boolean canTransform(String word1, String word2) {
    	if (word1.length() != word2.length()) return false;
    	int cntDiff = 0;
    	for (int i = 0; i < word1.length(); i++) {
    		if (word1.charAt(i) != word2.charAt(i)) cntDiff++;
    		if (cntDiff > 1) return false;
    	}
    	return true;
    }

    static final int inf = (int)1e9;
    static int[][] dist;
    static void bfs(int src) {
    	Queue<Integer> queue = new LinkedList<>();
    	Arrays.fill(dist[src], inf);
    	dist[src][src] = 0;
    	queue.add(src);
    	while (!queue.isEmpty()) {
    		int u = queue.poll();
    		for (Integer v: adj[u]) {
    			if (dist[src][v] == inf) {
    				dist[src][v] = dist[src][u] + 1;
    				queue.add(v);
    			}
    		}
    	}
    }
    static void preprocess() {
    	n = words.size();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {
        		if (canTransform(words.get(i), words.get(j))) {
        			adj[i].add(j);
        			adj[j].add(i);
        		}
        	}
        }
        dist = new int[n][n];
        for (int i = 0; i < n; i++) bfs(i);
    } 
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt(); inp.nextLine();
        while (numTests-- > 0) {
        	mp = new TreeMap<>();
        	words = new ArrayList<>();
        	while (true) {
        		String line = inp.nextLine();
        		if (line.equals("*")) break;
        		words.add(line);
        		mp.put(line, words.size() - 1);
        	}
        	preprocess();
        	while (inp.hasNextLine()) {
        		String line = inp.nextLine();
        		//System.out.println(line);
        		if (line.equals("")) break;
        		String[] line_split = line.split(" ");
        		System.out.println(line + " " + dist[mp.get(line_split[0])][mp.get(line_split[1])]);
        	}
        	if (numTests != 0) System.out.println();
        }
        //out.close();
    }
}