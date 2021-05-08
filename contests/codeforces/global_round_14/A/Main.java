/*input
3
3 2
3 2 1
5 3
1 2 3 4 8
1 5
5
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

    static int n, val;
    static int[] arr;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); val = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	int sum = 0;
        	boolean ans = true;
        	for (int i = 0; i < n; i++) {
        		sum += arr[i];
        		if (sum == val) {
        			ans = false;
        			if (i != n - 1) {
        				for (int j = i + 1; j < n; j++) {
        					if (arr[j] != arr[i]) {
        						ans = true;
        						int tmp = arr[j]; arr[j] = arr[i]; arr[i] = tmp;
        					}
        				}
        			}
        			break;
        		}
        	}
        	System.out.println(ans? "YES" : "NO");
        	if (ans)
        		for (int i = 0; i < n; i++) System.out.print(arr[i] + (i == n - 1? "\n" : " "));
        }
        
        //out.close();
    }
}