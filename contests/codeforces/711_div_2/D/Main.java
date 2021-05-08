/*input

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
    static class Operation {
    	int type, updVal,maxTimes;
    	double val;
    	Operation(int type, int updVal, int maxTimes) {
    		this.type = type; this.updVal = updVal; this.maxTimes = maxTimes;
    		this.val = (double)updVal / 1e5;
    	}
    	int ceilVal() {
    		return (int)Math.ceil(val);
    	}
    }

    static int n, m;
    Operation[] arr;
    int[] dp;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        n = inp.nextInt(); m = inp.nextInt();
        dp = new int[m];
        for (int i = 1; i <= n; i++) {
        	arr[i] = new Operation(inp.nextInt(), inp.nextInt(), inp.nextInt());
        }
        
        //out.close();
    }
}