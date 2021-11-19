/*input
4
30
199
1000
1520
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
    static ArrayList<Integer>[] list;

    static int sumOfDigits(int x) {
    	int res = 0;
    	for (int i = x; i > 0; i /= 10) res += i % 10;
    	return res;
    }
    static void precompute() {
    	list = new ArrayList[9 * 5 + 1];
    	for (int i = 0; i < list.length; i++) list[i] = new ArrayList<>();
    	for (int i = 0; i < 100000; i++) list[sumOfDigits(i)].add(i);
    }
    public static void main(String[] args) {      
        //runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        precompute();
        int numTests = inp.nextInt(); 
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	int sum = sumOfDigits(n) - 1;
        	int pos = Collections.binarySearch(list[sum], n);
        	if (pos < 0) pos = -pos - 1;
        	pos--;
        	out.println(list[sum].get(pos));
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