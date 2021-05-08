/*input
2

10
c 1 5
c 2 7
q 7 1
c 3 9
q 9 6
c 2 5
q 7 5

1
q 1 1
c 1 1
q 1 1
*/
import java.util.*;
import java.io.*;

class DisjointSets {
	int size;
	int[] parent, rank;
	DisjointSets(int size) {
		this.size = size;
		parent = new int[size]; rank = new int[size];
 		for (int i = 0; i < size; i++) {
			parent[i] = i; rank[i] = 0;
		}
	}
	int findSet(int i)  {
        if (parent[i] == i) return i;
        parent[i] = findSet(parent[i]);
        return parent[i];
	}
	boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}
	void unionSet(int i, int j) {
		int root_i = findSet(i), root_j = findSet(j);
		if (root_i == root_j) return;
		if (rank[root_i] > rank[root_j]) parent[root_j] = root_i;
		else { 
			parent[root_i] = root_j;
			if (rank[root_i] == rank[root_j]) rank[root_j]++;
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
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt(); inp.nextLine(); inp.nextLine();
        while (numTests-- > 0) {
        	int n = inp.nextInt(); inp.nextLine();
            //System.out.println(n);
        	DisjointSets dsu = new DisjointSets(n);
        	int[] ans = new int[2];
        	while (inp.hasNextLine()) {
        		String line = inp.nextLine();
                //System.out.println(line);
        		if (line == null || line.isEmpty()) break;
        		String[] line_split = line.split(" ");
        		char type = line_split[0].charAt(0);
        		int i = Integer.parseInt(line_split[1]) - 1, j = Integer.parseInt(line_split[2]) - 1;
        		if (type == 'c') {
        			dsu.unionSet(i, j);
        		}
        		else ans[dsu.isSameSet(i, j)? 1: 0]++;
        	}
        	System.out.println(ans[1] + "," + ans[0]);
            if (numTests != 0) System.out.println();
        }
    }
}