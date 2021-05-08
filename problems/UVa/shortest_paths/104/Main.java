/*input
3
1.2 .89
.88 5.1
1.1 0.15
4
3.1 0.0023 0.35
0.21 0.00353 8.13
200 180.559 10.339
2.11 0.089 0.06111
2
2.0
0.45
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

    static int src, len;
    static double[][][] dist;
    static int[][][] par;
    static void floyd() {
        par = new int[n + 1][n][n];
        
      			
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    par[l][i][j] = i;
                    dist[l][i][j] = -1e9;
                }
            }

    	   for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[l - 1][i][k] * dist[0][k][j] > dist[l][i][j]) {
                            dist[l][i][j] = dist[l - 1][i][k] * dist[0][k][j];
                            par[l][i][j] = k;
                        }
                    }
                }
    	   }

           for (int i = 0; i < n; i++) {
                if (dist[l][i][i] > 1.01) {
                    src = i;
                    len = l;
                    return;
                }
           }
        }
    }
    static void trace(int from, int to, int curLen) {
    	if (curLen > 0) trace(from, par[curLen][from][to], curLen -1);
    
 		System.out.print(" " + (to + 1));
    }
    static void solve() {
    	src = -1; len = -1;
        floyd();
        //System.out.println("src = " + src + ", dest = " + dest + ", mul = " + dist[src][dest] * dist[dest][src]  + ", len = " + len[src][dest]);
        if (src == -1 && len == -1) System.out.println("no arbitrage sequence exists");
        else {
            System.out.print(src + 1);
        	trace(src, src, len);
            System.out.println();
        }

    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	dist = new double[n + 1][n][n]; 
        	for (int i = 0; i < n; i++) 
        		for (int j = 0; j < n; j++) dist[0][i][j] = i == j? 1.0 : inp.nextDouble();
        	solve();
            
        }
        
        //out.close();
    }
}