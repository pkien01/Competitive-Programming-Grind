/*input
5
1 2 3 4 5
5 4 1 2 3
0
6
6 5 4 3 2 1
0
0
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
    boolean solve(int n, int[] perm) {
    	int idxA = 0, idxB = 0;
    	Stack<Integer> st = new Stack<>();
    	while (idxB < n) {
    		if (idxA < n && perm[idxB] == idxA) {
    			idxA++; idxB++;
    		}
    		else if (!st.empty() && st.peek() == perm[idxB]) {
    			st.pop(); idxB++; 
    		} 
    		else if (idxA < n) {
    			st.push(idxA);
    			idxA++;
    		}
    		else return false;
    	}
    	return idxB == n; 
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	if (n == 0) break;
        	int[] perm = new int[n];
        	while (inp.hasNextInt()) {
        		perm[0] = inp.nextInt();
        		if (perm[0] == 0) break;
        		perm[0]--;
        		for (int i = 1; i < n && inp.hasNextInt(); i++) perm[i] = inp.nextInt() - 1;
        		System.out.println(obj.solve(n, perm)? "Yes" : "No");
        	}
        	System.out.println();
        }
    }
}