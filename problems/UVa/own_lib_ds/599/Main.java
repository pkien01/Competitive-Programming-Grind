/*input
2
(A,B)
(B,C)
(B,D)
(D,E)
(E,F)
(B,G)
(G,H)
(G,I)
(J,K)
(K,L)
(K,M)
****
A,B,C,D,E,F,G,H,I,J,K,L,M,N
(A,B)
(A,C)
(C,F)
**
A,B,C,D,F
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
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt(); inp.nextLine();
        while (numTests-- > 0) {
        	boolean[] acorns = new boolean[26];
        	Arrays.fill(acorns, true);
        	int numEdges = 0;
        	while (inp.hasNextLine()) {
        		String line = inp.nextLine();
        		boolean delim = false;
        		for (char c: line.toCharArray()) {
        			if (c >= 'A' && c <= 'Z') acorns[(int)c - 'A'] = false;
        			else if (c == '*') {
        				delim = true;
        				break;
        			}
        		}
        		if (delim) break;
        		numEdges++;
        	}
        	String line = inp.nextLine();
        	int numPoints = 0, numAcorns = 0;
        	for (char c: line.toCharArray()) {
        		if (c >= 'A' && c <= 'Z') {
        			if (!acorns[(int)c - 'A']) numPoints++;
        			else numAcorns++;
        		}
        	}
        	System.out.format("There are %d tree(s) and %d acorn(s).\n", numPoints - numEdges, numAcorns);
        }
    }
}