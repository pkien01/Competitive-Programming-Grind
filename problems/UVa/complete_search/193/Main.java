/*input
1
6 8
1 2
1 3
2 4
2 5
3 4
3 6
4 6
5 6
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

    static BitSet ans;

    static void solve(int u, BitSet color) {
    	if (n - u + color.cardinality() < ans.cardinality()) return;
    	if (u == n) {
    		if (color.cardinality() > ans.cardinality())  ans = (BitSet)color.clone();
    		return;
    	}

    	boolean hasBlack = false;
    	for (Integer v: adj[u]) {
    		if (color.get(v)) {
    			hasBlack = true; break;
    		}
    	}
    	
    	solve(u + 1, color);

   		if (!hasBlack) {
   			color.set(u);
   			solve(u + 1, color);
   			color.clear(u);
   		}
    	//color[u] = -1;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	int m = inp.nextInt();
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	while (m-- > 0) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		adj[u].add(v); adj[v].add(u);
        	}
        	ans = new BitSet(n);
        	solve(0, new BitSet(n));
        	out.println(ans.cardinality());
      		ArrayList<Integer> ansList = new ArrayList<>();
        	for (int i = 0; i < n; i++) 
        		if (ans.get(i)) ansList.add(i + 1);
        	for (int i = 0; i < ansList.size(); i++)
        		out.print(ansList.get(i) + (i == ansList.size() - 1? "\n": " "));
        }
        out.close();
    }
}