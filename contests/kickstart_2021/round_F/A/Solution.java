/*input
2
3
111
6
100100
*/
import java.util.*;
import java.io.*;

public class Solution {
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
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	int n = inp.nextInt();
        	String s  = inp.next();
        	int[] pref = new int[n], suff = new int[n];
        	pref[0] = s.charAt(0) == '1'? 0 : n + 1; 
        	for (int i = 1; i < n; i++) 
        		pref[i] = s.charAt(i) == '1'? 0 : pref[i - 1] + 1;
        	suff[n - 1] = s.charAt(n - 1) == '1'? 0 : n + 1;
        	for (int i = n - 2; i >= 0; i--) 
        		suff[i] = s.charAt(i) == '1'? 0: suff[i + 1] + 1;

        	long ans = 0;
        	for (int i = 0; i < n; i++) ans += Math.min(pref[i], suff[i]);
        	System.out.format("Case #%d: %d\n", test, ans);
        }
        
        
        //out.close();
    }
}