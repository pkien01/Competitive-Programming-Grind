/*input
HELP
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
    static boolean solve() {
    	int n = s.length();
    	if (n < 2) return true;
    	int[] arr = new int[n];
    	for (int i = 0; i < n; i++) arr[i] = (int)s.charAt(i) - 'A';
    	Arrays.sort(arr);
    	System.out.println(Arrays.toString(arr));
    	for (int i = 2; i < n; i++) {
    		if (arr[i] == arr[i - 1] + arr[i - 2]) return true;
    	}
    	return false;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        s = inp.nextLine();
        System.out.println(solve()?"YES" : "NO");
        //out.close();
    }
}