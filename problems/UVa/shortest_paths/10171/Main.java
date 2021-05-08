/*input
2
Y B A A 2
M B A A 3
A A
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
    static final int n = 26, inf = (int)2e8;
    static int[][] distY, distM;
    static int me, prof;

    static void init() {
		distY = new int[n][n]; distM = new int[n][n];
        for (int i = 0; i < n; i++) {
        	Arrays.fill(distY[i], inf); distY[i][i] = 0;
        	Arrays.fill(distM[i], inf); distM[i][i] = 0;
        }
    }
    static void solve() {	
    	for (int k = 0; k < n; k++) {
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < n; j++) {
    				distY[i][j] = Math.min(distY[i][j], distY[i][k] + distY[k][j]);
    				distM[i][j] = Math.min(distM[i][j], distM[i][k] + distM[k][j]);
    			}
    		}
    	}
    	int ans = inf;
    	for (int i = 0; i < n; i++) ans = Math.min(ans, distY[me][i] + distM[prof][i]);
    	if (ans != inf) {
    		System.out.print(ans);
    		for (int i = 0; i < n; i++) if (distY[me][i] + distM[prof][i] == ans) System.out.print(" " + (char)(i + 'A'));
    		System.out.println();
    	}
    	else System.out.println("You will never meet.");
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	int m = inp.nextInt();
        	if (m == 0) break;
        	init();
        	while (m-- > 0) {
        		char type = inp.next().charAt(0), dir = inp.next().charAt(0);
        		int u = (int)inp.next().charAt(0) - 'A', v = inp.next().charAt(0) - 'A', w = inp.nextInt();
        		if (type == 'Y') {
        			distY[u][v] = Math.min(distY[u][v], w);
        			if (dir == 'B') distY[v][u] = Math.min(distY[v][u], w);
        		}
        		else {
        			distM[u][v] = Math.min(distM[u][v], w);
        			if (dir == 'B') distM[v][u] = Math.min(distM[v][u], w);
        		}
        	}
        	me = (int)inp.next().charAt(0) - 'A'; prof = (int)inp.next().charAt(0) - 'A';
        	solve();
        }
        
        //out.close();
    }
}