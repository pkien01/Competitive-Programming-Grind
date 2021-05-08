/*input
2
2 2
KICK
START
8 2
G
G
GO
GO
GOO
GOO
GOOO
GOOO
*/
import java.util.*;
import java.io.*;

class Trie {
	int countPref, countEnd;
	Trie[] next;	
	int depth;
	Trie(int depth) {
		countPref = 0; countEnd = 0;
		next = new Trie[26];
		this.depth = depth;
	}
	void insert(String str) {
		Trie cur = this;
		for (int i = 0; i < str.length(); i++) {
			int curChar = (int)str.charAt(i) - 'A';
			cur.countPref++;
			if (cur.next[curChar] == null) cur.next[curChar] = new Trie(depth + i + 1);
			cur = cur.next[curChar];
		}
		cur.countPref++;
		cur.countEnd++;
	}
	boolean isLeaf() {
		for (int i = 0; i < 26; i++) if (this.next[i] != null) return false;
		return true;
	}
	//Trie prev; 
	int solve(int k) {
		int res = 0;
		int remSize = 0;
		for (int i = 0; i < 26; i++) {
			if (this.next[i] == null) continue;
			res += this.next[i].solve(k);
			remSize += this.next[i].countPref % k;
		}
		remSize += countEnd;

		res += (remSize / k) * depth;
		//System.out.println("depth " + depth + ": " + remSize);
		return res; 
	}
}
class Solution {
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
        Solution obj = new Solution();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	int n = inp.nextInt(), k = inp.nextInt();
 			Trie root = new Trie(0);
        	for (int i = 0; i < n; i++) root.insert(inp.next());
        	System.out.format("Case #%d: %d\n", test, root.solve(k));
        }
    }
}