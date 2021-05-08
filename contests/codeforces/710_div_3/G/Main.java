/*input
6
codeforces
aezakmi
abacaba
convexhull
swflldjgpaxs
myneeocktxpqjpz
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
        	char[] s = inp.next().toCharArray();
        	int[] lastIdx = new int[26];
        	for (int i = 0; i < s.length; i++) lastIdx[(int)s[i] - 'a'] = i;
        	/*
        	StringBuilder ans = new StringBuilder();
        	for (int i = 0; i < s.length; i++) {
        		if (s[i] < )
        	}*/
        	System.out.println(Arrays.toString(lastIdx));
        }
        
        //out.close();
    }
}