/*input
4 6
-2 6 0 -1
C 1 10
P 1 4
C 3 7
P 2 2
C 4 -5
P 1 4
5 9
1 5 -2 4 3
P 1 2
P 1 5
C 4 -5
P 1 5
P 4 5
C 3 0
P 1 5
C 4 -5
C 4 -5
*/
import java.util.*;
import java.io.*;

class FenwickTree {
	// all query and update input indexes lie in [0, n - 1] 
	int length;
	int[] tree;
	FenwickTree(int length) {
		this.length = length;
		tree = new int[length + 1];
	}
	void update(int index, int value) {
		index++;
		for (int i = index; i <= length; i += (i & -i)) tree[i] += value;
	}
	FenwickTree(int[] arr, int length) {
		this(length);
		for (int i = 0; i < length; i++) update(i, arr[i]);
	}
	int query(int toIndex) {
		int res = 0;
		toIndex++;
		if (toIndex <= 0) return 0;
		for (int i = toIndex; i > 0; i -= (i & -i))  res += tree[i];
		return res;
	}
	int query(int fromIndex, int toIndex) {
		return query(toIndex) - query(fromIndex - 1);
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
    	while (inp.hasNextInt()) {
    		int n = inp.nextInt(), k = inp.nextInt();
    		int[] arr = new int[n];
    		for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
    		int[] negsArr = new int[n], zerosArr = new int[n];
    		for (int i = 0; i < n; i++) {
    			if (arr[i] == 0) zerosArr[i] = 1;
    			else negsArr[i] = arr[i] < 0 ? 1: 0;
    		}

    		FenwickTree negsFT = new FenwickTree(negsArr, n), zerosFT = new FenwickTree(zerosArr, n);
    		for (int i = 0; i < k; i++) {
    			String type = inp.next();
    			if (type.equals("C")) {
    				int idx = inp.nextInt() - 1, val = inp.nextInt();
    				if (val == 0) {
    					if (zerosArr[idx] == 0) zerosFT.update(idx, 1);
    					zerosArr[idx] = 1;
    				}
    				else if (val > 0) {
    					if (negsArr[idx] == 1) negsFT.update(idx, -1);
    					if (zerosArr[idx] == 1) zerosFT.update(idx, -1);
    					negsArr[idx] = 0;
    					zerosArr[idx] = 0;
    				}
    				else {
    					if (negsArr[idx] == 0) negsFT.update(idx, 1);
    					if (zerosArr[idx] == 1) zerosFT.update(idx, -1);
    					negsArr[idx] = 1;
    					zerosArr[idx] = 0;
    				}
    			}
    			else {
    				int l = inp.nextInt() - 1, r = inp.nextInt() - 1;
    				char ans = negsFT.query(l, r) % 2 == 0? '+' : '-';
    				if (zerosFT.query(l, r) > 0) ans = '0';
    				//System.out.println(Arrays.toString(negsArr));
    				System.out.print(ans);
    			}
    		}
    		System.out.println();
    	}    
    }
}