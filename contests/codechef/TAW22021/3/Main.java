/*input
ababbaaab 3
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
        String s = inp.next(); int rem = inp.nextInt();

        //System.out.println(ans);
        while (true) {
        	int l = 0, r = 0;
        	StringBuilder sb = new StringBuilder();
        	boolean has = false;
        	for (int i = 1; i <= s.length(); i++) {
        		if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {	
        			r = i - 1;
        			int seg = r - l + 1;
        			if (seg >= rem) has = true;
        			seg %= rem;
        			while (seg-- > 0) sb.append(s.charAt(i - 1));
        			l = i;
        		}
        	}
        	if (!has) break;
        	s = sb.toString();
        }
        System.out.println(s);

        //out.close();
    }
}