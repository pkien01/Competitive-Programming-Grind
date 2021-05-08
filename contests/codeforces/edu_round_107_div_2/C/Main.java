/*input
7 5
2 1 1 4 3 3 1
3 2 1 1 4
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
        int n = inp.nextInt(), q = inp.nextInt();
        int[] pos = new int[51];
        Arrays.fill(pos, -1);
        for (int i = 1; i <= n; i++) {
        	int x = inp.nextInt();
        	if (pos[x] == -1) pos[x] = i;
        }
        for (int i = 1; i <= q; i++) {
        	int val = inp.nextInt();
        	for (int x = 1; x <= 50; x++) if (x != val && pos[x] < pos[val]) pos[x]++;
        	System.out.print(pos[val] + (i == q? "\n": " "));
        	pos[val] = 1;
        }
        
        
        //out.close();
    }
}