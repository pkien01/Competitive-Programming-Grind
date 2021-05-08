/*input
2 0
10 20
20 30
2 0
10 30
20 21
1 1
1000 2000
0 10 1000
0 0
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
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
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        BitSet bits = new BitSet(1000002);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	bits.clear();
        	boolean ans = true;
        	while (n-- > 0) {
        		int l = inp.nextInt(), r = inp.nextInt();
        		int pos = bits.nextSetBit(l);
        		if (pos >= l && pos <= r && ans) ans = false;
        		bits.set(l, r);
        	}
        	while (m-- > 0) {
        		int l = inp.nextInt(), r = inp.nextInt(), rep = inp.nextInt();
        		while (r <= 1000000) {
        			int pos = bits.nextSetBit(l);
        			if (pos >= l && pos <= r && ans) ans = false;
        			bits.set(l, r);
        			l += rep; r += rep;
        		}
        	}
        	System.out.println(ans? "NO CONFLICT" : "CONFLICT");
        }
    }
}