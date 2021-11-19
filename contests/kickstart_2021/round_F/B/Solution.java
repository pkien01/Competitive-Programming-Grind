/*input
2
10 4 2
800 2 8
1500 6 9
200 4 7
400 3 5
5 3 3
400 1 3
500 5 5
300 2 3
*/
import java.util.*;
import java.io.*;

public class Solution {
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
    static class Attraction implements Comparable<Attraction> {
    	int score, l ,r;
    	Attraction(int score, int l, int r) {
    		this.score = score; this.l = l; this.r = r;
    	}
    	@Override
    	public int compareTo(Attraction other) {
    		return other.score - this.score;
    	}
    }

    static class FenwickTree {
    	int n;
    	long[] tree;
    	FenwickTree(int n) {
    		this.n = n;
    		tree = new long[n + 1];
    	}
    	long get(int pos) {
    		long res = 0;
    		for (int i = pos; i > 0; i -= (i & -i)) res += tree[i];
    		return res;
    	}
    	void update(int pos, int val) {
    		for (int i = pos; i <= n; i += (i & -i)) tree[i] += (long)val;
    	}
    }

    static int d, n, k;
    static Attraction[] arr;
    static ArrayList<Integer>[] seg;
    static FenwickTree bitCnt, bitVal;

    static int kthElem() {
    	int l = 1, r = n;
    	while (l <= r) {
    		int mid = (l + r) >> 1;
    		//System.out.println(mid + ":  " + bitCnt.get(mid));
    		if ((int)bitCnt.get(mid) <= k) l = mid + 1;
    		else r = mid - 1;
    	}
    	return l - 1;
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	d = inp.nextInt(); n = inp.nextInt(); k = inp.nextInt();
        	//arr = new ArrayList[d + 1];
        	//for (int i = 0; i <= d; i++) arr[i] = new ArrayList<>();
        	arr = new Attraction[n];
        	for (int i = 0; i < n; i++) arr[i] = new Attraction(inp.nextInt(), inp.nextInt() - 1, inp.nextInt() - 1);
        	Arrays.sort(arr);

        	seg = new ArrayList[d + 1];
        	for (int i = 0; i <= d; i++) seg[i] = new ArrayList<>();
        	for (int i = 0; i < n; i++) {
        		seg[arr[i].l].add(i + 1);
        		seg[arr[i].r + 1].add(-i - 1); 
        	}
        	//for (int i = 0; i <= d; i++) System.out.println(seg[i]);

        		
        	bitCnt = new FenwickTree(n);
        	bitVal = new FenwickTree(n);
        	//bitCnt.update(1, 2);
        	//System.out.println("bug: " + bitCnt.get(n));
        	long ans = 0;
        	for (int i = 0; i < d; i++) {
        		for (Integer pos: seg[i]) {
        			if (pos > 0) {
        				bitCnt.update(pos, 1);
        				bitVal.update(pos, arr[pos - 1].score);
        			}
        			else {
        				pos = -pos;
        				bitCnt.update(pos, -1);
        				bitVal.update(pos, -arr[pos - 1].score);
        			}
        		}
        		int x = kthElem();
        		ans = Math.max(ans, bitVal.get(x));
        	}
        	System.out.format("Case #%d: %d\n", test, ans);
        }
        
        
        //out.close();
    }
}