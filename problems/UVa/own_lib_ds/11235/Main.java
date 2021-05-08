/*input
10 3
-1 -1 1 1 1 1 3 10 10 10
2 3
1 10
5 10
0
*/
import java.util.*;
import java.io.*;

class SegmentTree {
	int length;
	int[] arr;
	int[] tree;
	int[] firstIdx, lastIdx;
	void build(int node, int left, int right) {
		if (left == right) {
			tree[node] = 1;
			return;
		}
		int mid = (left + right) >> 1;
		build(node << 1, left, mid); build(node << 1 | 1, mid + 1, right);
		int res = Math.max(tree[node << 1], tree[node << 1 | 1]);
		if (arr[mid] == arr[mid + 1]) {
			int curFirstIdx = Math.max(firstIdx[mid], left), curLastIdx = Math.min(lastIdx[mid + 1], right);
			res = Math.max(res, curLastIdx - curFirstIdx + 1);
		}
		tree[node] = res;

	}
	SegmentTree(int[] arr, int length) {
		this.arr = arr.clone();
		this.length = length;

		firstIdx = new int[length]; lastIdx = new int[length];
		for (int i = 0; i < length; i++) {
			if (i == 0 || arr[i] != arr[i - 1]) firstIdx[i] = i;
			else firstIdx[i] = firstIdx[i - 1];
		}
		for (int i = length - 1; i >= 0; i--) {
			if (i == length - 1 || arr[i] != arr[i + 1]) lastIdx[i] = i;
			else lastIdx[i] = lastIdx[i + 1];
		}

		tree = new int[(length << 2) + 1];
		build(1, 0, length - 1);
	}
	int query(int node, int left, int right, int fromIndex, int toIndex) {
		if (right < fromIndex || left > toIndex) return 0; //  outside range [fromIndex, toIndex]
		if (fromIndex <= left && right <= toIndex) {
			return tree[node];
		}

		int mid = (left + right) >> 1;
		int res = query(node << 1, left, mid, fromIndex, toIndex); 
		res = Math.max(res, query(node << 1 | 1, mid + 1, right, fromIndex, toIndex));

		if (arr[mid] == arr[mid + 1]) {
			int curFirstIdx = Math.max(firstIdx[mid], fromIndex), curLastIdx = Math.min(lastIdx[mid + 1], toIndex);
			res = Math.max(res, curLastIdx - curFirstIdx + 1);
		}
		return res;
	}
	int query(int fromIndex, int toIndex) {
		return query(1, 0, length - 1, fromIndex, toIndex);
	}
}

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
        	int q = inp.nextInt();
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	SegmentTree segTree = new SegmentTree(arr, n);
        	while (q-- > 0) {
        		int l = inp.nextInt() - 1, r = inp.nextInt() - 1;
        		System.out.println(segTree.query(l, r));
        	}
        }
    }
}