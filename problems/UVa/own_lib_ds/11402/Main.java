/*input
2
2
5
10
2
1000
5
F 0 17
I 0 5
S 1 10
E 4 9
S 2 10
3
3
1
4
0
2
0
2
I 0 2
S 0 8
*/
import java.util.*;
import java.io.*;

class SegmentTree {
	int length;
	int[] tree;
	Character[] lazy;
	char[] arr;
	final char None = 'N';
	void build(int node, int left, int right) {
		if (left == right) {
			tree[node] = arr[left] == '1'? 1 : 0;
			return;
		}
		int mid = (left + right) >> 1;
		build(node << 1, left, mid); build(node << 1 | 1, mid + 1, right);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	SegmentTree(char[] arr, int length) {
		this.length = length;
		this.arr = arr.clone();
		tree = new int[(length << 2) + 1];
		lazy = new Character[(length << 2) + 1];
		Arrays.fill(lazy, None);
		build(1, 0, length - 1);
	}
	char updateVal(char cur, char upd) {
		if (upd == None) return cur; 
		if (upd != 'I') return upd;
		switch (cur) {
			case 'F': return 'E';
			case 'E': return 'F';
			case 'I': return 'N';
		}
		return upd;
	}
	void propagate(int node, int left, int right) {
		if (lazy[node] == None) return;
		
		switch (lazy[node]) {
			case 'F': tree[node] = right - left + 1; break;
			case 'E': tree[node] = 0; break;
			case 'I': tree[node] = (right - left + 1) - tree[node]; break;
		}
		if (left != right) { 
			lazy[node << 1] = updateVal(lazy[node << 1], lazy[node]);
			lazy[node << 1 | 1] = updateVal(lazy[node << 1 | 1], lazy[node]);
		}
		lazy[node] = None;
	}
	void update(int node, int left, int right, int fromIndex, int toIndex, char type) {
		propagate(node, left, right);
		if (left > toIndex || right < fromIndex) return;
		if (fromIndex <= left && right <= toIndex) {
			lazy[node] = type;
			propagate(node, left, right);
			return;
		}
		int mid = (left + right) >> 1;
		update(node << 1, left, mid, fromIndex, toIndex, type); 
		update(node << 1 | 1, mid + 1, right, fromIndex, toIndex, type);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	} 
	void update(int fromIndex, int toIndex, char type) {
		update(1, 0, length - 1, fromIndex, toIndex, type);
	}
	int query(int node, int left, int right, int fromIndex, int toIndex) {
		if (left > toIndex || right < fromIndex) return 0;
		propagate(node, left, right);
		if (fromIndex <= left && right <= toIndex) return tree[node];
		
		int mid = (left + right) >> 1;
		int res = query(node << 1, left, mid, fromIndex, toIndex);
		res += query(node << 1 | 1, mid + 1, right, fromIndex, toIndex);

		//System.out.format("[%d, %d] => %d\n", left, right, res);
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
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	int m = inp.nextInt();
        	StringBuilder str = new StringBuilder();
        	while (m-- > 0) {
        		int times = inp.nextInt(); String pirates = inp.next();
        		while (times-- > 0) str.append(pirates);
        	}
        	//System.out.println(str.toString());
        	SegmentTree segTree = new SegmentTree(str.toString().toCharArray(), str.length());
         	int q = inp.nextInt();

         	System.out.format("Case %d:\n", test);
         	//segTree.update(0, 6, 'E');
         	//System.out.println(segTree.query(0, 6));
         	int queryNum = 0;
         	while (q-- > 0) {
         		char type = inp.next().charAt(0);
         		int l = inp.nextInt(), r = inp.nextInt();

         		if (type == 'S') {
         			//for (int i = 0; i < segTree.length; i++) System.out.print(segTree.query(i, i));
         			System.out.format("Q%d: %d\n", ++queryNum, segTree.query(l, r));
         		}
         		else segTree.update(l, r, type);
         	}
        }
    }
}