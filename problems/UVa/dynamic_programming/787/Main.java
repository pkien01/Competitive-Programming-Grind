/*input
1 2 3 -999999
-5 -2 2 -30 -999999
-8 -999999
-1 0 -2 -999999
*/
import java.util.*;
import java.io.*;
import java.math.BigInteger;

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
        while (inp.hasNextLine()) {
        	String[] line_split = inp.nextLine().split(" ");
        	BigInteger ans = null;
        	BigInteger curProd = BigInteger.ONE, maxNegProd = null;
        	
        	for (int i = 0; i < line_split.length - 1; i++) {
        		BigInteger num = new BigInteger(line_split[i]);
        		if (ans == null || ans.compareTo(num) < 0) ans = num;

        		if (num.equals(BigInteger.ZERO)) {
        			curProd = BigInteger.ONE;
        			maxNegProd = null;
        		}
        		else {
        			curProd = curProd.multiply(num);
        			if (curProd.signum() < 0) {
        				if (maxNegProd != null) {
        					BigInteger curAns = curProd.divide(maxNegProd);
        					if (ans.compareTo(curAns) < 0) ans = curAns;
        					maxNegProd = maxNegProd.max(curProd);
        				}
        				else maxNegProd = curProd;
        			}
        			else if (ans == null || ans.compareTo(curProd) < 0) ans = curProd;

        		}
        	}
        	System.out.println(ans.toString());
        }
        
        //out.close();
    }
}