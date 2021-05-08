/*input
2 0
10 20
20 30
2 0
10 30
20 21
1 1
1000 2000
0 10 1000
0 0
*/
import java.util.*;
import java.io.*;

class Interval implements Comparable<Interval> {
	int left, right;
	Interval(int left, int right) {
		this.left = left; this.right = right;
	}
	void display() {
		System.out.format("[%d, %d]\n", left, right);
	}
	@Override
	public int compareTo(Interval other) {
		if (this.left != other.left) return this.left - other.left;
		return this.right - other.right;
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
    boolean solve(ArrayList<Interval> tasks) {
    	Collections.sort(tasks);
    	//for (Interval x: tasks) x.display();
    	for (int i = 0; i < tasks.size() - 1; i++) {
    		// if exists lx in (l, r)
    		// task[i].left < task[i + 1].left < task[i].right
    		if (tasks.get(i + 1).left < tasks.get(i).right) return false;
    	}
    	return true;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt(), m = inp.nextInt();
        	if (n == 0 && m == 0) break;
        	ArrayList<Interval> tasks = new ArrayList<>();
        	for (int i = 0; i < n; i++) {
        		int l = inp.nextInt(), r = inp.nextInt();
        		tasks.add(new Interval(l, r));
        	}
        	for (int i = 0; i < m; i++) {
        		int l = inp.nextInt(), r = inp.nextInt(), rep = inp.nextInt(); 
        		tasks.add(new Interval(l, r));
        		while (r < 1000000) {
        			l += rep;
        			r += rep;
        			tasks.add(new Interval(l, r));
        		}
        		//tasks.add(new Interval(l, r));
        	}
        	System.out.println(obj.solve(tasks)? "NO CONFLICT" : "CONFLICT");
        }
    }
}