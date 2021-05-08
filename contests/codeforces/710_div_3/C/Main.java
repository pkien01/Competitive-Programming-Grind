/*input
5
a
a
abcd
bc
hello
codeforces
hello
helo
dhjakjsnasjhfksafasd
adjsnasjhfksvdafdser
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
        	String a = inp.next(), b = inp.next();
        	int ans = a.length() + b.length();
        	for (int i = 0; i < a.length(); i++) {
        		for (int j = i; j < a.length(); j++) {
        			String sub_a = a.substring(i, j + 1);
        			int pos_b = b.indexOf(sub_a);
        			if (pos_b != -1) ans = Math.min(ans, a.length() - sub_a.length() + b.length() - sub_a.length());
        		}
        	}
        	System.out.println(ans);
        }
        
        //out.close();
    }
}