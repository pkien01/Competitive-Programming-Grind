/*input
2
4 6
1 1
2 1 2
3 1 2 3
4 1 2 3 4
2 2 3
1 3
2 2
1 1
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

    public static void main(String[] args) {      
        //runFromFile();
        FastReader inp = new FastReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	int halfm = (int)Math.ceil((double)m / 2);
        	//out.println(halfm);
        	int[] cnt = new int[n];
        	ArrayList<Integer>[] friends = new ArrayList[m];
        	for (int i = 0; i < m; i++) {
        		int k = inp.nextInt();
        		friends[i] = new ArrayList<>();
        		for (int j = 0; j < k; j++) {
        			int friend = inp.nextInt() - 1;
        			cnt[friend]++;
        			if (cnt[friend] > halfm) cnt[friend] = halfm;
        			friends[i].add(friend);
        		}
        		//Collections.sort(friends[i]);
        	}
        	//int[] curCnt = cnt.clone();
        	ArrayList<Integer> ans = new ArrayList<>();
        	for (int i = 0; i < m; i++) {
        		int choose = -1;
        		for (int friend: friends[i]) {
        			if (choose == -1 || cnt[friend] < cnt[choose]) {
        				choose = friend;
        			}
        		}
        		if (choose != -1 && cnt[choose] > 0) {
        			ans.add(choose + 1);
        			cnt[choose]--;
        		}
        		else break;
        	}
        	if (ans.size() == m) {
        		out.println("YES");
        		for (int i = 0; i < ans.size(); i++) out.print(ans.get(i) + (i == ans.size() - 1? "\n": " "));
        	}
        	else out.println("NO");
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