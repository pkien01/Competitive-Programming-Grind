/*input
6 3 1 10
10 2 1 50
50 5 3 14
50 6 4 1
50 6 3 1
1 1 1 1
0 0 0 0
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
    String solve(int height, int up, int down, int fatigue) {
    	int dayCnt = 0;
    	double curHeight = 0.0, curClimb = up;
    	double climbReduce = up *  (double)fatigue / 100.;
    	//System.out.println(climbReduce);
    	while (true) {
    		dayCnt++;
    		if (curClimb > 0) curHeight += curClimb;
    		//System.out.println("dist climbed: " + curClimb);
    		curClimb -= climbReduce;
    		//System.out.println("morning height: " + curHeight);
    		if (curHeight > height) return "success on day " + dayCnt;

    		curHeight -= (double)down;
    		//System.out.println("night height: " + curHeight);
    		if (curHeight < 0) return "failure on day " + dayCnt;
    	}
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextInt()) {
        	int height = inp.nextInt();
        	if (height == 0) break;
        	int up = inp.nextInt(), down = inp.nextInt(), fatigue = inp.nextInt();
        	System.out.println(obj.solve(height, up, down, fatigue));
        }
    }
}