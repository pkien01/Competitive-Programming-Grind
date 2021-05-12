/*input
2
7 9
ooo**oooo
**oo*ooo*
o*oo**o**
ooooooooo
*******oo
o*o*oo*oo
*******oo
10 1
*
*
*
o
*
*
*
*
*
*
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

    static final int[] dx = {-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    static int n, m;
    static char[][] arr;
    static ArrayList<Integer>[] adj;

    static int[] match;
    static boolean[] vis;
    static boolean kuhn(int u) {
    	if (vis[u]) return false;
    	vis[u] = true;
    	for (Integer v: adj[u]) {
    		if (match[v] == -1 || kuhn(match[v])) {
    			match[v] = u; 
    			return true;
    		}
    	}
    	return false;
    }
    static int solve() {
    	adj = new ArrayList[n*m];
    	for (int i = 0; i < n*m; i++) adj[i] = new ArrayList<>();

    	int cnt = 0;
    	for (int x = 0; x < n; x++) {
    		for (int y = 0; y < m; y++) {
    			if (arr[x][y] == '*') {
    				int u = x*m + y;
    				cnt++;
    				for (int i = 0; i < 4; i++) {
    					if (x + dx[i] >= 0 && x + dx[i] < n && y + dy[i] >= 0 && y + dy[i] < m && arr[x + dx[i]][y + dy[i]] == '*') {
    						int v = (x + dx[i])*m + y + dy[i];
    						adj[u].add(v); 
    					}
    				}
    			}
    		}
    	}

    	match = new int[n*m];
    	vis = new boolean[n*m];
    	Arrays.fill(match, -1);
    	int ans = 0;
    	for (int v = 0; v < n*m; v++) {
    		if (adj[v].isEmpty()) continue;
    		Arrays.fill(vis, false);
    		if (kuhn(v)) ans++;
    	}
    	//System.out.println(cnt);
    	return cnt - ans/2;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); m = inp.nextInt();
        	arr = new char[n][m];
        	for (int i = 0; i < n; i++) arr[i] = inp.next().toCharArray();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}