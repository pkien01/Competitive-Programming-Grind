/*input
5 4
1 2
2 3
1 3
1 5
0 0
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
    static ArrayList<Integer>[] adj;
    static int[] deg;

    static ArrayList<Integer> topoSort() {
    	Queue<Integer> q = new LinkedList<>();
    	for (int i = 0; i < n; i++) 
    		if (deg[i] == 0) q.add(i);
    	
    	//System.out.println(deg);

    	ArrayList<Integer> res = new ArrayList<>();
    	while (!q.isEmpty()) {
    		int u = q.poll();
    		res.add(u);
    		for (Integer v: adj[u]) {
    			deg[v]--;
    			if (deg[v] == 0) q.add(v);
    		}
    	}
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true)  {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	deg = new int[n];
        	for (int i = 0; i < m; i++) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		adj[u].add(v);
        		deg[v]++;
        	}
        	ArrayList<Integer> ans = topoSort();
        	for (int i = 0; i < n; i++) System.out.print((ans.get(i) + 1) + (i == n - 1? "\n": " "));
        }
        
        //out.close();
    }
}