/*input
1 1
1 1
10 4
2 5
6 9
1 1
10 10
5 1
1 1
0 0
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
        while (true) {
        	int numSoldiers = inp.nextInt(), numReports = inp.nextInt();
        	if (numSoldiers == 0 && numReports == 0) break;
        	int[] leftIdx = new int[numSoldiers], rightIdx = new int[numSoldiers];
        	for (int i = 0; i < numSoldiers; i++) {
        		leftIdx[i] = i - 1;
        		rightIdx[i] = i + 1;
        	}
            StringBuilder ans = new StringBuilder();
        	for (int i = 0; i < numReports; i++) {
        		int l = inp.nextInt(), r = inp.nextInt();
        		l--; r--;
        		if (rightIdx[r] < numSoldiers) leftIdx[rightIdx[r]] = leftIdx[l];
        		if (leftIdx[l] >= 0) rightIdx[leftIdx[l]] = rightIdx[r];
        		String leftAns = leftIdx[l] >= 0 && leftIdx[l] < numSoldiers? Integer.toString(leftIdx[l] + 1) : "*";
        		String rightAns = rightIdx[r] >= 0 && rightIdx[r] < numSoldiers? Integer.toString(rightIdx[r] + 1) : "*" ;
        		//String ans = leftAns + " " + rightAns;
                ans.append(leftAns + " " + rightAns + "\n");
        		//System.out.println(ans);
        		//out.print(leftAns + " " + rightAns + "\n");
        	}
            out.print(ans.toString());
        	out.println("-");
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
    boolean ready() throws IOException {
        return reader.ready();
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

