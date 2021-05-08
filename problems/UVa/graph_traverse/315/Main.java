/*input
7
1 2 5 6
2 4 5 6
3 5 7
4 5
5 7
0
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

    static int n;
    static ArrayList<Integer>[] adj;

    static int cntNum, rootChildren;
    static int[] dfs_num, dfs_low;
    static boolean[] criticals;
    static void dfs(int u, int p) {
    	dfs_num[u] = dfs_low[u] = ++cntNum;
    	for (Integer v: adj[u]) {
    		if (v == p) continue;
    		if (dfs_num[v] == 0) {
    			if (u == 0) rootChildren++;
    			dfs(v, u);
    			if (dfs_num[u] <= dfs_low[v]) criticals[u] = true;
    			dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
    		}
    		else dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
    	}
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); inp.nextLine();
        	if (n == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (true) {
        		String[] line_split = inp.nextLine().split(" ");
        		int u = Integer.parseInt(line_split[0]);
        		//System.out.println(Arrays.toString(line_split));
        		if (u == 0) break;
        		u--;
        		for (int i = 1; i < line_split.length; i++) {
        			int v = Integer.parseInt(line_split[i]) - 1;
        			adj[u].add(v); adj[v].add(u);
        		}
        	}
        	dfs_num = new int[n];
        	dfs_low = new int[n];
        	criticals = new boolean[n];
        	cntNum = 0;
        	rootChildren = 0;
        	dfs(0, -1);
        	criticals[0] = rootChildren > 1;
            //System.out.println(Arrays.toString(dfs_low));
        	int ans = 0;
        	for (int i = 0; i < n; i++) ans += criticals[i]? 1 : 0;
        	System.out.println(ans);
        }
        
        //out.close();
    }
}