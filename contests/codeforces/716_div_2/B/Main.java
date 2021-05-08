/*input
2
2 2
100000 20
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
    static final int mod = (int)1e9 + 7;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt(), k = inp.nextInt();
        	long ans = 1;
        	for (int i = 0; i < k; i++) ans = (ans * n) % mod;
        	System.out.println(ans);
        }
        
        //out.close();
    }
}