/*input
4
1
2
3
1 2 3
5
1 1 1 1 1
3
3 3 2
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
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int ups = 0, downs = 0, others = 0;
        	for (int i = 0; i < n; i++) {
        		int x = inp.nextInt();
        		if (x == 1) ups++;
        		else if (x == 2) downs++;
        		else others++;
        	}
        	int ans = ups + (ups >= 0? others: 0);
        	System.out.println(ans);
        }
        
        //out.close();
    }
}