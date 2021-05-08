/*input
7
1 1 1 2 2 2 2
7
1 1 2 3 4 5 6
7
1 1 2 2 2 3 3
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
    static int[] arr;

    static List<List<Integer>> solve() {
    	Arrays.sort(arr);
    	int maxLen = 0, prev = 0;
    	//res.add(new ArrayList<>()); res.get(0).add(arr[0]);
    	for (int i = 0; i < n - 1; i++) {
    		if (arr[i] != arr[i + 1]) {
    			maxLen = Math.max(maxLen, i - prev + 1);
    			prev = i + 1;
    		}
    	}
    	maxLen = Math.max(maxLen, n - prev);

    	List<List<Integer>> res = new ArrayList<>();
    	for (int i = 0; i < maxLen; i++) res.add(new ArrayList<>());
    	for (int i = 0; i < n; i++) res.get(i % maxLen).add(arr[i]);
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        boolean firstLoop = true;
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	if (n == 0) break;
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();

        	List<List<Integer>> ans = solve();
        	if (firstLoop) firstLoop = false;
        	else System.out.println();
        	System.out.println(ans.size());
        	for (int i = 0; i < ans.size(); i++) {
        		List<Integer> curAns = ans.get(i);
        		for (int j = 0; j < curAns.size(); j++) System.out.print(curAns.get(j) + (j == curAns.size() - 1? "\n" : " "));
        	}
        }
        
        //out.close();
    }
}