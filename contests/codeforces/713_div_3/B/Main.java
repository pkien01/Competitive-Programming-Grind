/*input
6
4
..*.
....
*...
....
2
*.
.*
2
.*
.*
3
*.*
...
...
5
.....
..*..
.....
.*...
.....
4
....
....
*...
*...
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

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	int n = inp.nextInt();
        	char[][] arr = new char[n][n];
        	for (int i = 0; i < n; i++) arr[i] = inp.next().toCharArray();
        	int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			if (arr[i][j] == '*') {
        				if (x1 == -1 && y1 == -1) {
        					x1 = i; y1 = j;
        				}
        				else {
        					x2 = i; y2 = j;
        				}
        			}
        		}
        	}
        	//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
        	if (x1 == x2) {
        		if (x1 > 0) {
        			arr[x1 - 1][y1] = arr[x1 - 1][y2] = '*';
        		}
        		else if (x1 + 1 < n) {
        			arr[x1 + 1][y1] = arr[x1 + 1][y2] = '*';
        		}
        	}
        	else if (y1 == y2) {
        		if (y1 > 0) {
        			arr[x1][y1 - 1] = arr[x2][y1 - 1] = '*';
        		}
        		else if (y1 + 1 < n) {
        			arr[x1][y1 + 1] = arr[x2][y2 + 1] = '*';
        		}
        	}
        	else {
        		arr[x1][y2] = arr[x2][y1] = '*';
        	}

        	for (int i = 0; i < n; i++) {
        		System.out.println(String.valueOf(arr[i]));
        	}
        }
        
        //out.close();
    }
}