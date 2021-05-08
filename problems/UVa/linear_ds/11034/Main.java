/*input
4
20 4
380 left
720 left
1340 right
1040 left
15 4
380 left
720 left
1340 right
1040 left
15 4
380 left
720 left
1340 left
1040 left
15 4
380 right
720 right
1340 right
1040 right
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
        Queue<Integer>[] queues = new Queue[2];
        queues[0] = new LinkedList<>(); queues[1] = new LinkedList<>();
        int numTests = inp.nextInt();
        for (int test = 0; test < numTests; test++) {
        	int maxLen = inp.nextInt() * 100, m = inp.nextInt();
        	for (int i = 0; i < m; i++) {
        		int len = inp.nextInt(), bank = inp.next().equals("left")? 0 : 1;
        		queues[bank].add(len);
        	}
        	int ans = 0, curBank = 0;
        	while (!queues[0].isEmpty() || !queues[1].isEmpty()) {
        		int curLen = 0;
        		while (!queues[curBank].isEmpty() && curLen + queues[curBank].peek() <= maxLen) 
        			curLen += queues[curBank].poll();
        	
        		curBank ^= 1;
        		ans++;
        	}
        	System.out.println(ans);
        }
    }
}