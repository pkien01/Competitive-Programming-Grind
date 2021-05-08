/*input
2
5 3
100
-75
-25
-42
42
0 1
1 2
3 4
4 2
15
20
-10
-25
0 2
1 3
*/
import java.util.*;
import java.io.*;

class DisjointSets {
	int nsets;
	int[] parent, rank, size;
	DisjointSets(int nsets, int[] size) {
		this.nsets = nsets;
		this.parent = new int[nsets]; this.rank = new int[nsets]; this.size = new int[nsets];
		for (int i = 0; i < nsets; i++) {
			this.parent[i] = i;
			this.rank[i] = 0;
			this.size[i] = size[i];
		}
	}
	int findSet(int i) {
		if (parent[i] == i) return i;
		parent[i] = findSet(parent[i]);
		return parent[i];
	}
	boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}
	int unionSet(int i, int j) {
		int root_i = findSet(i), root_j = findSet(j);
		if (root_i == root_j) return root_i;
		if (rank[root_i] > rank[root_j]) {
			parent[root_j] = root_i;
			size[root_i] += size[root_j];
			return root_i;
		}
		else {
			parent[root_i] = root_j;
			size[root_j] += size[root_i];
			if (rank[root_i] == rank[root_j]) rank[root_j]++;
			return root_j;
		}
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
        obj.runFromFile(); 
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	int[] money = new int[n];
        	for (int i = 0; i < n; i++) money[i] = inp.nextInt();
        	DisjointSets dsu = new DisjointSets(n, money);
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		dsu.unionSet(u, v);
        	}
        	boolean ans = true;
        	for (int i = 0; i < n; i++) {
        		if (dsu.size[dsu.findSet(i)] != 0) {
        			ans = false; break;
        		}
        	}
        	//System.out.println(Arrays.toString(money));
        	System.out.println(ans? "POSSIBLE" : "IMPOSSIBLE");
        }
    }
}