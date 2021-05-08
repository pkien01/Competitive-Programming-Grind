/*input
2
3 3 1
RRR
RSR
RRR
3 4 2
RSPR
SPRS
PRSP
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    boolean defeat(char loc1, char loc2) {
    	if (loc1 == 'R') return loc2 == 'S';
    	if (loc1 == 'S') return loc2 == 'P';
    	if (loc1 == 'P') return loc2 == 'R';
    	return false;
    }
   	final int[] di = new int[]{-1, 0, 1, 0}, dj = new int[]{0, 1, 0, -1};
    void solve(char[][] grid, int n, int m, int numDays) {
    	while (numDays-- > 0) {
    		char[][] newGrid = new char[n][m];
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				if (newGrid[i][j] == '\u0000') newGrid[i][j] = grid[i][j];
    				int winPos = -1, losePos = -1;
    				for (int k = 0; k < 4; k++) {
    					if (i + di[k] < 0 || i + di[k] >= n || j + dj[k] < 0 || j + dj[k] >= m) continue;
    					if (grid[i][j] == grid[i + di[k]][j + dj[k]]) continue;
    					if (defeat(grid[i + di[k]][j + dj[k]], grid[i][j])) winPos = k;
    					else losePos = k;
    				}
    				if (winPos != -1) newGrid[i][j] = grid[i + di[winPos]][j + dj[winPos]];
    				if (losePos != -1) newGrid[i + di[losePos]][j + dj[losePos]] = grid[i][j];
    				if (winPos == -1 && losePos == -1) newGrid[i][j] = grid[i][j];
    			}
    		}
    		for (int i = 0; i < n; i++)
    			for (int j = 0; j < m; j++) grid[i][j] = newGrid[i][j];
    	}
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int numTests = inp.nextInt();
        boolean firstLoop = true;
        while (numTests-- > 0) {
        	int n = inp.nextInt(), m = inp.nextInt(), numDays = inp.nextInt();
        	inp.nextLine();
        	char[][] grid = new char[n][m];
        	for (int i = 0; i < n && inp.hasNextLine(); i++) 
        		grid[i] = inp.nextLine().toCharArray();
        	obj.solve(grid, n, m, numDays);
        	if (firstLoop) firstLoop = false;
        	else System.out.println();
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < m; j++) System.out.print(grid[i][j]);
        		System.out.println();
        	}
        }
    }
}