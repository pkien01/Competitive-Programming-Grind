/*input
4 2
ABBA
ABBB
BAAA
BABB
AB
BB
6 2
ABCDCD
BCDCBD
BACDDC
DCBDCA
DCBABD
ABCDBA
BC
CD
0 0
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
    void rotate(char[][] arr, int len) {
    	//Transpose
    	for (int i = 0; i < len; i++) {
    		for (int j = i + 1; j < len; j++) {
    			char tmp = arr[i][j]; arr[i][j] =arr[j][i]; arr[j][i] = tmp;
    		}
    	}

    	//Reverse cols
    	for (int j = 0; j < len / 2; j++) {
    		for (int i = 0; i < len; i++) {
    			char tmp = arr[i][j]; arr[i][j] =  arr[i][len - j - 1]; arr[i][len - j - 1] = tmp;
    		}
    	}
    }
    int solve(char[][] bigArr, char[][] smallArr, int N, int n) {
    	int res = 0;
    	for (int i = 0; i + n - 1 < N; i++) {
    		for (int j = 0; j + n - 1< N; j++) {
    			boolean checkCur = true;
    			for (int k = 0; k < n; k++) {
    				for (int l = 0; l < n; l++) {
    					if (smallArr[k][l] != bigArr[i + k][j + l]) {
    						checkCur = false; break;
    					}
    				}
    			}
    			res += checkCur? 1 : 0;
    		}
    	}
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextInt()) {
        	int N = inp.nextInt(), n = inp.nextInt(); inp.nextLine();
        	if (N == 0 && n ==0) break;	
        	char[][] bigArr = new char[N][N], smallArr = new char[n][n];
        	for (int i = 0; i < N; i++) bigArr[i] = inp.nextLine().toCharArray();
        	for (int i = 0; i < n; i++) smallArr[i] = inp.nextLine().toCharArray();

        	for (int i = 0; i < 4; i++) {
      			if (i > 0) obj.rotate(smallArr, n);
      			//for (int j = 0; j < N; j++) System.out.println(Arrays.toString(bigArr[j]));
      			//System.out.println();
      			System.out.print(obj.solve(bigArr, smallArr, N, n) + (i == 3? "\n": " "));
      			//System.out.println();
      		}
      		//for (int i = 0; i < N; i++) System.out.println(Arrays.toString(bigArr[i]));
        }
    }
}