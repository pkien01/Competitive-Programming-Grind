/*input
2
5 16
1 2 8 4 8
6 10
2 8 8 2 2 8
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

    
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	int W = inp.nextInt();
        	int[] cnt = new int[31];
        	int sum = 0;
        	for (int i = 0; i < n; i++) {
        		int pw2 = inp.nextInt();
        		sum += pw2;
        		int x = 0;
        		while (pw2 != 0) {
        			x++;
        			pw2 >>=1;
        		}
        		cnt[x - 1]++;
        	}
        	//System.out.println(Arrays.toString(cnt));
        	int ans = 0;
        	while (true) {
        		ans++;
        		int curW = W;
        		//boolean has = true;
        		for (int i = 20; i >= 0; i--) {
        			if (cnt[i] > 0 && (1 << i) <= curW) {
        				while (curW >= (1 << i)) {
        					curW -= (1 << i);
        					sum -= (1 << i);
        					if (sum == 0) break;
        					cnt[i]--;
        				}
        			}
        			if (sum == 0) break;
        		}
        		if (sum == 0) break;
        	}
        	System.out.println(ans);
        }
        
        //out.close();
    }
}