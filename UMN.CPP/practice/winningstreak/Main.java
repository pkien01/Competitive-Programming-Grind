/*input
3 0.4
10 0.75
*/
import java.util.*;
import java.io.*;
import java.lang.Math;

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

    static int n;
    static double p;


    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	n = inp.nextInt();
        	p = inp.nextDouble();

			double[][][] dp = new double[n][n + 1][2];
			for (int i = 0; i < n; i++) {
				for (int streak = 0; streak <= n; streak++) {
                    if (streak == 0) {
                        dp[i][streak][1] = 0.0;
                        dp[i][streak][0] = Math.pow(1. - p, i + 1);
                    }
                    if (streak == i + 1) {
                        dp[i][streak][1] = Math.pow(p, i + 1);
                        dp[i][streak][0] = 0.0;
                    }
                    for (int j = 1; i + j < n && j < streak; j++) 
                        dp[i + j][Math.max(streak, j)][1] += dp[i][streak][0] * Math.pow(p, j);
                    for (int j = 1; i + j < n; j++) 
                        dp[i + j][streak][0] += dp[i][streak][1] * Math.pow(1. - p, j);
				    //System.out.println(Arrays.toString(dp[i][streak]));
                    //System.out.println(dp[i][streak][0] + " " + dp[i][streak][1]);
                }
			}
			double ans = 0.;
			for (int streak = 0; streak <= n; streak++) {
				ans += (dp[n - 1][streak][0] + dp[n - 1][streak][1]) * streak;
				System.out.println("streak " +  streak + ": "  + (dp[n - 1][streak][0] + dp[n - 1][streak][1]));
			}
			System.out.println(ans);
			
        }
        
        //out.close();
    }
}