/*input
3
3
1 2
2 3
3 1
4
1 2
2 1
4 3
3 2
5
1 2
2 1
5 3
3 4
4 5
*/

//expected output: 1, not 5
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
   	static int[] forward;
   	static boolean[] vis;
   	static int[] sz;
   	static void dfs(int u) {
   		vis[u] = true;
   		sz[u] = 1;
   		if (!vis[forward[u]]) {
   			dfs(forward[u]);
   			sz[u] += sz[forward[u]];
   		}
   		vis[u] = false;
    }
  
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
       	for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt();
        	forward = new int[n];
        	for (int i = 0; i < n; i++) {
        		int u = inp.nextInt() - 1, v = inp.nextInt() - 1;
        		forward[u] = v;
         	}

        	sz = new int[n];
        	Arrays.fill(sz, -1);
        	vis = new boolean[n];
        	
        	int ans = -1, maxNum = -1;
        	for (int i = 0; i < n; i++) {
        		if (sz[i] == -1) dfs(i);
        		if (sz[i] > maxNum) {
        			maxNum = sz[i];
        			ans = i;
        		} 
        	}
          //System.out.println(Arrays.toString(sz));
        	System.out.println("Case " + test + ": " + (ans + 1));
        }
        
        //out.close();
    }
}