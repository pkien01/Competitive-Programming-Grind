/*input
aaaaaaaaaaaaaabbbbbbbbbdddddddddddccccccccccccc
3
aaaaaaaaaaaaaaaaaaa
aaaaaaaaaaabbbbbbbbbbbc
abccc
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
    static String s;
    static ArrayList<Integer>[] indexes;
    
    static int charToNum(char c) {
    	if (Character.isLowerCase(c)) return (int)c - 'a';
    	if (Character.isUpperCase(c)) return (int)c - 'A' + 26;
    	return 52;
    }
    static void preprocess() {
    	indexes = new ArrayList[53];
    	for (int i = 0; i < 53; i++) indexes[i] = new ArrayList<Integer>();
    	for (int i = 0; i < s.length(); i++) indexes[charToNum(s.charAt(i))].add(i);
    }
    static void solve(String t) {
    	int firstIdx = -1, lastIdx = -1;
    	for (int i = 0, j = 0; i < t.length(); i++) {
    		int curChar = charToNum(t.charAt(i));
    		int find = Collections.binarySearch(indexes[curChar], j);
    		if (find < 0) find = -find - 1;
    		if (find >= indexes[curChar].size()) {
    			System.out.println("Not matched");
    			return;
    		}
    		int pos = indexes[curChar].get(find);
    		if (firstIdx == -1) firstIdx = pos;
    		lastIdx = pos;
    		j = pos + 1;
    	}
    	System.out.println("Matched " + firstIdx + " " + lastIdx);
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        s = inp.next();
        preprocess();
        int q = inp.nextInt();
        while (q-- > 0) {
        	String t = inp.next();
        	solve(t);
        }
        //out.close();
    }
}