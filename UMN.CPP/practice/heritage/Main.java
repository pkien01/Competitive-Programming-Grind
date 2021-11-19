/*input
5 heimark
hei 2
mark 2
heim 1
ark 2
heima 1
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

    static class Trie {
    	Trie[] child;
    	int val;
    	Trie() {
    		child = new Trie[26];
    		val = 0;
    	}
    	void insert(String str, int val) {
    		Trie cur = this;
    		for (int i = str.length() - 1; i >= 0; i--) {
    			int c = str.charAt(i) - 'a';
    			if (cur.child[c] == null) cur.child[c] = new Trie();
    			cur = cur.child[c];
    		}
    		cur.val += val;
    	}
    }
    static int n, w;
    static String s;
    static Trie root;

    static final long mod = 1000000007;
    static int solve() {
    	long[] dp = new long[n + 1];
    	dp[0] = 1;
    	for (int i = 1; i <= n; i++) {
    		dp[i] = 0;
    		Trie cur = root;
    		//System.out.println(cur.val);
    		for (int j = i; j >= 1; j--) {
    			int c = s.charAt(j - 1) - 'a';
    			cur = cur.child[c];
    			if (cur == null) break;
    			if (cur.val != 0) dp[i] = (dp[i] + dp[j - 1] * cur.val % mod) % mod;
    		}
    	}
    	//System.out.println(Arrays.toString(dp));
    	return (int)dp[n];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
    	w = inp.nextInt(); s = inp.next();
    	n = s.length();

    	root = new Trie();
    	while (w-- > 0) root.insert(inp.next(), inp.nextInt());
        System.out.println(solve());
        //out.close();
    }
}