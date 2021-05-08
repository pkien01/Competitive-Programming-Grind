/*input
3
6
0 3 0 0 1 3
10
0 0 0 1 0 5 0 0 0 2
3
0 0 0
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
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
       		int[] arr = new int[n + 1];
        	for (int i = 0; i < n; i++) {
        		int l = Math.max(0, i - inp.nextInt() + 1), r = i;
        		if (l > r) continue;
        		arr[l]++;
        		arr[r + 1]--;
        	}
        	for (int i = 1; i < n; i++) arr[i] += arr[i - 1]; 
    		for (int i = 0; i < n; i++) {
    			out.print(arr[i] > 0? "1" : "0");
    			out.print(i == n - 1? "\n": " ");
    		}
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