import java.util.*;
import java.io.*;
class Anagram {
    String str;
    int[] cnt;
    Anagram(String str, int idx) {
        this.str = str;
        this.cnt = new int[26];
        for (char c: str.toCharArray()) 
            if (Character.isLowerCase(c)) cnt[c - 'a']++;
    }
    int compareTo(Anagram other) {
        for (int i = 0; i < 26; i++)
            if (this.cnt[i] != other.cnt[i]) return this.cnt[i] - other.cnt[i];
        return this.str.compareTo(other.str);
    }
    boolean equals(Anagram other) {
        for (int i = 0; i < 26; i++)
            if (this.cnt[i] != other.cnt[i]) return false;
        return true;
    }
}
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
    List<String> solve(List<String> phrases) {
        Anagram[] arr = new Anagram[phrases.size()];
        for (int i = 0; i < phrases.size(); i++) arr[i] = new Anagram(phrases.get(i), i);
        Comparator<Anagram> cmp = new Comparator<Anagram>(){
            @Override
            public int compare(Anagram an1, Anagram an2) {
                return an1.compareTo(an2);
            }
        };
        Arrays.sort(arr, cmp);
        int lastPos = 0;
        List<String> res = new ArrayList<>();
        for (int pos = 0; pos < arr.length; pos++) {
            if (pos > 0 && !arr[pos].equals(arr[pos - 1])) {
                for (int i = lastPos; i < pos - 1; i++) 
                    for (int j = i + 1; j < pos; j++) 
                        res.add(arr[i].str + " = " + arr[j].str);
                //System.out.println(lastPos + ", " + pos);
                lastPos = pos;
            }
        }
        for (int i = lastPos; i < arr.length - 1; i++) 
            for (int j = i + 1; j < arr.length; j++) 
                res.add(arr[i].str + " = " + arr[j].str);
        //for (Anagram x: arr) System.out.println(x.str + " " + Arrays.toString(x.cnt));
        return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int numTests = inp.nextInt();
        if (inp.hasNextLine()) inp.nextLine();
        while (numTests-- > 0 && inp.hasNextLine()) {
            inp.nextLine();
            List<String> phrases = new ArrayList<>();
            while (inp.hasNextLine()) {
                String s = inp.nextLine();
                if (s.isEmpty() || s == null) break;
                phrases.add(s);
            } 
            
            List<String> ans = obj.solve(phrases);
            for (String s: ans) System.out.println(s);
            System.out.println();
            
        }
    }
}