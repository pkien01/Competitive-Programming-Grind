/*input
3
100 101 102 103 488
100 200 300 101 102
103 102 101 488 100
3
200 202 204 206 208
123 234 345 456 321
100 200 300 400 444
0
*/
import java.util.*;
import java.io.*;

class Frosh implements Comparable<Frosh> {
    int[] courses;
    Frosh(int[] courses) {
        this.courses = new int[5];
        assert courses.length == 5;
        for (int i = 0; i < 5; i++) this.courses[i] = courses[i];
    }
    @Override
    public int compareTo(Frosh other) {
        for (int i = 0; i < 5; i++)
            if (this.courses[i] != other.courses[i]) return this.courses[i] - other.courses[i];
        return 0;
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
    int solve(int n, int[][] arr) {
    	Map<Frosh, Integer> mp = new TreeMap<>();
    	for (int i = 0; i < n; i++) {
            Arrays.sort(arr[i]);
            Frosh curFrosh = new Frosh(arr[i]);
    		if (!mp.containsKey(curFrosh)) mp.put(curFrosh, 0);
    		mp.put(curFrosh, mp.get(curFrosh) + 1);
    	}
    	int maxCnt = 0;
        for (int i = 0; i < n; i++) maxCnt = Math.max(maxCnt, mp.get(new Frosh(arr[i])));

    	int res = 0;
    	for (int i = 0; i < n; i++) res += mp.get(new Frosh(arr[i])) == maxCnt? 1 : 0;
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
            if (n == 0) break;
            int[][] arr = new int[n][5];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < 5; j++) arr[i][j] = inp.nextInt();
            System.out.println(obj.solve(n, arr));
        }
    }
}