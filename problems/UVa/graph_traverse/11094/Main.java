/*input
5 5
wwwww
wwllw
wwwww
wllww
wwwww
1 3
6 6
aaaaaa
bbaaab
aaaabb
abaaba
abbaaa
abbabb
5 5
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
    static final int inf = (int)1e6;
    static int m, n, x_mijid, y_mijid;
    static char[][] arr;
    static boolean[][] vis;
    static char land;
    static int dfs(int x, int y) {
    	if (x < 0 || x >= m || vis[x][y] || arr[x][y] != land) return 0;
    	//System.out.println(x + " " + y);
    	if (x == x_mijid && y == y_mijid) return -inf;
    	vis[x][y] = true;
    	return 1 + dfs(x - 1 , y) + dfs(x, (y + 1) % n) + dfs(x + 1, y) + dfs(x, (y - 1 + n) % n);
    }

    static int solve() {
		vis = new boolean[m][n];
        int ans = 0;
        land = arr[x_mijid][y_mijid];
       	//System.out.println(dfs(1, 0));
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (!vis[i][j] && arr[i][j] == land) {
        			int regions = dfs(i, j);
        			ans = Math.max(ans, regions);
        		}
        	}
        }
        return ans;
        
    }
    public static void main(String[] args) {      
       // runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	m = inp.nextInt(); n = inp.nextInt();
        	arr = new char[m][n];
        	for (int i = 0; i < m; i++) arr[i] = inp.next().toCharArray();
        	x_mijid = inp.nextInt(); y_mijid = inp.nextInt();
        
        	System.out.println(solve());
        }
        
        //out.close();
    }
}