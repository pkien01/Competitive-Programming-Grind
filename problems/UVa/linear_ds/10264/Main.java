/*input
3
82
73
8
49
120
44
242
58
2
1
1
1
1
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
        int[] corners = new int[1 << 15], potent = new int[1 << 15];
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	for (int i = 0; i < (1 << n) && inp.hasNextInt(); i++) corners[i] = inp.nextInt();
    
        	for (int i = 0; i < (1 << n); i++) {
        		int sum = 0;
        		for (int j = 0; j < n; j++) sum += corners[i ^ (1 << j)];
        		potent[i] = sum;
        	}
        	int ans = 0;
        	for (int i = 0; i < (1 << n); i++) 
        		for (int j = 0; j < n; j++) ans = Math.max(ans, potent[i] + potent[i ^ (1 << j)]);
        	
        	System.out.println(ans);
        }
    }
}