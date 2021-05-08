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
    HashMap<Character, Integer> countChars(String s) {
        HashMap<Character, Integer> res = new HashMap<>();
        for (char c: s.toCharArray()) {
            if (c == ' ') continue;
            Integer cur = res.get(c);
            res.put(c, (cur == null? 0 : cur) + 1);
        }
        return res;
    }
    List<String> solve(List<String> phrases) {
        Collections.sort(phrases);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < phrases.size() - 1; i++) {
            HashMap<Character, Integer> curCnt = countChars(phrases.get(i));
            for (int j = i + 1; j < phrases.size(); j++) {
                if (curCnt.equals(countChars(phrases.get(j)))) {
                    res.add(phrases.get(i) + " = " + phrases.get(j));
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int numTests = inp.nextInt(); 
        inp.nextLine(); inp.nextLine();
        boolean firstLoop = true;
        while (numTests-- > 0 && inp.hasNextLine()) {
            List<String> phrases = new ArrayList<>();
            while (inp.hasNextLine()) {
                String s = inp.nextLine();
                if (s == null || s.isEmpty()) break;
                phrases.add(s);
            } 
            //System.out.println(phrases.toString());
            List<String> ans = obj.solve(phrases);
            if (firstLoop) firstLoop = false;
            else System.out.println();
            for (String curAns: ans) System.out.println(curAns);
            
        }
    }
}