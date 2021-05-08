/*input

*/
import java.util.*;
import java.io.*;

class Test {
    void runFromFile() {
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
    int binToDec(String s) {
    	int res = 0;
    	for (int i = s.length() - 1; i >= 0; i--) 
    		if (s.charAt(i) == '1') res += (1 << (s.length() - 1 - i));
    	return res;
    }
    String decToBin(int x) {
    	StringBuilder res = new StringBuilder();
    	while (x != 0 && res.length() < 32) {
    		res.append((char)((x & 1) + '0'));
    		x >>= 1;
    	}
    	return res.reverse().toString();
    }
    public static void main(String[] args) {
        Test obj = new Test();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int n = obj.binToDec("10010000");
        int neg = -n;
        System.out.println(obj.decToBin(neg));
        System.out.println(obj.decToBin(n & -neg));
    }
}