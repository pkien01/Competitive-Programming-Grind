/*input
3 2
1 2 3
-2 3
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
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int n = inp.nextInt(), m = inp.nextInt();
    
    	int ans = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
        	int x = inp.nextInt();
        	if (x <= 0) ans = -1;
        	a[i] = (i > 0? a[i - 1] : 0) + x;
        }
        int[] b = new int[m];
    	for (int i = 0; i < m; i++) {
    		int x = inp.nextInt();
    		if (x <= 0) ans = -1;
    		b[i] = (i > 0? b[i - 1]: 0) + x;
    	}

    	if (ans != -1) {
    		ans = 0;
    		for (int i = 0; i < n - 1 && i < m - 1; i++) {
    			if (a[i] == b[i]) {
    				ans = Math.max(ans, a[i]);
    				//break;
    			}
    		}
    	}
    	if (n == 0 || m == 0) ans = -1;
      
    	System.out.println(ans == -1? "Not possible" : ans);
    	
        
        //out.close();
    }
}