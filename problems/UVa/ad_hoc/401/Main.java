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
    HashMap<Character, Character> mp;
    void initMap() {
        mp = new HashMap();
        mp.put('A', 'A');
        mp.put('E', '3');
        mp.put('H', 'H');
        mp.put('I', 'I');
        mp.put('J', 'L');
        mp.put('L', 'J');
        mp.put('M', 'M');
        mp.put('O', 'O');
        mp.put('S', '2');
        mp.put('T', 'T');
        mp.put('U', 'U');
        mp.put('V', 'V');
        mp.put('W', 'W');
        mp.put('X', 'X');
        mp.put('Y', 'Y');
        mp.put('Z', '5');
        mp.put('1', '1');
        mp.put('2', 'S');
        mp.put('3', 'E');
        mp.put('5', 'Z');
        mp.put('8', '8');
    }
    String solve(String str) {
        boolean palin = true, mirror = true;
        for (int i = 0; i < str.length() / 2; i++) {
            char left = str.charAt(i), right = str.charAt(str.length() - 1 - i);
            if (palin && left != right) palin = false;
            if (mirror && (mp.get(left) == null || mp.get(left) != right)) mirror = false;
        }
        if (mirror && str.length() % 2 == 1) {
            char midChar = str.charAt(str.length() / 2);
            if (mp.get(midChar) == null || mp.get(midChar) != midChar) mirror = false;
        }
        
        if (!palin && !mirror) return str + " -- is not a palindrome.";
        if (palin && !mirror) return str + " -- is a regular palindrome.";
        if (!palin && mirror) return str + " -- is a mirrored string.";
        if (palin && mirror) return str + " -- is a mirrored palindrome.";
        return "";
    }
    
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();

        obj.initMap();
        Scanner inp = new Scanner(System.in);
        //boolean firstLoop = true;
        while (inp.hasNextLine()) {
            String str = inp.nextLine();
            //if (firstLoop) firstLoop = false;
            //else System.out.println();

            System.out.println(obj.solve(str));
            System.out.println();
        }
    }
}