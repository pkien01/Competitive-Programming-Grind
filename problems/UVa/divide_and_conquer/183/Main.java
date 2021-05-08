/*input
B 3 4
001000011011
D 2 3
DD10111
#
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
    static int n, m;
    static String bitMap;
    static StringBuilder ans;
    static void solveB(int x1, int y1, int x2, int y2) {
        //System.out.format("x1=%d, y1=%d, x2=%d, y2=%d\n", x1, y1, x2, y2);
    	boolean allOnes = true, allZeros = true;
    	for (int i = x1; i <= x2; i++) {
    		for (int j = y1; j <= y2; j++) {
    			if (bitMap.charAt(i * m + j) == '1') allZeros = false; 
    			else allOnes = false; 
    		}
    	}

    	if (allOnes) { 
            ans.append('1');
        }
    	else if (allZeros) {
            ans.append('0');
        }
    	else {
    		ans.append('D');
    		int x_mid = (x1 + x2) / 2, y_mid = (y1 + y2) / 2;
    		if (x1 == x2) {
    			solveB(x_mid, y1, x_mid, y_mid);
    			solveB(x_mid, y_mid + 1, x_mid, y2);
    		}
    		else if (y1 == y2) {
    			solveB(x1, y_mid, x_mid, y_mid);
    			solveB(x_mid + 1, y_mid, x2, y_mid);
    		}
    		else {
    			solveB(x1, y1, x_mid, y_mid);
    			solveB(x1, y_mid + 1, x_mid, y2);
    			solveB(x_mid + 1, y1, x2, y_mid);
    			solveB(x_mid + 1, y_mid + 1, x2, y2);
    		}
    	} 
    }
    static int curIdx;
    static void solveD(int x1, int y1, int x2, int y2) {
    	if (curIdx == bitMap.length()) return;
    	char curChar = bitMap.charAt(curIdx);
    	//System.out.println(idx + ": " + curChar);
        curIdx++;
    	if (curChar != 'D') {
    		for (int i = x1; i <= x2; i++) 
    			for (int j = y1; j <= y2; j++) 
    				ans.setCharAt(i * m + j, curChar);
    	}
    	else {
    		int x_mid = (x1 + x2) / 2, y_mid = (y1 + y2) / 2;
    		if (x1 == x2) {
    			solveD(x_mid, y1, x_mid, y_mid);
    			solveD(x_mid, y_mid + 1, x_mid, y2);
    		}
    		else if (y1 == y2) {
    			solveD(x1, y_mid, x_mid, y_mid);
    			solveD(x_mid + 1, y_mid, x2, y_mid);
    		}
    		else {
    			solveD(x1, y1, x_mid, y_mid);
    			solveD(x1, y_mid + 1, x_mid, y2);
    			solveD(x_mid + 1, y1, x2, y_mid);
    			solveD(x_mid + 1, y_mid + 1, x2, y2);
    		}
    	}
    }
    static ArrayList<String> splitSpaces(String s) {
        s += " ";
        ArrayList<String> res = new ArrayList<>();
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (prev != -1) {
                    res.add(s.substring(prev, i));
                    prev = -1;
                }
            }
            else if (prev == -1) prev = i;
        }
        return res;
    }

    static void viewGrid() {
        System.out.println("n = " + n + ", m = " + m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) System.out.print(bitMap.charAt(i * m + j));
            System.out.println();
        }
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);

        String line = inp.nextLine();
        ArrayList<String> line_split = splitSpaces(line);
        while (inp.hasNextLine()) {
            ///String[] line_split = line.split(" ");
            //System.out.println(line_split.toString());
        	if (line.equals("#")) break;
        	String type = line_split.get(0);
        	n = Integer.parseInt(line_split.get(1));
        	m = Integer.parseInt(line_split.get(2));

            bitMap = "";
            while (inp.hasNextLine()) {
                line = inp.nextLine();
                line_split = splitSpaces(line);
                if (line_split.size() > 1 || line.equals("#")) break;
                bitMap += line;
            }
            //System.out.println(type + " " + bitMap);
        	if (type.equals("B")) {
        		ans = new StringBuilder();
                //viewGrid();
        		solveB(0, 0, n - 1, m - 1);
        	}
        	else {
        		ans = new StringBuilder();
        		for (int i = 0; i < n * m; i++) ans.append((char)0);
        		curIdx = 0;
        		solveD(0, 0, n - 1, m - 1);
        	}
            System.out.format("%s%4d%4d\n", type.equals("B")? "D" : "B", n, m);
            for (int i = 0; i < ans.length(); i++) {
                if (i > 0 && i < ans.length() - 1 && i % 50 == 0) System.out.println();
                System.out.print(ans.charAt(i)); 
            }
            System.out.println();
        }
        
        //out.close();
    }
}