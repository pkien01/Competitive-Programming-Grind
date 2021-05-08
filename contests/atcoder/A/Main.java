/*input
-100 -100
100 100
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
        int a = inp.nextInt();
        int b = inp.nextInt();
        int c = inp.nextInt();
        int d = inp.nextInt();
        
        int res = a - d;
        res = Math.max(res, a - c);
        res = Math.max(res, b - c);
        res = Math.max(res, b - d);
        System.out.println(res);
        //out.close();
    }
}