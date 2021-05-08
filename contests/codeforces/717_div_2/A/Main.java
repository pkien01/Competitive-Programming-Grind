/*input
1
1 1
1
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
    static int n, k;
    static int[] arr;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt(); k = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	while (k-- > 0) {
        		int minusIdx = -1;
        		for (int i = 0; i < n; i++) {
        			if (arr[i] > 0) {
        				minusIdx = i; 
        				break;
        			}
        		}
        		if (minusIdx == -1 || minusIdx == n - 1) {
        			break;
        		}
        		arr[minusIdx]--; arr[n - 1]++;
        	}
        	for (int i = 0; i < n; i++) System.out.print(arr[i] + (i == n - 1? "\n" : " "));
        }
        
        //out.close();
    }
}