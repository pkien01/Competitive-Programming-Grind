/*input
3 1
3 3
3 9
5 9
5 10
0 0
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    int findMinCycle(int pos) {
    	double root = (Math.sqrt((double)pos) - 1) / 2;
    	return (int)Math.ceil(root);
    }
    int[] solve(int sz, int pos) {
    	if (pos == 1) return new int[]{(sz + 1) / 2, (sz + 1) / 2};

    	//System.out.println("pos = " + pos + ", size = " + sz);

    	int cycle = findMinCycle(pos);
    	int cycleEdge = 2 * cycle + 1; 
    	int maxCycleNum = (2 * cycle + 1) * (2 * cycle + 1);
    	int minCycleNum = (2 * cycle - 1) * (2 * cycle - 1) + 1;

    	//System.out.println("cycle = " + cycle);
    	//System.out.println("cycleEdge = " + cycleEdge);
    	//System.out.println("minCycleNum = " + minCycleNum + ", maxCycleNum = " + maxCycleNum);

    	int low = (sz - 1) / 2 - cycle;
    	int high = (sz - 1) / 2 + cycle;
    	//System.out.println("low = " + low + ", high = " + high);

    	int corner1 = minCycleNum + cycleEdge - 2;
    	int corner2 = corner1 + cycleEdge - 1;
    	int corner3 = corner2 + cycleEdge - 1;
    	int corner4 = corner3 + cycleEdge - 1;
    	assert corner4 == maxCycleNum;
    	//System.out.format("corner1 = %d, corner2 = %d, corner3 = %d, corner4 = %d\n", corner1, corner2, corner3, corner4);

    	int row = -1, col = -1;
    	if (minCycleNum <= pos && pos <= corner1) {
    		row = high;
    		col = high - 1 - (pos - minCycleNum);
    	}
    	else if (corner1 < pos && pos <= corner2) {
    		row = high - (pos - corner1);
    		col = low;
    	}
    	else if (corner2 < pos && pos <= corner3) {
    		row = low;
    		col = low + pos - corner2;
    	}
    	else {
    		assert corner3 < pos && pos <= maxCycleNum;
    		row = low + pos - corner3;
    		col = high;
    	}
    	return new int[]{row + 1, col + 1};

    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int sz = inp.nextInt(), pos = inp.nextInt();
        	if (sz == 0 && pos == 0) break;
        	int[] ans = obj.solve(sz, pos);
        	System.out.format("Line = %d, column = %d.\n", ans[0], ans[1]);
        }
    }
}