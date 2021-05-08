/*input
1
7
a 3
W 10
A 100
, 10
k 7
. 3
I 13
7
ACM International Collegiate Programming Contest (abbreviated
as ACM-ICPC or just ICPC) is an annual multi-tiered competition
among the universities of the world. The ICPC challenges students
to set ever higher standards of excellence for themselves
through competition that rewards team work, problem analysis,
and rapid software development.
From Wikipedia.
*/
import java.util.*;
import java.io.*;


class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
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
    	int[] mp = new int[66000];
        while (numTests-- > 0) {
        	int numChars = inp.nextInt();
            List<Character> keysArr = new ArrayList<>();
        	while (numChars-- > 0) {
        		char key = inp.next().charAt(0);
        		int val = inp.nextInt();
        		mp[(int)key] = val;
                keysArr.add(key);
        	}
        	int numLines = inp.nextInt();
        	int ans = 0;
        	while (numLines-- > 0) {
        		String line = inp.nextLine();
        		for (char c: line.toCharArray()) ans += mp[(int)c];
        	}
            for (Character key: keysArr) mp[(int)key] = 0; 
        	out.println(String.format("%.2f$", (double)ans / 100.));
            
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

