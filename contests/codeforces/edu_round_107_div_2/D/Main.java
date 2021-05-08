/*input
100 10
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
       	
        TreeMap<String, Integer> cnt = new TreeMap<>();
        int n = inp.nextInt(), k = inp.nextInt(); 
       	int[] cntPref = new int[k];
        char[] ans = new char[n];
        ans[0] = 'a';
        int sum = 0;
        for (int i = 1; i < n; i++) {
        	int minCnt = n + 1;
        	char minChar = 0;

        	//System.out.println(cnt.toString());
        	for (int j = 0; j < k; j++) {
        		char c = (char)(j + 'a');
        		String curSub = String.valueOf(ans[i - 1]) + String.valueOf(c);
        		int curCnt = cnt.containsKey(curSub)? cnt.get(curSub) : 0;
        		if (curCnt < minCnt || (curCnt == minCnt && cntPref[j] < cntPref[minChar - 'a'])) {
        			minCnt = curCnt;
        			minChar = c;
        		}
        	}
        	sum += minCnt;
        	String minSub = String.valueOf(ans[i - 1]) + String.valueOf(minChar);
        	//System.out.println(minSub);
        	cnt.put(minSub, (cnt.containsKey(minSub)? cnt.get(minSub) : 0) + 1);
        	cntPref[ans[i - 1] - 'a']++;
        	ans[i] = minChar;
        }
        System.out.println(cnt.toString() + "\nsum = " + sum);
        //out.close();
        System.out.println(String.valueOf(ans));
    }
}