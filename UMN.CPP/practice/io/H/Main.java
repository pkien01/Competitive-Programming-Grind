/*input
5
9
0
7
11
24
*/
import java.util.*;
import java.io.*;

public class Main {
    static void runFromFile() {
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


    static int weight(int op) {
        return (op == 3 || op == 4)? 1 : 0;
    }
    static int calc(int x, int y, int op) {
        switch (op) {
            case 0: return x + y;
            case 1: return x - y;
            case 2: return x * y;
            case 3: return x / y;
        }
        return -1;
    }

    static char sign(int op) {
        switch (op) {
            case 0: return '+';
            case 1: return '-';
            case 2: return '*';
            case 3: return '/';
        }
        return ' ';
    }
    static void printAns(int[] ans, int n) {
        if (ans[0] == -1 && ans[1] == -1 && ans[2] == -1 && ans[3] == -1) 
            System.out.println("no solution");
        else System.out.println("4 " + sign(ans[0]) + " 4 " + sign(ans[1]) + " 4 " + sign(ans[2]) + " 4 = " + n);
    }

    static int[][] arr;
    static void precompute() {
        arr = new int[256 + 101][4];
        for (int i = 0; i < arr.length; i++) Arrays.fill(arr[i], -1);
        boolean sol = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    Stack<Integer> numStack = new Stack<>(), opStack = new Stack<>();
                    numStack.push(4);
                    opStack.push(i);
                    numStack.push(4);

                    if (weight(opStack.peek()) >= weight(j)) {
                        int top1 = numStack.pop();
                        int top2 = numStack.pop();
                        numStack.push(calc(top1, top2, opStack.pop()));
                    }
                    opStack.push(j);
                    numStack.push(4);

                    if (weight(opStack.peek()) >= weight(k)) {
                        int top1 = numStack.pop();
                        int top2 = numStack.pop();
                        numStack.push(calc(top1, top2, opStack.pop()));
                    }
                    opStack.push(k);
                    numStack.push(4);

                    while (!opStack.empty()) {
                        int top1 = numStack.pop();
                        int top2 = numStack.pop();
                        numStack.push(calc(top1, top2, opStack.pop()));
                    }

                    //System.out.println(numStack.peek());
                    
                    int cur = numStack.peek() + 100;
                    if (arr[cur][0] != -1 && arr[cur][1] != -1 && arr[cur][2] != -1) continue;
                    arr[cur] = new int[]{i, j, k};

                    printAns(arr[cur], cur - 100);
                    
                }
            }
        }
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        precompute();
       	int numTests = inp.nextInt();
       	while (numTests-- > 0) {
       		int n = inp.nextInt();
            if (n + 100 < 0 || n + 100 >= arr.length) System.out.println("no solution");
            else printAns(arr[n + 100], n);
       	}
        
        //out.close();
    }
}