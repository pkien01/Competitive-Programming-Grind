/*input
29
3 1 3
3 3 3
1 8 21
17 108 121
10 1 100
47 240 1637
18 1 162
23 385 195
37 1664 489
17 1 145
44 544 1224
42 1 1394
24 1 386
12 24 74
8 1 40
55 629 1659
60 1410 2318
34 836 498
46 980 1290
19 56 213
11 48 41
57 3468 1481
52 2210 1350
8 1 130
1 30 38
1 30 38
1 4 9
5 1 13
3 1 5
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
    static int cmpArr(int[] arr1, int[] arr2) {
    	for (int i = 0; i < arr1.length && i < arr2.length; i++)
    		if (arr1[i] != arr2[i]) return arr1[i] - arr2[i];
    	return 0;
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int a = inp.nextInt(), b = inp.nextInt(), c = inp.nextInt();
        	int range_x = (int)Math.cbrt((double)b) + 1;
        	//System.out.println(range);
        	int[] ans = null;
        	for (int x = -range_x; x <= range_x; x++) {
        		int range_y = (int)Math.sqrt((double)(c - x * x)  / 2.0) + 1;
        		for (int y = -range_y; y <= range_y; y++) {
        			int z = a - x - y;
        			if (x != y && y != z && z != x && x * y * z == b && x*x + y*y + z*z == c) {
        				int[] curAns = new int[]{x, y, z};
        				Arrays.sort(curAns);
        				if (ans == null || curAns[0] < ans[0]) {
        					ans = curAns.clone();
        				}
        			}
        		}
        	}
        	if (ans == null) System.out.println("No solution.");
        	else System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
        }
    }
}