/*input
abaacb
cbbaa
#
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
    String solve(String str) {
    	int[] arr = new int[str.length()];
    	for (int i = 0; i < str.length(); i++) arr[i] = (int)str.charAt(i) - 'a';
    	//System.out.println(Arrays.toString(arr));
    	boolean successor = false;
    	for (int i = arr.length - 2; i >= 0; i--) {
    		if (arr[i] < arr[i + 1]) {
    			//find smallest a[j] (i < j < arr.length) such that a[j] > a[i]
    			int upperBound = -1;
    			for (int j = arr.length - 1; j > i; j--) {
    				if (arr[j] > arr[i]) {
    					upperBound = j;
    					break;
    				} 
    			}
    			int tmp = arr[i]; arr[i] = arr[upperBound]; arr[upperBound] = tmp;
    			for (int l = i + 1, r = arr.length - 1; l < r; l++, r--) {
    				tmp = arr[l]; arr[l] = arr[r]; arr[r] = tmp;
    			}
    			successor = true;
    			break;
    		}
    	}
    	if (!successor) return "No Successor";
    	StringBuilder res = new StringBuilder();
    	for (int i = 0; i < arr.length; i++) res.append((char)(arr[i] + 'a'));
    	return res.toString();
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        while (inp.hasNextLine()) {
        	String str = inp.nextLine();
        	if (str.equals("#")) break;
        	System.out.println(obj.solve(str));
        }
    }
}