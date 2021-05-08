/*input
4
1 2
1 3
1 4
2 3
2 4
3 4
0 0
6
1 2
1 3
1 6
2 3
2 5
3 4
4 5
4 6
5 6
0 0
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

    static int[] color;
    static boolean dfs(int u, int curColor) {
    	if (color[u] != -1) return color[u] == curColor;
    	color[u] = curColor;
    	boolean res = true;
    	for (Integer v: adj[u]) res = res && dfs(v, curColor^1);
    	return res;
    }

    static boolean solve() {
    	boolean ans = true;
    	color = new int[n];
    	Arrays.fill(color, -1);
    	for (int i = 0; i < n; i++) 
    		if (color[i] == -1) ans = ans && dfs(i, 0);
    	return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        
        while (true) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (true) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		if (u == 0 && v == 0) break;
        		u--; v--;
        		adj[u].add(v); adj[v].add(u);
        	}
        	System.out.println(solve()? "YES" : "NO");
        }
        
        //out.close();
    }
}