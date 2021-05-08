/*input
2709727097 1717317173 3015630156
1142911429 2225322253 3015630156
3083430834 3060830608 3230932309
2832328323 1288712887 3230932309
1390913909 1367713677 3230932309
2150721507 53335333 3230932309
21072107 2429024290 3230932309
3274332743 2447124471 3230932309
2660126601 1153311533 3265532655
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

    static long n, low, high;

    static long solve() {
    	long res = 0;
    	for (int i = 32 - 1; i >= 0; i--) {
    		int onesPref = i == 0? 0: (1 << i) - 1;
    		if (((n >> i & 1L) == 0 && (res | (1L << i)) <= high) || (res | onesPref) < low)  
    			res |= (1L << i);


    		/*
    		else {
    			if (res < low) res |= (1 << i);
    		}*/
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLong()) {
        	n = inp.nextLong(); low = inp.nextLong(); high = inp.nextLong();
        	//System.out.println(low + " " + high);
        	System.out.println(solve());
        }
        
        //out.close();
    }
}