/*input
2 3
elsie mildred dean
elsie mildred dean
elsie dean mildred
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

    static int k, n;
    static String[] names;

 	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        k = inp.nextInt(); n = inp.nextInt(); 
        Map<String, Integer> nameId = new TreeMap<String, Integer>();
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
        	names[i] = inp.next();
        	nameId.put(names[i], i);
        }
        

        char[][] ans = new char[n][n];
   
    	for (int i = 0; i < n; i++) {
    		Arrays.fill(ans[i], '?');
    		ans[i][i] = 'B';
    	}
    	
        int[][] order = new int[k][n], pubId = new int[k][n];
        for (int i = 0; i < k; i++) {
        	for (int j = 0; j < n; j++) {
        	 	String author = inp.next();
        		order[i][nameId.get(author)] = j;
        		pubId[i][j] = nameId.get(author);
        	}
        }

        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {
        		int cmp = names[i].compareTo(names[j]);
        		boolean one = true, zero = true;
        		for (int p = 0; p < k; p++) {
        			if (order[p][i] < order[p][j] && cmp > 0) {
        				one = false;
        			}
        			else if (order[p][j] < order[p][i] && cmp < 0) {
        				zero = false;
        			}	
        		}
        		if (one && !zero) {
        			ans[i][j] = '1';
        			ans[j][i] = '0';
        		}
        		else if (zero && !one) {
        			ans[i][j] = '0';
        			ans[j][i] = '1';
        		}
        		//if (i == 1 && j == 2) System.out.println(zero + " " + one);
        	}
        }
        
        for (int p = 0; p < n; p++) {
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			if (ans[i][p] == '1' && ans[p][j] == '1') ans[i][j] = '1';
        			if (ans[i][p] == '0' && ans[p][j] == '0') ans[i][j] = '0';
        		}
        	}
        }

        for (int i = 0; i < n; i++) {
        	System.out.println(String.valueOf(ans[i]));
        }
        //out.close();
    }
}