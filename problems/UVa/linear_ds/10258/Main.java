/*input
2

1 2 10 I
3 1 11 C
1 2 19 R
1 2 21 C
1 1 25 C

1 2 10 I
3 1 11 C
1 2 19 R
1 2 21 C
1 1 25 C
*/
import java.util.*;
import java.io.*;

class Contestant implements Comparable<Contestant> {
	int id, corrects, penalty;
	int[] tries;
	boolean[] solved;
	Contestant(int id, int corrects, int penalty) {
		this.id = id; this.corrects = corrects; this.penalty = penalty;
		tries = new int[9]; solved = new boolean[9];
	}
	Contestant(int id) {
		this(id, 0, 0);
	}
	void addSubmission(int problem, int time, char label) {
		problem--;
		if (solved[problem]) return;
		if (label == 'C') {
			solved[problem] = true;
			corrects++;
			penalty += tries[problem] * 20 + time;
		}
		else if (label == 'I') {
			tries[problem]++;
		}
	}
	void output() {
		System.out.format("%d %d %d\n", id + 1, corrects, penalty);
	}
	@Override 
	public int compareTo(Contestant other) {
		if (this.corrects != other.corrects) return other.corrects - this.corrects;
		if (this.penalty != other.penalty) return this.penalty - other.penalty;
		return this.id - other.id;
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
        int numTest = inp.nextInt(); inp.nextLine(); inp.nextLine();
        for (int test = 0; test < numTest && inp.hasNextLine(); test++) {
        	Contestant[] arr = new Contestant[100];
        	//Arrays.fill(arr, null);
        	while (inp.hasNextLine()) {
        		String s = inp.nextLine();
        		//System.out.println(s);
        		if (s == null || s.isEmpty()) break;
        		String[] s_split = s.split(" ");
        		int cur_id = Integer.parseInt(s_split[0]) - 1;
        		int cur_prob = Integer.parseInt(s_split[1]);
        		int cur_time = Integer.parseInt(s_split[2]);
        		char cur_label = s_split[3].charAt(0); 
        		if (arr[cur_id] == null) arr[cur_id] = new Contestant(cur_id);
        		arr[cur_id].addSubmission(cur_prob, cur_time, cur_label);
        	}
        	ArrayList<Contestant> ans = new ArrayList<>();
        	for (Contestant x: arr) if (x != null) ans.add(x);
        	Collections.sort(ans);

        	if (test != 0) System.out.println();
        	for (Contestant x: ans) x.output();
        }
    }
}