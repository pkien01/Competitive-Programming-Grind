/*input
Register 2004 200
Register 2005 300
#
5
*/
import java.util.*;
import java.io.*;

class Pair<X extends Comparable<X>, Y extends Comparable<Y>> implements Comparable<Pair<X, Y>> {
	X first; Y second;
	Pair(X first, Y second) {
		this.first = first; this.second = second;
	}
	X getKey() {return this.first;}
	Y getValue() {return this.second;}
	void setKey(X first) {this.first = first;}
	void setValue(Y second){this.second = second;}

	@Override
	public int compareTo(Pair<X, Y> other) {
		if (this.getKey().compareTo(other.getKey()) != 0) return this.getKey().compareTo(other.getKey());
		return this.getValue().compareTo(other.second);
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
        PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> pq = new PriorityQueue<>();
        int k = 0;
        while (inp.hasNext()) {
        	String s = inp.next();
        	if (s.equals("#")) {
        		k = inp.nextInt();
        		break;
        	}
        	else {
        		int q_num = inp.nextInt(), period = inp.nextInt();
        		pq.add(new Pair<>(period, new Pair<>(q_num, period)));
        	}
        }
       	while (k-- > 0) {
        	Pair<Integer, Pair<Integer, Integer>> cur = pq.poll();
        	System.out.println(cur.second.first);
        	pq.add(new Pair<>(cur.first + cur.second.second, cur.second)); 
        }
    }
}
