/*input
10
4 7 3 2 5 1 9 10 6 8
-4 40 -46 -8 -16 4 -10 41 12 3
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
        FastReader inp = new FastReader(System.in);
        //PrintWriter out = new PrintWriter(System.out);
	    int numTests = 1;
	    while (numTests-- > 0) {
	    	int n = inp.nextInt();
	    	int[] height = new int[n], beauty = new int[n];
	    	for (int i = 0; i < n; i++) height[i] = inp.nextInt();
	    	for (int i = 0; i < n; i++) beauty[i] = inp.nextInt();

	    	SegmentTree minHeight = new SegmentTree(height, false);
	    	SegmentTree maxDp = new SegmentTree(n, true);

	    	long[] dp = new long[n];
	    	dp[0] = (long)beauty[0];
	    	maxDp.update(0, dp[0]);
	    	for (int i = 1; i < n; i++) {
	    		/*
	    		for (int j = i - 1; j >= 0; j--) {
	    			//if (height[j] < height[curMin]) curMin = j;
	    			dp[i] = Math.max(dp[i], dp[j] + beauty[curMin]);
	    			if (height[j] < height[curMin]) curMin = j;
	    		}*/
	    		int l = 0, r = i;
	    		while (l <= r) {
	    			int mid = (l + r) >> 1;
	    			if (minHeight.query(mid, i) >= height[i]) r = mid - 1;
	    			else l = mid + 1;
	    		}
	    		int leftMost = r + 1;
	    		//System.out.println(leftMost - 1 + " " + (i - 1) + ": " + maxDp.query(leftMost - 1, i - 1));
	    		//System.out.println(leftMost);
	    		if (leftMost <= i) {
	    			if (leftMost > 0) dp[i] = maxDp.query(leftMost - 1, i - 1) + beauty[i];
	    			else dp[i] = Math.max(maxDp.query(0, i - 1), 0) + beauty[i];
	    		}
	    		if (beauty[i] < 0 && leftMost > 0) dp[i] = Math.max(dp[i], dp[leftMost - 1]); 
	    		maxDp.update(i, dp[i]);

	    	}
	    	System.out.println(dp[n - 1]);
	    }
        //out.close();
    }
}


class SegmentTree {
	int length;
	long[] tree;
	int[] arr;
	boolean type;
	long invalid;

	long combine(long leftVal, long rightVal) {
		return type? Math.max(leftVal, rightVal) : Math.min(leftVal, rightVal);
	}

	void build(int node, int left, int right) {
		if (left == right) {
			tree[node] = (long)arr[left];
			return;
		}
		int mid = (left + right) >> 1;
		build(node << 1, left, mid); build(node << 1 | 1, mid + 1, right);
		tree[node] = combine(tree[node << 1], tree[node << 1 | 1]);
	}
	SegmentTree(int[] arr, boolean type) {
		this.length = arr.length;
		this.arr = arr.clone();
		this.type = type;
		this.invalid = (long)1e15 * (type? -1: 1);
		tree = new long[length << 2];
		build(1, 0, length - 1);
	}
	SegmentTree(int length, boolean type) {
		this.length = length;
		this.type = type;
		this.invalid = (long)1e15 * (type? -1: 1);
		tree = new long[length << 2];
		Arrays.fill(tree, invalid);
	}
	void update(int node, int left, int right, int pos, long val) {
		if (pos < left || pos > right) return;
		if (left == pos && pos == right) {
			tree[node] = val;
			return;
		}
		int mid = (left + right) >> 1;
		update(node << 1, left, mid, pos, val); 
		update(node << 1| 1, mid + 1, right, pos, val);
		tree[node] = combine(tree[node << 1], tree[node << 1 | 1]);
	}
	void update(int pos, long val) {
		update(1, 0, length - 1, pos, val);
	}
	long query(int node, int left, int right, int fromIndex, int toIndex) {
		if (left > toIndex || right < fromIndex) return invalid;
		if (fromIndex <= left && right <= toIndex) return tree[node];
		int mid = (left + right) >> 1;
		return combine(query(node << 1, left, mid, fromIndex, toIndex), query(node << 1 | 1, mid + 1, right, fromIndex, toIndex));
	}
	long query(int fromIndex, int toIndex) {
		return query(1, 0, length - 1, fromIndex, toIndex);
	}
}


class FastReader {
    BufferedReader reader;
    StringTokenizer tokenizer;
    FastReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }
    FastReader() {
        this(System.in);
    }
    String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }        
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String line = new String();
        try {
            line = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}