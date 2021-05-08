/*input
A
CBACBACBACBACBA
CCCCBBBBAAAA
ACMICPC
ECJE
RZPQYR
FEZBAD
end
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
    int solve2(String str) {
    	int curMax = -1, ans = 0;
    	int[] cnt = new int[26];
    	for (char c: str.toCharArray()) {
    		int cnum = (int)c - 'A';
    		boolean findTop = false;
    		for (int i = cnum; i < 26; i++) {
    			if (cnt[i] > 0) {
    				cnt[i]--;
    				findTop = true; break;
    			}
    		}
    		cnt[cnum]++;
    		if (!findTop) ans++;
    	}
    	return ans;
    }
    int solve(String str) {
    	ArrayList<Stack<Character>> stacks = new ArrayList<>();
    	for (char c: str.toCharArray()) {
    		int idx = -1;
    		for (int i = 0; i < stacks.size(); i++) {
    			char top = stacks.get(i).peek();
    			if (top >= c) {
    				idx = i; 
    				break;
    			}
    		}
    		if (idx != -1) stacks.get(idx).push(c);
    		else {
    			stacks.add(new Stack<>());
    			stacks.get(stacks.size() - 1).push(c);
    		}
    	}	 
    	for (Stack<Character> curStack: stacks) 
    		System.out.println(curStack.toString());
    	return stacks.size();
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int caseNum = 0;
        while (inp.hasNextLine()) {
        	String str = inp.nextLine();
        	if (str.equals("end")) break;
        	System.out.format("Case %d: %d\n", ++caseNum, obj.solve(str));
        }
    }
}