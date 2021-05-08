/*input
2
6 4
9 5
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
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt(), k = inp.nextInt();
        	//for (int i = 0; i < k; i++) out.print("1" + (i == k - 1? "\n": " "));
        	//ArrayList<Integer> factors = primeFact(n);
        	//System.out.println(factors.toString());
       
        	int rep = 0, val = 0;
        	for (int i = 1; i <= k; i++) {
        		if ((n - k) % i == 0 && (n - k) / i <= n / 2 - 1) {
        			rep = i; 
        			val = (n - k) / i + 1;
        			break; 
        		}
        	}
        	ArrayList<Integer> ans = new ArrayList<>();
        	for (int i = 0; i < rep; i++) ans.add(val);
        	for (int i = 0; i < k - rep; i++) ans.add(1);

        	for (int i = 0; i < ans.size(); i++) out.print(ans.get(i) + (i == ans.size() - 1? "\n": " "));
    	}
        out.close();
    }
}