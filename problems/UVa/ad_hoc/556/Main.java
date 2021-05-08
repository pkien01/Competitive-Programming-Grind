/*input
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

    final int[] dx = new int[]{0, 1, 0, -1};
    final int[] dy = new int[]{1, 0, -1, 0};
    int n, m;
    char[][] maze;
    boolean validMove(int x, int y, int dir) {
    	return x + dx[dir] >= 0 && x + dx[dir] < n && y + dy[dir] >= 0 && y + dy[dir] < m && maze[x + dx[dir]][y+ dy[dir]] == '0';
    }

    int[] solve(int n, int m, char[][] maze) {
    	int[][] cnt = new int[n][m];
    	int startX = n - 1, startY = 0;
    	int curX = startX, curY = startY;
    	int dir = 0; // 0 = right, 1 = up, 2 = left, 3 = down
    	do {
    		if (validMove(curX, curY, (dir + 1) % 4)) dir = (dir + 1) % 4;
     		while (!validMove(curX, curY, dir)) dir = (dir + 3) % 4;
     		curX += dx[dir]; curY += dy[dir];
    		cnt[curX][curY]++; 
    	} while (curX != startX || curY != startY);

    	//for (int i = 0; i < n; i++) System.out.println(Arrays.toString(cnt[i]));

    	int[] res = new int[5];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < m; j++) 
    			if (maze[i][j] == '0' && cnt[i][j] <= 4) res[cnt[i][j]]++;
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextInt()) {
        	obj.n = inp.nextInt();
        	obj.m = inp.nextInt();
        	if (inp.hasNextLine()) inp.nextLine();
        	if (obj.n == 0 && obj.m == 0) break;
        	obj.maze = new char[obj.n][obj.m];
        	for (int i = 0; i < obj.n; i++) obj.maze[i] = inp.nextLine().toCharArray();
        	int[] ans = obj.solve(obj.n, obj.m, obj.maze);
        	for (int i = 0; i < 5; i++) System.out.format("%3d", ans[i]);
        	System.out.println();
        }
    }
}