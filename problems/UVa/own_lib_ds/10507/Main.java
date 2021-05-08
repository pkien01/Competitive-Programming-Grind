/*input
6
11
HAB
AB
AC
AH
BD
BC
BF
CD
CF
CH
DF
FH
*/
import java.util.*;
import java.io.*;

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
    int solve(int n, int m, String stim, String[] edges) {
    	ArrayList<Integer>[] adj = new ArrayList[26];
    	for (int i = 0; i < 26; i++) adj[i] = new ArrayList<>();
    	for (String edge: edges) {
        	int u = (int)edge.charAt(0) - 'A', v = (int)edge.charAt(1) - 'A';
        	adj[u].add(v); adj[v].add(u);
        }
        boolean[] wake = new boolean[26];
        for (int i = 0; i < stim.length(); i++) wake[(int)stim.charAt(i) - 'A'] = true;
        int res = 0;
        while (true) {
        	ArrayList<Integer> change = new ArrayList<>();
        	for (int u = 0; u < 26; u++) {
        		if (!wake[u]) {
        			int cnt = 0;
        			for (int v: adj[u]) if (v != u) cnt += wake[v]? 1 : 0;
        			if (cnt >= 3) change.add(u);
        		}
        	}
        	if (change.size() == 0) break;
        	for (int u: change) wake[u] = true;
        	res++;
        }
        int cntWake = 0;
        for (int i = 0; i < 26; i++) cntWake += wake[i]? 1 : 0; 
       	if (cntWake < n) res = -1;
       	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	String stim = inp.next();
        	String[] edges = new String[m];
        	for (int i = 0; i < m; i++) edges[i] = inp.next();
        	int ans = obj.solve(n, m, stim, edges);
        	if (ans != -1) System.out.format("WAKE UP IN, %d, YEARS\n", ans);
        	else System.out.println("THIS BRAIN NEVER WAKES UP");
        }
    }
}