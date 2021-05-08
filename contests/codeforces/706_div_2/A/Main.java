/*input
5
4 1
0 1 3 4
3 1
0 1 4
3 0
0 1 4
3 2
0 1 2
3 2
1 2 3
*/
import java.util.*;
import java.io.*;

public class Main {
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
    
    int solve(int n, int k, TreeSet<Integer> set) {
    	if (k == 0) return n;
    	int maxSet = set.last();
    	int mexSet = n + 1;
    	for (int i = 0; i <= n; i++) {
    		if (!set.contains(i)) {
    			mexSet = i; break;
    		}
    	}
    	int addNum = (int)Math.ceil((double)(maxSet + mexSet) / 2);
    	if (mexSet < maxSet) {
    		if (!set.contains(addNum)) return n + 1;
    		return n;
    	}
    	if (mexSet > maxSet) return n + k;
    	return n;
    }
    void simulate(int n, int k, TreeSet<Integer> set) {
    	System.out.println(set.toString());
    	while (k-- > 0) {
    		int maxSet = set.last(), mexSet = n;
    		for (int i = 0; i <= set.size(); i++) {
    			if (!set.contains(i)) {
    				mexSet = i; break;
    			}
    		}
    		int addNum = (int)Math.ceil((double)(maxSet + mexSet) / 2);
    		set.add(addNum);
    		System.out.println(set.toString());
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        FastReader inp = new FastReader(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
       		int n = inp.nextInt(), k = inp.nextInt();
        	TreeSet<Integer> set = new TreeSet<>();
        	for (int i = 0; i < n; i++) set.add(inp.nextInt());
        	//obj.simulate(n, k, set);
        	System.out.println(obj.solve(n, k, set));
        }
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