/*input
4 3
3 1 2 3
1 3 2
2 2 3
4 -1
0
3 1 2 3
5 -2 11
*/
import java.util.*;
import java.io.*;

class Pair<X extends Comparable<X>, Y extends Comparable<Y>> implements Comparable<Pair<X, Y>> {
	X first; Y second;
	Pair(X first, Y second) {
		this.first = first; this.second = second;
	}

	@Override
	public int compareTo(Pair<X, Y> other) {
		if (this.first.compareTo(other.first) != 0) return this.first.compareTo(other.first);
		return this.second.compareTo(other.second);
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
        	int m = inp.nextInt(), n = inp.nextInt();
        	ArrayList<Pair<Integer, Integer>>[] ans = new ArrayList[n];
        	for (int i = 0; i < m; i++) {
        		int r = inp.nextInt();
        		int[] inds = new int[r], elems = new int[r]; 
        		for (int j = 0; j < r; j++) inds[j] = inp.nextInt() - 1;
        		for (int j = 0; j < r; j++) elems[j] = inp.nextInt();
        		for (int j = 0; j < r; j++) {
        			if (ans[inds[j]] == null) ans[inds[j]] = new ArrayList<>();
        			ans[inds[j]].add(new Pair(i + 1, elems[j]));
        		} 
        		
        	}
        	System.out.println(n + " " + m);
        	for (int i = 0; i < n; i++) {
        		if (ans[i] != null && ans[i].size() > 0) {
        			System.out.print(ans[i].size() + " ");
    
        			for (int j = 0; j < ans[i].size(); j++) {
        				System.out.print(ans[i].get(j).first);
						if (j < ans[i].size() - 1) System.out.print(" ");
        			}
        			System.out.println();
        			for (int j = 0; j < ans[i].size(); j++) { 
        				System.out.print(ans[i].get(j).second);
        				if (j < ans[i].size() - 1) System.out.print(" ");
        			}
        			System.out.println();
        		}
        		else System.out.println("0\n");
        	}
        }
    }
}