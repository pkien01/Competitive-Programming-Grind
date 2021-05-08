/*input
3
1 2 3
4
1 2 3 4
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
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	if (n == 0) break;
        	PriorityQueue<Integer> pq = new PriorityQueue<>();
        	for (int i = 0; i < n; i++) pq.add(inp.nextInt());
        	int ans = 0;
        	while (pq.size() >= 2) {
        		int sum = pq.poll() + pq.poll();
        		ans += sum;
        		pq.add(sum);
        	}
        	System.out.println(ans);
        }
    }
}