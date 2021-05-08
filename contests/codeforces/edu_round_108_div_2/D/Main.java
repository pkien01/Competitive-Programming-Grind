/*input
6
1 8 7 6 3 6
5 9 6 8 8 6
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
    static int[] a, b;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt();
        a = new int[n]; b = new int[n];
        for (int i = 0; i < n; i++) a[i] = inp.nextInt();
        for (int i = 0; i < n; i++) b[i] = inp.nextInt();

        long[][] rev = new long[n][n];
        long[] pref = new long[n], suff = new long[n];
        for (int i = 0; i < n; i++) pref[i] = (i > 0? pref[i - 1]:0) + (long)a[i] * b[i];
        for (int i = n - 1; i >= 0; i--) suff[i] = (i + 1 < n? suff[i + 1]:0) + (long)a[i] * b[i];
    	for (int i = 0; i < n; i++) {
    		rev[i][i] = (long)a[i] * b[i];
    		if (i + 1 < n) rev[i][i + 1] = (long)a[i + 1] * b[i] + (long)a[i] * b[i + 1];
    	}
    	for (int j = 2; j < n; j++)
    		for (int i = 0; i + j < n; i++) rev[i][i + j] = rev[i + 1][i + j - 1] + (long)a[i] * b[i + j] + (long)b[i] * a[i + j];


    	long ans = pref[n - 1];
    	for (int i = 0; i < n; i++) {
    		for (int j = i; j < n; j++) {
    			ans = Math.max(ans, (i > 0? pref[i - 1] : 0) + (j + 1 < n? suff[j + 1] : 0) + rev[i][j]);
    		}
    	}
    	System.out.println(ans);
        //out.close();
    }
}