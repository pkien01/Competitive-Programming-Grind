/*input
8 4
1 3 2 2 4 3 2 1
1 3
2 4
3 2
4 2
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
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        ArrayList<Integer>[] inds = new ArrayList[1000002];
        for (int i = 0; i <= 1000000; i++) inds[i] = new ArrayList<>(); 
        while (true) {
        	String s = inp.nextLine();
        	if (s == null) break;
        	String[] s_split = s.split(" ");
        	int n = Integer.parseInt(s_split[0]), m = Integer.parseInt(s_split[1]);
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) {
        		arr[i] = inp.nextInt();
        		inds[arr[i]].add(i + 1);
        	}
        	while (m-- > 0) {
        		int k = inp.nextInt() - 1, val = inp.nextInt();
        		if (inds[val].isEmpty() || inds[val].size() <= k) out.println("0");
        		else out.println(inds[val].get(k));
        	}
        	for (int i = 0; i < n; i++) inds[arr[i]].clear();
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