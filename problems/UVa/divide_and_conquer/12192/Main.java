/*input
4 5
13 21 25 33 34
16 21 33 35 35
16 33 33 45 50
23 51 66 83 93
3
22 90
33 35
20 100
4 4
1 7 9 11
5 8 10 12
7 10 15 17
11 19 30 41
4
6 20
7 9
10 10
13 14
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

    static int lowerBound(int[] nums, int key) {
    	int l = 0, r = nums.length - 1;
    	while (l <= r) {
    		int mid = (l + r) >> 1;
    		if (nums[mid] >= key) r = mid - 1;
    		else l = mid + 1;
    	}
    	return l;
    }

    static int n, m;
    static int[][] arr;
    static int solve(int lowNum, int highNum) {
    	int res = 0;
    	for (int i = 0; i + res < n; i++) {
    		int lowPos = lowerBound(arr[i], lowNum);
    		int prevRes = res;
    		while (i + res < n && lowPos + res < m && arr[i + res][lowPos + res] <= highNum)     			
    			res++;
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	arr = new int[n][m];
        	for (int i = 0; i < n; i++)
        		for (int j = 0; j < m; j++) arr[i][j] = inp.nextInt();
        	int q = inp.nextInt();
        	while (q-- > 0) {
        		int lowNum = inp.nextInt(), highNum = inp.nextInt();
        		System.out.println(solve(lowNum, highNum));
        	}	
        	System.out.println("-"); 
        }
        
        //out.close();
    }
}