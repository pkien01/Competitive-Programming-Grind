/*input
1
YYYYNYY
2
NNNNNNN
NNNNNNN
2
YYYYYYY
YYYYYYY
3
YNYYYYY
YNYYNYY
NYYNNYY
3
YNYYYYN
YNYYNYN
NYYNNYN
3
YNYYYYN
YNYYNYN
NYYNYYN
4
YYYYYYY
NYYNNNN
NNYYYYN
NNNYNNN
3
NNNNNNN
YNNNNNN
NNNNYNN
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
    static final String[] digitStrs = {"YYYYYYN", "NYYNNNN", "YYNYYNY", "YYYYNNY", 
"NYYNNYY", "YNYYNYY", "YNYYYYY", "YYYNNNN", "YYYYYYY", "YYYYNYY"};
	static int[] digitMasks;
	
	static void precompute() {
		digitMasks = new int[digitStrs.length];
		for (int i = 0; i < digitStrs.length; i++) {
			assert digitStrs[i].length() == 7;
			int curMask = 0;
			for (int j = 0; j < 7; j++) 
				if (digitStrs[i].charAt(j) == 'Y') curMask |= (1 << j);
			digitMasks[i] = curMask; 
		}
	}
	static boolean isSubset(int subMask, int mask) {
		return (mask | subMask) == mask; 
	}

	static int n;
	static int[] seqMasks;

	static String binString(int num) {
		StringBuilder res = new StringBuilder();
		while (num != 0) {
			res.append(num & 1);
			num >>= 1;
		}
		while (res.length() < 7) res.append('0');
		return res.toString();
	}

	static boolean isSequence(int startDigit) {
		//if (startDigit - n + 1 < 0) return false;
		//System.out.println(startDigit);
		int prevBurned = 0;
		for (int i = 0; i < n; i++) {
			int curMask = digitMasks[startDigit - i];
			if (!isSubset(seqMasks[i], curMask)) {
				return false;
			}
			
			int curBurned = curMask ^ seqMasks[i];
			for (int j = 0; j < 7; j++)
				if ((curMask >> j & 1) == 0 && (seqMasks[i] >> j & 1) == 0 && (prevBurned >> j & 1) == 1)
					curBurned |= (1 << j);
			//System.out.print(binString(prevBurned) + " ");
			//System.out.println(binString(curBurned));
			if (!isSubset(prevBurned, curBurned)) return false;
			prevBurned = curBurned;
		}
		return true;
	}
	static boolean solve() {
		for (int i = 9; i - n + 1 >= 0; i--) 
			if (isSequence(i)) return true;
		return false;
	}
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        precompute();
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	seqMasks = new int[n];
        	for (int i = 0; i < n; i++) {
        		String s = inp.next();
        		int s_mask = 0;
        		for (int j = 0; j < s.length(); j++)
        			if (s.charAt(j) == 'Y') s_mask |= (1 << j);
        		seqMasks[i] = s_mask;
        	}
        	System.out.println(solve()? "MATCH" : "MISMATCH");
        }
        
        //out.close();
    }
}