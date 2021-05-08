/*input
2
3
4
0
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
    static int n;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	ArrayList<Integer> ans = new ArrayList<>();
        	boolean hasZero = false;
        	for (int i = 0; i < n; i++) {
        		int x = inp.nextInt();
        		if (x != 0) ans.add(x);
        		else hasZero = true;
        	}
        	if (ans.isEmpty() && hasZero) ans.add(0);
        	for (int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + (i == ans.size() - 1? "\n": " "));
        }
        
        //out.close();
    }
}