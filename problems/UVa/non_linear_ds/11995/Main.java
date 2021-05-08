/*input
6
1 1
1 2
1 3
2 1
2 2
2 3
6
1 1
1 2
1 3
2 3
2 2
2 1
2
1 1
2 2
4
1 2
1 1
2 1
2 2
7
1 2
1 5
1 1
1 3
2 5
1 4
2 4
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
        	Stack<Integer> stack = new Stack<>();
        	Queue<Integer> queue = new LinkedList<>();
        	PriorityQueue<Integer> pq = new PriorityQueue<>(11, Collections.reverseOrder());
        	boolean isStack = true, isQueue = true, isPQ = true;
        	while (n-- > 0) {
        		int type = inp.nextInt(), x = inp.nextInt();
        		if (type == 1) {
        			if (isStack) stack.push(x); 
        			if (isQueue) queue.add(x); 
        			if (isPQ) pq.add(x);
        		}
        		else {
        			if (stack.empty() || x != stack.peek()) isStack = false;
        			else stack.pop();
        			if (queue.isEmpty() || x != queue.peek()) isQueue = false;
        			else queue.poll();
        			if (pq.isEmpty() || x != pq.peek()) isPQ = false;
        			else pq.poll();
        		}
        	}
        	int corrects = (isStack? 1 : 0) + (isQueue? 1: 0) + (isPQ? 1: 0);
        	if (corrects > 1) System.out.println("not sure");
        	else if (corrects == 0) System.out.println("impossible");
        	else {
        		if (isStack) System.out.println("stack");
        		else if (isQueue) System.out.println("queue");
        		else {
        			assert isPQ;
        			System.out.println("priority queue");
        		}
        	}
        }
    }
}