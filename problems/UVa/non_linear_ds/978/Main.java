/*input
3
5 1 1
10
10
2 2 3
20
10
10
10
15
3 5 5
50
40
30
40
50
50
30
30
20
60
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
        int numTests = inp.nextInt();
        for (int test = 0; test < numTests; test++) {
        	int numBattles = inp.nextInt(), numGreens = inp.nextInt(), numBlues = inp.nextInt();
        	PriorityQueue<Integer> greens = new PriorityQueue<>(Collections.reverseOrder());
        	PriorityQueue<Integer> blues = new PriorityQueue<>(Collections.reverseOrder());
        	for (int i = 0; i < numGreens; i++) greens.add(inp.nextInt());
        	for (int i = 0; i < numBlues; i++) blues.add(inp.nextInt());
        	while (!greens.isEmpty() && !blues.isEmpty()) {
        		//System.out.println(Arrays.toString(greens.toArray()));
        		//NSystem.out.println(Arrays.toString(blues.toArray()));
        		ArrayList<Integer> greenWins = new ArrayList<>(), blueWins = new ArrayList<>();
        		for (int i = 0; i < numBattles && !greens.isEmpty() && !blues.isEmpty(); i++) {
        			int greenTop = greens.poll(), blueTop = blues.poll();
        			int maxTop = Math.min(greenTop, blueTop);
        			greenTop -= maxTop; blueTop -= maxTop;
        			if (greenTop > 0) greenWins.add(greenTop);
        			if (blueTop > 0) blueWins.add(blueTop);
        		}
        		for (int x: greenWins) greens.add(x);
        		for (int x: blueWins) blues.add(x);

        		//System.out.println(Arrays.toString(greens.toArray()));
        		//System.out.println(Arrays.toString(blues.toArray()));
        	}
        	if (test > 0) System.out.println();
        	if (greens.isEmpty() && blues.isEmpty()) {
        		System.out.println("green and blue died");
        	}
        	else if (greens.isEmpty()) {
        		System.out.println("blue wins");
        		while (!blues.isEmpty()) System.out.println(blues.poll());
        	}
        	else if (blues.isEmpty()) {
        		System.out.println("green wins");
        		while (!greens.isEmpty()) System.out.println(greens.poll());
        	}
        	else assert false;
        }
    }
}