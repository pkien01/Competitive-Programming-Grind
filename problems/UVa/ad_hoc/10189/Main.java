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
    char[][] solve(int n, int m, char[][] field) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] != '.') continue;
                int cnt = 0;
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if (di == 0 && dj == 0) continue;
                        if (i + di < 0 || i + di >= n || j + dj < 0 || j + dj >= m) continue;
                        if (field[i + di][j + dj] == '*') cnt++;
                    }
                }
                field[i][j] = (char)(cnt + '0');
            }
        }
        return field;
    }
    
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int fieldNum = 0;
        while (true) {
            int n = inp.nextInt(), m = inp.nextInt();
            if (n == 0 && m == 0) break;
            inp.nextLine();
            char[][] field = new char[n][m];
            for (int i = 0; i < n; i++) field[i] = inp.nextLine().toCharArray();
            char[][] ans = obj.solve(n, m, field);

            fieldNum++;
            if (fieldNum > 1) System.out.println();
            System.out.println("Field #" + fieldNum + ":");
            for (int i = 0; i < n; i++) System.out.println(String.valueOf(ans[i]));
        }
    }
}