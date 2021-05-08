/*input
5
6
1 6 1 1 4 4
2
1 2
2
1 1
5
4 5 4 5 4
6
2 3 2 1 3 1
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
        	TreeMap<Integer, Integer> cnt = new TreeMap<Integer, Integer>();
        	for (int i = 0; i < n; i++) {
        		int x = inp.nextInt();
        		if (!cnt.containsKey(x)) cnt.put(x, 0);
        		cnt.put(x, cnt.get(x) + 1);
        	}
        	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        	for (Integer val: cnt.values()) pq.add(val);
        	while (pq.size() >= 2) {
        		int cnt1 = pq.poll(), cnt2 = pq.poll();
        		if (cnt1 > 0 && cnt2 > 0) {
        			pq.add(cnt1 - 1); pq.add(cnt2 - 1);
        		}
        		else {
        			pq.add(cnt1); pq.add(cnt2); break;
        		}
        	}
        	int ans = 0;
        	while (!pq.isEmpty()) ans += pq.poll();
        	System.out.println(ans);
        }
        
        //out.close();
    }
}
