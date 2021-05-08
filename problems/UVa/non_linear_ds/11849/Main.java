/*input
3 3
1
2
3
1
2
4
0 0
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
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
    int solve(int n, int m, int[] jack, int[] jill) {
    	HashSet<Integer> hset = new HashSet<>();
    	for (int i = 0; i < n; i++) hset.add(jack[i]);
    	int ans = 0;
    	for (int i = 0; i < m; i++) ans += hset.contains(jill[i])? 1 : 0;
    	return ans;
    }
    int solve2(int n, int m, int[] jack, int[] jill) {
    	int ans = 0;
    	for (int i = 0, j = 0; i < n && j < m; i++) {
    		while (jill[j] < jack[i]) j++;
    		ans += jack[i] == jill[j]? 1 : 0; 
    	}
    	return ans;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        //FastReader inp = new FastReader(System.in);
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (true) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	int[] jack = new int[n], jill = new int[m];
        	for (int i = 0; i < n; i++) jack[i] = inp.nextInt();
        	for (int i = 0; i < m; i++) jill[i] = inp.nextInt();
        	System.out.println(obj.solve2(n, m, jack, jill));
        }
        //out.close();
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