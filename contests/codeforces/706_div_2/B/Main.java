/*input
3
2
0 1
1 0
0 -1
-2 0
4
1 0
3 0
-5 0
6 0
0 3
0 1
0 2
0 4
5
3 0
0 4
0 -3
4 0
2 0
1 0
-3 0
0 -10
0 -2
0 -10
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

    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();

        	ArrayList<Double> a = new ArrayList<>(), b = new ArrayList<>();
        	for (int i = 0; i < 2*n; i++) {
        		int u = inp.nextInt();
        		int v = inp.nextInt();
        		
        		if(v == 0) a.add((double)u * u);
        		else if (u == 0) b.add((double)v * v);

        	}
        	Collections.sort(a); Collections.sort(b);
        	double ans1 = 0.0, ans2 = 0.0, ans3 = 0.0;
        	for (int i = 0; i < n; i++) 
        		ans1 += Math.sqrt((double)a.get(i) + b.get(i));
        	for (int i = 0; i < n; i++)
        		ans2 += Math.sqrt((double)a.get(n - i - 1) + b.get(i));
        	for (int i = 0; i < n; i++)
        		ans3 += Math.sqrt((double)a.get(i)  + b.get(n - i- 1));
        	out.println(Math.min(ans1, Math.min(ans2, ans3)));

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