/*input
6
8
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
    static boolean[] isPrime;
    static void precompute() {
    	isPrime = new boolean[33];
    	Arrays.fill(isPrime, true);
    	isPrime[0] = isPrime[1] = false;
    	for (int i = 2; i <= 32; i++) {
    		if (!isPrime[i]) continue;
    		for (int j = i + i; j <= 32; j += i) isPrime[j] = false;
    	}
    }

    static int[] curAns;
    static PrintWriter out;
    static void solve(int idx, int n, int used, int lastChoices) {
    	if (lastChoices == 0) return;
    	if (idx == n - 1) {
    		int lastNum = -1;
    		for (int x = 2; x <= n; x++) {
    			if ((lastChoices >> x & 1) != 0 && isPrime[x + curAns[idx - 1]]) {
    				lastNum = x; break;
    			}
    		}
    		if (lastNum != -1) {
    			curAns[n - 1] = lastNum;
    			for (int i = 0; i < n; i++) out.print(curAns[i] + (i == n - 1? "\n": " "));
    		}
    		return;
    	} 
    	for (int x = 2; x <= n; x++) {
    		if ((used >> x & 1) == 0 && isPrime[x + curAns[idx - 1]]) {
    			curAns[idx] = x;
    			solve(idx + 1, n, used | (1 << x), lastChoices & ~(1 << x));
    		}
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        out = new PrintWriter(System.out);
        precompute();
        int caseNum = 0;
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	if (caseNum != 0) out.println();
        	out.println("Case " + ++caseNum + ":");
        	if (n == 1) {
        		out.println("1");
        		continue;
        	}
        	curAns = new int[n];
        	curAns[0] = 1; 
        	int lastChoices = 0; 
        	for (int x = 2; x <= n; x++)
        		if (isPrime[x + 1]) lastChoices |= (1 << x);

        	solve(1, n, (1 << 1), lastChoices);
        }
        out.close();
    }
}