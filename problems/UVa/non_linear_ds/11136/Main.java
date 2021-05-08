/*input
2
2 1 1
2 2 5
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
        	int n = inp.nextInt();
        	if (n == 0) break;
        	TreeMap<Integer, Integer> mp = new TreeMap<>();
        	long ans = 0L;
        	for (int i = 0; i < n; i++) {
        		int k = inp.nextInt();
        		while (k-- > 0) {
        			int x = inp.nextInt();
        			if (!mp.containsKey(x)) mp.put(x, 0);
        			mp.put(x, mp.get(x) + 1);
        		}
        		int low = mp.firstKey(), high = mp.lastKey();
        		//System.out.println(low + " " + high);
        		ans += (long)high - low;
        		mp.put(low, mp.get(low) - 1);
        		mp.put(high, mp.get(high) - 1);
        		if (mp.containsKey(low) && mp.get(low) == 0) mp.remove(low);
        		if (mp.containsKey(high) && mp.get(high) == 0) mp.remove(high);
        	}
        	System.out.println(ans);
        }
    }
}