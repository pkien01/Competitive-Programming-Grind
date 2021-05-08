/*input
5 3
14 14 16 12 14
10
4  1 2 4 5      1
2  3 4          1
2  1 2          1
5  1 2 3 4 5    2
4  1 2 3 5      2
3  3 4 5        2
2  4 5          3
4  1 3 4 5      3
3  1 2 3        3
2  2 3          3
0 0
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
   
    static int bitCount(int num) {
    	int res = 0;
    	while (num != 0) {
    		res += (num & 1);
    		num >>= 1;
    	} 
    	return res;
    }

    static int numPlan, numBuild, m;
    static int[] towers;
    static int[][] common;
    
    static void solve() {
    	int resSum = 0, resMask = 0;
    	for (int mask = (1 << numBuild) - 1; mask < (1 << numPlan); mask++) {
    		if (bitCount(mask) != numBuild) continue;;
    		int sum = 0;
    		for (int i = 0; i < numPlan; i++) 
    			sum += (mask >> i & 1) * towers[i];
    		
    		//System.out.print(Integer.toBinaryString(mask) + ": ");
    		for (int i = 0; i < m; i++) {
    			int share = bitCount(mask & common[i][0]);
    			if (share >= 2) sum -= (share - 1) * common[i][1];
    		}
    		//System.out.println();
    		if (sum > resSum) {
    			resSum = sum;
    			resMask = mask;
    		}
    	}
    	ArrayList<Integer> resTowers = new ArrayList<>();
    	for (int i = 0; i < numPlan; i++) if ((resMask >> i & 1) != 0) resTowers.add(i + 1);
    	System.out.println("Number of Customers: " + resSum);
    	System.out.print("Locations recommended:");
    	for (Integer x: resTowers) System.out.print(" " + x);
    	System.out.println();
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int caseNum = 0;
        while (inp.hasNextInt()) {
        	numPlan = inp.nextInt(); numBuild = inp.nextInt();
        	if (numPlan == 0 && numBuild == 0) continue;
        	towers = new int[numPlan];
        	for (int i = 0; i < numPlan; i++) towers[i] = inp.nextInt();
        	m = inp.nextInt();
        	common = new int[m][2];
        	for (int i = 0; i < m; i++) {
        		int mask = 0;
        		int numCommon = inp.nextInt();
        		while (numCommon-- > 0) mask |= (1 << (inp.nextInt() - 1));
        		//System.out.println(Integer.toBinaryString(mask));
        		common[i] = new int[]{mask, inp.nextInt()};
        	}
        	System.out.println("Case Number  " + (++caseNum));
        	solve();
        	System.out.println();
        }
    }
}