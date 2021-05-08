/*input
1
3
4
60
70
50
2
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
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
        ArrayList<Integer> arr = new ArrayList<>();
        int n = 0;
        while (inp.hasNextInt()) {
        	arr.add(inp.nextInt());
        	n++;
        	Collections.sort(arr);
        	int ans = arr.get((n - 1)/ 2);
        	if (n % 2 == 0) ans = (ans + arr.get(n / 2)) / 2;
        	System.out.println(ans);
        }
    }
}