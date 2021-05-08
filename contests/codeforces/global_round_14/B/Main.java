/*input
1
18
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
        	if (n % 2 != 0) {
        		System.out.println("NO");
        	}
        	else {
        		while (n % 2 == 0) n /= 2;
        		double sqrt_n = Math.sqrt(n);
        		System.out.println(sqrt_n == Math.floor(sqrt_n)? "YES" : "NO");
        	}
        }
        
        //out.close();
    }
}