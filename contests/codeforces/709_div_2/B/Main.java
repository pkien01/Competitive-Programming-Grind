/*input
6
6
1 9 17 6 14 3
3
4 2 2
3
7 3 4
3
2 2 4
5
0 1000000000 0 1000000000 0
2
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
    static int n;
    static int[] arr;
	static PrintWriter out = new PrintWriter(System.out);
    static void solve() {
    	Integer c_neg = null, c_pos = null;
    	for (int i = 1; i < n; i++) {
    		if (arr[i] >= arr[i - 1]) {
    			if (c_pos == null) c_pos = arr[i] - arr[i - 1];
    			else if (arr[i] - arr[i - 1] != c_pos) {
    				out.println("-1");
    				return;
    			} 
    		}
    		else {
    			if (c_neg == null) c_neg = arr[i] - arr[i - 1];
    			else if (arr[i] - arr[i - 1] != c_neg) {
    				out.println("-1");
    				return;
    			}
    		}
    	}
    	if (c_pos == null || c_neg == null) {
    		out.println("0");
    		return;
    	}
    	int m = c_pos - c_neg;
    	for (int i = 0; i < n; i++) {
    		if (arr[i] >= m) {
    			out.println("-1");
    			return;
    		}
    	}
    	out.println(m + " " + c_pos);
    }
    public static void main(String[] args) {      
        //runFromFile();
        FastReader inp = new FastReader(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	solve();
        }
        
        out.close();
    }
}

class FastReader {
    BufferedReader reader;
    StringTokenizer tokenizer;
    FastReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }
    FastReader() {
        this(System.in);
    }
    String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }        
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String line = new String();
        try {
            line = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}