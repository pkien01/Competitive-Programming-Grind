/*input
2
A(B(C,D),E)
E(A,B(C,D))
A(B(C,D),E)
E(A(B(C,D)))
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
    static String a, b;
    static ArrayList<String> subTrees(String s) {
    	int brackets = 0, prev = -1;
    	ArrayList<String> res = new ArrayList<>();
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == '(') brackets++;
    		else if (s.charAt(i) == ')') brackets--;
    		if (brackets == 1) {
    			if (prev == -1) {
    				if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
    					if (i + 1 < s.length() && s.charAt(i + 1) == '(') prev = i;
    					else res.add(s.substring(i, i + 1));
    				}
    			}
    			else {
    				res.add(s.substring(prev, i + 1));
    				prev = -1;
    			}
    		}
    	}
    	return res;
    }


    static void toAdjList(String s, ArrayList<Integer>[] adj) {
    	//if (s.length() <= 3) return;
    	ArrayList<String> list = subTrees(s);
        //System.out.println(list);
    	int u = s.charAt(0) - 'A';
    	for (String nxt: list) {
    		int v = nxt.charAt(0) - 'A';
    		adj[u].add(v); adj[v].add(u);
    		toAdjList(nxt,adj);
    	}
    }


    static ArrayList<Integer>[] adj_a, adj_b;
    static boolean[] vis; 

    static boolean isRotation(int u) {
        if (adj_a[u].size() != adj_b[u].size()) return false;
        int sz = adj_a[u].size();
        if (sz == 0) return true;
        int pos = adj_b[u].indexOf(adj_a[u].get(0));
        if (pos == -1) return false;
        for (int i = 0; i < adj_a[u].size(); i++) {
            if (adj_a[u].get(i) != adj_b[u].get((i + pos) % sz)) return false;
        }
        return true;
    }
    static boolean dfs(int u) {
        vis[u] = true;
        if (!isRotation(u)) return false;
        for (int i = 0; i < adj_a[u].size(); i++) {
            int v = adj_a[u].get(i);
            if (vis[v]) continue;
            if (!dfs(v)) return false;
        }
        return true;
    }
    static void showAdjList(ArrayList<Integer>[] adj) {
        for (int u = 0; u < 26; u++) {
            System.out.print(u + ": ");
            for (int v: adj[u]) System.out.print(v + ", ");
            System.out.println();
        }
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	String a = inp.next(), b = inp.next();

        	adj_a = new ArrayList[26];
            for (int i = 0; i < 26; i++) adj_a[i] = new ArrayList<>();
            toAdjList(a, adj_a);

            adj_b = new ArrayList[26];
            for (int i = 0; i < 26; i++) adj_b[i] = new ArrayList<>();
            toAdjList(b, adj_b);

            vis = new boolean[26];

        	System.out.println(dfs(0)? "same" : "different");

        }
        
        //out.close();
    }
}