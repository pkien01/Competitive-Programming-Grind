/*input
3
2 10 10
0 left
10 left
20 left
30 left
40 left
50 left
60 left
70 left
80 left
90 left
2 10 3
10 right
25 left
40 left
1 5 2
0 left
0 left
*/
import java.util.*;
import java.io.*;

class Car {
    int id, arrival;
    Car(int id, int arrival) {
    	this.id = id; this.arrival =arrival;
    }
}
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
        	int n = inp.nextInt(), t = inp.nextInt(), m = inp.nextInt();
      
        	Queue<Car>[] queues = new Queue[2];
        	queues[0] = new LinkedList<>(); queues[1] = new LinkedList<>();
        	for (int i = 0; i < m; i++) {
        		int arrival = inp.nextInt(), bank = inp.next().equals("left")? 0 : 1;
        		queues[bank].add(new Car(i, arrival));
        	}    

        	int curTime = 0, curBank = 0;  
        	int[] ans = new int[m];
        	while (!queues[0].isEmpty() || !queues[1].isEmpty()) {
        		int cnt = 0;
        		while (!queues[curBank].isEmpty()  && cnt < n) {
        			Car curCar = queues[curBank].peek();
        			if (curCar.arrival > curTime) break;
        			ans[curCar.id] = curTime + t;
        			queues[curBank].poll();
        			cnt++;
        		}
        		if (cnt == 0) {
        			if (!queues[curBank].isEmpty() && (queues[curBank ^1].isEmpty() || queues[curBank].peek().arrival < queues[curBank ^ 1].peek().arrival)) 
        				curTime = queues[curBank].peek().arrival;
        			else if (!queues[curBank ^ 1].isEmpty()) {
        				curTime = Math.max(queues[curBank^1].peek().arrival, curTime) + t;
        				curBank ^= 1;
        			}
        		}

        		else {
        			curTime += t;
        			curBank ^= 1;
        		}
        	}  	
        	if (test > 0) System.out.println();
        	for (int i = 0; i < m; i++) System.out.println(ans[i]);
        }
    }
}