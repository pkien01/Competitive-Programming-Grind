/*input
25
2147483647
2147483646
2147483645
2147483644
2147483643
896630635
720068461
1974962998
1457334986
260059716
1011316052
314491857
1736703113
446305897
780377038
1254403284
680861658
812059036
579477881
997429188
1765098997
52159266
1255516486
1770934622
1440619540
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

    static long sumTo(long n) {
    	return n * (n + 1) / 2;
    }
  	static long numDigitsTo(long n, boolean allNums) {
  		long res = 0, pw10 = 1, cntDigit = 0, curNumDigits = 0;
  		for (long i = n; i != 0; i /= 10) {
  			cntDigit++;
  			if (i >= 10) {
  				res += (pw10 * 9) * curNumDigits + sumTo(pw10 * 9) * cntDigit;
  				curNumDigits += cntDigit * pw10 * 9;
  				//System.out.println(curNumDigits);
  				pw10 *= 10;
  			}
  			else { 
  				res += (n - pw10 + 1) * curNumDigits + sumTo(n - pw10 + 1) * cntDigit; 
  				curNumDigits += (n - pw10 + 1) * cntDigit;
  			}
  		}
  		return allNums? res: curNumDigits;
  	}
  	static int kthDigit(long n, int kth) {
  		return (int)String.valueOf(n).charAt(kth - 1) - '0';
  	}

    static int pos;
    static int solve() {
    	long l = 0, r = pos;
    	while (l <= r) {
    		long mid = (l + r) >> 1;
    		if (numDigitsTo(mid, true) < pos) l = mid + 1;
    		else r = mid - 1;
    	}
    	pos -= numDigitsTo(r, true);
    	//System.out.println(pos);
    	
    	l = 1; r = pos;
    	while (l <= r) {
    		long mid = (l + r) >> 1;
    		if (numDigitsTo(mid, false) <= pos) l = mid + 1;
    		else r = mid - 1;
    	}
    	pos -= numDigitsTo(r, false);
    	//System.out.println(r + " " + numDigitsTo(r, false) + " " + pos);
    	if (pos > 0) return kthDigit(r + 1, pos);
    	return (int)(r % 10);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	pos = inp.nextInt();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}