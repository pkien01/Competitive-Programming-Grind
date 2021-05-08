/*input
4
3
2 3 7 12 2
4
9 1 7 1 6 5
5
18 2 2 3 2 9 2
3
2 6 9 2 1
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
    static final long limit = (long)1e9;
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int[] b = new int[n + 2];
        	for (int i = 0; i < n + 2; i++) b[i] = inp.nextInt();
        	long sum = 0;
        	for (int i = 0; i < n + 2; i++) sum += (long)b[i];
        	
        	TreeMap<Integer, Integer> cnt = new TreeMap<Integer, Integer>();
        	for (int i = 0; i < n + 2; i++) {
        		if (!cnt.containsKey(b[i])) cnt.put(b[i], 0);
        		cnt.put(b[i], cnt.get(b[i]) + 1);
        	}
        	//System.out.println(sum);
        	int x = -1;
        	long sum_a = -1L;
        	for (int i = 0; i < n + 2; i++) {
        		long cur_sum_a = sum - b[i];
        		if (cur_sum_a % 2 != 0) continue;
        		cur_sum_a /= 2;
        		cnt.put(b[i], cnt.get(b[i]) - 1);
        		if (cur_sum_a <= limit && cnt.containsKey((int)cur_sum_a) && cnt.get((int)cur_sum_a) > 0) {
        			sum_a = cur_sum_a;
        			x = b[i];
        			break;
        		}
        		cnt.put(b[i], cnt.get(b[i]) + 1);
        	}
        	if (x == -1 || sum_a == -1) {
        		System.out.println("-1");
        	}
        	else {
        		//System.out.println(x + " " + sum_a);
        		ArrayList<Integer> a = new ArrayList<>();
        		for (int i = 0; i < n + 2; i++) {
        			if ((long)b[i] == sum_a) {
        				sum_a = -1;
        			}
        			else if ((long)b[i] == x) {
        				x = -1;
        			}
        			else a.add(b[i]);
        		}
        		//System.out.println("a.size() = " + a.size());
        		for (int i = 0; i < n; i++) System.out.print(a.get(i) + (i == n - 1? "\n" : " "));
        	}
        }
        
        //out.close();
    }
}