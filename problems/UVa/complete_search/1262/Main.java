/*input
3
1
AYGSU
DOMRA
CPFAS
XBODG
WDYPK
PRXWO
CBOPT
DOSBG
GTRAR
APMMS
WSXNU
EFGHI
5
AYGSU
DOMRA
CPFAS
XBODG
WDYPK
PRXWO
CBOPT
DOSBG
GTRAR
APMMS
WSXNU
EFGHI
64
FGHIJ
EFGHI
DEFGH
CDEFG
BCDEF
ABCDE
WBXDY
UWYXZ
XXZFG
YYFYH
EZWZI
ZGHIJ
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
    static int k;
    static ArrayList<Character>[] sharedChars;
    static int cntPass;
    static char[] curAns;

    static void solve(int idx) {
    	if (idx == 5) {
    		cntPass++;
    		if (cntPass == k) System.out.println(String.valueOf(curAns));
   			return;
    	}
    	for (Character c: sharedChars[idx]) {
    		curAns[idx] = c;
    		solve(idx + 1);
    		if (cntPass >= k) return; 
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	k = inp.nextInt();
        	int[] grid1 = new int[5], grid2 = new int[5];
			for (int i = 0; i < 6; i++) {
				String s = inp.next();
				for (int j = 0; j < 5; j++) grid1[j] |= (1 << ((int)s.charAt(j) - 'A'));
			}
			for (int i = 0; i < 6; i++) {
				String s = inp.next();
				for (int j = 0; j < 5; j++) grid2[j] |= (1 << ((int)s.charAt(j) - 'A'));
			}
			sharedChars = new ArrayList[5];
			for (int i = 0; i < 5; i++) {
				int curCol = grid1[i] & grid2[i];
				sharedChars[i] = new ArrayList<>();
				for (int j = 0; j < 26; j++) 
					if ((curCol >> j & 1) != 0) sharedChars[i].add((char)(j + 'A'));
			}

			cntPass = 0;
			curAns = new char[5];
			solve(0);
			if (cntPass < k) {
				System.out.println("NO");
			}
        }	
        
        //out.close();
    }
}