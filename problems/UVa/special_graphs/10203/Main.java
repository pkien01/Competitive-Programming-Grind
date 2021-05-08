/*input
1
0 0
0 0 10000 10000
5000 -10000 5000 10000
5000 10000 10000 10000
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

    static class Point implements Comparable<Point> {
    	int x, y;
    	Point(int x, int y) {
    		this.x = x; this.y = y;
    	}
    	double distance(Point other) {
    		int diff_x = x - other.x, diff_y = y - other.y;
    		return Math.sqrt((double)diff_x * diff_x + (double)diff_y * diff_y);
    	}
    	@Override
    	public String toString() {
    		return String.format("(%d, %d)", x, y);
    	}
    	@Override
    	public int compareTo(Point other) {
    		return x != other.x? x - other.x : y - other.y;
    	}
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt(); inp.nextLine(); inp.nextLine();
        while (numTests-- > 0) {
        	String line = inp.nextLine();
        	String[] line_split = line.split(" ");
        	Point src = new Point(Integer.parseInt(line_split[0]), Integer.parseInt(line_split[1]));

        	double totalTime = 0.0;
        	while (inp.hasNextLine()) {
        		line = inp.nextLine();
        		if (line.isEmpty()) break;
        		line_split = line.split(" ");
        		//System.out.println(Arrays.toString(line_split));
        		Point u = new Point(Integer.parseInt(line_split[0]), Integer.parseInt(line_split[1]));
        		Point v = new Point(Integer.parseInt(line_split[2]), Integer.parseInt(line_split[3]));
        		double uv = u.distance(v);

        		totalTime += uv * 2 * 60 / (20.0 * 1000);
        	}
        	
        	int hh = (int)Math.floor(totalTime / 60), mm = (int)Math.round(totalTime - 60.0 * hh);
            if (mm >= 60) {
                hh++; mm -= 60;
            }
        	System.out.format("%d:%02d\n", hh, mm);
            if (numTests != 0) System.out.println();
        }

        
        //out.close();
    }
}