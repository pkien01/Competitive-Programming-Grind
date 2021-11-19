/*input
10 6 1 2 1 8
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

    static class FenwickTree {
    	int n;
    	int[] tree;
    	FenwickTree(int n) {
    		this.n = n;
    		tree = new int[n + 1];
    	}
    	int get(int pos) {
    		int res = 0;
    		for (int i = pos + 1; i > 0; i -= (i & -i)) res += tree[i];
    		return res;
    	}
    	int get(int l, int r) {
    		return get(r) - (l == 0? 0: get(l - 1));
    	}
    	void update(int pos, int val) {
    		for (int i = pos + 1; i <= n; i += (i & -i)) tree[i] += val;
    	}
    }

    static int n, r, k, x0, a, b;
    static int[] arr;

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt();
        r = inp.nextInt();
        k = inp.nextInt();
        x0 = inp.nextInt();
        a = inp.nextInt();
        b = inp.nextInt();

        arr = new int[n];

        int ans = 0;
    	long x = x0;
    	FenwickTree bit = new FenwickTree(n);
    	boolean[] used = new boolean[n];
        for (int i = 1; i <= r; i++) {
        	x = (x*a + b) % n;
        	int cur = (int)x;
        	if (arr[cur] == k) {
        		if (!used[cur]) {
        			bit.update(cur, 1);
        			used[cur] = true;
        		}
        		int l = 0, r = i - 1;
        		while (l <= r) {
        			int mid = (l + r) >> 1;
        			if (bit.get(mid, i - 1) < i - mid) l = mid + 1;
        			else r = mid - 1; 
        		}
        		cur= l - 1;
        		if (cur < 0) {
        			ans = -1;
        			break;
        		}
        	}
        	if (ans == -1) break;
        	arr[cur] += 1;
        	ans = (ans * 53 + cur) % 199933;
        }
        
        System.out.println(ans == -1? "OVERFLOW": ans);
        //out.close();
    }
}