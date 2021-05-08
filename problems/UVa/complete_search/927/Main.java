/*input
2
4 3 0 0 0 23
25
100
1 0 1
1
6
*/
import java.util.*;
import java.io.*;

public class Main {
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
    int findCeilRoot(int a, int b, int c) {
    	double delta = (double)b * b - 4.0 *a * c;
    	return (int)Math.ceil(((double)-b + Math.sqrt(delta)) / (2.0 * a));
    }
    long solve(int[] coef, int d, int k) {
    	int n = findCeilRoot(1, 1, -2 * k / d);
    	long res = 0L, pw_n = 1L;
    	for (int i = 0; i < coef.length; i++) {
    		res += (long)coef[i] * pw_n;
    		pw_n *= n;
    	}
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTest = inp.nextInt();
        while (numTest-- > 0) {
        	int degree = inp.nextInt();
        	int[] coef = new int[degree + 1];
        	for (int i = 0; i <= degree; i++) coef[i] = inp.nextInt();
        	int d = inp.nextInt(), k = inp.nextInt();
        	System.out.println(obj.solve(coef, d, k));
        }
    }
}