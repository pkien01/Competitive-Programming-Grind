/*input

*/
import java.util.*;
import java.io.*;

public class Solution {
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
   	
   	static Scanner inp = new Scanner(System.in);
   	static int n;
    static ArrayList<Integer> ans;

   	static int searchPos(int num, int l, int r) {
        if (l == r) return l + 1;
        System.out.println(ans.get(l) + " " + ans.get(r) + " " + num);
        int res = inp.nextInt();
        if (res == ans.get(l)) return l;
        if (res == ans.get(r)) return r + 1;
        int mid = (l + r) >> 1;
        if (l == mid) return l + 1;
        int posLeft = searchPos(num, l, mid);
        if (posLeft <= mid) return posLeft;
        if (mid + 1 == r) return mid + 1;
        return searchPos(num, mid + 1, r);
   	}
    static void solve() {
    	ans = new ArrayList<>();
        ans.add(1); ans.add(2);
    	for (int i = 3; i <= n; i++) {
    		int pos = searchPos(i, 0, ans.size() - 1);
    		ans.add(pos, i);
           // System.out.println("pos = " + pos + ", ans = " + ans.toString());
    	}
    }
    public static void main(String[] args) {      
        //runFromFile();
        
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        n = inp.nextInt(); 
        int q = inp.nextInt();
        for (int test = 1; test <= numTests; test++) {
        	solve();
        	for (int i = 0; i < n; i++) System.out.print(ans.get(i) + (i == n - 1? "\n": " "));
            int judge = inp.nextInt();
            if (judge == -1) break;
        }	
        
        //out.close();
    }
}