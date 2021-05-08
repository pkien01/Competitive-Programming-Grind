/*input
3
3
1 2 3
1 2 3
3
2 3 2
3 3 2
2
1 2
1 2
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
 
    static int n;
    static ArrayList<Integer> a, b;
 
    static int solve(int idx, ArrayList<Integer> cur_a, ArrayList<Integer> cur_b) {
    	ArrayList<Integer> next_a = new ArrayList<>(), next_b = new ArrayList<>();
    	for (Integer num_a: cur_a) if ((num_a >> idx & 1) == 0) next_a.add(num_a);
    	for (Integer num_b: cur_b) if ((num_b >> idx & 1) == 1) next_b.add(num_b);
 		if (idx == 0) return next_a.size() == next_b.size()? 1 : 0;
    
    	if (next_a.size() != next_b.size()) {
    		return solve(idx - 1, cur_a, cur_b);
    	}
    	int res1 = next_a.size() == 0 && next_b.size() == 0? -1: solve(idx - 1, next_a, next_b);
    	//System.out.println(idx + "first: "  + next_a + " " + next_b);
    	next_a = new ArrayList<>(); next_b = new ArrayList<>();
    	for (Integer num_a: cur_a) if ((num_a >> idx & 1) == 1) next_a.add(num_a);
    	for (Integer num_b: cur_b) if ((num_b >> idx & 1) == 0) next_b.add(num_b);
    	//System.out.println(idx + "second: " + next_a + " " + next_b);
    	assert next_a.size() == next_b.size();
    	int res2 = next_a.size() == 0 && next_b.size() == 0? -1: solve(idx - 1, next_a, next_b);
    	if (res1 == -1) return res2 | (1 << idx);
    	if (res2 == -1) return res1 | (1 << idx);
    	return (res1 & res2) | (1 << idx);
    }	
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	n = inp.nextInt();
        	a = new ArrayList<>();
        	for (int i = 0; i < n; i++) a.add(inp.nextInt());
        	b = new ArrayList<>();
        	for (int i = 0; i < n; i++) b.add(inp.nextInt());
 
        	System.out.println(solve(30, a, b));
        }
        
        //out.close();
    }
}