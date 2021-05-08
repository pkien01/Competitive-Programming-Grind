/*input
5
3
TMT
3
MTT
6
TMTMTT
6
TMTTTT
6
TTMMTT
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
    static String s;

    static boolean solve() {
    	int cntT = 0, cntM = 0;;
    	for (int i = 0; i < n; i++) {
    		if (s.charAt(i) == 'T') cntT++;
    		else {
    			cntT--;
    			cntM++;
    		}
    		if (cntT < 0) return false;
    	}
    	if (cntT != cntM) return false;
    	cntT = 0; cntM = 0;
    	for (int i = n - 1; i >= 0; i--) {
    		if (s.charAt(i) == 'T') cntT++;
    		else {
    			cntT--;
    			cntM++;
    		}
    		if (cntT < 0) return false;
    	}
    	if (cntT != cntM) return false;
    	return true;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	s = inp.next();
        	System.out.println(solve()? "YES": "NO");
        }	
        
        //out.close();
    }
}