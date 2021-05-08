/*input
1
4
HONDA 10000 45000
PEUGEOT 12000 44000
BMW 30000 75900
CHEVROLET 7000 37000
4
60000
7500
5000
10000
*/
import java.util.*;
import java.io.*;

/*
class CarMaker {
	String name;
	int low, high;
	CarMaker(String name, int low, int high) {
		this.name = name;
		this.low = low; this.high = high;
	}
}*/
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
    static class CarMaker {
		String name;
		int low, high;
		CarMaker(String name, int low, int high) {
			this.name = name;
			this.low = low; this.high = high;
		}
	}
    static int n;
    static CarMaker[] data;
    static String solve(int price) {
    	String ans = new String();
    	for (CarMaker x: data) {
    		if (x.low <= price && price <= x.high) {
    			if (ans.isEmpty()) ans = x.name;
    			else return "UNDETERMINED";
    		}
    	}
    	return ans.isEmpty()? "UNDETERMINED" : ans;
    }
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTest = inp.nextInt();
        boolean firstTest = true;
        while (numTest-- > 0) {
        	n = inp.nextInt();
        	data = new CarMaker[n];
        	for (int i = 0; i < n; i++) data[i] = new CarMaker(inp.next(), inp.nextInt(), inp.nextInt());
        	int q = inp.nextInt();
        	if (firstTest) firstTest = false;
        	else System.out.println();
        	while (q-- > 0) {
        		int price = inp.nextInt();
        		System.out.println(solve(price));
        	}
        }
    }
}