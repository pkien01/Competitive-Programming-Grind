/*input
1
3
Fred Barney
Barney Betty
Betty Wilma
*/
import java.util.*;
import java.io.*;

class DisjointSets {
	int nsets;
	int[] parent, rank, size;
	DisjointSets(int nsets) {
		this.nsets = nsets;
		parent = new int[nsets]; rank = new int[nsets]; size = new int[nsets];
		for (int i = 0; i < nsets; i++) {
			parent[i] = i;
			rank[i] = 0;
			size[i] = 1;
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
			if (rank[root_i] == rank[root_j]) rank[root_j]++;
			size[root_j] += size[root_i];
			return root_j;
		}
	}
}

class PairInt {
	int first, second;
	PairInt(int first, int second) {
		this.first = first; this.second = second;
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
        while (numTests-- > 0) {
        	int m = inp.nextInt();
        	Map<String, Integer> mp = new TreeMap<>();
        	int cntNames = 0;
        	ArrayList<PairInt> edges = new ArrayList<>();
        	while (m-- > 0) {
        		String name1 = inp.next(), name2 = inp.next();
        		if (!mp.containsKey(name1)) mp.put(name1, cntNames++);
        		if (!mp.containsKey(name2)) mp.put(name2, cntNames++);
        		edges.add(new PairInt(mp.get(name1), mp.get(name2)));
        	}
        	DisjointSets dsu = new DisjointSets(cntNames);
        	for (PairInt edge: edges) {
        		System.out.println(dsu.size[dsu.unionSet(edge.first, edge.second)]);
        	}
        }
    }
}