/*input
3 1 2 3
3 -1 -2 -3
3 1 2 4
4 2 4 6 10
3 -1 -2 -4
10 1 1 1 1 1 1 1 1 1 4
10 1 -1 1 -1 1 -1 -1 1 1 1
10 1 -1 1 -1 1 -1 -1 1 1 -3
0
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
    int gcd(int a, int b) {
    	return b == 0? a : gcd(b, a % b);
    }
    int[] solve(int n, int arr[]) {
    	int sum = 0;
    	for (int x: arr) sum += x;
    	int minus = 0;
 		if (sum < 0) {
 			minus = 2;
 			sum = -sum;
 		} 
    	int d = gcd(sum, n);
    	int b = sum / d, c = n /d;
    	int a = b / c;
    	b -= a * c;
    	return new int[]{minus, a, b, c};
    }
    int numDigits(int x) {
    	if (x == 0) return 0;
    	int res = 0;
    	while (x != 0) {
    		x /= 10; res++;
    	}
    	return res;
    }
    String repChar(char c, int times) {
    	char[] cArr = new char[times];
    	Arrays.fill(cArr, c);
    	return new String(cArr);
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int caseNum = 0;
        while (inp.hasNextInt()) {
        	int n = inp.nextInt();
        	if (n == 0) break;
        	int[] arr = new int[n];
        	for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        	int[] ans = obj.solve(n, arr);
        	caseNum++;
        	System.out.println("Case " + caseNum + ":");
        	if (ans[2] == 0) {
        		if (ans[0] != 0) System.out.print("- ");
        		System.out.println(ans[1]);
        	}
        	else {
        		int space = (ans[0] == 0? 0 : 2) + obj.numDigits(ans[1]) + obj.numDigits(ans[3]);
        		System.out.println(obj.repChar(' ', space - obj.numDigits(ans[2])) + ans[2]);

        		System.out.print(ans[0] != 0? "- " : "");
        		if (ans[1] != 0) System.out.print(ans[1]);
        		System.out.println(obj.repChar('-', obj.numDigits(ans[3])));

        		System.out.println(obj.repChar(' ', space - obj.numDigits(ans[3])) + ans[3]);
        	}
        }
    }
}