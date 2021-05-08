/*input
4
1
2 2 1
4
0 0 1
4 4 1
0 4 1
4 0 1
5
0 0 1
1 1 1
2 2 1
3 3 1
4 4 1
7
4 2 2
3 3 1
2 4 3
2 1 1
1 3 4
1 2 2
1 0 1
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
    static class Area {
    	int x, y, val;
    	Area(int x, int y, int val) {
    		this.x = x; this.y = y; this.val = val;
    	}
    	Area(int index) {
    		this.x = index / 5;
    		this.y = index % 5;
    		this.val = 0;
    	}
    	int dist(Area other) {
    		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    	}
    	int weightedDist(Area[] offices) {
    		int res = this.dist(offices[0]);
    		for (int i = 1; i < offices.length; i++) res = Math.min(res, this.dist(offices[i]));
    		return res * val;
    	}
    	int toIndex() {
    		return x * 5 + y;
    	}
    }

    static Area[] city;
    static int[] solve() {
    	int resSum = Integer.MAX_VALUE;
    	int[] res = new int[5];
    	for (int i1 = 0; i1 < 25 - 4 ; i1++) {
    		for (int i2 = i1 + 1; i2 < 25 - 3; i2++) {
    			for (int i3 = i2 + 1; i3 < 25 - 2; i3++) {
    				for (int i4 = i3 + 1; i4 < 25 - 1; i4++) {
    					for (int i5 = i4 + 1; i5 < 25; i5++) {
    						Area[] offices = new Area[]{new Area(i1), new Area(i2), new Area(i3), new Area(i4), new Area(i5)};
    						int sum = 0;
    						//boolean smaller = true;
    						for (Area area: city) {
    							sum += area.weightedDist(offices);
    							/*if (sum >= resSum) {
    								smaller = false; break;
    							}*/
    						}
    						if (sum < resSum) {
    							resSum = sum;
    							res = new int[]{i1, i2, i3, i4, i5};
    						}
    					}
    				}
    			}
    		}
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	city = new Area[n];
        	for (int i = 0; i < n; i++) 
        		city[i] = new Area(inp.nextInt(), inp.nextInt(), inp.nextInt());
        	int[] ans = solve();
        	for (int i = 0; i < 5; i++) System.out.print(ans[i] + (i == 4? "\n": " "));
        }
    }
}