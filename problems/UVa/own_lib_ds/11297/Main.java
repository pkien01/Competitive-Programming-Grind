/*input
5
1 2 3 4 5
0 9 2 1 3
0 2 3 4 1
0 1 2 4 5
8 5 3 1 4
4
q 1 1 2 3
c 2 3 10
q 1 1 5 5
q 1 2 2 2
*/
import java.util.*;
import java.io.*;

class RecArea {
	int x1, y1, x2, y2;
	RecArea(int x1, int y1, int x2, int y2) {
		assert x1 <= x2 && y1 <= y2;
		this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
	}
	RecArea subArea(int id) {
		int x_mid = (x1 + x2) >> 1, y_mid = (y1 + y2) >> 1; 
		switch (id) {
			case 1: return new RecArea(x1, y1, x_mid, y_mid); 
			case 2: return new RecArea(x1, y_mid + 1, x_mid, y2); 
			case 3: return new RecArea(x_mid + 1, y1, x2, y_mid);
			case 4: return new RecArea(x_mid + 1, y_mid + 1, x2, y2);
		}
		return new RecArea(-1, -1, -1, -1);
	}
	boolean equals(RecArea other) {
		return x1 == other.x1 && y1 == other.y1 && x2 == other.x2 && y2 == other.y2;
	}
	boolean inside(RecArea other) {
		return other.x1 <= x1 && x2 <= other.x2 && other.y1 <= y1 && y2 <= other.y2;
	}
	boolean outside(RecArea other) {
		if (x2 < other.x1 || x1 > other.x2) return true;
		if (y2 < other.y1 || y1 > other.y2) return true;
		return false;
	}
	@Override
	public String toString() {
		return String.format("(x1=%d, y1=%d, x2=%d, y2=%d)", x1, y1, x2, y2);
	}
}
class SegmentTree2D {
	int nrows, ncols;
	int[] tree;
	boolean mode;
	int combine(int a, int b) {
		return mode? Math.max(a, b) : Math.min(a, b);
	}
	void build(int node, RecArea curArea, int[][] arr) {
		if (curArea.x1 == curArea.x2 && curArea.y1 == curArea.y2) {
			tree[node] = arr[curArea.x1][curArea.y1];
 			return;
		}
		int res = mode? 0 : Integer.MAX_VALUE;
		if (curArea.x1 == curArea.x2) {
			build(node * 4 + 1, curArea.subArea(1), arr);
			build(node * 4 + 2, curArea.subArea(2), arr);
			res = combine(tree[node * 4 + 1], tree[node * 4 + 2]);
		}
		else if (curArea.y1 == curArea.y2) {
			build(node * 4 + 1, curArea.subArea(1), arr);
			build(node * 4 + 3, curArea.subArea(3), arr);
			res = combine(tree[node * 4 + 1], tree[node * 4 + 3]);
		}
		else {
			for (int i = 1; i <= 4; i++) {
				build(node * 4 + i, curArea.subArea(i), arr);
				res = combine(res, tree[node * 4 + i]);
			}
		}
		//System.out.format("build| node = %d, %s: %d\n", node, curArea.toString(), res);
		tree[node] = res;
		//System.out.format("%s, tree[%d][%d] = %d\n", curArea.toString(), xNode, yNode, tree[xNode][yNode]);
		//System.out.format("%s, mode = %b: %d\n", curArea.toString(), mode, res);
	}
	SegmentTree2D(int nrows, int ncols, int[][] arr, boolean mode) {
		this.nrows = nrows; this.ncols = ncols;
		this.mode = mode;
		tree = new int[(nrows * ncols) << 2 | 1];
		build(0, new RecArea(0, 0, nrows - 1, ncols - 1), arr);
	}
	int query(int node, RecArea curArea, RecArea targetArea) {
		if (curArea.outside(targetArea)) return mode? 0: Integer.MAX_VALUE;
		if (curArea.inside(targetArea)) {
			//System.out.format("queryx| node = %d, %s: %d\n", node, curArea.toString(), tree[node]);
			return tree[node];
		}

		int res = mode? 0: Integer.MAX_VALUE;
		if (curArea.x1 == curArea.x2) 
			res = combine(query(node * 4 + 1, curArea.subArea(1), targetArea), query(node * 4 + 2, curArea.subArea(2), targetArea));
		
		else if (curArea.y1 == curArea.y2) 
			res = combine(query(node * 4 + 1, curArea.subArea(1), targetArea), query(node * 4 + 3, curArea.subArea(3), targetArea));
		
		else {
			for (int i = 1; i <= 4; i++) {
				//System.out.println((node * 4 + i) + " " + curArea.subArea(i).toString());
				res = combine(res, query(node * 4 + i, curArea.subArea(i), targetArea));
			}
		}
		//System.out.format("query| node = %d, %s: %d\n\n", node, curArea.toString(), res);
		return res;
	}
	int query(RecArea targetArea) {
		return query(0, new RecArea(0, 0, nrows - 1, ncols - 1), targetArea);
	}
	void update(int node, RecArea curArea, int upd_x, int upd_y, int upd_val) {
		final RecArea upd_pos = new RecArea(upd_x, upd_y, upd_x, upd_y);
		if (curArea.outside(upd_pos)) return;
		if (curArea.equals(upd_pos)) {
			tree[node] = upd_val; 
			return;
		}
		int res = mode? 0 : Integer.MAX_VALUE;
		if (curArea.x1 == curArea.x2) {
			update(node * 4 + 1, curArea.subArea(1), upd_x, upd_y, upd_val);
			update(node * 4 + 2, curArea.subArea(2), upd_x, upd_y, upd_val);
			res = combine(tree[node * 4 + 1], tree[node * 4 + 2]);
		}
		else if (curArea.y1 == curArea.y2) {
			update(node * 4 + 1, curArea.subArea(1), upd_x, upd_y, upd_val);
			update(node * 4 + 3, curArea.subArea(3), upd_x, upd_y, upd_val);
			res = combine(tree[node * 4 + 1], tree[node * 4 + 3]);
		}
		else {
			for (int i = 1; i <= 4; i++) {
				update(node * 4 + i, curArea.subArea(i), upd_x, upd_y, upd_val);
				res = combine(res, tree[node * 4 + i]);
			}
		}
		tree[node] = res;
	}
	void update(int upd_x, int upd_y, int upd_val) {
		update(0, new RecArea(0, 0, nrows - 1, ncols - 1), upd_x, upd_y, upd_val);
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
      	int n = inp.nextInt();
      	int[][] arr = new int[n][n];
      	for (int i = 0; i < n; i++)
      		for (int j = 0; j < n; j++) arr[i][j] = inp.nextInt();

      	SegmentTree2D segTreeMin = new SegmentTree2D(n, n, arr, false);
      	SegmentTree2D segTreeMax = new SegmentTree2D(n, n, arr, true);
      	//System.out.println(segTreeMax.query(new RecArea(0, 0, 1, 1)));
      	
      	int q = inp.nextInt();
      	while (q-- > 0) {
      		char type = inp.next().charAt(0); 
      		if (type == 'q') {
      			RecArea targetArea = new RecArea(inp.nextInt() - 1, inp.nextInt() - 1, inp.nextInt() - 1, inp.nextInt() - 1);
      			System.out.println(segTreeMax.query(targetArea) + " " + segTreeMin.query(targetArea));
      		}
      		else {
      			int upd_x = inp.nextInt() - 1, upd_y = inp.nextInt() - 1, upd_val = inp.nextInt();
      			segTreeMax.update(upd_x, upd_y, upd_val);
      			segTreeMin.update(upd_x, upd_y, upd_val);
      		}
      	}
      	
    }
}