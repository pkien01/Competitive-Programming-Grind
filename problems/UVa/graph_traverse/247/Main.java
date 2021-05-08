/*input
15 13
drx fmpqczsus
fmpqczsus ismtdgbs
znwx liq
drx copxbrhmrlka
k glvapurny
znwx liq
umjjuf fufarfxgygbjaty
cbeajzhnz fmpqczsus
uwwteubi umjjuf
umjjuf vna
fmpqczsus k
wjudt xqy
ismtdgbs k
0 0
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
    static int n, m;
    static ArrayList<Integer>[] adj;
    static TreeMap<String, Integer> nameId;
    static String[] names;

    static int cntNum;
    static int[] dfs_num, dfs_low;
    static Stack<Integer> stack;
    static boolean[] onStack;
    static void dfs(int u) {
    	dfs_num[u] = ++cntNum;
    	dfs_low[u] = dfs_num[u];
    	stack.push(u);
    	onStack[u] = true;
    	for (Integer v: adj[u]) {
    		if (dfs_num[v] == 0) {
    			dfs(v);
    			dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
    		}
    		else if (onStack[v]) dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
    	}

    	if (dfs_low[u] == dfs_num[u]) {
    		while (!stack.empty()) {
    			int top = stack.pop();
    			onStack[top] = false;
    			System.out.print(names[top]);
    			if (top != u) System.out.print(", ");
    			else {
    				System.out.println();
    				break;
    			}
    		}
    	}
    }

    static void solve() {
    	cntNum = 0;
    	dfs_num = new int[n]; dfs_low = new int[n];
    	stack = new Stack<>();
    	onStack = new boolean[n];
    	for (int i = 0; i < n; i++)
    		if (dfs_num[i] == 0) dfs(i);
    }

    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	adj = new ArrayList[n];
        	for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        	
        	nameId = new TreeMap<>();
        	names = new String[n];
        	int cntId= 0;
        	while (m-- > 0) {
        		String str_u = inp.next(), str_v = inp.next();
        		if (!nameId.containsKey(str_u)) {
        			names[cntId] = str_u;
        			nameId.put(str_u, cntId++);
        		}
        		if (!nameId.containsKey(str_v)) {
        			names[cntId] = str_v;
        			nameId.put(str_v, cntId++);
        		}
        		adj[nameId.get(str_u)].add(nameId.get(str_v));
        	}

        	if (test != 1) System.out.println();
        	System.out.format("Calling circles for data set %d:\n", test);
        	solve();
        }
        
        //out.close();
    }
}