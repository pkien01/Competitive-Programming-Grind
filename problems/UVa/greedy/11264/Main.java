/*input
7
7
1 5 9 74 111 121 159
10
1 2 3 4 5 6 7 8 9 10
5
1 2 4 8 15
8
1 5 9 17 25 33 42 100
16
1 2 4 17 58 69 125 254 478 1023 10000 145236 172589 172590 1000000 10000000
2
1 2
7
1 2 3 6 12 24 48
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

    static int solve() {
    	int res = 0, sum = 0;
    	for (int i = 0; i < n; i++) {
    		if (i == n - 1 || sum + arr[i] < arr[i + 1]) {
    			sum += arr[i]; 
    			res++;
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
        	n = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++)  arr[i] = inp.nextInt();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}