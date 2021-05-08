/*input
madam
adamm
bahama
bahama
long
short
eric
rice
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
    String s, t;
    Stack<Character> stack;
    ArrayList<Character> curAns;
    Main() {
    	stack = new Stack<>();
    	curAns = new ArrayList<>();
    }
    void solve(int s_idx, int t_idx) {
    	if (t_idx == t.length()) {
    		//System.out.print(curAns);
    		for (int i = 0; i < curAns.size(); i++) 
    			System.out.print("" + curAns.get(i) + (i == curAns.size() - 1? '\n' : ' '));
 			return;
    	}
    	if (s_idx < s.length()) {
    		stack.push(s.charAt(s_idx));
    		curAns.add('i');
    		solve(s_idx + 1, t_idx);
    		stack.pop();
    		curAns.remove(curAns.size() - 1);
    	}
    	if (!stack.empty() && stack.peek() == t.charAt(t_idx)) {
    		stack.pop();
    		curAns.add('o');	
    		solve(s_idx, t_idx + 1);
    		stack.push(t.charAt(t_idx));
    		curAns.remove(curAns.size() - 1);
    	}
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	obj.s = inp.nextLine();
        	obj.t = inp.nextLine();
        	System.out.println("[");
        	if (obj.s.length() == obj.t.length()) obj.solve(0, 0);
        	System.out.println("]");
        }
    }
}