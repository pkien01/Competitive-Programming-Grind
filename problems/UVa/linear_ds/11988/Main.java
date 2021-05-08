/*input
This_is_a_[Beiju]_text
[[]][][]Happy_Birthday_to_Tsinghua_University
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
    String solve(String str) {
    	LinkedList<Character> list = new LinkedList<>();
    	int curIdx = 0;
    	for (char c: str.toCharArray()) {
    		if (c == '[') curIdx = 0;
    		else if (c == ']') curIdx = list.size();
    		else list.add(curIdx++, c);
     	}
     	StringBuilder res = new StringBuilder();
     	for (char c: list) res.append(c);
     	return res.toString();
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	String str = inp.nextLine();
        	System.out.println(obj.solve(str));
        }
    }
}