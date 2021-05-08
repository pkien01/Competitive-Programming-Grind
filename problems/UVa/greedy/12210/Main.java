/*input
4 4
26
25
2
21
35
25
23
24
1 2
20
30
40
4 2
5
5
10
15
20
18
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

    static int n, m, youngest;

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1; inp.hasNextInt(); test++) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	youngest = 61;
        	for (int i = 0; i < n; i++) youngest = Math.min(youngest, inp.nextInt());
        	for (int i = 0; i < m; i++) inp.nextInt();
        	System.out.print("Case " + test + ": ");
        	if (n <= m) System.out.println("0");
        	else System.out.println((n - m) + " " + youngest);
        }	
        
        //out.close();
    }
}