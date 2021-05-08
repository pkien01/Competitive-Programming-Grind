/*input
1
A B F G
A<B B<F
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

	static int n;
	static Map<Character, Integer> varId;
    static String[] vars;
    static ArrayList<Integer>[] par;

    static boolean[] vis;
    static int[] curAns;
    static boolean hasAns;

    static boolean validNode(int u) {
        if (vis[u]) return false;
        for (Integer v: par[u]) 
            if (!vis[v]) return false;
        return true;
    }
    static void backtrack(int idx) {
        if (idx == n) {
            hasAns = true;
            for (int i = 0; i < n; i++) 
                System.out.print(vars[curAns[i]] + (i == n - 1? "\n" : " "));
            return;
        }
        for (int v = 0; v < n; v++) {
            if (validNode(v)) {
                vis[v] = true;
                curAns[idx] = v;
                backtrack(idx + 1);
                vis[v] = false;
            }
        }
    } 
    
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        int numTests = inp.nextInt(); inp.nextLine(); 

        while (numTests-- > 0) {
        	inp.nextLine();
        	vars = inp.nextLine().split(" ");
        	n = vars.length;
        	varId = new TreeMap<Character, Integer>();
        	for (int i = 0; i < n; i++) {
        		char curVar = vars[i].charAt(0);
        		if (varId.get(curVar) == null) varId.put(curVar, i);
        	}

        	String[] constraints = inp.nextLine().split(" ");
            par = new ArrayList[n];
            for (int i = 0; i < n; i++) par[i] = new ArrayList<>();
        	for (String s: constraints) {
        		int leftId = varId.get(s.charAt(0)), rightId = varId.get(s.charAt(2));
        		par[rightId].add(leftId);
        	}
        	
            vis = new boolean[n];
            curAns = new int[n];
            hasAns = false;
            backtrack(0);
            if (!hasAns) System.out.println("NO");
            if (numTests != 0) System.out.println(); 
        }
        
        //out.close();
    }
}