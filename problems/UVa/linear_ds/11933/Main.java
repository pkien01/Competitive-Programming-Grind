/*input
6
7
13
0
*/
import java.util.*;
import java.io.*;

class Main {
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
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int x = inp.nextInt();
        	if (x == 0) break;
        	int a = 0, b = 0, cnt = 0;
        	for (int i = 0; i < 31; i++) {
        		if ((x >> i & 1) != 0) {
        			if (cnt == 0) a |= (1 << i);
        			else b |= (1 << i);
        			cnt ^= 1;
        		}
        	}
        	System.out.println(a + " " + b);
        }
    }
}