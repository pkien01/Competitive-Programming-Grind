/*input
0 0 0 0 -2 1
1 0 0 0 -1 2
1 -1 1 -1 -1 1
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

    static final double eps = 1e-9;
   
   	static double p, q, r, s, t, u;
    static double func(double x) {
    	return p*Math.exp(-x) + q*Math.sin(x) + r*Math.cos(x) + s*Math.tan(x) + t*x*x + u;
    }
    static double solve() {
    	double l = 0.0, r = 1.0;
    	while (Math.abs(r - l) > eps) {
    		double mid = (l + r) / 2.0;
    		double fmid = func(mid);

    		//System.out.println(mid + " " + fmid);
    		if (Math.abs(fmid) < 1e-6) return mid; 
    		if (fmid < 0) r = mid;
    		else l = mid;
    	}
    	return -1;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	String[] line_split = inp.nextLine().split(" ");
        	if (line_split.length != 6) break;
        	p = (double)Integer.parseInt(line_split[0]);
        	q = (double)Integer.parseInt(line_split[1]);
        	r = (double)Integer.parseInt(line_split[2]);
        	s = (double)Integer.parseInt(line_split[3]);
        	t = (double)Integer.parseInt(line_split[4]);
        	u = (double)Integer.parseInt(line_split[5]);
        	//System.out.println(p + " " + q + " " + r + " " + s + " " + t + " " + u);
        	double ans = solve();
        	//System.out.println(ans);
        	if (ans < -eps || ans > 1 + eps) System.out.println("No solution");
        	else System.out.format("%.4f\n", ans);  
        }
        
        //out.close();
    }
}