/*input
3 3 2
***
*N*
***
DE
4 4 5
...#
*#O.
*.*.
*.#.
FFEFF
10 10 20
....*.....
.......*..
.....*....
..*.#.....
...#N.*..*
...*......
..........
..........
..........
..........
FDFFFFFFEEFFFFFFEFDF
0 0 0
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

    static int n, m, len;
    static char[][] arr;
    static String str;

    final static int dx[] = new int[]{-1, 0, 1, 0}, dy[] = new int[]{0, 1, 0, -1};
    final static String orients_str = "NLSO";

    static int solve() {
    	int cur_x = -1, cur_y = -1, orient = -1;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			orient = orients_str.indexOf(arr[i][j]);
    			//System.out.println(orient);
    			if (orient != -1) {
    				cur_x = i; cur_y = j; break;
    			} 
    		}
    		if (orient != -1) break;
    	}
    	//System.out.println(orient);
    	int res = 0;
    	for (int i = 0; i < len; i++) {
    		switch (str.charAt(i)) {
    			case 'D': orient = (orient + 1) % 4; break;
    			case 'E': orient = (orient - 1 + 4) % 4; break;
    			case 'F': 
    				int next_x = cur_x + dx[orient], next_y = cur_y + dy[orient];
    				if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < m && arr[next_x][next_y] != '#') {
    					cur_x = next_x; cur_y = next_y;
    					if (arr[cur_x][cur_y] == '*') {
    						res++; 
    						arr[cur_x][cur_y] = '.';
    					}
    				}
    				break;
    		}
    	}
    	return res;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	n = inp.nextInt(); m = inp.nextInt(); len = inp.nextInt();
        	if (n == 0 && m == 0 && len == 0) break;
        	arr = new char[n][m];
        	for (int i = 0; i < n; i++) arr[i] = inp.next().toCharArray();
        	str = inp.next();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}