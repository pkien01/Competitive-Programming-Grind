/*input
2
5 2 3
3 4 5 2
2 1 3
0
3 3 5 1
1 4
5 2 3
3 4 5 2
2 1 3
0
3 3 5 1
1 4
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
    int solve(int n, int maxStack, int maxQueue, int totalCargoes, Queue<Integer>[] queues) {
    	Stack<Integer> st = new Stack<>();
    	int ans = 0;
        while (totalCargoes > 0) {
        	for (int i = 0; i < n; i++) {
        		 while (!st.empty()) {
        			if (st.peek() == i) {
        				st.pop();
        				ans++;
        				totalCargoes--;
        			}
        			else if (queues[i].size() < maxQueue) {
        				queues[i].add(st.peek());
        				st.pop();
        				ans++;
        			}
        			else break;
	       		}
	       		while (!queues[i].isEmpty() && st.size() < maxStack) {
	       			st.push(queues[i].poll()); 
	       			ans++;
	       		}
	       		if (totalCargoes <= 0) break;
	       		ans += 2;
	       	}
        }
        return ans;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt(), maxStack = inp.nextInt(), maxQueue = inp.nextInt();
        	Queue<Integer>[] queues = new Queue[n];
        	int totalCargoes = 0;
        	for (int i = 0; i < n; i++) {
        		queues[i] = new LinkedList<>();
        		int curCargoes = inp.nextInt();
        		totalCargoes += curCargoes;
        		while (curCargoes-- > 0) queues[i].add(inp.nextInt() - 1);
        	} 
        	System.out.println(obj.solve(n, maxStack, maxQueue, totalCargoes, queues));
        }
    }
}