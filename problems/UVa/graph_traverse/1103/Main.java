/*input
4 4
0f0f
ff00
0000
f00f
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
    static final char[] symbols = new char[]{'W', 'A', 'K', 'J', 'S', 'D'};
    static int n, m;
    static char[][] arr;

    static String binaryString(char c) {
    	int num_c;
    	if (c >= '0' && c <= '9') num_c = (int)c - '0';
    	else {
    		assert c >= 'a' && c <= 'f';
    		num_c = 10 + (int)c - 'a';
    	}
    	char[] res = new char[4];
    	for (int i = 4 - 1; i >= 0; i--) 
    		res[4 - i - 1] = (num_c >> i & 1) == 1? '1' : '0';
    	return new String(res);
    }

    static boolean[][] vis;
    static boolean checkPos(int x, int y) {
    	return x >= 0 && x < n && y >= 0 && y < m && !vis[x][y];
    }
    static void dfs_white(int x, int y) {
    	if (x < 0 || x >= n || y < 0 || y >= m || vis[x][y] || arr[x][y] != '0') return;
    	vis[x][y] = true;
    	dfs_white(x - 1, y);
    	dfs_white(x, y + 1);
    	dfs_white(x + 1, y);
    	dfs_white(x, y - 1);
    	//System.out.println(x + " " + y);
    }
    static int dfs_black(int x, int y) {
    	if (x < 0 || x >= n || y < 0 || y >= m || vis[x][y]) return 0;
    	if (arr[x][y] == '0') {
    		if (vis[x][y]) return 0;
    		dfs_white(x, y);
    		return 1;
    	}
    	//System.out.println(x + " " + y);
    	vis[x][y] = true;
    	return dfs_black(x - 1, y) + dfs_black(x, y + 1) + dfs_black(x + 1, y) + dfs_black(x, y - 1);
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        for (int test = 1;; test++) {
        	n = inp.nextInt(); m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	n += 2; m = m*4 + 2;
        	arr = new char[n][m];
        	for (int i = 0; i < n; i++) {
        		arr[i][0] = '0';
        		arr[i][m - 1] = '0';
        	}
        	for (int j = 0; j < m; j++) {
        		arr[0][j] = '0';
        		arr[n - 1][j] = '0';
        	}
        	for (int i = 1; i < n - 1; i++) {
        		char[] s = inp.next().toCharArray();
        		StringBuilder curRow = new StringBuilder();
        		for (char c: s) curRow.append(binaryString(c));
        		for (int j = 0; j < curRow.length(); j++) arr[i][j + 1] = curRow.charAt(j);
        	}
        	//for (int i = 0; i < n; i++) System.out.println(String.valueOf(arr[i]));
        	vis = new boolean[n][m];
        	
        	ArrayList<Character> ans = new ArrayList<>();
        	boolean firstSym = true;
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < m; j++) {
        			if (arr[i][j] == '1' && !vis[i][j]) {
        				int ncomps = dfs_black(i, j) - (firstSym? 1: 0);
        				ans.add(symbols[ncomps]);
        				//System.out.println(ncomps);
        				if (firstSym) firstSym = false;
        			}
        		}
        	}
        	Collections.sort(ans);
        	System.out.print("Case " + test + ": ");
        	for (Character c: ans) System.out.print(c);
        	System.out.println();
        }
        
        //out.close();
    }
}