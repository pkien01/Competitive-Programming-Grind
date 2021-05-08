/*input
+A+B+C+D-E-F-G-H;
-A-B+C+D-E-F+G+H;
-A+B-C+D-E+F-G+H;
.
+A+B+C+D;
+E+F+F+H;
+A+B-G;
+O+J-F;
+H+I+C;
+P;
+O+M+L;
+M-L+P;
.
+A+B+C+D;
+E+F+F+H;
+A+B-G;
+P-O;
+O+J-F;
+H+I+C;
+P;
+O;
+O+M+L;
-O-P;
+M-L+P;
.
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

    static final int maxCharNum = (int)'P' - 'A' + 1;
    static int n;
    static ArrayList<Integer>[] include, omit;

    static String maskToString(int mask) {
    	StringBuilder res = new StringBuilder();
    	for (int i = 0; i < maxCharNum; i++) 
    		if ((mask >> i & 1) != 0) res.append((char)(i + 'A'));
    	return res.toString();
    }
    static boolean solve(int charNum, int satisfy, int ansMask) {
    	//System.out.println(maskToString(ansMask) + " " + Integer.toBinaryString(satisfy));
    	//if (charNum >= maxCharNum) return false;
    	if (satisfy == (1 << n) - 1) {
    		System.out.println("Toppings: " + maskToString(ansMask));
    		return true;
    	}
    	if (charNum >= maxCharNum) return false;
    	if (include[charNum].isEmpty() && omit[charNum].isEmpty()) return solve(charNum + 1, satisfy, ansMask);
    	// omit
    	int nextSatisfy = satisfy;
    	for (Integer idx: omit[charNum]) nextSatisfy |= (1 << idx);
    	if (solve(charNum + 1, nextSatisfy, ansMask)) return true;
    	
    	// include
    	nextSatisfy = satisfy;
    	ansMask |= (1 << charNum);
    	for (Integer idx: include[charNum]) nextSatisfy |= (1 << idx);
    	if (solve(charNum + 1, nextSatisfy, ansMask)) return true;

    	return false;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        include = new ArrayList[maxCharNum];
        omit = new ArrayList[maxCharNum];
        while (inp.hasNextLine()) {
        	for (int i = 0; i < maxCharNum; i++) {
        		include[i] = new ArrayList<>();
        		omit[i] = new ArrayList<>();
        	}
        	n = 0;
        	while (true) {
        		String s = inp.nextLine();
        		if (s.equals(".")) break;
        		for (int i = 1; i < s.length(); i += 2) {
        			int charNum = (int)s.charAt(i) - 'A';
        			if (s.charAt(i - 1) == '+') include[charNum].add(n);
        			else omit[charNum].add(n);
        		}
        		n++;
        	}
        	//System.out.println(n);
        	//for (int i = 0; i < maxCharNum; i++) System.out.println(include[i].toString() + " " + omit[i].toString());
        	if (!solve(0, 0, 0)) System.out.println("No pizza can satisfy these requests.");
        }
        
        //out.close();
    }
}