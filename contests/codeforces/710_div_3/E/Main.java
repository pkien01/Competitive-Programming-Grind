/*input
4
7
3 3 4 4 7 7 7
4
1 2 3 4
7
3 4 5 5 5 7 7
1
1
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
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        		
        	TreeMap<Integer, Boolean> set = new TreeMap<>();
        	for (int i = 1; i <= n; i++) set.put(i, true);
        	for (int i = 0; i < n; i++) set.remove(arr[i]);
        	int[] ans = new int[n];
        	for (int i = 0; i < n; i++) {
        		if (i == 0 || arr[i] != arr[i - 1]) {
        			ans[i] = arr[i];
        		}
        		else {
        			ans[i] = set.firstKey();
        			set.remove(ans[i]);
        		}
        	}
        	for (int i = 0; i < n; i++) System.out.print(ans[i] + (i == n - 1? "\n": " "));

        	set.clear();
        	for (int i = 1; i <= n; i++) set.put(i, true);
        	for (int i = 0; i < n; i++) set.remove(arr[i]);
        	ans = new int[n];
        	for (int i = 0; i < n; i++) {
        		if (i == 0 || arr[i] != arr[i - 1]) {
        			ans[i] = arr[i];
        		}
        		else {
        			Integer cur = set.lowerKey(arr[i]);
        			if (cur == null) cur = set.lastKey();
        			ans[i] = cur;
        			set.remove(ans[i]);
        		}
        	}

        	for (int i = 0; i < n; i++) System.out.print(ans[i] + (i == n - 1? "\n": " "));
        }
        
        //out.close();
    }
}