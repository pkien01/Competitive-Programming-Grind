/*input
8

111
111
111

111
110
000

110
000
000

110
001
101

101
010
101

111
000
111

000
000
000

101
000
101
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
    int[][] f(int[][] grid) {
    	assert grid.length == 3 && grid[0].length == 3;
    	int[][] res = new int[3][3];
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (i > 0) res[i][j] += grid[i - 1][j];
    			if (j > 0) res[i][j] += grid[i][j - 1];
    			if (i < 2) res[i][j] += grid[i + 1][j];
    			if (j < 2) res[i][j] += grid[i][j + 1];
    			res[i][j] &= 1;
    		}
    	}
    	return res;
    }
    void printGrid(int[][] grid) {
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) System.out.print(grid[i][j]);
    		System.out.println();
    	}
    	System.out.println();
    }
    boolean gridEquals(int[][] grid1, int[][] grid2) {
    	if (grid1.length != grid2.length || grid1[0].length != grid2[0].length) return false;
    	for (int i = 0; i < grid1.length; i++) 
    		for (int j = 0; j < grid1[0].length; j++) 
    			if (grid1[i][j] != grid2[i][j]) return false;
    		
    	return true;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt(); inp.nextLine();
        while (inp.hasNextLine()) {
        	int[][] grid = new int[3][3];

        	inp.nextLine();
        	if (!inp.hasNextLine()) break;
        	for (int i = 0; i < 3; i++) {
        		String s = inp.nextLine();
        		for (int j = 0; j < 3; j++) grid[i][j] = s.charAt(j) - '0';
        	}
        	boolean has = false;
        	for (int i = 0; i < 10; i++) {
	        	int[][] fgrid = obj.f(grid);
	        	if (obj.gridEquals(fgrid, grid)) {
	        		System.out.println(i - 1);
	        		has = true;
	        		break;
	        	}
	        	grid = Arrays.copyOf(fgrid, 3);
	        	obj.printGrid(grid);
        	}
        	if (!has) System.out.println("-1");
    	}
    	/*
    	obj.printGrid(grid);
        for (int i = 0; i < 10; i++) {
        	grid = obj.f(grid);
        	obj.printGrid(grid);
        }*/
    }
}