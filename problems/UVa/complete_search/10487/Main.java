/*input
5
3
12
17
33
34
3
1
51
30
3
1
2
3
3
1
2
3
3
1
2
3
3
4
5
6
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
    static int n;
    static int[] arr;
    static int solve(int target) {
    	int ans = arr[0] + arr[1];
    	int minDist = Math.abs(ans - target);
    	for (int i = 0; i < n - 1; i++) {
        	int pos = Arrays.binarySearch(arr, i + 1, n, target - arr[i]);
        	if (pos >= 0) return target;
        	pos = -pos - 1;
        	if (pos - 1 > i && Math.abs(arr[pos - 1] + arr[i] - target) < minDist) {
        		minDist = Math.abs(arr[pos - 1] + arr[i] - target);
        		ans = arr[pos - 1] + arr[i];
        	}
        	if (pos > i && pos < n && Math.abs(arr[pos] + arr[i] - target) < minDist) {
        		minDist = Math.abs(arr[pos] + arr[i] - target);
        		ans = arr[pos] + arr[i];
        	}
        }
        return ans;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int test = 0;
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
   			Arrays.sort(arr);
        	int m = inp.nextInt();
        	System.out.format("Case %d:\n", ++test);
        	while (m-- > 0) {
        		int target = inp.nextInt();
        		System.out.format("Closest sum to %d is %d.\n", target, solve(target));
        	}
        }
    }
}