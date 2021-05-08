/*input
5
7 3
.**.***
5 1
..*..
5 2
*.*.*
3 2
*.*
1 1
*
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
        int numTest = inp.nextInt();
        while (numTest-- > 0) {
        	int n = inp.nextInt(), k = inp.nextInt();
        	char[] s = inp.next().toCharArray();
        	for (int i = 0; i < n; i++) {
        		if (s[i] == '*') {
        			s[i] = 'x';
        			break;
        		}
        	}
        	for (int i = n - 1; i >= 0; i--) {
        		if (s[i] == '*') {
        			s[i] = 'x';
        			break;
        		}
        	}
        	int ans = 0;
        	for (int i = 0; i < n; i++) {
        		if (s[i] == '*') {
        			int dist = i;
        			for (int j = i - 1; j >= 0; j--) {
        				if (s[j] == 'x') {
        					dist = i - j;
        					break;
        				}
        			}
        			if (dist > k) {
        				ans++;
        				for (int j = i - 1; j >= i - k; j--) {
        					if (s[j] == '*') {
        						dist = i - j;
        						break;
        					}
        				}

        			}
        		}
        	}
        	System.out.println(ans);
        }
        
        //out.close();
    }
}