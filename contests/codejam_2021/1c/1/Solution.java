/*input

*/
import java.util.*;
import java.io.*;

public class Solution {
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

    static int compute(int pos) {
        int l_val, r_val;
        if (pos == 0) {
            if (arr[0] == 1) return 0;
            return arr[0] - 1;
        }
        else if (pos == n) {
            if (arr[n - 1] == k) return 0;
            return k - arr[n - 1];
        }
        else {
            int gap = arr[pos] - arr[pos - 1];
            if (gap <= 1) return 0;
            
        }

    }
    static double solve() {
        Arrays.sort(arr);
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int l_val = -1, r_val = -1;
                
            }
        }
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
            n = inp.nextInt(); k = inp.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = inp.nextInt();

        }
        
        //out.close();
    }
}