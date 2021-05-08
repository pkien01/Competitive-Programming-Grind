/*input
5
1 0
2 0
3 2
0 1
1 2
3 3
0 1
1 2
0 2
13 10
0 5
5 4
5 3
5 2
5 1
5 6
7 8
7 9
7 10
7 11
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
    static int[] color, cntColor;

   static boolean dfs(int u, int curColor) {
    	if (color[u] != -1) return color[u] == curColor;
    	color[u] = curColor;
        cntColor[curColor]++;
    	boolean res = true;
    	for (Integer v: adj[u]) res = res && dfs(v, curColor^1);
    	return res;
    }

    static int solve() {
        color = new int[n];
        cntColor = new int[2];
        Arrays.fill(color, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                Arrays.fill(cntColor, 0);
                if (!dfs(i, 0)) return -1;
                int curRes = Math.min(cntColor[0], cntColor[1]);
                if (curRes == 0) curRes++;
                res += curRes;
            }    
        }
        return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		adj[u].add(v); adj[v].add(u);
        	}
        	System.out.println(solve());
        }
        
        //out.close();
    }
}