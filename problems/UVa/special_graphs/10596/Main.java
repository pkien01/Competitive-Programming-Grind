/*input
5 1
3 3
5 0
*/
import java.util.*;
import java.io.*;

class DisjointSets {
	int nsets;
	int[] par, rank;
	DisjointSets(int nsets) {
		this.nsets = nsets;
		par = new int[nsets]; rank = new int[nsets];
		for (int i = 0; i < nsets; i++) par[i] = i;
	}
	int findSet(int i) {
		if (par[i] == i) return i;
		par[i] = findSet(par[i]);
		return par[i];
	}
	boolean sameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}
	void unionSets(int i, int j) {
		int root_i = findSet(i), root_j = findSet(j);
		if (root_i == root_j) return;
		nsets--;
		if (rank[root_i] < rank[root_j]) par[root_i] = root_j;
		else {
			par[root_j] = root_i;
			if (rank[root_i] == rank[root_j]) rank[root_i]++;
		}
	}
}

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
    static int n, m;

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt(); m = inp.nextInt();
        	int[] deg = new int[n];
        	DisjointSets dsu = new DisjointSets(n);
        	boolean ans = m > 0;
        	while (m-- > 0) {
        		int u = inp.nextInt(), v = inp.nextInt();
        		deg[u]++; deg[v]++;
        		dsu.unionSets(u, v);
        	}
        	int comp = -1;
        	for (int i = 0; i < n; i++) {
        		if (deg[i] % 2 != 0) {
        			ans = false; break;
        		}
        		if (deg[i] > 0) {
        			if (comp == -1) comp = dsu.findSet(i);
        			else if (dsu.findSet(i) != comp) {
        				ans = false; break;
        			}
        		}
        	}
        	System.out.println(ans? "Possible" : "Not Possible");
        }
        
        //out.close();
    }
}