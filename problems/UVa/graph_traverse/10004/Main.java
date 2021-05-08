/*input
3
3
0 1
1 2
2 0
3
2
0 1
1 2
9
8
0 1
0 2
0 3
0 4
0 5
0 6
0 7
0 8
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
    static int n, m;
    static ArrayList<Integer>[] adj;
    static int[] color;

   static boolean solve(int u, int curColor) {
    	if (color[u] != -1) return color[u] == curColor;
    	color[u] = curColor;
    	boolean res = true;
    	for (Integer v: adj[u]) res = res && solve(v, curColor^1);
    	return res;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		adj[u].add(v); adj[v].add(u);
        	}
        	color = new int[n];
        	Arrays.fill(color, -1);
        	System.out.println(solve(0, 0)? "BICOLORABLE." : "NOT BICOLORABLE.");
 ;       }
        
        //out.close();
    }
}