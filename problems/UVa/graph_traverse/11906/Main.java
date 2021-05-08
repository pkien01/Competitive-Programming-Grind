/*input
2
3 3 2 1
0
4 4 1 2
2
3 3
1 1
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
    static int rows, cols, m, n;
    static boolean[][] water, vis, counted;
    static int[] dx, dy;
    
    static boolean validSquare(int x, int y) {
    	return x >= 0 && x < rows && y >= 0 && y < cols && !water[x][y];
    }

    static int[][] reach;
    static void dfs(int cur_x, int cur_y) {
    	vis[cur_x][cur_y] = true;
    	for (int i = 0; i < 8; i++) {
    		int next_x = cur_x + dx[i], next_y = cur_y + dy[i];
    		if (!validSquare(next_x, next_y)) continue;
    		if (!counted[next_x][next_y]) {
    			reach[next_x][next_y]++;
    			counted[next_x][next_y] = true;
    		}
    	}
    	for (int i = 0; i < 8; i++) {
    		int next_x = cur_x + dx[i], next_y = cur_y + dy[i];
    		if (validSquare(next_x, next_y) && counted[next_x][next_y]) counted[next_x][next_y] = false;
    	}
    	for (int i = 0; i < 8; i++) {
    		int next_x = cur_x + dx[i], next_y = cur_y + dy[i];
    		if (validSquare(next_x, next_y) && !vis[next_x][next_y]) dfs(next_x, next_y);
    	}
    }
    public static void main(String[] args) {      
        runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	rows = inp.nextInt(); cols = inp.nextInt(); m = inp.nextInt(); n = inp.nextInt();
        	water = new boolean[rows][cols];
        	int nwaters = inp.nextInt();
        	while (nwaters-- > 0) {
        		int x = inp.nextInt(), y = inp.nextInt();
        		water[x][y] = true;
        	}

        	dx = new int[]{-m, -m, m, m, -n, -n, n, n};
        	dy = new int[]{-n, n, -n, n, -m, m, -m, m};

        	vis = new boolean[rows][cols];
        	reach = new int[rows][cols];
        	counted = new boolean[rows][cols];
        	if (!water[0][0]) dfs(0, 0);

        	int ans_odd = 0, ans_even = 0;
        	for (int i = 0; i < rows; i++) {
        		for (int j = 0; j < cols; j++) {
        			if ((i == 0 && j == 0) || (!water[i][j] && reach[i][j] > 0)) {
        				if (reach[i][j] % 2 == 0) ans_even++;
        				else ans_odd++;
        			}
        		}
        	}

        	//for (int i = 0; i < rows; i++) System.out.println(Arrays.toString(reach[i]));
        	System.out.println("Case " + test + ": " + ans_even + " " + ans_odd);
        }	
        //out.close();
    }
}