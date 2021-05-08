/*input
5
4 6
2 1
7 12
7 2
2 1000
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

    static int n, c;
    ArrayList<Integer> arr;
    static int solve() {
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) 
                if (arr.get(j) < arr.get(minIdx)) minIdx = j;
            res += minIdx - i + 1;
            for (int l = i, r = minIdx; l < r; l++, r--) {
                int tmp = arr.get(l); arr.get(l) = arr.get(r); arr.get(r) = tmp;
            }
        }
        return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	n = inp.nextInt(); 
            c = inp.nextInt();
            
            System.out.print("Case #" + test + ": ");
            if (solve(1, 0, 1)) {
                ans = new LinkedList<>();
                ans.add(1);
                trace(1, 0, 1);
                while (true) {
                    System.out.print(ans.poll());
                    if (ans.isEmpty()) {
                        System.out.print("\n"); break;
                    }
                    else System.out.print(" ");
                }
            }
            else System.out.println("IMPOSSIBLE");
        }
        
        //out.close();
    }
}