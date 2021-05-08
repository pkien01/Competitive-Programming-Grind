/*input

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

    
    
   	static final int inf = (int)1e9;
   	static final int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};

    static int n, m, unoccupied;
   	static int[][] arr;

   	
   	static class Cell {
    	int x, y, val;
    	Cell(int x, int y, int val) {
    		this.x = x; this.y = y; this.val = val;
    	}
    	Cell(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	boolean valid() {
    		return x >= 0 && x < n && y >= 0 && y < n && arr[i][j] >= 0;
    	}
    }


   	static void solve() {
   		int[][] dist = new int[n][m];
   		Arrays.fill(dist, inf);
   		Queue<Cell> queue; queue.add(new Cell(0, 0));
   		while (!queue.isEmpty()) {
   			Cell u = queue.poll();
   			if (u.x == n && u.y == m) break;
   			for (int i = 0; i < 4; i++) {
   				Cell v = new Cell(u.x + dx[i], u.y + dy[i]);
   				if (!v.valid()) continue;
   				if (dist[v.x][v.y] == inf) {
   					dist[v.x][v.y] = dist[u.x][u.y] + unoccupied;
   					queue.add(v);
   				}
   			}
   		}

   		long ans = 0;
   		for (int i = 0; i < n; i++) 
   			for (int j = 0; j < m; j++) 
   				if 
   		

   	}
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt(); m = inp.nextInt(); unoccupied = inp.nextInt();
        arr = new int[n][m];
        for (int i = 0; i < n; i++)
        	for (int j = 0; j < m; j++) arr[i][j] = inp.nextInt();
        
        //out.close();
    }
}