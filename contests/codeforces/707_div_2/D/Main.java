/*input
1 2 89390548278
1
1 2
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
    static int n, m;
 	static long k;
 	static int[] a, b;

    public static void main(String[] args) {      
        //runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt(); m = inp.nextInt();
        k = inp.nextLong();
        a = new int[n]; b = new int[m];
        for (int i = 0; i < n; i++) a[i] = inp.nextInt();
        for (int i = 0; i < m; i++) b[i] = inp.nextInt();

        ArrayList<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < n || i < m; i++) 
           if (a[i % n] != b[i % m]) diffs.add(i);

        
        long numCycles = k / diffs.size();
    	long leftOver = k % diffs.size();
        System.out.println(numCycles + " " + leftOver);
        if (leftOver == 0) {
            out.println((numCycles - 1L) * Math.max(n, m) + diffs.get(diffs.size() - 1) + 1);
        }
        else {
    	   long rem = 0L;
    	   for (int i = 0; i < n || i < m; i++) {
    		  if (leftOver > 0L) leftOver -= (a[i % n] != b[i % m]? 1L : 0L);
    		  else break;
    		  rem++;
    	   }
            out.println(numCycles * Math.max(n, m) + rem);
        }

        //System.out.println(numCycles + " " + rem);
        //out.println(numCycles * Math.max(n, m) + rem);
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